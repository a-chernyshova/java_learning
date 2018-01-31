package org.calculator;
import com.epam.tat.module4.Calculator;;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class CalculatorTestIsPositive extends CalculatorTestPrecondition{

    @Test(dataProvider = "isPositiveDataProvider")
    public void testIsPositive(long a, boolean expected) {

        Assert.assertEquals(calc.isPositive(a), expected);
    }

    @DataProvider(name="isPositiveDataProvider")
    public Object[][] isPositiveDataProvider(){
        return new Object[][]{
                {0, false},
                {2, true},
                {-3, false}
        };
    }
}