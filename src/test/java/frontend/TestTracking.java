
package frontend;

import org.junit.*;
import syntaxtree.*;

public class TestTracking {

    private Program prog;

    public TestTracking() {
        prog = TestRunner.parseFile("Tracking.java");
    }

    @Test
    public void exists() {
        Assert.assertTrue(true);
    }
}