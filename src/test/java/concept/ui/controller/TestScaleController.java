/**
  @author  Thomas Lehmann
  @file    TestScaleController.java
  @brief   Testing of class {@link ScaleController}.

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

import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

/**
 * Testing of class {@link ScaleController}.
 */
public class TestScaleController {
    /**
     * Testing default usage.
     */
    @Test
    public void testDefault() {
        final ScaleController controller = new ScaleController();
        final List<Boolean> results = new ArrayList<>();

        controller.addObserver(new Observer() {
            @Override
            public void update(Observable observable, Object value) {
                results.add(controller.equals(observable));
                results.add(Double.valueOf(123).equals(value));
            }
        });
        // simulation of a mouse wheel event ...
        final MouseWheelEvent event = new MouseWheelEvent(new JLabel(), 0, 0, 0, 0, 0, 0, false, 0, 0, 123);
        controller.getMouseAdapter().mouseWheelMoved(event);
        assertThat(results, contains(true, true));
    }
}
