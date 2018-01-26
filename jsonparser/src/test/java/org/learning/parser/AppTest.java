package org.learning.parser;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {

    public AppTest( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( AppTest.class );
    }

    public void testApp() {

        try {
            App.main(new String[]{"c:/test.json", "lterm"});
            assertTrue(false);
        } catch (RuntimeException e){
            assertTrue(true);
        }
    }
}
