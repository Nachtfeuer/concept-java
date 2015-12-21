/**
  @author  Thomas Lehmann
  @file    TagValidator.java
  @brief   Testing for correct XML open/close tags (consistency).

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
package concept.validator;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * Testing for correct XML open/close tags (consistency).
 *
 */
public final class TagValidator {
    /**
     * Logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(TagValidator.class);
    
    /**
     * Testing for correct XML open/close tags (consistency).
     * 
     * @param xml
     *            a XML like string.
     * @return true when open/close tags are consistent.
     */
    public static boolean isValid(final String xml) {
        final Stack<String> tagStack = new Stack<>();
        final Pattern pattern = Pattern.compile("(<\\w*>)|(<\\/\\w*>)|(<\\w* .*\\/>)");
        final Matcher matcher = pattern.matcher(xml);

        while (matcher.find()) {
            String match = matcher.group();
            // a tag that is open and close can be ignored ...
            if (match.endsWith("/>")) {
                continue;
            }

            // is an end tag?
            if (match.startsWith("</")) {
                match = match.substring(2, match.length() - 1);
                if (!tagStack.isEmpty() && !tagStack.lastElement().equals(match)) {
                    LOGGER.debug("a tag that has been opened requires to be closed first!");
                    return false;
                }
                tagStack.pop();
            } else {
                match = match.substring(1, match.length() - 1);
                tagStack.push(match);
            }
        }

        return tagStack.empty();
    }
}
