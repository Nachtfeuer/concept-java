/**
  @author  Thomas Lehmann
  @file    TestIsMatcher.java
  @brief   Testing class {@link concept.model.IsMatcher}.

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

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Testing class {@link concept.model.IsMatcher}.
 */
public class TestIsMatcher {
    /**
     * Testing {@link concept.model.IsMatcher} with {@link java.lang.Integer}.
     */
    @Test
    public void testInteger() {
        final IsMatcher<Integer> matcher = new IsMatcher<Integer>(1234);
        assertThat(matcher.match(1234), equalTo(true));
        assertThat(matcher.match(4321), equalTo(false));
    }

    /**
     * Testing {@link concept.model.IsMatcher} with {@link java.lang.String}.
     */
    @Test
    public void testString() {
        final IsMatcher<String> matcher = new IsMatcher<String>("abcdef");
        assertThat(matcher.match("abcdef"), equalTo(true));
        assertThat(matcher.match("uvwxyz"), equalTo(false));
    }
}
