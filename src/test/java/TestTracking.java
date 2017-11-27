import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import syntaxtree.*;

import frontend.Lexer;
import frontend.Parser;

import java.io.*;

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