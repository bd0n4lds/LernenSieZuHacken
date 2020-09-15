import org.junit.Assert;
import org.junit.Test;

public class SuiteTest1 {
    @Test
    public void testPrintMessage() {
        int num = 5;
        System.out.println("Suite Test1 is executing");
        Assert.assertEquals(5, num);
    }
}
