
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

    public TestTreeVisitor() throws Exception {
        prog = TestRunner.parseFile("TreeVisitor.java");
    }

    @Test
    public void exists() {
        Assert.assertTrue(true);
    }
}