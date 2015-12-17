/**
  @author  Thomas Lehmann
  @file    Fact.java
  @brief   A fact is a name, a description and a matcher with a value to be matched.

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
package concept.model;

import concept.interfaces.Matcher;
import concept.matcher.AbstractMatcher;

/**
 * A fact is a name, a description and a matcher with a value to be matched.
 *
 * @param <T>
 */
public class Fact<T> extends AbstractEntity implements Matcher {

    private final AbstractMatcher matcher;

    /**
     * @param name
     *            name of the fact
     * @param description
     *            description of the fact
     * @param matcher
     *            matcher for the fact
     */
    public Fact(final String name, final String description, final AbstractMatcher matcher) {
        super(name, description);
        this.matcher = matcher;
    }

    /**
     * @param object
     * @return
     */
    @Override
    public boolean match(final Object object) {
        return this.matcher.match(object);
    }
}
