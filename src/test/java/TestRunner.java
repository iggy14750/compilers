
import frontend.Lexer;
import frontend.Parser;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import syntaxtree.*;
import java_cup.runtime.Symbol;
import frontend.sym;

public class TestRunner {

    public static void main(String[] args) throws Exception {
        new TestMain();
    }

    public static void assertInstance(Object o, Class c) {
        assert c.isAssignableFrom(o.getClass()): o.getClass().getName();
    }

    public static void printTokens(String str) throws Exception {
        Lexer l = new Lexer(new StringReader(str));
        Symbol t = l.next_token();
        while (t.sym != sym.EOF) {
            System.out.println(sym.terminalNames[t.sym]);
            t = l.next_token();
        }
    }

    public static void printTokens(File file) throws Exception {
        Lexer l = new Lexer(new FileReader(file));
        Symbol t = l.next_token();
        while (t.sym != sym.EOF) {
            System.out.println(sym.terminalNames[t.sym]);
            t = l.next_token();
        }
    }

}

class TestMain {
    public TestMain() throws Exception {
        basic();
    }

    private static void basic() throws Exception {
        String basic = "class Main {" +
            "public static void main(String[] args) {" +
            "System.out.println(helloworld);" +
        "}}";
        TestRunner.assertInstance(Parser.parse(basic), Program.class);
    }
}

class TestMethod {
    public TestMethod() throws Exception {
        simple();
        factorial();
        qsort();
        construct();
        getter();
        justReturn();
        binSearch();
    }

    private static void binSearch() throws Exception {
        TestRunner.assertInstance(Parser.parse(new File("src/test/etc/bin_search_Start.java")), MethodDecl.class);
    }


    private static void justReturn() throws Exception {
        TestRunner.assertInstance(Parser.parse(new File("src/test/etc/justReturn.java")), MethodDecl.class);
    }

    private static void getter() throws Exception {
        TestRunner.assertInstance(Parser.parse(new File("src/test/etc/getter.java")), MethodDecl.class);
    }

    private static void construct() throws Exception {
        TestRunner.assertInstance(Parser.parse(new File("src/test/etc/constructor.java")), MethodDecl.class);
    }

    private static void qsort() throws Exception {
        TestRunner.assertInstance(Parser.parse(new File("src/test/etc/qsort_method.txt")), MethodDecl.class);
        TestRunner.assertInstance(Parser.parse(new File("src/test/etc/tree_del.java")), MethodDecl.class);
    }

    private static void factorial() throws Exception {
        String method = 
        "public int fact(int n) {\n" + 
            "int temp;int ret;\n" +
            "if (n < 2) ret = 1;\n" +
            "else {\n" +
                "temp = this.fact(n-1);\n" + 
                "ret = n * temp;\n"+
            "}\n" +
            "return ret;\n" +
        "}\n";
        TestRunner.assertInstance(Parser.parse(method), MethodDecl.class);
    }

    private static void simple() throws Exception {
        TestRunner.assertInstance(Parser.parse("public int add(int a, int b) { int c; c = a + b; return c; }"), MethodDecl.class);
    }
}

class TestFormal {
    public TestFormal() throws Exception {
        single();
        list();
    }

    private static void list() throws Exception {
        TestRunner.assertInstance(Parser.parse("int four, nothing ever"), FormalList.class);
    }

    private static void single() throws Exception {
        TestRunner.assertInstance(Parser.parse("int four"), FormalList.class);
    }
}

class TestDecl {
    public TestDecl() throws Exception {
        single();
        list();
    }

    private static void list() throws Exception {
        TestRunner.assertInstance(Parser.parse("int four; boolean twelvethousand;"), VarDeclList.class);
    }

    private static void single() throws Exception {
        TestRunner.assertInstance(Parser.parse("int four;"), VarDeclList.class);
    }
}

class TestType {
    public TestType() throws Exception {
        identify();
        intt();
        boolt();
        int_array_t();
    }

    private static void int_array_t() throws Exception {
        TestRunner.assertInstance(Parser.parse("int[]"), IntArrayType.class);
    }

    private static void boolt() throws Exception {
        TestRunner.assertInstance(Parser.parse("boolean"), BooleanType.class);
    }

    private static void intt() throws Exception {
        TestRunner.assertInstance(Parser.parse("int"), IntegerType.class);
    }

    private static void identify() throws Exception {
        TestRunner.assertInstance(Parser.parse("four"), IdentifierType.class);
    }
}

class TestStatementList {
    public TestStatementList() throws Exception {
        arrayAssign();
        assign();
        sysout();
        whilel();
        iff();
        block();
        empty();
    }

    private static void empty() throws Exception {
        TestRunner.assertInstance(Parser.parse(""), StatementList.class);
    }

    private static void block() throws Exception {
        TestRunner.assertInstance(Parser.parse("{System.out.println(3);}"), StatementList.class);
    }

    private static void iff() throws Exception {
        TestRunner.assertInstance(Parser.parse("if (blue < 42) System.out.println(wow); else blue = 42;"), StatementList.class);
    }

    private static void whilel() throws Exception {
        TestRunner.assertInstance(Parser.parse("while (32) a = d - !elephant;"), StatementList.class);
    }

    private static void sysout() throws Exception {
        TestRunner.assertInstance(Parser.parse("System.out.println(3 && this.opcode(true));"), StatementList.class);
    }

    private static void assign() throws Exception {
        TestRunner.assertInstance(Parser.parse("efwaef = weF24f[new Object()];"), StatementList.class);
    }

    private static void arrayAssign() throws Exception {
        TestRunner.assertInstance(Parser.parse("efwaef[34] = true;"), StatementList.class);
    }
}

class TestStatement {
    public TestStatement() throws Exception {
        arrayAssign();
        assign();
        sysout();
        whilel();
        iff();
        block();
    }

    private static void block() throws Exception {
        TestRunner.assertInstance(Parser.parse("{System.out.println(3);}"), Block.class);
    }

    private static void iff() throws Exception {
        TestRunner.assertInstance(Parser.parse("if (blue < 42) System.out.println(wow); else blue = 42;"), If.class);
    }

    private static void whilel() throws Exception {
        TestRunner.assertInstance(Parser.parse("while (32) a = d - !elephant;"), While.class);
    }

    private static void sysout() throws Exception {
        TestRunner.assertInstance(Parser.parse("System.out.println(3 && this.opcode(true));"), Print.class);
    }

    private static void assign() throws Exception {
        TestRunner.assertInstance(Parser.parse("efwaef = weF24f[new Object()];"), Assign.class);
    }

    private static void arrayAssign() throws Exception {
        TestRunner.assertInstance(Parser.parse("efwaef[34] = true;"), ArrayAssign.class);
    }
}

class TestExpr {

    public TestExpr() throws Exception {
        test_times();
        test_minus();
        test_plus();
        test_lessThan();
        test_and();
        test_ArrayLookup();
        test_ArrayLength();
        test_call();
        test_Integer();
        test_true();
        test_false();
        test_this();
        test_newArray();
        test_newObject();
        test_not();
        test_id();
        test_parens();
    }

    private static void test_times() throws Exception {
        assertInstance(Parser.parse("true * +24143"), Times.class);
    }

    private static void test_minus() throws Exception {
        assertInstance(Parser.parse("223 - r23r"), Minus.class);
    }

    private static void test_plus() throws Exception {
        assertInstance(Parser.parse("weF24f.length + !true"), Plus.class);
    }

    private static void test_lessThan() throws Exception {
        assertInstance(Parser.parse("23 < 0x2"), LessThan.class);
    }

    private static void test_and() throws Exception {
        assertInstance(Parser.parse("34 && true"), And.class);
    }

    private static void test_ArrayLookup() throws Exception {
        assertInstance(Parser.parse("weF24f[new Object()]"), ArrayLookup.class);
        assertInstance(Parser.parse("boi[0x34]"), ArrayLookup.class);
    }

    private static void test_ArrayLength() throws Exception {
        assertInstance(Parser.parse("weF24f.length"), ArrayLength.class);
    }

    private static void test_call() throws Exception {
        assertInstance(Parser.parse("this.no(23, wef, new int[2])"), Call.class);
    }

    private static void test_Integer() throws Exception {
        assertInstance(Parser.parse("24143"), IntegerLiteral.class);
        assertInstance(Parser.parse("0xfacebee"), IntegerLiteral.class);
    }

    private static void test_id() throws Exception {
        assertInstance(Parser.parse("wef23f"), IdentifierExp.class);
    }

    private static void test_this() throws Exception {
        assertInstance(Parser.parse("this"), This.class);
    }

    private static void test_newArray() throws Exception {
        assert (Parser.parse("new int[3]") instanceof NewArray);
    }

    private static void test_not() throws Exception {
        assert (Parser.parse("!false") instanceof Not);
    }

    private static void test_newObject() throws Exception {
        assert (Parser.parse("new String()") instanceof syntaxtree.NewObject);
    }

    private static void test_true() throws Exception {
        assert (Parser.parse("true") instanceof True);
    }

    private static void test_false() throws Exception {
        assert (Parser.parse("false") instanceof False);
    }

    private static void test_parens() throws Exception {
        assertInstance(Parser.parse("(2332)"), IntegerLiteral.class);
    }

    private static void assertInstance(Object o, Class c) {
        assert c.isAssignableFrom(o.getClass()): o.getClass().getName();
    }
}