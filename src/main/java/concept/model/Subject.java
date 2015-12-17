/**
  @author  Thomas Lehmann
  @file    Subject.java
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
package concept.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * A subject can by anything.
 */
public class Subject extends AbstractEntity {
    /**
     * Logger for this class.
     */
    private final static Logger LOGGER = Logger.getLogger(Subject.class);

    /**
     * Initializes the subject.
     * 
     * @param name
     *            name of the subject
     * @param description
     *            describtion of the subject
     */
    public Subject(final String name, final String description) {
        super(name, description);
    }

    @Override
    public String toString() {
        final ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("name", this.getName()).append("description", this.getDescription());
        return builder.toString();
    }

    /**
     * Creates a subject from a JSON string.
     * 
     * @param json
     *            JSON string expected representing a subject.
     * @return subject instance created from a JSON string.
     */
    public static Subject fromJson(final String json) {
        final Gson converter = new Gson();
        try {
            final Subject subject = converter.fromJson(json, Subject.class);
            if (subject.getName() == null || subject.getDescription() == null) {
                return null;
            }
            return subject;
        } catch (JsonSyntaxException e) {
            LOGGER.error("failed to create subject because of bad/wrong JSON string", e);
            return null;
        }
    }
}
