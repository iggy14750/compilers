
import java.io.FileReader;
import java.io.IOException;
import java_cup.runtime.Scanner;
import java_cup.runtime.Symbol;

public class PrettyPrinter {
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
                case sym.PUBLIC:
                    System.out.println("Public");
                    break;
                case sym.TRUE:
                    System.out.println("TRUE");
                    break;
                case sym.FALSE:
                    System.out.println("FALSE");
                    break;
                default:
                    System.out.println("Unknown: " + s.sym + ", " + s.value);
            }
        }
    }
}