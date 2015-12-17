/**
  @author  Thomas Lehmann
  @file    TestPrimeTools.java
  @brief   Testing of class {@link PrimeTools}.

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
package concept.math;

import org.hamcrest.Matcher;
import org.junit.Test;

import concept.tools.DummyDescription;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Every.everyItem;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static concept.math.PrimeTools.isPrime;

import java.util.List;

/**
 * Testing of class {@link PrimeTools}.
 */
public class TestPrimeTools {
    /**
     * Testing of {@link PrimeTools#isPrime(long)}.
     */
    @Test
    public void testIsPrimeSimple() {
        assertThat(PrimeTools.isPrime(1), equalTo(false));
        assertThat(PrimeTools.isPrime(2), equalTo(true));
        assertThat(PrimeTools.isPrime(3), equalTo(true));
        assertThat(PrimeTools.isPrime(4), equalTo(false));
        assertThat(PrimeTools.isPrime(5), equalTo(true));
    }

    /**
     * Testing of {@link PrimeTools#generatePrimes(long)}. The matcher does use
     * {@link PrimeTools#isPrime(long)}.
     */
    @Test
    public void testGeneratePrimes() {
        final List<Long> primes = PrimeTools.generatePrimes(1000L);
        assertThat(primes, everyItem(isPrime()));
        assertThat(PrimeTools.generatePrimes(1), empty());
    }

    /**
     * Testing of {@link PrimeTools#isPrime()}.
     */
    @Test
    public void testIsPrimeMatcher() {
        final Matcher<Long> matcher = isPrime();
        assertThat(matcher.matches(1L), equalTo(false));
        assertThat(matcher.matches(2L), equalTo(true));
        assertThat(matcher.matches("hello"), equalTo(false));
    }
    
    /**
     * Testing of matcher description.
     */
    @Test 
    public void testFail() {
        final Matcher<Long> matcher = isPrime();
        final DummyDescription description = new DummyDescription();
        matcher.describeTo(description);
        assertThat(description.appendText, equalTo("expected to be a prime: "));
    }
}
