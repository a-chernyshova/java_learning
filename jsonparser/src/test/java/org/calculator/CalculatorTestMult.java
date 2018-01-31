package org.calculator;
import com.epam.tat.module4.Calculator;;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class CalculatorTestMult extends CalculatorTestPrecondition{

    @Test(dataProvider = "multDataProvider", groups = {"main", "smoke"})
    public void testMultLong(long a, long b, long expected) {
        Assert.assertEquals(calc.mult(a, b),expected);
    }

    @DataProvider(name="multDataProvider")
    public Object[][] multDataProvider(){
        return new Object[][]{
                {5, 5, 25},
                {1, 100, 100},
                {-10, 200, -2000},
                {0, 100, 0},
        };
    }
}
