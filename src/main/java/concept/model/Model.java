/**
  @author  Thomas Lehmann
  @file    Model.java
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

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import concept.collections.MultiMap;

/**
 * A model has a name and a description.
 */
public class Model extends AbstractEntity {
    /**
     * Logger for this class.
     */
    private final static Logger LOGGER = Logger.getLogger(Model.class);

    /**
     * Container with registered subjects (by its name).
     */
    private final Map<String, Subject> subjects;

    /**
     * Container with registered facts (by its name).
     */
    private final MultiMap<String, Fact> facts;

    /**
     * Initializes the model.
     * 
     * @param name
     *            name of the model
     * @param description
     *            description of the model
     */
    public Model(final String name, final String description) {
        super(name, description);
        this.subjects = new HashMap<>();
        this.facts = new MultiMap<>();
    }

    @Override
    public String toString() {
        final ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("name", this.getName()).append("description", this.getDescription());
        return builder.toString();
    }

    /**
     * @return true when at list one entity has been registered.
     */
    public boolean hasSubjects() {
        return !this.subjects.isEmpty();
    }

    /**
     * Adding an entity to the model.
     * 
     * @param subject
     *            expected to an instance of class {@link AbstractEntity} but
     *            not a derived one!
     * @return true when successfully added. You can add two entities with same
     *         name once only.
     */
    public boolean addSubject(final Subject subject) {
        if (subject == null || this.subjects.containsKey(subject.getName())) {
            return false;
        }
        this.subjects.put(subject.getName(), subject);
        return true;
    }

    /**
     * @param name
     *            exact name of the subject
     * @return subject when found or null (when not found).
     */
    public Subject getSubject(final String name) {
        return this.subjects.get(name);
    }

    /**
     * @return true when there is at least one registered fact.
     */
    public boolean hasFacts() {
        return !this.facts.isEmpty();
    }

    /**
     * Adding a fact to the model.
     * 
     * @param fact
     *            new fact to add to the model.
     * @return true when successfully added the fact otherwise false.
     */
    public boolean addFact(final Fact fact) {
        if (fact == null) {
            return false;
        }

        return this.facts.put(fact.getName(), fact);
    }

    /**
     * Creates a subject from a JSON string.
     * 
     * @param json
     *            JSON string expected representing a subject.
     * @return subject instance created from a JSON string.
     */
    public static Model fromJson(final String json) {
        final Gson converter = new Gson();
        try {
            final Model model = converter.fromJson(json, Model.class);
            if (model.getName() == null || model.getDescription() == null) {
                return null;
            }
            return model;
        } catch (JsonSyntaxException e) {
            LOGGER.error("failed to create model because of bad/wrong JSON string", e);
            return null;
        }
    }

}
