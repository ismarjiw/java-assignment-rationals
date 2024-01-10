package com.cooksys.ftd.assignments.objects;

import com.cooksys.ftd.assignments.objects.util.MissingImplementationException;

public class SimplifiedRational implements IRational {

    private int numerator;
    private int denominator;

    /**
     * Determines the greatest common denominator for the given values
     *
     * @param a the first value to consider
     * @param b the second value to consider
     * @return the greatest common denominator, or shared factor, of `a` and `b`
     * @throws IllegalArgumentException if a <= 0 or b < 0
     */
//    The Euclidean algorithm is based on the observation that the GCD
//    of two numbers does not change if the larger number is replaced
//    by its remainder when divided by the smaller number. By repeatedly
//    applying this rule, the algorithm eventually finds the GCD.
//    O(Log min(a, b))
    public static int gcd(int a, int b) throws IllegalArgumentException {

        if (a <= 0 || b < 0) {
            throw new IllegalArgumentException();
        }
        if (b == 0) { // the base case
            return a;
        }
        return gcd(b, a % b);
    }

    /**
     * Simplifies the numerator and denominator of a rational value.
     * <p>
     * For example:
     * `simplify(10, 100) = [1, 10]`
     * or:
     * `simplify(0, 10) = [0, 1]`
     *
     * @param numerator   the numerator of the rational value to simplify
     * @param denominator the denominator of the rational value to simplify
     * @return a two element array representation of the simplified numerator and denominator
     * @throws IllegalArgumentException if the given denominator is 0
     */
    public static int[] simplify(int numerator, int denominator) throws IllegalArgumentException {
        if (denominator == 0) {
            throw new IllegalArgumentException();
        }

        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        int sign;
        if ((numerator < 0 && denominator < 0) || (numerator >= 0 && denominator > 0)) {
            sign = 1; // positive
        } else {
            sign = -1; // negative
        }

        return new int[]{sign * Math.abs(numerator) / gcd, Math.abs(denominator) / gcd};
    }

    /**
     * Constructor for rational values of the type:
     * <p>
     * `numerator / denominator`
     * <p>
     * Simplification of numerator/denominator pair should occur in this method.
     * If the numerator is zero, no further simplification can be performed
     *
     * @param numerator   the numerator of the rational value
     * @param denominator the denominator of the rational value
     * @throws IllegalArgumentException if the given denominator is 0
     */
    public SimplifiedRational(int numerator, int denominator) throws IllegalArgumentException {

        if (denominator == 0) {
            throw new IllegalArgumentException();
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * @return the numerator of this rational number
     */
    @Override
    public int getNumerator() {

        return numerator;
    }

    /**
     * @return the denominator of this rational number
     */
    @Override
    public int getDenominator() {

        return denominator;
    }

    /**
     * Specializable constructor to take advantage of shared code between Rational and SimplifiedRational
     * <p>
     * Essentially, this method allows us to implement most of IRational methods directly in the interface while
     * preserving the underlying type
     *
     * @param numerator   the numerator of the rational value to construct
     * @param denominator the denominator of the rational value to construct
     * @return the constructed rational value (specifically, a SimplifiedRational value)
     * @throws IllegalArgumentException if the given denominator is 0
     */
    @Override
    public SimplifiedRational construct(int numerator, int denominator) throws IllegalArgumentException {

        if (denominator == 0) {
            throw new IllegalArgumentException();
        }
        return new SimplifiedRational(numerator, denominator);
    }

    /**
     * @param obj the object to check this against for equality
     * @return true if the given obj is a Simplified rational value and its
     * numerator and denominator are equal to this Simplified rational value's numerator and denominator,
     * false otherwise
     */
    @Override
    public boolean equals(Object obj) {

        if (obj instanceof SimplifiedRational) {
            SimplifiedRational that = (SimplifiedRational) obj;
            return this.numerator == that.numerator && this.denominator == that.denominator;
        }
        return false;
    }
//instanceOf()
    /**
     * If this is positive, the string should be of the form `numerator/denominator`
     * <p>
     * If this is negative, the string should be of the form `-numerator/denominator`
     *
     * @return a string representation of this rational value
     */
    @Override
    public String toString() {
        if ((numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0)) {
            return "-" + Math.abs(numerator) + "/" + Math.abs(denominator);
        } else {
            return Math.abs(numerator) + "/" + Math.abs(denominator);
        }
    }

}
