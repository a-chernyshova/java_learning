package org.calculator;
import com.epam.tat.module4.Calculator;;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class CalculatorTestSumDouble {
    private Calculator calc;

    @BeforeClass()
    public void setUp() {
        calc = new Calculator();
    }

    @Test(dataProvider = "sumDataProvider")
    public void testSumLong(double a, double b, double expected) {
        Assert.assertEquals(calc.sum(a, b),expected);
    }

    @DataProvider(name="sumDataProvider")
    public Object[][] sumDataProvider(){
        return new Object[][]{
                {1.25, 0.21, 1.46},
                {5.0, -6.25, -1.25}
        };
    }
}
