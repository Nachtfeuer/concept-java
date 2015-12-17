/**
  @author  Thomas Lehmann
  @file    TestSubject.java
  @brief   Testing of class {@link concept.model.Subject}.

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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.not;

import org.junit.Test;

/**
 * Testing of class {@link concept.model.Subject}.
 */
public class TestSubject {
    /**
     * Testing construction of a subject.
     */
    @Test
    public void testInit() {
        final Subject subject = new Subject("color", "visual property like black and white");
        assertThat(subject.getName(), equalTo("color"));
        assertThat(subject.getDescription(), equalTo("visual property like black and white"));
    }

    /**
     * Testing of {@link concept.model.Subject#toString()}.
     */
    @Test
    public void testToStringDefault() {
        final Subject subject = new Subject("color", "visual property like black and white");
        final String expected = "Subject[name=color,description=visual property like black and white]";
        assertThat(subject.toString(), equalTo(expected));
    }

    /**
     * Testing of {@link concept.model.Subject#toJson()}.
     */
    @Test
    public void testToJsonDefault() {
        final Subject subject = new Subject("color", "visual property like black and white");
        final String expected = "{\"name\":\"color\",\"description\":\"visual property like black and white\"}";
        assertThat(subject.toJson(), equalTo(expected));
    }

    /**
     * Testing of {@link concept.model.Subject#fromJson(String)}.
     */
    @Test
    public void testFromJsonDefault() {
        final String json = "{\"name\":\"color\",\"description\":\"visual property like black and white\"}";
        final Subject subject = Subject.fromJson(json);
        assertThat(subject, not((Subject) null));
        assertThat(subject.getName(), equalTo("color"));
        assertThat(subject.getDescription(), equalTo("visual property like black and white"));
    }

    /**
     * Testing of {@link concept.model.Subject#fromJson(String)} for JSON string
     * "{}".
     */
    @Test
    public void testFromJsonWithEmptyJson() {
        final Subject subject = Subject.fromJson("{}");
        assertThat(subject, equalTo((Subject) null));
    }

    /**
     * Testing of {@link concept.model.Subject#fromJson(String)} for bad/wrong
     * JSON string.
     */
    @Test
    public void testFromJsonWithBadJson() {
        final Subject subject = Subject.fromJson("abc");
        assertThat(subject, equalTo((Subject) null));
    }

    /**
     * Testing of {@link concept.model.Subject#toXml()}.
     */
    @Test
    public void testToXmlDefault() {
        final Subject subject = new Subject("color", "visual property like black and white");

        final StringBuilder xml = new StringBuilder();
        xml.append("<subject>");
        xml.append("<name>color</name>");
        xml.append("<description>visual property like black and white</description>");
        xml.append("</subject>");

        final String given = subject.toXml().replaceAll("\n[ ]*", "");
        assertThat(given, equalTo(xml.toString()));
    }
}
