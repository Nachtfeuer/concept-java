/**
  @author  Thomas Lehmann
  @file    Selector.java
  @brief   Tool that builds a list of numbers for given type allow stepping a range and using filters.

  Copyright (c) 2015 Thomas Lehmann

  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
  documentation files (the "Software"), to deal in the Software without restriction, including without limitation
  the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
  and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

  The above copyright notice and this permission notice shall be included in all copies
  or substantial portions of the Software.

  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
  INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
  IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
  DAMAGES OR OTHER LIABILITY,
  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package concept.generator;

import java.util.ArrayList;
import java.util.List;

import concept.interfaces.Filter;
import concept.interfaces.Transformer;

/**
 * Tool that builds a list of numbers for given type allow stepping a range and
 * using filters.
 *
 * @param <T>
 *            has to be a number.
 */
public class Selector<T extends Number> {
    /**
     * Beginning of the range.
     */
    private final T fromValue;

    /**
     * End of the ranged.
     */
    private final T toValue;

    /**
     * Step to iterate from the beginning of the range to the end.
     */
    private final T stepValue;

    /**
     * List of registered filters with {@link Selector#where(Filter)}.
     */
    private final List<Filter<T>> filters;

    /**
     * List of registered transformer with
     * {@link Selector#transform(Transformer)}.
     */
    private final List<Transformer<T>> transformers;

    /**
     * Initializes the range setup.
     *
     * @param fromValue
     *            minimum value
     * @param toValue
     *            maximum value
     * @param stepValue
     *            step value
     */
    public Selector(final T fromValue, final T toValue, final T stepValue) {
        this.fromValue = fromValue;
        this.toValue = toValue;
        this.stepValue = stepValue;
        this.filters = new ArrayList<>();
        this.transformers = new ArrayList<>();
    }

    /**
     * Adds a filter that will be used in {@link Selector#build()}.
     *
     * @param filter
     *            a filter that can be used to reduce the elements in final
     *            list.
     * @return self to allow further operation on the current selection.
     */
    public Selector<T> where(final Filter<T> filter) {
        this.filters.add(filter);
        return this;
    }

    /**
     * Add a transformer to change a given value into another of same type.
     *
     * @param transformer
     *            new transformer to be registered
     * @return self to allow further operation on the current selection.
     */
    public Selector<T> transform(final Transformer<T> transformer) {
        this.transformers.add(transformer);
        return this;
    }

    /**
     * Does iterate the range by given range setup filtering the values (when
     * given).
     *
     * @return list of filter values.
     */
    public List<T> build() {
        final List<T> results = new ArrayList<T>();

        for (T value = fromValue; value.doubleValue() <= toValue.doubleValue(); value = step(value)) {
            if (isValid(value)) {
                results.add(transformValue(value));
            }
        }

        return results;
    }

    /**
     * Transforms a value by all registered transformers in order of
     * registration.
     *
     * @param value
     *            value to be transformed-
     * @return transformed value
     */
    private T transformValue(T value) {
        T transformedValue = value;
        for (final Transformer<T> transformer : transformers) {
            transformedValue = transformer.transform(transformedValue);
        }
        return transformedValue;
    }

    /**
     * Checks a value to be accepted by all registered filters (and operator).
     *
     * @param value
     *            value to be checked to be accepted by all filters
     * @return true when accepted by all registered filters.
     */
    private boolean isValid(final T value) {
        for (final Filter<T> filter : filters) {
            if (!filter.isValid(value)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Provided value incremented by configured step.
     *
     * @param value
     *            the value that should be incremented by configured step value.
     * @return incremented value or null when type is not supported.
     */
    @SuppressWarnings("unchecked")
    private T step(final T value) {
        if (value instanceof Integer) {
            return (T) Integer.valueOf(((Integer) value).intValue() + ((Integer) stepValue).intValue());
        }

        if (value instanceof Long) {
            return (T) Long.valueOf(((Long) value).longValue() + ((Long) stepValue).longValue());
        }

        if (value instanceof Double) {
            return (T) Double.valueOf(((Double) value).doubleValue() + ((Double) stepValue).doubleValue());
        }
        return null;
    }
}
