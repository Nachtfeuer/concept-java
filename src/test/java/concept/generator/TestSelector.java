/**
  @author  Thomas Lehmann
  @file    TestSelector.java
  @brief   Testing of class {@link Selector}.

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

import java.util.List;

import org.junit.Test;

import concept.interfaces.Filter;
import concept.interfaces.Transformer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Testing of class {@link Selector}.
 */
public class TestSelector {
    /**
     * Testing simple range with step 1 (for Integer).
     */
    @Test
    public void testSimpleRangeForInteger() {
        final List<Integer> values = new Selector<Integer>(1, 10, 1).build();
        for (int value = 1; value <= 10; ++value) {
            assertThat(values.get(value - 1), equalTo(value));
        }
    }

    /**
     * Testing simple range with step 1 (for Long).
     */
    @Test
    public void testSimpleRangeForLong() {
        final List<Long> values = new Selector<Long>(1L, 10L, 1L).build();
        for (int value = 1; value <= 10; ++value) {
            assertThat(values.get(value - 1), equalTo(Long.valueOf(value)));
        }
    }

    /**
     * Testing simple range with step 1 (for Double).
     */
    @Test
    public void testSimpleRangeForDouble() {
        final List<Double> values = new Selector<Double>(1d, 10d, 1d).build();
        for (int value = 1; value <= 10; ++value) {
            assertThat(values.get(value - 1), equalTo(Double.valueOf(value)));
        }
    }

    /**
     * Testing range user base class (@link Number} only.
     */
    @Test
    public void testSimpleRangeUsingBaseType() {
        final List<Number> values = new Selector<Number>(1, 10, 1).build();
        for (int value = 1; value <= 10; ++value) {
            assertThat(values.get(value - 1), equalTo((Number)Integer.valueOf(value)));
        }
    }

    /**
     * Testing range with step other than 1.
     */
    @Test
    public void testOddRangeByStepForInteger() {
        final List<Integer> values = new Selector<Integer>(1, 10, 2).build();
        for (int value = 1; value <= 10; value += 2) {
            assertThat(values.get(value / 2), equalTo(value));
        }
    }

    /**
     * Testing range with a custom filter (step 1).
     */
    @Test
    public void testOddRangeByFilterForInteger() {
        final List<Integer> values = new Selector<Integer>(1, 10, 1).where(new Filter<Integer>() {
            @Override
            public boolean isValid(Integer value) {
                return value % 2 != 0;
            }
        }).build();

        for (int value = 1; value <= 10; value += 2) {
            assertThat(values.get(value / 2), equalTo(value));
        }
    }

    /**
     * Testing of {@link Selector#transform(Transformer)}.
     */
    @Test
    public void testWithOneTransformerAndNoFilter() {
        final List<Integer> values = new Selector<Integer>(1, 10, 1).transform(new Transformer<Integer>() {
            @Override
            public Integer transform(Integer value) {
                return Integer.valueOf(value.intValue() * 2);
            }
        }).build();

        for (int value = 1; value <= 10; ++value) {
            assertThat(values.get(value-1), equalTo(value*2));
        }
    }
}
