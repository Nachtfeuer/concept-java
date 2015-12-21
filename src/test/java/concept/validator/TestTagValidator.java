/**
  @author  Thomas Lehmann
  @file    TestTagValidator.java
  @brief   Testing of class {@link TagValidator}.

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

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Testing of class {@link TagValidator}.
 *
 */
public class TestTagValidator {
    /**
     * Testing for {@link TagValidator#isValid(String)}.
     */
    @Test
    public void testSimpleValid() {
        final String xml = "<data></data>";
        assertThat(TagValidator.isValid(xml), equalTo(true));
    }

    /**
     * Testing for {@link TagValidator#isValid(String)} including all tag
     * variants.
     */
    @Test
    public void testExtendedValid() {
        final String xml = "<data><movies><movie title=\"foo\"/></movies></data>";
        assertThat(TagValidator.isValid(xml), equalTo(true));
    }

    /**
     * Testing for {@link TagValidator#isValid(String)} for missing close tag.
     */
    @Test
    public void testMissingCloseTag() {
        final String xml = "<data><movies></data>";
        assertThat(TagValidator.isValid(xml), equalTo(false));
    }

    /**
     * Testing for {@link TagValidator#isValid(String)} for missing opening tag.
     */
    @Test
    public void testMissingOpeningTag() {
        final String xml = "<data></movies></data>";
        assertThat(TagValidator.isValid(xml), equalTo(false));
    }

    /**
     * Testing for {@link TagValidator#isValid(String)} for missing opening tag.
     */
    @Test
    public void testInconsitentOpenCloseTag() {
        final String xml = "<data><movies></data></movies>";
        assertThat(TagValidator.isValid(xml), equalTo(false));
    }
}
