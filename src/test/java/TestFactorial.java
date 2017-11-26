

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import syntaxtree.*;

import frontend.Lexer;
import frontend.Parser;

public class TestFactorial {

    private Program syntaxTree;

    public TestFactorial() {
        Object temp = null;
        try {
            temp = Parser.parse("src/test/etc/Factorial.java");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        assertInstance(temp, Program.class);
        syntaxTree = (Program) temp;
    }

    public static void assertInstance(Object o, Class c) {
        assert c.isAssignableFrom(o.getClass()): o.getClass().getName();
    }
}