
import java.io.FileReader;
import java.io.IOException;
import java_cup.runtime.Scanner;
import java_cup.runtime.Symbol;

public class PrettyPrinter {

    private static final String[] literals = { 
        "public", "private", "true", "false", "this", "new", "int", "boolean", 
        "String", "return", "if", "else", "while", "for", "do", "class", "extends", 
        "[", "]", "(", ")", "{", "}", ";", ",", ".", "?", ":", "=", "==", "!", "&", 
        "&&", "|", "||", "<", ">", "+", "-", "*", "/"};

    public static void main(String[] args) throws IOException {
        MJLexer lex = new MJLexer(new FileReader(args[0]));
        for (Symbol s = lex.next_token(); s.sym != sym.EOF; s = lex.next_token()) {
            switch (s.sym) {
                case sym.ID:
                    System.out.println("Identifier: " + s.value);
                    break;
                case sym.INT:
                    System.out.println("Integer: " + s.value);
                    break;
                default:
                    if (s.sym < literals.length) {
                        System.out.println(literals[s.sym]);
                    } else {
                        System.out.println("Unknown: " + s.sym + ", " + s.value);
                    }
            }
        }
    }
}