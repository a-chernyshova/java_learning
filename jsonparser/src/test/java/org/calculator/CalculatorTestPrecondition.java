package org.calculator;

import com.epam.tat.module4.Calculator;
import org.testng.annotations.BeforeClass;

public class CalculatorTestPrecondition {
    protected Calculator calc;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        calc = new Calculator();
    }
}
