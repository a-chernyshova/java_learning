package org.calculator;
import com.epam.tat.module4.Calculator;;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CalculatorTestSubDouble {
    private Calculator calc;

    @BeforeClass()
    public void setUp() {
        calc = new Calculator();
    }

    @Test
    @Parameters({"a", "b", "expected"})
    public void testSubLong(double a, double b, double expected) {
        Assert.assertEquals(calc.sub(a, b),expected);
    }
}
