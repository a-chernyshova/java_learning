package org.calculator;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CalculatorTestTrigonometry extends CalculatorTestPrecondition{

    @Test(dataProvider = "cosDataProvider")
    public void testCosDouble(double a, double expected) {

        Assert.assertEquals(calc.cos(a),expected);
    }
    @Test(dataProvider = "sinDataProvider")
    public void testSinDouble(double a, double expected) {

        Assert.assertEquals(calc.sin(a),expected);
    }
    @Test(dataProvider = "tgDataProvider")
    public void testTgDouble(double a, double expected) {

        Assert.assertEquals(calc.tg(a),expected);
    }
    @Test(dataProvider = "ctgDataProvider")
    public void testCtgDouble(double a, double expected) {

        Assert.assertEquals(calc.ctg(a),expected);
    }
    @DataProvider(name="cosDataProvider")
    public Object[][] cosDataProvider(){
        return new Object[][]{
                {0.0, 1.0},
                {60.0, 0.5},
                {90.0, 0}
        };
    }
    @DataProvider(name="sinDataProvider")
    public Object[][] sinDataProvider(){
        return new Object[][]{
                {0.0, 0.0},
                {30.0, 0.5},
                {90.0, 1}
        };
    }
    @DataProvider(name="tgDataProvider")
    public Object[][] tgDataProvider(){
        return new Object[][]{
                {0, 0.0},
                {45.0, 1.0},
        };
    }
    @DataProvider(name="ctgDataProvider")
    public Object[][] ctgDataProvider(){
        return new Object[][]{
                {45.0, 1.0},
                {90.0, 0}
        };
    }
    @Test
    public void testCtgNegative() {
        double a = 0;
        Assert.assertEquals(calc.ctg(a), "NaN");
    }
    @Test
    public void testTgNegative() {
        double a = 90.0;
        Assert.assertEquals(calc.tg(a), "NaN");
    }
}