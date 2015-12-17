/**
  @author  Thomas Lehmann
  @file    Serializer.java
  @brief   Serializer tools.

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
package concept.tools;

import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;

import concept.matcher.AbstractMatcher;
import concept.matcher.gson.MatcherSerializer;

/**
 * Provides different tools for serialize.
 */
public final class Serializer {
    /**
     * Serializes hierarchy to a JSON string using gson library.
     *
     * @param object
     *            root object of hierarchy to serialize.
     * @return JSON string for given hierarchy,
     */
    public static String withGsonToJson(final Object object) {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(AbstractMatcher.class, new MatcherSerializer());
        final Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }

    /**
     * Serializes hierarchy to a XML string using the XStream library.
     *
     * @param object
     *            root object of hierarchy to serialize.
     * @param aliases
     *            a map for names and relating classes.
     * @return XML string for given hierarchy.
     */
    @SuppressWarnings("rawtypes")
    public static String withXStreamToXml(final Object object, final Map<String, Class> aliases) {
        final XStream stream = new XStream();
        // allows adjusting more readable tags
        for (final Entry<String, Class> alias : aliases.entrySet()) {
            stream.alias(alias.getKey(), alias.getValue());
        }
        return stream.toXML(object);
    }
}
