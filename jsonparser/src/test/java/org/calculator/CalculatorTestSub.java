package org.calculator;
import com.epam.tat.module4.Calculator;;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CalculatorTestSub extends CalculatorTestPrecondition{

    @Test(groups = {"smoke"})
    @Parameters({"a", "b", "expected"})
    public void testSubLong(long a, long b, long expected) {

        Assert.assertEquals(calc.sub(a, b),expected);
    }
}
