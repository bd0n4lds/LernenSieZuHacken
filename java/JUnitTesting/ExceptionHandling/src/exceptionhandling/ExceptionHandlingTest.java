package exceptionhandling;

import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExceptionHandlingTest {
    
    public ExceptionHandlingTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of main method, of class ExceptionHandling.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testMain() {
     new ArrayList<Object>().get(0); 
    }

    @Test (expected = ArithmeticException.class)
    public void testDivideByZero(){
        int x = 10/0;
    }
}

