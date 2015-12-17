/**
  @author  Thomas Lehmann
  @file    Matcher.java
  @brief   Provides interface for the capability of matching a value.

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
package concept.interfaces;

/**
 * Provides interface for the capability of matching a value.
 */
public interface Matcher {

    /**
     * The derived class has to implement the way to do the matching.
     *
     * @param object
     *            value to check in a defined way against the internal value.
     * @return true when given value does match value stored in the matcher.
     */
    boolean match(final Object object);
}