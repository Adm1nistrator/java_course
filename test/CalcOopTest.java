import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.ArrayList;

/**
 * Created by anykey on 14.05.16.
 */
public class CalcOopTest {

    @Test
    public void test(){
        Assert.assertEquals(new ArrayList<Object>().size(), 3);
    }
}
