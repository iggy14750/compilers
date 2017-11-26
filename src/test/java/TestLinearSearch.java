import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import syntaxtree.*;

import frontend.Lexer;
import frontend.Parser;

import java.io.*;

public class TestLinearSearch {
    private Program prog;

    public TestLinearSearch() {
        prog = TestRunner.parseFile("LinearSearch.java");
    }

    @Test
    public void exists() {
        Assert.assertTrue(true);
    }
}