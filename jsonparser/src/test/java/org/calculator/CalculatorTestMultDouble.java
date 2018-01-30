package org.calculator;
import com.epam.tat.module4.Calculator;;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class CalculatorTestMultDouble {
    private Calculator calc;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        calc = new Calculator();
    }

    @org.testng.annotations.Test(dataProvider = "multDataProvider", groups = {"smoke"})
    public void testMultLong(double a, double b, double expected) {

        Assert.assertEquals(calc.mult(a, b),expected);
    }

    @DataProvider(name="multDataProvider")
    public Object[][] multDataProvider(){
        return new Object[][]{
                {5.0, 5.0, 25.0},
                {1.01, 100.0, 101.0},
                {-10.0, 200.0, -2000.0},
                {0.0, 100.0, 0.0},
        };
    }
}
