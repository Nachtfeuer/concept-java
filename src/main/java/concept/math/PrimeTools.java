/**
  @author  Thomas Lehmann
  @file    PrimeTools.java
  @brief   some prime tools.

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

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

/**
 * Some prime tools.
 */
public final class PrimeTools {
    /**
     * Testing a number to be prime.
     *
     * @param n
     *            an integer to be tested to be a prime.
     * @return true when given number is a prime
     */
    public static boolean isPrime(final long n) {
        if (n < 2) {
            return false;
        }

        if (n % 2 == 0) {
            return n == 2;
        }

        final long limit = Double.valueOf(Math.sqrt(n)).longValue() + 1;
        for (long d = 3; d <= limit; d += 2) {
            if (n % d == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Generates primes up to this limit (inclusive).
     * 
     * @param limit
     *            maximum number
     * @return list of primes
     */
    public static List<Long> generatePrimes(final long limit) {
        final List<Long> primes = new ArrayList<Long>();

        if (limit >= 2) {
            primes.add(2L);
        }

        for (long n = 3; n <= limit; n += 2) {
            if (isPrime(n)) {
                primes.add(n);
            }
        }
        return primes;
    }

    /**
     * Matcher that does check a given value to be prime.
     * 
     * @return matcher that can be used with other hamcrest matchers.
     */
    @Factory
    public static Matcher<Long> isPrime() {
        return new BaseMatcher<Long>() {

            @Override
            public boolean matches(Object object) {
                if (object instanceof Long) {
                    return isPrime(((Long) object).longValue());
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("expected to be a prime: ");
            }
        };
    }
}
