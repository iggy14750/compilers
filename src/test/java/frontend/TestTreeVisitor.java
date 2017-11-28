
package frontend;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import syntaxtree.*;

import frontend.Lexer;
import frontend.Parser;

import java.io.*;

public class TestTreeVisitor {

    private Program prog;
    private Parser parser;

    public TestTreeVisitor() throws Exception {
        parser = new Parser(new File("src/test/etc/TreeVisitor.java"));
        prog = parser.getProgram();
    }

    @Test
    public void exists() {
        Assert.assertTrue(true);
    }

    /**
     * Find a particular symbol, TreeVisitor.class[1].method[0].param[0].name
     * at line 75, column 29.
     */
    @Test
    public void findSymbol() {
        Identifier i = ((ClassDeclSimple) prog.cl.elementAt(1)).ml.elementAt(0).fl.elementAt(0).i;
        Position pos = parser.getPosition(i);
        Assert.assertNotNull(pos);
        Assert.assertEquals(75, pos.line);
        Assert.assertEquals(29, pos.column);
    }
}