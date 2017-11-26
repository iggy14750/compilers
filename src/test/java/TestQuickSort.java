import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import syntaxtree.*;

import frontend.Lexer;
import frontend.Parser;

import java.io.*;

public class TestQuickSort {
    private Program prog;

    public TestQuickSort() {
        prog = TestRunner.parseFile("QuickSort.java");
    }

    @Test
    public void exists() {
        Assert.assertTrue(true);
    }
}