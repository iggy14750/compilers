
package semantic;

import org.junit.*;
import static org.junit.Assert.*;

public class QuadTest {


    @Test
    public void and() {
        Quad a = Quad.and("x", "y", "z");
        assertEquals("x := y && z", a.toString());
    }

    @Test
    public void plus() {
        Quad a = Quad.plus("x", "y", "z");
        assertEquals("x := y + z", a.toString());
    }

    @Test
    public void minus() {
        Quad a = Quad.minus("x", "y", "z");
        assertEquals("x := y - z", a.toString());
    }

    @Test
    public void times() {
        Quad a = Quad.times("x", "y", "z");
        assertEquals("x := y * z", a.toString());
    }

    @Test
    public void lessThan() {
        Quad a = Quad.lessThan("x", "y", "z");
        assertEquals("x := y < z", a.toString());
    }
}