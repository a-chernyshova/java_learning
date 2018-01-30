package org.calculator.runner;

import org.calculator.listener.CalculatorTestListener;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import java.util.ArrayList;
import java.util.List;

public class TestRunner {

    public static void main(String[] args){
        TestListenerAdapter tia = new TestListenerAdapter();
        TestNG tng = new TestNG();
        tng.addListener(tia);
        tng.addListener(new CalculatorTestListener());

        XmlSuite suite = new XmlSuite();
        suite.setName("Calculator");

        List<String> files = new ArrayList<String>();
        files.addAll(new ArrayList<String>(){{
            add("src/test/resources/suites/testSuite.xml");
        }});

        suite.setSuiteFiles(files);

        List<XmlSuite> suites = new ArrayList<XmlSuite>();
        suites.add(suite);
        tng.setXmlSuites(suites);

        tng.run();
    }
}