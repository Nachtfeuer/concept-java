/**
  @author  Thomas Lehmann
  @file    VariableBar.java
  @brief   A StatusBar displaying names and values.

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

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import concept.variables.Variable;

/**
 * A StatusBar displaying names and values.
 */
@SuppressWarnings("rawtypes")
public class VariableBar extends JPanel {
    /**
     * Serial version ID for bar.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Variable-Bar item displaying one variable.
     */
    class Item extends JLabel implements Observer {
        /**
         * Serial version ID for bar item.
         */
        private static final long serialVersionUID = 1L;

        /**
         * Observed variable.
         */
        private Variable variable;

        /**
         * Initializes the bar item with a variable.
         * 
         * @param variable
         *            variable to observe.
         */
        public Item(final Variable variable) {
            this.variable = variable;
            this.variable.addObserver(this);
            this.setOpaque(true);
            this.setBorder(BorderFactory.createEtchedBorder());
            update(this.variable, null);
        }

        @Override
        public void update(Observable o, Object arg) {
            setText(" " + this.variable.getName() + ": " + this.variable.getValue() + " ");
        }
    }

    /**
     * List of status-bar items.
     */
    final Map<String, Item> items;

    /**
     * Initializing the status-bar.
     */
    public VariableBar() {
        this.items = new HashMap<>();
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    /**
     * @return true when the bar has at least one item.
     */
    public Object hasItems() {
        return !this.items.isEmpty();
    }

    /**
     * @param variable
     *            any variable representing a name and a value.
     * @return true when successfully added the variable to the bar.
     */
    public boolean addVariable(final Variable variable) {
        if (variable != null && !this.items.containsKey(variable.getName())) {
            final Item newItem = new Item(variable);
            this.items.put(variable.getName(), newItem);
            add(newItem);
            invalidate();
            return true;
        }
        return false;
    }
}
