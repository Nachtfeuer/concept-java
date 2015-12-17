/**
  @author  Thomas Lehmann
  @file    Entity.java
  @brief   an entity with a name and a description.

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

import concept.tools.Serializer;

/**
 * An entity has a name and a description and those fields are immutable.
 */
public abstract class AbstractEntity {
    /**
     * Name of the entity.
     */
    private final String name;

    /**
     * Description of the entity.
     */
    private final String description;

    /**
     * Initializes the entity.
     *
     * @param name
     *            name of the entity.
     * @param description
     *            description of the entity
     */
    public AbstractEntity(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * @return name of the entity.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return description of the entity.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return content of entity as JSON string.
     */
    public String toJson() {
        return Serializer.withGsonToJson(this);
    }

    /**
     * @return content of entity as XML string.
     */
    public String toXml() {
        @SuppressWarnings("rawtypes")
        final Map<String, Class> aliases = new HashMap<>();
        aliases.put("model", Model.class);
        aliases.put("subject", Subject.class);
        return Serializer.withXStreamToXml(this, aliases);
    }
}
