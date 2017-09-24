
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java_cup.runtime.Scanner;
import java_cup.runtime.Symbol;

public class PrettyPrinter {

    private static final String[] literals = { "public", "private", "true", "false", "this", "new", "int", "boolean", "String", "void", "return", "if", "else", "while", "for", "do", "class", "extends", "static", "[", "]", "(", ")", "{", "}", ";", ",", ".", "?", ":", "=", "==", "!", "&", "&&", "|", "||", "<", ">", "+", "-", "*", "/"};

    public static void main(String[] args) throws IOException {
        MJLexer lex = new MJLexer(new FileReader(args[0]));
        int indentLevel = 0;
        Symbol one;
        Symbol two;

        for (one = lex.next_token(); one.sym != sym.EOF; one = two) {
            two = lex.next_token();
            switch (one.sym) {
                case sym.EQ:
                case sym.EQEQ:
                case sym.AND:
                case sym.ANDAND:
                case sym.OR:
                case sym.OROR:
                case sym.LESS_THAN:
                case sym.GREATER_THAN:
                case sym.PLUS:
                case sym.MINUS:
                case sym.TIMES:
                case sym.DIVIDE:
                    print(" " + literals[one.sym] + " ");
                    break;
                case sym.ID:
                case sym.INT:
                case sym.COMMENT:
                    print(one.value.toString());
                    break;
                case sym.SEMICOLON:
                    print(literals[one.sym] + "\n");
                    printTabs(indentLevel);
                    break;
                case sym.LEFT_CURLY:
                    print(" " + literals[one.sym] + "\n");
                    indentLevel++;
                    printTabs(indentLevel);
                    break;
                case sym.RIGHT_CURLY:
                    indentLevel--;
                    print("\n");
                    printTabs(indentLevel);
                    print(literals[one.sym] + "\n");
                    printTabs(indentLevel);
                    break;
                case sym.INT_TYPE:
                case sym.STRING_TYPE:
                case sym.BOOLEAN_TYPE:
                    if (two.sym == sym.LEFT_SQUARE_BRACKET) {
                        print(literals[one.sym]);
                        break;
                    }
                case sym.CLASS:
                case sym.PUBLIC:
                case sym.PRIVATE:
                case sym.STATIC:
                case sym.VOID_TYPE:
                case sym.NEW:
                case sym.IF:
                case sym.WHILE:
                case sym.DO:
                case sym.FOR:
                    print(literals[one.sym] + " ");
                    break;
                default:
                    print(literals[one.sym]);
            }
        }
    }

    /** Abstracted printing, to save keystrokes and simply change to file printing. */
    private static void print(String s) {
        System.out.print(s);
    }

    /** Prints <code>n</code> tabs. */
    private static void printTabs(int n) {
        while (n > 0) {
            print("\t");
            n--;
        }
    }

    /**
     * Turns the raw text of a comment into a list of "words",
     * splitting the string into tokens around whitespace,
     * and drops the first tokens, which are the comment beginning and end.
     */
    private static String[] parseComment(String comment) {
        String[] basic = comment.split("[ \n\r\t]+");
        return Arrays.copyOfRange(basic, 1, basic.length-1);
    }
}