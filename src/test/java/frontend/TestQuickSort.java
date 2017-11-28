
package frontend;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import syntaxtree.*;

import frontend.Lexer;
import frontend.Parser;

import java.io.*;

public class TestQuickSort {

    private Program prog;
    private Parser parser;

    public TestQuickSort() throws Exception {
        parser = new Parser(new File("src/test/etc/QuickSort.java"));
        prog = parser.getProgram();
    }

    @Test
    public void exists() {
        Assert.assertTrue(true);
    }

    @Test
    public void findClassId() {
        Identifier name = ((ClassDeclSimple) prog.cl.elementAt(0)).i;
        Position pos = parser.getPosition(name);
        Assert.assertNotNull(pos);
        Assert.assertEquals(11, pos.line);
        Assert.assertEquals(7, pos.column);
    }

    @Test
    public void findMethodName() {
        Identifier name = ((ClassDeclSimple) prog.cl.elementAt(0)).ml.elementAt(1).i;
        Position pos = parser.getPosition(name);
        Assert.assertNotNull(pos);
        Assert.assertEquals(31, pos.line);
        Assert.assertEquals(16, pos.column);
    }
}