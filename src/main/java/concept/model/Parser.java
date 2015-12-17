/**
  @author  Thomas Lehmann
  @file    Parser.java
  @brief   parsing model data from an internal format.

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

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * Line parser for subject.
 */
public class Parser {
    /**
     * Logger of this class.
     */
    private final static Logger LOGGER = Logger.getLogger(Parser.class);

    /**
     * Trying to parse a subject from current line.
     *
     * @param line
     *            current line
     * @return new subject instance when current line represents a subject.
     */
    public static Subject parseSubject(final String line) {
        final Pattern pattern = Pattern.compile("subject[ ]+(?<name>[^\"]*?) \"(?<description>[^\"]+)\"");
        final Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            return new Subject(matcher.group("name"), matcher.group("description"));
        }
        return null;
    }

    /**
     * Trying to create model from current line.
     *
     * @param line
     *            line of a script containing model construction specific data
     *            (name and description).
     * @return new model or null if failed.
     */
    public static Model parseModel(final String line) {
        final Pattern pattern = Pattern.compile("model[ ]+(?<name>[^\"]*?) \"(?<description>[^\"]+)\"");
        final Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            return new Model(matcher.group("name"), matcher.group("description"));
        }
        return null;
    }

    /**
     * Parsing a script.
     *
     * @param script
     *            a whole script containing model, subjects and comments.
     * @return unique set of models or null if an error has occured.
     */
    public static Set<Model> parseScript(final String script) {
        final Set<Model> models = new HashSet<>();
        Model currentModel = null;
        for (final String line : script.split("\n")) {
            final Model model = parseModel(line);
            if (model != null) {
                models.add(model);
                currentModel = model;
                continue;
            }

            final Subject subject = parseSubject(line);
            if (subject != null) {
                if (currentModel == null) {
                    LOGGER.error("No model to add subject " + subject + "!");
                    return null;
                }
                if (!currentModel.addSubject(subject)) {
                    LOGGER.error("Failed to add subject " + subject + " to model " + currentModel + "!");
                    return null;
                }
                continue;
            }
        }

        return models;
    }
}
