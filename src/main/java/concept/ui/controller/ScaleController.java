/**
  @author  Thomas Lehmann
  @file    ScaleController.java
  @brief   The scaling feature is implemented for use with the mouse wheel button.

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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;
import java.util.Observable;

/**
 * The <b>scaling feature</b> is implemented for use with the <b>mouse wheel
 * button</b>. You create an instance of this controller and register an
 * observer at it. For a JPanel (as an example) you then call
 * <b>addMouseWheelListener</b> with this instance as parameter. The result of
 * the update is then as explained in
 * {@link java.awt.event.MouseWheelEvent#getPreciseWheelRotation()}.
 * 
 * @see TestScaleController
 */
public class ScaleController extends Observable {
    /**
     * Required to notify on change in scale.
     */
    private final MouseAdapter mouseAdapter;

    /**
     * Implements mouse wheel logic providing result of
     * {@link java.awt.event.MouseWheelEvent#getPreciseWheelRotation()} on each
     * update.
     */
    public ScaleController() {
        this.mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseWheelMoved(final MouseWheelEvent event) {
                setChanged();
                notifyObservers(event.getPreciseWheelRotation());
                super.mouseWheelMoved(event);
            }
        };
    }

    /**
     * @return the mouseAdapter to allow register for observer to get updates
     *         when mouse wheel has been moved.
     */
    public MouseAdapter getMouseAdapter() {
        return mouseAdapter;
    }

}
