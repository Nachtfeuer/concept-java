/**
  @author  Thomas Lehmann
  @file    TestParser.java
  @brief   Testing of class {@link concept.model.Parser}.

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

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Set;

import org.junit.Test;

/**
 * Testing of class {@link concept.model.Parser}.
 */
public class TestParser {
    /**
     * Testing of {@link concept.model.Parser#parseSubject(String)}.
     */
    @Test
    public void testParseSubject() {
        final Subject subject = Parser.parseSubject("subject Agatha Christie \"is a writer\"");
        assertThat(subject, not((Subject) null));
        assertThat(subject.getName(), equalTo("Agatha Christie"));
        assertThat(subject.getDescription(), equalTo("is a writer"));
    }

    /**
     * Testing of {@link concept.model.Parser#parseScript(String)}.
     */
    @Test
    public void testScriptOneModelOneSubject() {
        final StringBuilder script = new StringBuilder();
        script.append("model Authors \"book authors\"\n");
        script.append("subject Agatha Christie \"is a writer\"\n");

        final Set<Model> models = Parser.parseScript(script.toString());
        assertThat(models.size(), equalTo(1));

        final Model model = models.iterator().next();
        assertThat(model.getName(), equalTo("Authors"));
        assertThat(model.getDescription(), equalTo("book authors"));
        assertThat(model.hasSubjects(), equalTo(true));

        final Subject subject = model.getSubject("Agatha Christie");
        assertThat(subject, not(equalTo((Subject) null)));
        assertThat(subject.getName(), equalTo("Agatha Christie"));
        assertThat(subject.getDescription(), equalTo("is a writer"));
    }

    /**
     * Testing of {@link concept.model.Parser#parseScript(String)} for missing
     * model.
     */
    @Test
    public void testScriptFailNoModelButSubject() {
        final StringBuilder script = new StringBuilder();
        script.append("subject Agatha Christie \"is a writer\"\n");
        final Set<Model> models = Parser.parseScript(script.toString());
        assertThat(models, equalTo((Set<Model>) null));
    }

    /**
     * Testing of {@link concept.model.Parser#parseScript(String)} when trying
     * to add same subject twice.
     */
    @Test
    public void testScriptFailTryingToAddSameSubjectTwice() {
        final StringBuilder script = new StringBuilder();
        script.append("model Authors \"book authors\"\n");
        script.append("subject Agatha Christie \"is a writer\"\n");
        script.append("subject Agatha Christie \"is a writer\"\n");

        final Set<Model> models = Parser.parseScript(script.toString());
        assertThat(models, equalTo((Set<Model>) null));
    }
}
