/**
  @author  Thomas Lehmann
  @file    IsMatcher.java
  @brief   Matcher for testing two values to be equal.

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
package concept.matcher;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Matcher for testing two values to be equal.
 *
 * @param <T>
 */
public class IsMatcher<T> extends GenericMatcher<T> {

    /**
     * Initializes matcher with the value to be matched.
     * 
     * @param value
     *            value to be matched (equal).
     */
    public IsMatcher(T value) {
        super(value);
    }

    @Override
    public boolean match(final Object object) {
        return this.value.equals(object);
    }

    @Override
    public String getName() {
        return "IsMatcher";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IsMatcher)) {
            return false;
        }

        @SuppressWarnings("rawtypes")
        final IsMatcher other = (IsMatcher) obj;

        final EqualsBuilder builder = new EqualsBuilder();
        builder.append(this.getName(), other.getName());
        builder.append(this.getValue(), other.getValue());
        return builder.isEquals();
    }

    @Override
    public int hashCode() {
        final HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(this.getName());
        builder.append(this.getValue());
        return builder.hashCode();
    }

}
