/**
  @author  Thomas Lehmann
  @file    TestFact.java
  @brief   Testing of class {@link concept.model.Fact}.

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

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import concept.matcher.IsMatcher;

/**
 * Testing of class {@link concept.model.Fact}.
 */
public class TestFact {
    /**
     * Testing of {@link concept.model.Fact} with a
     * {@link concept.matcher.IsMatcher} with the type {@link java.lang.Integer}
     * and matching.
     */
    @Test
    public void testIntegerAndIsMatcher() {
        final Fact<Integer> fact = new Fact<>("John", "age of John", new IsMatcher<>(46));
        assertThat(fact.getName(), equalTo("John"));
        assertThat(fact.getDescription(), equalTo("age of John"));
        assertThat(fact.match(46), equalTo(true));
        assertThat(fact.match(64), equalTo(false));
    }

    /**
     * Testing of {@link concept.model.Fact} with a
     * {@link concept.matcher.IsMatcher} and the type {@link java.lang.Integer}
     * and JSON serialization.
     */
    @Test
    public void testIntegerAndIsMatcherWithJson() {
        final Fact<Integer> fact = new Fact<>("John", "age of John", new IsMatcher<>(46));
        final StringBuilder json = new StringBuilder();
        json.append("{\"matcher\":{\"IsMatcher\":{\"value\":\"46\",\"type\":\"java.lang.Integer\"}},");
        json.append("\"name\":\"John\",");
        json.append("\"description\":\"age of John\"}");
        assertThat(fact.toJson(), equalTo(json.toString()));
    }
}
