/**
  @author  Thomas Lehmann
  @file    TestVariable.java
  @brief   Testing of class {@link Variable}.

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
package concept.variables;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.contains;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Testing of class {@link Variable}.
 */
public class TestVariable {
    /**
     * Testing of class {@link Variable} for initialization and getter for
     * {@link Integer}.
     */
    @Test
    public void testInitializationAndGetForInteger() {
        final Variable<Integer> variable = new Variable<Integer>("foo", 1024);
        assertThat(variable.getName(), equalTo("foo"));
        assertThat(variable.getValue(), equalTo(1024));
    }

    /**
     * Testing of class {@link Variable} for setter for {@link Integer}.
     */
    @Test
    public void testSetForInteger() {
        final Variable<Integer> variable = new Variable<Integer>("foo", 1024);
        variable.setValue(2048);
        assertThat(variable.getName(), equalTo("foo"));
        assertThat(variable.getValue(), equalTo(2048));
    }

    /**
     * Testing of class {@link Variable} for {@link Observable}.
     */
    @Test
    public void testObservable() {
        final Variable<Integer> variable = new Variable<Integer>("foo", 1024);
        final List<Boolean> results = new ArrayList<>();

        variable.addObserver(new Observer() {
            @Override
            public void update(final Observable observable, final Object argument) {
                results.add(observable.equals(variable));
                results.add(argument == null);
            }
        });

        variable.setValue(2048);
        assertThat(results, contains(true, true));
    }

    /**
     * Testing of {@link Variable#equals(Object)}.
     */
    @Test
    public void testEquals() {
        final Variable<Integer> variableA = new Variable<Integer>("foo", 1024);
        final Variable<Integer> variableB = new Variable<Integer>("foo", 1024);
        final Variable<Integer> variableC = new Variable<Integer>("bar", 1024);
        final Variable<Integer> variableD = new Variable<Integer>("foo", 2048);

        assertThat(variableA, equalTo(variableB));
        assertThat(variableA, not(equalTo(variableC)));
        assertThat(variableA, not(equalTo(variableD)));
        assertThat(variableA, not(equalTo(null)));
        assertThat(variableA, not(equalTo(1234)));
    }

    /**
     * Testing of {@link Variable#hashCode()}.
     */
    @Test
    public void testHashCode() {
        final Variable<Integer> variableA = new Variable<Integer>("foo", 1024);
        final Variable<Integer> variableB = new Variable<Integer>("foo", 1024);
        final Variable<Integer> variableC = new Variable<Integer>("bar", 1024);
        final Variable<Integer> variableD = new Variable<Integer>("foo", 2048);
        
        assertThat(variableA.hashCode(), equalTo(variableB.hashCode()));
        assertThat(variableA.hashCode(), not(equalTo(variableC.hashCode())));
        assertThat(variableA.hashCode(), not(equalTo(variableD.hashCode())));
    }
}
