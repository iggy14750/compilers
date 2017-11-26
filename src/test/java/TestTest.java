
import org.junit.Test;
import org.junit.Assert;

import java.io.*;
import java.util.Scanner;

import syntaxtree.Program;
import frontend.Parser;

public class TestTest {

    @Test
    public void findFile() throws IOException {
        File file = new File("src/test/etc/Factorial.java");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String word = sc.nextLine();
            System.out.println(word);
        }
    }

    public static void assertInstance(Object o, Class c) {
        assert c.isAssignableFrom(o.getClass()): String.format(
            "Incorrect class: expected <%s>, found <%s>", 
            c.getName(), 
            o.getClass().getName()
        );
    }

    public static Program parseFile(String name) {
        Object temp = null;
        try {
            temp = Parser.parse(new File("src/test/etc/" + name));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.toString());
        }
        assertInstance(temp, Program.class);
        return (Program) temp;
    }
}