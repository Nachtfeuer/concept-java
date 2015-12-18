/**
  @author  Thomas Lehmann
  @file    MultiMap.java
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
package concept.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Missing documentation.
 *
 * @param <K>
 * @param <V>
 */
public class MultiMap<K, V> {
    /**
     * Missing documentation
     */
    private final Map<K, Set<V>> data;

    /**
     * Initializes the container.
     */
    public MultiMap() {
        this.data = new HashMap<>();
    }

    /**
     * Does remove all entries.
     */
    public void clear() {
        this.data.clear();
    }

    /**
     * Checking for existence of given key.
     * 
     * @param key
     *            key of type K
     * @return true when container does contain that key.
     */
    public boolean containsKey(final K key) {
        return this.data.containsKey(key);
    }

    /**
     * Searching for a value in the container.
     * 
     * @param value
     *            the value to search
     * @return true when value is contained.
     */
    public boolean containsValue(final V value) {
        for (final Set<V> values : this.data.values()) {
            if (values.contains(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Provide all values for a given key.
     * 
     * @param key
     *            search key
     * @return all values for given key.
     */
    public Set<V> get(final K key) {
        return this.data.get(key);
    }

    /**
     * Adding given key and value to container.
     * 
     * @param key
     *            key to be added.
     * @param value
     *            value to be added.
     * @return true when value has been added successfully.
     */
    public boolean put(final K key, final V value) {
        Set<V> values = this.data.get(key);
        if (values == null) {
            values = new HashSet<>();
            values.add(value);
            this.data.put(key, values);
            return true;
        }
        return values.add(value);
    }

    /**
     * @return true when there is no key and no value in the container.
     */
    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    /**
     * @return number of registered keys.
     */
    public int size() {
        return this.data.size();
    }

    /**
     * @return all values which is a collection of sets of values.
     */
    public Collection<Set<V>> values() {
        return this.data.values();
    }
}
