
package frontend;

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

    // Should move these out to their own files
    @Test
    public void testBinaryTree() {
        TestRunner.parseFile("BinaryTree.java");
    }

    @Test
    public void testBubbleSort() {
        TestRunner.parseFile("BubbleSort.java");
    }

    @Test
    public void testLinkedList() {
        TestRunner.parseFile("LinkedList.java");
    }
}