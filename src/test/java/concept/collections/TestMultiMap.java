/**
  @author  Thomas Lehmann
  @file    TestMultiMap.java
  @brief   Testing of class {@link concept.collections.MultiMap].

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
package concept.collections;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.hasItem;

import java.util.HashSet;
import java.util.Set;

/**
 * Testing of class {@link concept.collections.MultiMap].
 */
public class TestMultiMap {
    /**
     * Testing construction of container with no data.
     */
    @Test
    public void testNoData() {
        final MultiMap<String, String> container = new MultiMap<>();
        assertThat(container.isEmpty(), equalTo(true));
        assertThat(container.size(), equalTo(0));
        assertThat(container.containsKey("foo"), equalTo(false));
        assertThat(container.get("foo"), equalTo((Set<String>) null));
    }

    /**
     * Testing of {@link concept.collections.MultiMap#put(Object, Object)} with
     * one key and one value.
     */
    @Test
    public void testOneKeyOneValue() {
        final MultiMap<String, String> container = new MultiMap<>();
        assertThat(container.put("foo", "bar"), equalTo(true));
        assertThat(container.put("foo", "bar"), equalTo(false));
        assertThat(container.isEmpty(), equalTo(false));
        assertThat(container.size(), equalTo(1));
        assertThat(container.containsKey("foo"), equalTo(true));
        assertThat(container.get("foo"), not(equalTo((Set<String>) null)));
        assertThat(container.get("foo").size(), equalTo(1));
    }

    /**
     * Testing of {@link concept.collections.MultiMap#put(Object, Object)} with
     * one key and two values.
     */
    @Test
    public void testOneKeyTwoValues() {
        final MultiMap<String, String> container = new MultiMap<>();
        container.put("foo", "bar-one");
        assertThat(container.put("foo", "bar-two"), equalTo(true));
        assertThat(container.put("foo", "bar-two"), equalTo(false));
        assertThat(container.isEmpty(), equalTo(false));
        assertThat(container.size(), equalTo(1));
        assertThat(container.get("foo").size(), equalTo(2));
    }

    /**
     * Testing of {@link concept.collections.MultiMap#put(Object, Object)} with
     * two keys and two values.
     */
    @Test
    public void testTwoKeysAndEachTwoValues() {
        final MultiMap<String, String> container = new MultiMap<>();
        container.put("foo", "bar-one");
        container.put("foo", "bar-two");
        container.put("foo2", "bar2-one");
        container.put("foo2", "bar2-two");

        assertThat(container.size(), equalTo(2));
        assertThat(container.get("foo").size(), equalTo(2));
        assertThat(container.get("foo2").size(), equalTo(2));
    }

    /**
     * Testing of {@link concept.collections.MultiMap#values()}.
     */
    @Test
    public void testValues() {
        final MultiMap<String, String> container = new MultiMap<>();
        container.put("foo", "bar-one");
        container.put("foo", "bar-two");
        container.put("foo2", "bar2-one");
        container.put("foo2", "bar2-two");

        final Set<String> values = new HashSet<>();
        for (final Set<String> entries: container.values()) {
            values.addAll(entries);
        }

        assertThat(values, hasItem("bar-one"));
        assertThat(values, hasItem("bar-two"));
        assertThat(values, hasItem("bar2-one"));
        assertThat(values, hasItem("bar2-two"));
    }

    /**
     * Testing of {@link concept.collections.MultiMap#containsValue(Object)}.
     */
    @Test
    public void testContainsValue() {
        final MultiMap<String, String> container = new MultiMap<>();
        container.put("foo", "bar-one");
        container.put("foo", "bar-two");

        assertThat(container.containsValue("bar-one"), equalTo(true));
        assertThat(container.containsValue("bar-two"), equalTo(true));
        assertThat(container.containsValue("bar-three"), equalTo(false));
    }

    /**
     * Testing of {@link concept.collections.MultiMap#clear()}.
     */
    @Test
    public void testClear() {
        final MultiMap<String, String> container = new MultiMap<>();
        container.put("foo", "bar-one");
        container.put("foo", "bar-two");
        container.clear();

        assertThat(container.isEmpty(), equalTo(true));
        assertThat(container.size(), equalTo(0));
    }

}
