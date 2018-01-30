package org.calculator;
import com.epam.tat.module4.Calculator;;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class CalculatorTestDiv {
    private Calculator calc;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        calc = new Calculator();
    }

    @org.testng.annotations.Test(dataProvider = "sumDataProviderPositive", groups = {"main", "smoke"})
    public void testSumLong(long a, long b, long expected) {
        Assert.assertEquals(calc.div(a, b),expected);
    }

    @org.testng.annotations.Test(dataProvider = "sumDataProviderNegative", groups = {"main"})
    public void testSumLong(long a, long b, double expected) {

        Assert.assertEquals(calc.div(a, b),expected);
    }

    @DataProvider(name="sumDataProviderPositive")
    public Object[][] sumDataProviderPositive(){
        return new Object[][]{
                {100, 100, 1},
                {0, 100, 0},
                {-100, 20, -5}
        };
    }

    @DataProvider(name="sumDataProviderNegative")
    public Object[][] sumDataProviderNegative(){
        return new Object[][]{
                {5, 2, 2.5}
        };
    }

    @org.testng.annotations.Test(expectedExceptions = NumberFormatException.class, groups = {"main"})
    public void testDivLong() {
        long a = 1L;
        long b = 0;
        long result = calc.div(a, b);
    }
}