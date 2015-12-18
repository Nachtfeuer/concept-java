/**
  @author  Thomas Lehmann
  @file    TestDeserializer.java
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
package concept.tools;

import java.io.FileNotFoundException;
import java.net.URL;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Testing of class {@link concept.tools.Deserializer}.
 */
public class TestDeserializer {
    /**
     * Testing of
     * {@link concept.tools.Deserializer#withGsonFromJson(String, Class)}. Here
     * we provide the full path and name of file to the read function.
     * 
     * @throws FileNotFoundException
     *             when the test JSON has not been found.
     */
    @Test
    public void testReadJson() throws FileNotFoundException {
        final URL url = TestDeserializer.class.getClassLoader().getResource("SimpleModelOneSubjectNoFacts.json");
        final JsonObject json = Deserializer.withGsonFromJson(url.getFile(), JsonObject.class);
        assertThat(json, not((JsonObject) null));
        assertThat(json.get("subjects"), not((JsonElement) null));
        assertThat(json.get("name").getAsString(), equalTo("persons"));
        assertThat(json.get("description").getAsString(), equalTo("model about some persons"));

        final JsonObject jsonSubject = json.get("subjects").getAsJsonObject().get("Agatha Christie").getAsJsonObject();
        assertThat(jsonSubject.get("name").getAsString(), equalTo("Agatha Christie"));
        assertThat(jsonSubject.get("description").getAsString(), equalTo("is a writer"));
    }
}
