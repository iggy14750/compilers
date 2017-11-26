
import frontend.Lexer;
import frontend.Parser;
import frontend.sym;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;

import java_cup.runtime.Symbol;
import syntaxtree.*;
import visitor.DepthFirstVisitor;


public class TestRunner {

    public static void main(String[] args) throws Exception {
        new TestMain();
    }

    public static void assertInstance(Object o, Class c) {
        assert c.isAssignableFrom(o.getClass()): o.getClass().getName();
    }

    public static void printTokens(String str) throws Exception {
        for (Symbol t: new SymbolIterator(new Lexer(new StringReader(str)))) {
            System.out.println(sym.terminalNames[t.sym]);
        }
    }

    public static void printTokens(File file) throws Exception {
        for (Symbol s: new SymbolIterator(new Lexer(new FileReader(file)))) {
            System.out.println(sym.terminalNames[t.sym]);
        }
    }
}

class SymbolIterator implements Iterator<Symbol>, Iterable<Symbol> {
    private Lexer l;
    private Symbol next;
    public SymbolIterator(Lexer lexer) throws IOException {
        l = lexer;
        next = l.next_token();
    }

    public Iterator<Symbol> iterator() {
        return this;
    }

    public boolean hasNext() {
        return next.sym != sym.EOF;
    }

    public Symbol next() {
        Symbol ret = next;
        try {
            next = l.next_token();
        } catch (IOException e) {
            return null;
        }
        return ret;
    }
}

class TestMain {
    public TestMain() throws Exception {
        basic();
        fact();
        binsearch();
        bubble();
        visit();
        qsort();
        linear();
        linked();
        bintree();
    }

    private static void bintree() throws Exception {
        File f = new File("src/test/etc/BinaryTree.java");
        Object o = Parser.parse(f);
        TestRunner.assertInstance(o, Program.class);
        Program p = (Program) o;
        DepthFirstVisitor v = new DepthFirstVisitor();
        v.visit(p);
    }

    private static void linked() throws Exception {
        File f = new File("src/test/etc/LinkedList.java");
        Object o = Parser.parse(f);
        TestRunner.assertInstance(o, Program.class);
        Program p = (Program) o;
        DepthFirstVisitor v = new DepthFirstVisitor();
        v.visit(p);
    }

    private static void linear() throws Exception {
        File f = new File("src/test/etc/LinearSearch.java");
        Object o = Parser.parse(f);
        TestRunner.assertInstance(o, Program.class);
        Program p = (Program) o;
        DepthFirstVisitor v = new DepthFirstVisitor();
        v.visit(p);
    }

    private static void qsort() throws Exception {
        File f = new File("src/test/etc/QuickSort.java");
        Object o = Parser.parse(f);
        TestRunner.assertInstance(o, Program.class);
        Program p = (Program) o;
        DepthFirstVisitor v = new DepthFirstVisitor();
        v.visit(p);
    }

    private static void visit() throws Exception {
        File f = new File("src/test/etc/TreeVisitor.java");
        Object o = Parser.parse(f);
        TestRunner.assertInstance(o, Program.class);
        Program p = (Program) o;
        DepthFirstVisitor v = new DepthFirstVisitor();
        v.visit(p);
    }

    private static void bubble() throws Exception {
        File f = new File("src/test/etc/BubbleSort.java");
        Object o = Parser.parse(f);
        TestRunner.assertInstance(o, Program.class);
        Program p = (Program) o;
        DepthFirstVisitor v = new DepthFirstVisitor();
        v.visit(p);
    }

    private static void binsearch() throws Exception {
        File f = new File("src/test/etc/BinarySearch.java");
        Object o = Parser.parse(f);
        TestRunner.assertInstance(o, Program.class);
        Program p = (Program) o;
        DepthFirstVisitor v = new DepthFirstVisitor();
        v.visit(p);
    }

    private static void fact() throws Exception {
        File f = new File("src/test/etc/Factorial.java");
        Object o = Parser.parse(f);
        TestRunner.assertInstance(o, Program.class);
        Program p = (Program) o;
        DepthFirstVisitor v = new DepthFirstVisitor();
        v.visit(p);
    }

    private static void basic() throws Exception {
        String basic = "class Main {" +
            "public static void main(String[] args) {" +
            "System.out.println(helloworld);" +
        "}}";
        Object o = Parser.parse(basic);
        TestRunner.assertInstance(o, Program.class);
        Program p = (Program) o;
        DepthFirstVisitor v = new DepthFirstVisitor();
        v.visit(p);
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