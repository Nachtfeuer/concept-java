/**
  @author  Thomas Lehmann
  @file    Variable.java
  @brief   Implementation of a variable.

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
package concept.variables;

import java.util.Observable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Implementation of a variable.
 *
 * @param <T>
 */
public class Variable<T> extends Observable {
    /**
     * Name of the variable.
     */
    private final String name;

    /**
     * Value of the variable
     */
    private T value;

    /**
     * Initializes the variable.
     * 
     * @param name
     *            name of the variable.
     * @param value
     *            initial value of the variable.
     */
    public Variable(final String name, final T value) {
        this.name = name;
        this.setValue(value);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the value
     */
    public T getValue() {
        return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(T value) {
        this.value = value;
        setChanged();
        notifyObservers();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.getName()).append(this.value).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        @SuppressWarnings("rawtypes")
        final Variable other = (Variable) obj;

        return new EqualsBuilder().append(this.name, other.getName()).append(this.value, other.getValue()).isEquals();
    }
}
