package org.calculator;
import com.epam.tat.module4.Calculator;;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class CalculatorTestSqrtDouble {
    private Calculator calc;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        calc = new Calculator();
    }

    @Test(dataProvider = "sqrtDataProvider", groups = {"smoke"})
    public void testSqrtDouble(double a, double expected) {
        Assert.assertEquals(calc.sqrt(a),expected);
    }

    @DataProvider(name="sqrtDataProvider")
    public Object[][] sqrtDataProvider(){
        return new Object[][]{
                {4.0, 2.0},
                {1.0, 1.0},
                {0, 0}
        };
    }

    @Test(groups = {"main"})
    public void testSqrtNegative() {
        double a = -4L;
        double result = calc.sqrt(a);
        Assert.assertEquals(calc.sqrt(a), "NaN");
    }
}
