
package semantic;

import java.util.List;

import org.junit.*;
import static org.junit.Assert.*;

import syntaxtree.*;
import visitor.Visitor;

public class TestIRVisitor {

    IrGenVisitor v = new IrGenVisitor();

    @Test
    public void iff() {
        If n = new If(
            new True(),
            new Assign(
                new Identifier("x"),
                new IdentifierExp("y")
            ),
            new Assign(
                new Identifier("a"),
                new IdentifierExp("b")
            )
        );
        v.visit(n);

        String[] expected = new String[] {
            "iffalse 1 goto L0",
            "x := y",
            "goto L1",
            "L0:",
            "a := b",
            "L1:"
        };
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], v.getCode().get(i).toString());
        }
    }

    private ExpList listify(Exp[] array) {
        ExpList el = new ExpList();
        for (Exp e: array) {
            el.addElement(e);
        }
        return el;
    }

    @Test
    public void complexExpression() {
        // (2 * i < n + ~t) && (arr.length * this.func(6))
        Exp e = new And(
            new LessThan(
                new Times(
                    new IntegerLiteral(2),
                    new IdentifierExp("i")
                ),
                new Plus(
                    new IdentifierExp("n"),
                    new Not(new IdentifierExp("t"))
                )
            ),
            new Times(
                new ArrayLength(
                    new IdentifierExp("arr")
                ),
                new Call(
                    new This(),
                    new Identifier("func"),
                    listify(new Exp[] {
                        new IntegerLiteral(6)
                    })
                )
            )
        );

        e.accept(v);

        String[] expected = new String[] {
            "t0 := 2 * i",
            "t1 := ~t",
            "t2 := n + t1",
            "t3 := t0 < t2",
            "t4 := arr.length",
            "param this",
            "param 6",
            "t5 := call func, 2",
            "t6 := t4 * t5",
            "t7 := t3 && t6"
        };

        for (int i = 0; i < v.getCode().size(); i++) {
            // System.err.println(v.getCode().get(i));
            assertEquals(expected[i], v.getCode().get(i).toString());
        }
    }

    @Test
    public void whileLoop() {
        // while (true) {
        //     x = x + 1;
        // }
        While w = new While(
            new True(),
            new Assign(
                new Identifier("x"),
                new Plus(
                    new IdentifierExp("x"),
                    new IntegerLiteral(1)
                )
            )
        );

        w.accept(v);

        String[] expected = new String[] {
            "L0:",
            "iffalse 1 goto L1",
            "t0 := x + 1",
            "x := t0",
            "goto L0",
            "L1:"
        };

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], v.getCode().get(i).toString());
        }
    }

    @Test
    public void simpleMain() {
        // class Test {
        //     public static void main(String[] args) {
        //         System.out.println(9);
        //     }
        // }
        MainClass mc = new MainClass(
            new Identifier("Test"),
            new Identifier("args"),
            new Print(new IntegerLiteral(9))
        );

        mc.accept(v);

        String[] expected = new String[] {
            "param 9",
            "t0 := call _system_out_println, 1"
        };
        
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], v.getCode().get(i).toString());
        }
    }

    private void print(List<Quad> code) {
        for (Quad inst: code) {
            System.err.println(inst);
        }
        fail();
    }
}