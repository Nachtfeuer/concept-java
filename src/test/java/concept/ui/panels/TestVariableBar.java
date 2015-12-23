/**
  @author  Thomas Lehmann
  @file    TestVariableBar.java
  @brief   Testing of class {@link VariableBar}.

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
package concept.ui.panels;

import org.junit.Test;

import concept.variables.Variable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.awt.Component;

import javax.swing.JLabel;

/**
 * Testing of class {@link VariableBar}.
 */
public class TestVariableBar {
    /**
     * Testing one variable resulting in one JLabel.
     */
    @Test
    public void testDefault() {
        final VariableBar bar = new VariableBar();
        assertThat(bar.hasItems(), equalTo(false));
        final Variable<String> variable = new Variable<>("test", "test with a string");
        assertThat(bar.addVariable(variable), equalTo(true));
        final Component[] components = bar.getComponents();
        assertThat(components.length, equalTo(1));
        assertThat(components[0].getClass().getGenericSuperclass(), equalTo(JLabel.class));
        assertThat(((JLabel) components[0]).getText(), equalTo(" test: test with a string "));
        assertThat(bar.hasItems(), equalTo(true));
    }

    /**
     * You should not be able to add same variable twice and also null should be
     * rejected.
     */
    @Test
    public void testFailAddVariable() {
        final VariableBar bar = new VariableBar();
        final Variable<String> variable = new Variable<>("test", "test with a string");
        bar.addVariable(variable);
        assertThat(bar.addVariable(variable), equalTo(false));
        assertThat(bar.addVariable(null), equalTo(false));
    }
}
