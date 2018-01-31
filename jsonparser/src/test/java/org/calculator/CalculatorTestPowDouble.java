package org.calculator;
import com.epam.tat.module4.Calculator;;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class CalculatorTestPowDouble extends CalculatorTestPrecondition{

    @Test(dataProvider = "powDataProvider")
    public void testSumLong(double a, double b, double expected) {
        Assert.assertEquals(calc.pow(a, b),expected);
    }

    @DataProvider(name="powDataProvider")
    public Object[][] powDataProvider(){
        return new Object[][]{
                {2.0, 2.0, 4.0},
                {-2.0, 2.0, 4.0},
                {2.0, -2.0, 0.25},
                {5.345, 0.0, 1.0}
        };
    }
}
