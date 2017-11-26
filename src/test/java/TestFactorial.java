
import java.io.File;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import syntaxtree.*;

import frontend.Lexer;
import frontend.Parser;

public class TestFactorial {

    private Program prog;

    public TestFactorial() {
        Object temp = null;
        try {
            temp = Parser.parse(new File("src/test/etc/Factorial.java"));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.toString());
        }
        assertInstance(temp, Program.class);
        prog = (Program) temp;
    }

    @Test
    public void mainClassName() {
        Assert.assertEquals("Factorial", prog.m.i1.s);
    }

    @Test
    public void mainClassStatement() {
        assertInstance(prog.m.s, Print.class);
    }

    @Test
    public void callsComputeFac() {
        Assert.assertEquals("ComputeFac", ((Call) ((Print) prog.m.s).e).i.s);
    }

    @Test
    public void classFacIsFirstInList() {
        Assert.assertEquals("Fac", ((ClassDeclSimple) prog.cl.elementAt(0)).i.s);
    }

    @Test
    public void classFacMethodComputeFacReturns_num_aux() {
        Assert.assertEquals("num_aux", 
            ((IdentifierExp) ((ClassDeclSimple) prog.cl.elementAt(0)).ml.elementAt(0).e).s);
    }

    public static void assertInstance(Object o, Class c) {
        assert c.isAssignableFrom(o.getClass()): String.format(
            "Incorrect class: expected <%s>, found <%s>", 
            c.getName(), 
            o.getClass().getName()
        );
    }
}