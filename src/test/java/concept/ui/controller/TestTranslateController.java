/**
  @author  Thomas Lehmann
  @file    TestTranslateController.java
  @brief   Testing of class {@link TranslateController}.

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
package concept.ui.controller;

import org.junit.Test;

import concept.variables.Variable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

/**
 * Testing of class {@link TranslateController}.
 */
public class TestTranslateController {
    /**
     * Testing of default usage.
     */
    @Test
    public void testDefault() {
        final Variable<Dimension> offsets = new Variable<Dimension>("offsets", new Dimension(0, 0));
        final TranslateController controller = new TranslateController(offsets);

        // simulation of required mouse events ...
        final MouseEvent eventPressed = new MouseEvent(new JLabel(), 0, 0, MouseEvent.BUTTON1_DOWN_MASK, 0, 0, 1, false,
                MouseEvent.BUTTON1);
        controller.getMouseAdapter().mousePressed(eventPressed);
        final MouseEvent eventDragged = new MouseEvent(new JLabel(), 0, 0, 0, 10, 20, 1, false);
        controller.getMouseAdapter().mouseDragged(eventDragged);
        final MouseEvent eventReleased = new MouseEvent(new JLabel(), 0, 0, 0, 0, 0, 1, false, MouseEvent.BUTTON1);
        controller.getMouseAdapter().mouseReleased(eventReleased);

        assertThat(offsets.getValue().getWidth(), equalTo(10.0));
        assertThat(offsets.getValue().getHeight(), equalTo(20.0));
    }
}
