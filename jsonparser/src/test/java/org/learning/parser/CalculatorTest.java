package org.learning.calculator;

import com.epam.tat.module4.Calculator;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class CalculatorTest extends TestCase {
    Calculator calc = new Calculator();

    public CalculatorTest( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( CalculatorTest.class );
    }

    public void testSumLong() {
        long a = 100L;
        long b = 200L;
        assertEquals(a+b, calc.sum(a, b));
    }
    public void testSumDouble() {
        double a = 1.505;
        double b = 2.5;
        assertEquals(a+b, calc.sum(a, b));
    }
    public void testSubLong(){
        long a = 100L;
        long b = 200L;
        assertEquals(b-a, calc.sub(b, a));
    }
    public void testSubDouble(){
        double a = 1.505;
        double b = 2.5;
        assertEquals(b-a, calc.sub(b, a));
    }
    public void testMultLong(){
        long a = 100L;
        long b = 200L;
        assertEquals(a*b, calc.mult(a, b));
    }
    public void testMultDouble(){
        double a = 1.505;
        double b = 2.5;
        assertEquals(a*b, calc.mult(a, b));
    }
    public void testDivLongPositive(){
        long a = 4L;
        long b = 2L;
        assertEquals(a/b, calc.div(a, b));
    }
    public void testDivLongNegative(){
        long a = 5L;
        long b = 2L;
        assertEquals((double)a/b, calc.div(a, b));
    }
    public void testDivLongZero(){
        long a = 5L;
        long b = 0L;
        try {
            calc.div(a, b);
            assertTrue(false);
        } catch (NumberFormatException e) {
            assertTrue("Zero division", true);
        }
    }
    public void testDivDouble(){
        double a = 2.505;
        double b = 1.5;
        assertEquals(calc.div(a, b), a/b);
    }
    public void testDivDoubleZero(){
        double a = 2.505;
        double b = 0;
        try {
            calc.div(a, b);
            assertTrue(false);
        } catch(NumberFormatException e){
            assertTrue("Zero division",true);
        }
    }
    public void testPowDouble(){
        double a = 2.5;
        double b = 3.5;
        assertEquals(Math.pow(a, b), calc.pow(a, b));
    }
    public void testSqrtDoubleNegative(){
        double a = -9.0;
        assertEquals(Math.sqrt(a), calc.sqrt(a));
    }
    public void testSqrtDoublePositive(){
        double a = 9;
        assertEquals(Math.sqrt(a), calc.sqrt(a));
    }
    public void testTg(){
        double a = 45;
        assertEquals(Math.tan(a), calc.tg(a));
    }
    public void testCtg(){
        double a = 45;
        assertEquals(1/Math.tan(a), calc.ctg(a));
    }
    public void testCos(){
        double a = 45;
        assertEquals(Math.cos(a), calc.cos(a));
    }
    public void testSin(){
        double a = 45;
        assertEquals(Math.sin(a), calc.sin(a));
    }
    public void testPisitivness(){
        long a = 0;
        assertEquals(false, calc.isPositive(a));
    }
    public void testPisitivness2(){
        long a = 2;
        assertEquals(true, calc.isPositive(a));
    }
    public void testPisitivness3(){
        long a = -3;
        assertEquals(false, calc.isPositive(a));
    }
    public void testNegativeCheck(){
        long a = -1;
        assertEquals(true, calc.isNegative(a));
    }
    public void testNegative2(){
        long a = 2;
        assertEquals(false, calc.isNegative(a));
    }
    public void testNegative3(){
        long a = 0;
        assertEquals(false, calc.isNegative(a));
    }
}
