
import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import syntaxtree.*;

import frontend.Lexer;
import frontend.Parser;

import java.io.*;

public class TestBinarySearch {

    private Program prog;

    public TestBinarySearch() throws Exception {
        prog = TestRunner.parseFile("BinarySearch.java");
    }

    @Test
    public void exists() {
        Assert.assertTrue(true);
    }
}