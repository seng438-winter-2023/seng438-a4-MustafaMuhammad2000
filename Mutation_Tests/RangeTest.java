package org.jfree.data;

//Uses Jmock and Junit 

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.*;

public class RangeTest {

    // Contains tests START

    // For the function contains()
    // Testing a value within the range
    @Test
    public void testContains_InRange() {
        double point = 0.5;
        Range exampleRange = new Range(0.0, 1.0);
        boolean result = exampleRange.contains(point);
        assertTrue(result);
    }

    // For the function contains()
    // Testing a value below the range
    @Test
    public void testContains_BelowRange() {
        double point = -1.0;
        Range exampleRange = new Range(0.0, 1.0);
        boolean result = exampleRange.contains(point);
        assertFalse(result);
    }

    @Test
    // For the function contains()
    // Testing a value above the range
    public void testContains_AboveRange() {
        double point = 11.0;
        Range exampleRange = new Range(0.0, 1.0);
        boolean result = exampleRange.contains(point);
        assertFalse(result);
    }

    // For the function contains()
    // Testing a value at the lower boundary of the range
    @Test
    public void testContains_AtLowerRangeBoundary() {
        double point = 0.0;
        Range exampleRange = new Range(0.0, 1.0);
        boolean result = exampleRange.contains(point);
        assertTrue(result);
    }

    // For the function contains()
    // Testing a value at the upper boundary of the range
    @Test
    public void testContains_AtUpperRangeBoundary() {
        double point = 10.0;
        Range exampleRange = new Range(0.0, 10.0);
        boolean result = exampleRange.contains(point);
        assertTrue(result);
    }

    // For the function contains()
    // Testing a range going up to the max double value
    @Test
    public void testContains_MaxDoubleValue() {
        double point = Double.MAX_VALUE;
        Range exampleRange = new Range(0.0, Double.MAX_VALUE);
        boolean result = exampleRange.contains(point);
        assertTrue(result);
    }

    // For the function contains()
    // Testing a range starting from the min double value
    @Test
    public void testContains_MinDoubleValue() {
        double point = Double.MIN_VALUE;
        Range exampleRange = new Range(0.0, Double.MIN_VALUE);
        boolean result = exampleRange.contains(point);
        assertTrue(result);
    }

    // For the function contains()
    // Testing a range going up to the double's version of positive infinity
    @Test
    public void testContains_PositiveInfinity() {
        double point = Double.POSITIVE_INFINITY;
        Range exampleRange = new Range(0.0, Double.POSITIVE_INFINITY);
        boolean result = exampleRange.contains(point);
        assertTrue(result);
    }

    // For the function contains()
    // Testing a range starting from the double's version of negative infinity
    @Test
    public void testContains_NegativeInfinity() {
        double point = Double.NEGATIVE_INFINITY;
        Range exampleRange = new Range(Double.NEGATIVE_INFINITY, 0.0);
        boolean result = exampleRange.contains(point);
        assertTrue(result);
    }
    

    // Contains tests END

    // getCentralValue tests START

    // Testing original method getCentralValue()
    // This test covers the length between two positive values for -50 and 50 which
    // is 0
    @Test
    public void test_getCentralValue_applicableValue() {
        Range test = new Range(-50, 50);
        assertEquals(0.0, test.getCentralValue(), .000000001d);
    }
    
    @Test
    //Mutation (No change)
    public void test_getCentralValue_ModulusMutation() {
    	Range test = new Range(0,1);
    	assertEquals(0.5, test.getCentralValue(), .000000001d);
    }

    // getCentralValue tests END

    // intersects tests START

    @Test
    // Testing original method intersects()
    // Method tests whether there is an intersection with the proposed range
    public void test_intersects_Regular() {
        Range range = new Range(5, 10);
        assertTrue(range.intersects(2, 8));
    }

    // Testing original method intersects()
    // Method tests whether there is an intersection with the proposed range and
    // another range
    @Test
    public void test_intersects_MultiIntersection() {
        Range range1 = new Range(8, 16);
        Range range2 = new Range(10, 14);
        assertTrue(range1.intersects(range2));
    }

    // Testing original method intersects()
    // Method tests whether there is an intersection with the proposed range that is
    // outside the actual range
    @Test
    public void test_intersects_FalseIntersection() {
        Range range = new Range(5, 10);
        assertFalse(range.intersects(1, 2));
    }

    // Testing original method intersects()
    // Method tests whether there is an intersection with the proposed range only
    // being in the upper branch locale of the real code
    @Test
    public void test_intersects_UpperBranch() {
        Range range = new Range(4, 8);
        assertTrue(range.intersects(6, 8));
    }

    // Testing original method intersects()
    // Method tests whether there is an intersection with the proposed range only
    // being in the lower branch locale of the real code
    @Test
    public void test_intersects_LowerBranch() {
        Range range = new Range(2, 5);
        assertTrue(range.intersects(2, 7));
    }

    // Testing original method intersects()
    // Method tests whether there is an intersection with the proposed range where
    // the lower bound is an impossible value
    @Test
    public void test_intersect_FalseLowerBound() {
        Range range = new Range(-10, 10);
        assertFalse(range.intersects(11, 10));
    }

    // Testing original method intersects()
    // Method tests whether there is an intersection with the proposed range where
    // the upper bound has an impossible value
    @Test
    public void test_intersect_FalseUpperBound() {
        Range range = new Range(-10, 10);
        assertFalse(range.intersects(10, 11));
    }

    // Testing original method intersects()
    // Method tests whether there is an intersection with a valid range inside the
    // range
    @Test
    public void test_intersect_validrange() {
        Range range = new Range(50, 70);
        assertTrue(range.intersects(51, 51));
    }

    // Testing original method intersects()
    // Method tests whether there is an intersection with a range outside the actual
    // range
    @Test
    public void test_intersect_invalid1belowlower() {
        Range range = new Range(50, 70);
        assertFalse(range.intersects(49, 49));
    }

    // Testing original method intersects()
    // Method tests whether there is an intersection with a range crossing from
    // outside the boundary to inside near the lower
    @Test
    public void test_intersect_lower_outsidetoinside() {
        Range range = new Range(50, 70);
        assertTrue(range.intersects(49, 51));
    }

    // Testing original method intersects()
    // Method tests whether there is an intersection with a range crossing from
    // inside to outside the boundary near the upper
    @Test
    public void test_intersect_upper_insideToOutside() {
        Range range = new Range(50, 70);
        assertTrue(range.intersects(69, 71));
    }

    // Testing original method intersects()
    // Method tests whether there is an intersection with a valid range right on the
    // lower
    @Test
    public void test_intersect_completelylower() {
        Range range = new Range(50, 51);
        assertFalse(range.intersects(50, 50));
    }

    // Testing original method intersects()
    // Method tests whether there is an intersection with a valid range right on the
    // upper
    @Test
    public void test_intersect_completelyupper() {
        Range range = new Range(50, 51);
        assertFalse(range.intersects(51, 51));
    }

    // Testing original method intersects()
    // Method tests whether there is an intersection with a range completely outside
    // the given range
    @Test
    public void test_intersect_completely_invalid() {
        Range range1 = new Range(50, 70);
        assertFalse(range1.intersects(80, 100));
    }

    // Testing original method intersects()
    // Method tests whether there is an intersection using range values of 1 and 3
    @Test
    public void intersects_1_3() {
        Range range1 = new Range(1, 3);
        assertTrue(range1.intersects(1, 3));
    }

    // Testing original method intersects()
    // Method tests whether there is an intersection with a NaN range boundary
    // values
    @Test
    public void test_intersect_NaNValues() {
        Range range = new Range(Double.NaN, Double.NaN);
        assertFalse(range.intersects(Double.NaN, Double.NaN));
    }

    // intersects tests END

    // equals tests START

    // Testing original method equals()
    // Method tests whether two equal ranges are equal or not

    @Test
    public void test_Equals_StandardValues() {
        Range range1 = new Range(9, 10);
        Range range2 = new Range(9, 10);
        boolean eqv_Val = range1.equals(range2);
        assertTrue(eqv_Val);
    }

    // Testing original method equals()
    // Method tests whether two ranges are not equal where the lower bound for one
    // range is lower than the second
    @Test
    public void test_Equals_LowerFirstRange() {
        Range range1 = new Range(-1, 10);
        Range range2 = new Range(-2, 10);
        boolean eqv_Val = range1.equals(range2);
        assertFalse(eqv_Val);
    }

    // Testing original method equals()
    // Method tests whether two ranges are equal or not where the upper bound for
    // one range is higher than the second
    @Test
    public void test_Equals_HigherSecondaryRange() {
        Range range1 = new Range(3, 8);
        Range range2 = new Range(3, 9);
        boolean eqv_Val = range1.equals(range2);
        assertFalse(eqv_Val);
    }

    // Testing original method equals()
    // Method tests whether two ranges are equal when comparing it to null
    @Test
    public void test_Equals_valNULL() {
        Range range = new Range(3, 8);
        boolean eqv_Val = range.equals(null);
        assertFalse(eqv_Val);
    }

    // Testing original method equals()
    // Method tests whether two ranges are equal with the smallest and biggest
    // integers
    @Test
    public void test_Equals_SmallestMinLargestMax() {
        Range range1 = new Range(-2147483648, 2147483647);
        Range range2 = new Range(-2147483648, 2147483647);
        assertTrue(range1.equals(range2));
    }

    // Testing original method equals()
    // Method tests whether two ranges are not equal where the upper is 1 smaller
    // than the other range
    @Test
    public void test_Equals_Upper1Less() {
        Range range1 = new Range(30, 40);
        Range range2 = new Range(30, 39);
        assertFalse(range1.equals(range2));
    }

    // Testing original method equals()
    // Method tests whether two ranges are not equal where the upper is one bigger
    // than the other range
    @Test
    public void test_Equals_Upper1More() {
        Range range1 = new Range(30, 40);
        Range range2 = new Range(30, 41);
        assertFalse(range1.equals(range2));
    }

    // Testing original method equals()
    // Method tests whether two ranges are not equal where the lower is one less
    // than the previous range
    @Test
    public void test_Equals_Lower1Less() {
        Range range1 = new Range(30, 40);
        Range range2 = new Range(29, 40);
        assertFalse(range1.equals(range2));
    }

    // Testing original method equals()
    // Method tests whether two ranges are not equal where the lower is one bigger
    // than the previous range
    @Test
    public void test_Equals_Lower1More() {
        Range range1 = new Range(30, 40);
        Range range2 = new Range(31, 40);
        assertFalse(range1.equals(range2));
    }

    // Testing original method equals()
    // Method tests whether two ranges are equal using only 1 and -1
    @Test
    public void test_Equals_1Pos_1Neg() {
        Range range1 = new Range(-1, 1);
        Range range2 = new Range(-1, 1);
        assertTrue(range1.equals(range2));
    }

    // equals tests END

    // constrain tests START

    // Testing original method constrain()
    // Method tests whether 17 will be returned since its closest to 17
    @Test
    public void test_constrainRegular() {
        Range range = new Range(10, 30);
        double test_value = range.constrain(17);
        assertEquals(17, test_value, .000000001d);
    }

    // Testing original method constrain()
    // Method tests whether -10 will be returned since its closest to -11 outside
    // the range but -10 is inside
    @Test
    public void test_constrain_InvalidNumberLower() {
        Range range = new Range(-10, 30);
        double test_value = range.constrain(-11);
        assertEquals(-10, test_value, .000000001d);
    }

    // Testing original method constrain()
    // Method tests whether 30 will be returned since its closest to 31 outside the
    // range but 30 is inside
    @Test
    public void test_constrain_InvalidNumberHigher() {
        Range range = new Range(-10, 30);
        double test_value = range.constrain(31);
        assertEquals(30, test_value, .000000001d);
    }

    // Testing original method constrain()
    // Method tests whether a valid number close to the lower is true
    @Test
    public void test_constrain_lowerabove() {
        Range range = new Range(50.0, 70.0);
        assertEquals(51.0, range.constrain(51.0), .0000001d);
    }

    // Testing original method constrain()
    // Method tests whether a number just below the lower gives the lower
    @Test
    public void test_constrain_lowerbelow() {
        Range range = new Range(50.0, 70.0);
        assertEquals(50.0, range.constrain(49.0), .0000001d);
    }

    // Testing original method constrain()
    // Method tests whether a valid number close to the upper outside the boundary
    // is true
    @Test
    public void test_constrain_upperabove() {
        Range range = new Range(50.0, 70.0);
        assertEquals(70.0, range.constrain(71.0), .0000001d);
    }

    // Testing original method constrain()
    // Method tests whether a valid number close to the upper inside the boundary is
    // true
    @Test
    public void test_constrain_upperbelow() {
        Range range = new Range(50.0, 70.0);
        assertEquals(69.0, range.constrain(69.0), .0000001d);
    }

    // Testing original method constrain()
    // Method tests whether NaN constitutes for boundary placements
    @Test
    public void test_constrain_NaN() {
        Range range = new Range(Double.NaN, Double.NaN);
        assertEquals(Double.NaN, range.constrain(Double.NaN), .0000001d);
    }

    // constrain tests END

    // hashCode tests START

    // Testing original method hashCode()
    // Method tests that the generated hashcode is valid and shouldn't equal 0
    @Test
    public void test_hashCode() {
        Range range = new Range(-30, 30);
        assertNotEquals(0, range.hashCode(), .000000001d);
    }

    // Testing original method hashCode()
    // Method tests the correct hashcode value from 0 to 100
    @Test
    public void test_hashCode_0to100() {
        Range range = new Range(0, 100.0);
        assertEquals(1079574528, range.hashCode());
    }

    // Testing original method hashCode()
    // Method tests the correct hashcode value from -100 to 0
    @Test
    public void test_hashCode_Neg100to0() {
        Range range = new Range(-100.0, 0);
        assertEquals(-904593408, range.hashCode());
    }

    // hasCode tests END

    // scale tests START

    // Testing original method scale()
    // Method tests whether the range is scaled by the specified factor
    @Test
    public void test_scale_Regular() {
        Range range = new Range(5, 10);
        Range outcome_range = new Range(25, 50);
        range = Range.scale(range, 5);
        assertEquals(range, outcome_range);
    }

    // Testing original method scale()
    // Method tests whether the range is scaled by -8 (the method should throw)
    // IllegalArgumentException with a negative factor)
    @Test(expected = IllegalArgumentException.class)
    public void test_scale_invalidData_factor() {
        Range range = new Range(5, 10);
        double outcome = -8;
        range = Range.scale(range, outcome);
    }

    // scale tests END

    // isNaNRange tests START

    // Testing original method isNaNRange()
    // This test covers the isNaNRange between two positive values for 50 and 100
    // which is not NaN so we should get false
    @Test
    public void test_isNaNRange_FalseTest() {
        Range range = new Range(50, 100);
        assertFalse(range.isNaNRange());
    }

    // Testing original method isNaNRange()
    // This test covers the isNaNRange where the lower bound is NaN
    @Test
    public void test_isNaNRange_LowerBoundisNaN() {
        double value = Double.NaN;
        Range range = new Range(value, 1);
        assertFalse("The range of Double.NaN and 1 should should not be NaN", range.isNaNRange());
    }

    // Testing original method isNaNRange()
    // This test covers the isNaNRange where the upper bound is NaN
    @Test
    public void test_isNaNRange_UpperBoundisNaN() {
        double value = Double.NaN;
        Range range = new Range(-1, value);
        assertFalse("The range of -1 and Double.NaN should should not be NaN", range.isNaNRange());
    }

    // Testing original method isNaNRange()
    // This test covers the isNaNRange where the lower bound and upper bound are
    // both NaN
    @Test
    public void test_isNaNRange_LowerAndUpperBoundisNaN() {
        double value = Double.NaN;
        Range range = new Range(value, value);
        assertTrue("The range of Double.NaN and Double.NaN should should be NaN", range.isNaNRange());
    }

    // isNaNRange tests END

    // getLength tests START

    // Testing original method getLength()
    // This test covers the length between two positive values for
    // positive_lowerbound to positive_upperbound
    @Test
    public void test_getLength_Pos_To_Pos() {
        double positive_lowerbound = 20.5;
        double positive_upperbound = 40.6;
        Range test = new Range(positive_lowerbound, positive_upperbound);
        assertEquals("The range between 20.5 and 40.6 should be 20.1", 20.1, test.getLength(), .000000001d);
    }

    // Testing original method getLength()
    // This test covers the length between two negative values for
    // negative_lowerbound to negative_upperbound
    @Test
    public void test_getLength_Neg_To_Neg() {
        double negative_lowerbound = -40.5;
        double negative_upperbound = -20.6;
        Range test = new Range(negative_lowerbound, negative_upperbound);
        assertEquals("The range between -40.5 and -20.6 should be 19.9", 19.9, test.getLength(), .000000001d);
    }

    // Testing original method getLength()
    // This test covers the length between a negative and positive value for
    // negative_lowerbound to positive_upperbound
    @Test
    public void test_getLength_Neg_To_Pos() {
        double negative_lowerbound = -20.5;
        double positive_upperbound = 40.6;
        Range test = new Range(negative_lowerbound, positive_upperbound);
        assertEquals("The range between -20.5 and 40.6 should be 61.1", 61.1, test.getLength(), .000000001d);
    }

    // Testing original method getLength()
    // This test covers the length between two positive values in the same range for
    // positive_upperbound
    @Test
    public void test_getLength_SameRange_Positive() {
        double positive_upperbound = 40.6;
        Range test = new Range(positive_upperbound, positive_upperbound);
        assertEquals("The range between 40 and 40 should be 0", 0, test.getLength(), .000000001d);
    }

    // Testing original method getLength()
    // This test covers the length between two negative values in the same range for
    // negative_upperbound
    @Test
    public void test_getLength_SameRange_Negative() {
        double negative_upperbound = -20.6;
        Range test = new Range(negative_upperbound, negative_upperbound);
        assertEquals("The range between -20 and -20 should be 0", 0, test.getLength(), .000000001d);
    }

    // Testing original method getLength()
    // This test covers the length between 0 and 0 using the variables zero_range
    @Test
    public void test_getLength_SameRange_Zero() {
        double zero_range = 0.0;
        Range test = new Range(zero_range, zero_range);
        assertEquals("The range between 0 and 0 should be 0", 0, test.getLength(), .000000001d);
    }

    // Testing original method getLength()
    // This test covers the length between the smallest possible negative double
    // with -MAX_VALUE to 0 using zero_range
    @Test
    public void test_getLength_NegativeSmallest_To_0() {
        double zero_range = 0.0;
        Range test = new Range(-(Double.MAX_VALUE), zero_range);
        assertEquals("The range between smallest negative and 0 should be 1.7976931348623157E308",
                1.7976931348623157E308, test.getLength(), .000000001d);
    }

    // Testing original method getLength()
    // This test covers the largest possible length using zero_range and MAX_VALUE
    // double
    @Test
    public void test_getLength_0_To_Max() {
        double zero_range = 0.0;
        Range test = new Range(zero_range, Double.MAX_VALUE);
        assertEquals("The range between 0 and largest positive double should be 1.7976931348623157E308",
                1.7976931348623157E308, test.getLength(), .000000001d);
    }

    // Testing original method getLength()
    // Any number from anywhere to infinity should be infinity
    // Therefore an equivalence class here only involves one ECT from negative
    // infinity to positive infinity accounts for all in between
    @Test
    public void test_getLength_Min_To_Infinity() {
        Range test = new Range(-(Double.POSITIVE_INFINITY), Double.POSITIVE_INFINITY);
        assertEquals("The range between negative infinity and infinity should be infinity", Double.POSITIVE_INFINITY,
                test.getLength(), .000000001d);
    }

    // Testing original method getLength()
    // This test covers an illegal length where the lowerbound > upperbound using
    // positive_upperbound and positive_lowerbound
    @Test(expected = IllegalArgumentException.class)
    public void test_getLength_Illegal_LowerBound_GreaterThan_UpperBound() {
        double positive_upperbound = 40.6;
        double positive_lowerbound = 20.5;
        Range test = new Range(positive_upperbound, positive_lowerbound);
        double length = test.getLength();
    }

    // Testing original method getLength()
    // This test covers the length when a null value is passed through
    @Test(expected = NullPointerException.class)
    public void test_getLength_Illegal_Numbers() {
        Range null_test = null;
        null_test.getLength();
    }

    // getLength tests END

    // GetLowerBound tests START

    @Test
    // Testing a positive lower bound with a positive upper bound
    public void test_getLowerBound_PositiveLowerBoundPositiveUpperBound() {
        Range exampleRange = new Range(1.5, 2);
        assertEquals(1.5, exampleRange.getLowerBound(), .000000001d);
    }

    @Test
    // Testing a negative lower bound with a positive upper bound
    public void test_getLowerBound_NegativeLowerBoundPositiveUpperBound() {
        Range exampleRange = new Range(-1.5, 2);
        assertEquals(-1.5, exampleRange.getLowerBound(), .000000001d);
    }

    @Test
    // Testing a zero lower bound with a positive upper bound
    public void test_getLowerBound_ZeroLowerBoundPositiveUpperBound() {
        Range exampleRange = new Range(0, 2);
        assertEquals(0, exampleRange.getLowerBound(), .000000001d);
    }

    @Test
    // Testing a negative lower bound with a negative upper bound
    public void test_getLowerBound_NegativeLowerBoundNegativeUpperBound() {
        Range exampleRange = new Range(-2, -1);
        assertEquals(-2, exampleRange.getLowerBound(), .000000001d);
    }

    @Test
    // Testing positive same values for lower and upper bound
    public void test_getLowerBound_PositiveSameDouble() {
        Range exampleRange = new Range(3.5, 3.5);
        assertEquals(3.5, exampleRange.getLowerBound(), .000000001d);
    }

    @Test
    // Testing negative same values for lower and upper bound
    public void test_getLowerBound_NegativSameDouble() {
        Range exampleRange = new Range(-3.5, -3.5);
        assertEquals(-3.5, exampleRange.getLowerBound(), .000000001d);
    }

    @Test
    // Testing zero values for lower and upper bound
    public void test_getLowerBound_ZeroValues() {
        Range exampleRange = new Range(0, 0);
        assertEquals(0, exampleRange.getLowerBound(), .000000001d);
    }

    // GetLowerBound tests END

    // ToString tests START

    // For the function toString()
    // Testing using positive integers. Will display in the string as a double
    @Test
    public void toString_IntegerPositiveInput() {
        Range range = new Range(1, 2);
        String result = range.toString();
        assertEquals("Range[1.0,2.0]", result);
    }

    // For the function toString()
    // Testing using negative integers. Will display in the string as a double
    @Test
    public void toString_IntegerNegativeInput() {
        Range range = new Range(-2, -1);
        String result = range.toString();
        assertEquals("Range[-2.0,-1.0]", result);
    }

    // For the function toString()
    // Testing using negative and positive integers. Will display in the string as a
    // double
    @Test
    public void toString_IntegerNegativeAndPositiveInput() {
        Range range = new Range(-1, 2);
        String result = range.toString();
        assertEquals("Range[-1.0,2.0]", result);
    }

    // For the function toString()
    // Testing using positive doubles.
    @Test
    public void toString_DoublePositiveInput() {
        Range range = new Range(1.5, 2.5);
        String result = range.toString();
        assertEquals("Range[1.5,2.5]", result);
    }

    // For the function toString()
    // Testing using negative doubles.
    @Test
    public void toString_DoubleNegativeInput() {
        Range range = new Range(-2.5, -1.5);
        String result = range.toString();
        assertEquals("Range[-2.5,-1.5]", result);
    }

    // For the function toString()
    // Testing using negative and positive doubles.
    @Test
    public void toString_DoublenegativeAndPositiveInput() {
        Range range = new Range(-1.5, 2.5);
        String result = range.toString();
        assertEquals("Range[-1.5,2.5]", result);
    }

    // For the function toString()
    // Testing using zero as inputs.
    @Test
    public void toString_ZeroInput() {
        Range range = new Range(0, 0);
        String result = range.toString();
        assertEquals("Range[0.0,0.0]", result);
    }

    // For the function toString()
    // Testing whether the result will be correct if the bounds are equal.
    @Test
    public void toString_EqualLowerAndUpperInput() {
        Range range = new Range(1, 1);
        String result = range.toString();
        assertEquals("Range[1.0,1.0]", result);
    }

    // For the function toString()
    // Testing using a very large input.
    @Test
    public void toString_LargeInput() {
        Range range = new Range(Double.MAX_VALUE, Double.MAX_VALUE);
        String result = range.toString();
        assertEquals("Range[1.7976931348623157E308,1.7976931348623157E308]", result);
    }

    // toString ENDS

    // getUpperBound tests START

    @Test
    // Testing a positive lower bound with a positive upper bound
    public void test_getUpperBound_PositiveLowerBoundPositiveUpperBound() {
        Range exampleRange = new Range(1.5, 2);
        assertEquals(2, exampleRange.getUpperBound(), .000000001d);
    }

    @Test
    // Testing a negative lower bound with a positive upper bound
    public void test_getUpperBound_NegativeLowerBoundPositiveUpperBound() {
        Range exampleRange = new Range(-1.5, 2);
        assertEquals(2, exampleRange.getUpperBound(), .000000001d);
    }

    @Test
    // Testing a zero lower bound with a positive upper bound
    public void test_getUpperBound_ZeroLowerBoundPositiveUpperBound() {
        Range exampleRange = new Range(0, 2);
        assertEquals(2, exampleRange.getUpperBound(), .000000001d);
    }

    @Test
    // Testing a negative lower bound with a negative upper bound
    public void test_getUpperBound_NegativeLowerBoundNegativeUpperBound() {
        Range exampleRange = new Range(-2, -1);
        assertEquals(-1, exampleRange.getUpperBound(), .000000001d);
    }

    @Test
    // Testing positive same values for lower and upper bound
    public void test_getUpperBound_PositiveSameDouble() {
        Range exampleRange = new Range(3.5, 3.5);
        assertEquals(3.5, exampleRange.getUpperBound(), .000000001d);
    }

    @Test
    // Testing negative same values for lower and upper bound
    public void test_getUpperBound_NegativSameDouble() {
        Range exampleRange = new Range(-3.5, -3.5);
        assertEquals(-3.5, exampleRange.getUpperBound(), .000000001d);
    }

    @Test
    // Testing zero values for lower and upper bound
    public void test_getUpperBound_ZeroValues() {
        Range exampleRange = new Range(0, 0);
        assertEquals(0, exampleRange.getUpperBound(), .000000001d);
    }

    @Test
    // Testing largest values for lower and upper bound
    public void test_getUpperBound_MAX() {
        Range exampleRange = new Range(-(Double.MAX_VALUE), Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE, exampleRange.getUpperBound(), .000000001d);
    }

    // getUpperBound tests END

    // Gian's Tests START
    // combine() tests START
    @Test
    // Testing for positive ranges
    public void testCombine_PositiveRanges() {
        Range range1 = new Range(0, 1);
        Range range2 = new Range(1, 5);
        Range result = Range.combine(range1, range2);
        Range expected = new Range(0, 5);
        assertEquals(expected, result);
    }

    @Test
    // Testing for one null range
    public void testCombine_RangeTwoIsNull() {
        Range range1 = new Range(0, 1);
        Range range2 = null;
        Range result = Range.combine(range1, range2);
        Range expected = new Range(0, 1);
        assertEquals(expected, result);
    }

    @Test
    // Testing for 2 null ranges
    public void testCombine_TwoNullRanges() {
        Range range1 = null;
        Range range2 = null;
        Range result = Range.combine(range1, range2);
        Range expected = null;
        assertEquals(expected, result);
    }
    
    @Test
    public void testCombine_RangeOneIsNull() {
    	Range range1 = null;
    	Range range2 = new Range(0,1);
    	Range result = Range.combine(range1, range2);
    	Range expected = new Range(0,1);
    	assertEquals(expected, result);
    }
    
    @Test
    //Mutation (No change)
    public void testCombine_MathminRemovalMutation() {
    	Range range1 = new Range(0,1);
    	Range range2 = new Range(-3,1);
    	Range result = Range.combine(range1, range2);
    	Range expected = new Range(-3,1);
    	assertEquals(expected, result);
    }
    
    

    // combine() tests END

    // combineIgnoringNaN() tests START

    @Test
    // Testing for one NaN upper bound
    public void testCombineIgnoringNaN_OneNaNUpperBound() {
        Range range1 = new Range(0, Double.NaN);
        Range range2 = new Range(1, 5);
        Range result = Range.combineIgnoringNaN(range1, range2);
        Range expected = new Range(0, 5);
        assertEquals(expected, result);
    }

    @Test
    // Testing for one NaN lower bound
    public void testCombineIgnoringNaN_OneNaNLowerBound() {
        Range range1 = new Range(Double.NaN, 1);
        Range range2 = new Range(1, 5);
        Range result = Range.combineIgnoringNaN(range1, range2);
        Range expected = new Range(1, 5);
        assertEquals(expected, result);
    }

    @Test
    // Testing for NaN values for lower and upper bound
    public void testCombineIgnoringNaN_TwoNaNLowerBoundAndUpperBound() {
        Range range1 = new Range(Double.NaN, Double.NaN);
        Range range2 = new Range(1, 5);
        Range result = Range.combineIgnoringNaN(range1, range2);
        Range expected = new Range(1, 5);
        assertEquals(expected, result);
    }

    /*
     * @Test
     * //Testing for NaN values for both lower bounds
     * public void testCombineIgnoringNaN_TwoNaNBothLowerBound() {
     * Range range1 = new Range(Double.NaN, 1);
     * Range range2 = new Range(Double.NaN, 5);
     * Range result = Range.combineIgnoringNaN(range1, range2);
     * Range expected = null;
     * assertEquals(expected, result);
     * }
     */

    @Test
    // Testing for NaN values for both upper bounds
    public void testCombineIgnoringNaN_OneNullRangeOne() {
        Range range1 = null;
        Range range2 = new Range(1, 5);
        Range result = Range.combineIgnoringNaN(range1, range2);
        Range expected = new Range(1, 5);
        assertEquals(expected, result);
    }

    @Test
    // Testing for a null range one
    public void testCombineIgnoringNaN_OneNullRangeTwo() {
        Range range1 = new Range(0, 1);
        Range range2 = null;
        Range result = Range.combineIgnoringNaN(range1, range2);
        Range expected = new Range(0, 1);
        assertEquals(expected, result);
    }

    @Test
    // Testing for a null range two
    public void testCombineIgnoringNaN_OneNullRangeTwoIsNan() {
        Range range1 = null;
        Range range2 = new Range(Double.NaN, Double.NaN);
        Range result = Range.combineIgnoringNaN(range1, range2);
        Range expected = null;
        assertEquals(expected, result);
    }

    @Test
    // Testing for a null range one with a NaN range two
    public void testCombineIgnoringNaN_OneNullRangeOneIsNan() {
        Range range1 = new Range(Double.NaN, Double.NaN);
        Range range2 = null;
        Range result = Range.combineIgnoringNaN(range1, range2);
        Range expected = null;
        assertEquals(expected, result);
    }

    @Test
    // Testing for a null range two with a NaN range one
    public void testCombineIgnoringNaN_TwoNullRanges() {
        Range range1 = null;
        Range range2 = null;
        Range result = Range.combineIgnoringNaN(range1, range2);
        Range expected = null;
        assertEquals(expected, result);
    }

    @Test
    // Testing for both ranges having NaN values
    public void testCombineIgnoringNaN_RangesUpperAndLowerBothNaN() {
        Range range1 = new Range(Double.NaN, Double.NaN);
        Range range2 = new Range(Double.NaN, Double.NaN);
        Range result = Range.combineIgnoringNaN(range1, range2);
        assertEquals(null, result);
    }

    @Test
    // Testing for range one having a NaN upper bound and range two having NaN on
    // both lower and upper bounds
    public void testCombineIgnoringNaN_OneUpperNanAndOtherNaNRange() {
        Range range1 = new Range(-10, Double.NaN);
        Range range2 = new Range(Double.NaN, Double.NaN);
        Range result = Range.combineIgnoringNaN(range1, range2);
        Range expected = new Range(-10, Double.NaN);
        assertEquals(expected.toString(), result.toString());
    }
    

    // combineIgnoringNaN() tests END

    // expand() tests START

    @Test(expected = IllegalArgumentException.class)
    // Testing for an IllegalArgumentException when range is null
    public void testExpand_NullRange() {
        Range result = Range.expand(null, 10, 10);
    }

    @Test
    // Testing for a lower and upper margin of zero
    public void testExpand_LowerMarginAndUpperMarginIsZero() {
        Range range = new Range(0, 1);
        Range result = Range.expand(range, 0, 0);
        Range expected = new Range(0, 1);
        assertEquals(expected, result);
    }

    @Test
    // Testing for a lower and upper margin between zero and one
    public void testExpand_LowerMarginAndUpperMarginIsBetweenZeroAndOne() {
        Range range = new Range(0, 1);
        Range result = Range.expand(range, 0.5, 0.5);
        Range expected = new Range(-0.5, 1.5);
        assertEquals(expected, result);
    }

    @Test
    // Testing for a lower and upper margin of one
    public void testExpand_LowerMarginAndUpperMarginIsOne() {
        Range range = new Range(0, 1);
        Range result = Range.expand(range, 1, 1);
        Range expected = new Range(-1, 2);
        assertEquals(expected, result);
    }

    @Test
    // Testing for a lower and upper margin above one
    public void testExpand_LowerMarginAndUpperMarginIsAboveOne() {
        Range range = new Range(0, 1);
        Range result = Range.expand(range, 2, 2);
        Range expected = new Range(-2, 3);
        assertEquals(expected, result);
    }

    @Test
    // Testing for both lower and upper margin is negative
    public void testExpand_LowerMarginAndUpperMarginIsLessThanZero() {
        Range range = new Range(0, 1);
        Range result = Range.expand(range, -0.5, -0.5);
        Range expected = new Range(0.5, 0.5);
        assertEquals(expected, result);
    }

    @Test
    // Testing for when the lower variable is greater than upper (variable in
    // expand())
    public void testExpand_LowerVariableIsGreaterThanUpperVariable() {
        Range range = new Range(0, 1);
        Range result = Range.expand(range, -0.5, -1);
        Range expected = new Range(0.25, 0.25);
        assertEquals(expected, result);
    }
    
    @Test
    // Testing for lower variable is greater than upper and uppermargin is greater than one (1%)
    public void testExpand_LowerVariableIsGreaterThanUpperVariableAndUpperMarginIsGreaterThanOne() {
        Range range = new Range(0, 1);
        Range result = Range.expand(range, -0.5, -2);
        Range expected = new Range(-0.25, -0.25);
        assertEquals(expected, result);
    }

    // expand() tests END

    // expandToInclude() tests START

    @Test
    // Testing for a null range
    public void testExpandToInclude_NullRange() {
        Range range = null;
        Range result = Range.expandToInclude(range, 3);
        Range expected = new Range(3, 3);
        assertEquals(expected, result);
    }

    @Test
    // Testing for a value less than the lower bound
    public void testExpandToInclude_ValueLessThanLowerBound() {
        Range range = new Range(0, 5);
        Range result = Range.expandToInclude(range, -1);
        Range expected = new Range(-1, 5);
        assertEquals(expected, result);
    }

    @Test
    // Testing for a value greater than the upper bound
    public void testExpandToInclude_ValueGreaterThanUpperBound() {
        Range range = new Range(0, 5);
        Range result = Range.expandToInclude(range, 6);
        Range expected = new Range(0, 6);
        assertEquals(expected, result);
    }

    @Test
    // Testing for a value within the range
    public void testExpandToInclude_ValueIsWithinRange() {
        Range range = new Range(0, 5);
        Range result = Range.expandToInclude(range, 3);
        Range expected = new Range(0, 5);
        assertEquals(expected, result);
    }
    

    // expandToInclude() tests END

    // shift(Range, double) tests START

    @Test
    // Testing for a positive delta
    public void testShift_PositiveDelta() {
        Range range = new Range(0, 1);
        Range result = Range.shift(range, 1);
        Range expected = new Range(1, 2);
        assertEquals(expected, result);
    }

    @Test
    // Testing for a negative delta
    public void testShift_NegativeDelta() {
        Range range = new Range(0, 1);
        Range result = Range.shift(range, -1);
        Range expected = new Range(-1, 0);
        assertEquals(expected, result);
    }

    @Test
    // Testing for a zero delta
    public void testShift_ZeroDelta() {
        Range range = new Range(0, 1);
        Range result = Range.shift(range, 0);
        Range expected = new Range(0, 1);
        assertEquals(expected, result);
    }

    // shift(Range, double) tests END

    // shift(Range, double, boolean) tests START

    @Test
    // Testing for a positive delta
    public void testShift_ZeroCrossing_PositiveDelta() {
        Range range = new Range(0, 1);
        Range result = Range.shift(range, 1, false);
        Range expected = new Range(1, 2);
        assertEquals(expected, result);
    }

    /*
     * @Test
     * //Testing for a negative delta and no zero crossing
     * public void testShift_ZeroCrossing_NegativeDeltaAndNoZeroCrossing() {
     * Range range = new Range(0, 1);
     * Range result = Range.shift(range, -1, false);
     * Range expected = new Range(0, 0);
     * assertEquals(expected, result);
     * }
     */

    @Test
    // Testing for a zero delta
    public void testShift_ZeroCrossing_ZeroDelta() {
        Range range = new Range(0, 1);
        Range result = Range.shift(range, 0, false);
        Range expected = new Range(0, 1);
        assertEquals(expected, result);
    }

    @Test
    // Testing for a negative delta and allow zero crossing
    public void testShift_ZeroCrossing_NegativeDeltaAndAllowZeroCrossing() {
        Range range = new Range(0, 1);
        Range result = Range.shift(range, -1, true);
        Range expected = new Range(-1, 0);
        assertEquals(expected, result);
    }
    
    @Test
    public void testShift_ZeroCrossing_DeltaBetweenZeroAndOneAndAllowZeroCrossing() {
        Range range = new Range(0, 1);
        Range result = Range.shift(range, 0.5, true);
        Range expected = new Range(0.5, 1.5);
        assertEquals(expected, result);
    }
    

    /*
     * @Test
     * //Testing for a negative delta with a negative lower and upper bound while
     * disallowing zero crossing
     * public void
     * testShift_ZeroCrossing_NegativeDeltaAndNegativeLowerAndUpperBoundAndNoZeroCrossing
     * () {
     * Range range = new Range(-3, -1);
     * Range result = Range.shift(range, -1, true);
     * Range expected = new Range(0, 0);
     * assertEquals(expected, result);
     * }
     */

    // shift(Range, double, boolean) tests END

}
