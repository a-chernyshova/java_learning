package org.calculator;
import com.epam.tat.module4.Calculator;;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class CalculatorTestIsNegative {
    private Calculator calc;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        calc = new Calculator();
    }

    @Test(dataProvider = "isNegativeDataProvider", groups = {"smoke"})
    public void testIsNegative(long a, boolean expected) {

        Assert.assertEquals(calc.isNegative(a), expected);
    }

    @DataProvider(name="isNegativeDataProvider")
    public Object[][] isNegativeDataProvider(){
        return new Object[][]{
                {0, false},
                {2, false},
                {-3, true}
        };
    }
}