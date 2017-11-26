
import org.junit.Test;

import java.io.*;
import java.util.Scanner;

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
}