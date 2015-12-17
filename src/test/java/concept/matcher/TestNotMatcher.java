/**
  @author  Thomas Lehmann
  @file    TestNotMatcher.java
  @brief   missing description

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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import concept.tools.Serializer;

/**
 * Testing of class {@link concept.matcher.NotMatcher}.
 */
public class TestNotMatcher {
    /**
     * Testing of class {@link concept.matcher.NotMatcher} with
     * {@link java.lang.Integer}.
     */
    @Test
    public void testInteger() {
        final NotMatcher matcher = new NotMatcher(new IsMatcher<Integer>(1234));
        assertThat(matcher.getName(), equalTo("NotMatcher"));
        assertThat(matcher.match(1234), equalTo(false));
        assertThat(matcher.match(4321), equalTo(true));
    }

    /**
     * Testing of class {@link concept.matcher.NotMatcher} with
     * {@link java.lang.Integer} and JSON serialization.
     */
    @Test
    public void testIntegerWithJson() {
        final NotMatcher matcher = new NotMatcher(new IsMatcher<Integer>(1234));
        final StringBuilder json = new StringBuilder();
        json.append("{\"value\":{\"IsMatcher\":");
        json.append("{\"value\":\"1234\",\"type\":\"java.lang.Integer\"}}}");
        assertThat(Serializer.withGsonToJson(matcher), equalTo(json.toString()));
    }
}
