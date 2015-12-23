/**
  @author  Thomas Lehmann
  @file    TranslateController.java
  @brief   The scaling feature is implemented for use with the mouse.

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

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;

import concept.variables.Variable;

/**
 * The scaling feature is implemented for use with the mouse.
 */
public class TranslateController extends Observable {
    /**
     * Required to notify on change in scale.
     */
    private final MouseAdapter mouseAdapter;

    /**
     * When true then dragging is enabled (left mouse button only).
     */
    private boolean dragMode;

    /**
     * Offsets for x and y.
     */
    @SuppressWarnings("unused")
    private final Variable<Dimension> offsets;

    /**
     * Internal offsets stored on each mouse drag operation.
     */
    private Dimension newOffsets;

    /**
     * Implements mouse wheel logic providing result of
     * {@link java.awt.event.MouseWheelEvent#getPreciseWheelRotation()} on each
     * update.
     *
     * @param offsets
     *            variable keeping the offsets outside then controller.
     */
    public TranslateController(final Variable<Dimension> offsets) {
        this.offsets = offsets;
        this.newOffsets = (Dimension) offsets.getValue().clone();
        this.dragMode = false;
        this.mouseAdapter = new MouseAdapter() {

            @Override
            public void mouseDragged(final MouseEvent event) {
                if (dragMode) {
                    offsets.setValue(new Dimension(event.getX() - newOffsets.width, event.getY() - newOffsets.height));
                    setChanged();
                    notifyObservers();
                }
                super.mouseDragged(event);
            }

            @Override
            public void mousePressed(final MouseEvent event) {
                dragMode = event.getButton() == MouseEvent.BUTTON1;
                newOffsets = (Dimension) offsets.getValue().clone();
                newOffsets.width = event.getX() - newOffsets.width;
                newOffsets.height = event.getY() - newOffsets.height;
                super.mousePressed(event);
            }

            @Override
            public void mouseReleased(final MouseEvent event) {
                dragMode = false;
                super.mouseReleased(event);
            }
        };
    }

    /**
     * @return the mouseAdapter to allow register for observer to get updates
     *         when mouse has been used to drag things when left mouse button is
     *         clicked.
     */
    public MouseAdapter getMouseAdapter() {
        return mouseAdapter;
    }
}
