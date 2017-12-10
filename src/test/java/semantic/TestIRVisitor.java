
package semantic;

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

    @Test
    public void complexExpression() {
        // 2 * i < n + t
        Exp e = new LessThan(
            new Times(
                new IntegerLiteral(2),
                new IdentifierExp("i")
            ),
            new Plus(
                new IdentifierExp("n"),
                new IdentifierExp("t")
            )
        );
        e.accept(v);

        String[] expected = new String[] {
            "t0 := 2 * i",
            "t1 := n + t",
            "t2 := t0 < t1"
        };

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], v.getCode().get(i).toString());
        }
    }
}