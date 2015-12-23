/**
  @author  Thomas Lehmann
  @file    Deserializer.java
  @brief   Some tools for reading from a file.

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

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import concept.matcher.AbstractMatcher;
import concept.matcher.gson.MatcherSerializer;

/**
 * Some tools for reading from a file.
 */
public final class Deserializer {
    /**
     * @param pathAndFileName
     *            path and name of file
     * @param cls
     *            class for which to deserialize.
     * @param <T> which can be any type derived from object.
     * @return instance of type T or null if failed to read JSON file.
     * @throws FileNotFoundException
     *             if file has not been found.
     */
    public static <T> T withGsonFromJson(final String pathAndFileName, final Class<T> cls)
            throws FileNotFoundException {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(AbstractMatcher.class, new MatcherSerializer());
        final Gson gson = gsonBuilder.create();

        final FileReader reader = new FileReader(pathAndFileName);
        return gson.fromJson(reader, cls);
    }

}
