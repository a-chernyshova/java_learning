package org.calculator.listener;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class CalculatorTestListener implements IInvokedMethodListener{
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        System.out.println("Method started: " + iInvokedMethod.getTestMethod().getMethodName());
    }

    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        System.out.println("Method finished: [" + iTestResult.getStatus() + "]:" + iInvokedMethod.getTestMethod().getMethodName() + "\n");
    }
}
