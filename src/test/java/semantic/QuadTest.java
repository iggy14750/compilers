
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

    @Test
    public void not() {
        Quad a = Quad.not("x", "y");
        assertEquals("x := ~y", a.toString());
    }

    @Test
    public void copy() {
        Quad a = Quad.copy("x", "y");
        assertEquals("x := y", a.toString());
    }

    @Test
    public void jump() {
        Quad a = Quad.jump("me");
        assertEquals("goto me", a.toString());
    }

    @Test
    public void iffalse() {
        Quad a = Quad.ifFalse("x", "y");
        assertEquals("iffalse x goto y", a.toString());
    }

    @Test
    public void param() {
        Quad a = Quad.param("x");
        assertEquals("param x", a.toString());
    }

    @Test
    public void call() {
        Quad a = Quad.call("x", "f", "y");
        assertEquals("x := call f, y", a.toString());
    }

    @Test
    public void ret() {
        Quad a = Quad.ret("x");
        assertEquals("return x", a.toString());
    }

    @Test
    public void indexFrom() {
        Quad a = Quad.indexFrom("x", "y", "i");
        assertEquals("x := y[i]", a.toString());
    }

    @Test
    public void indexTo() {
        Quad a = Quad.indexTo("x", "i", "y");
        assertEquals("x[i] := y", a.toString());
    }

    @Test
    public void newObject() {
        Quad a = Quad.newObject("x", "y");
        assertEquals("x := new y", a.toString());
    }

    @Test
    public void newArray() {
        Quad a = Quad.newArray("x", "y", "z");
        assertEquals("x := new y, z", a.toString());
    }

    @Test
    public void length() {
        Quad a = Quad.length("x", "y");
        assertEquals("x := y.length", a.toString());
    }
}