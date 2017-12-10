
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
            "t0 := 2",
            "t1 := t0 * i",
            "t2 := ~t",
            "t3 := n + t2",
            "t4 := t1 < t3",
            "t5 := arr.length",
            "t6 := 6",
            "param this",
            "param t6",
            "t7 := call func, 2",
            "t8 := t5 * t7",
            "t9 := t4 && t8"
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
            "t0 := 1",
            "t1 := x + t0",
            "x := t1",
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
            "t0 := 9",
            "param t0",
            "t1 := call _system_out_println, 1"
        };
        
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], v.getCode().get(i).toString());
        }
    }

    @Test
    public void functions() {
        ClassDeclSimple c = new ClassDeclSimple(
            new Identifier("Test2"),
            new VarDeclList(),
            listify(new MethodDecl[] {
                new MethodDecl(
                    new IntegerType(),
                    new Identifier("Start"),
                    listify(new Formal[] {
                        new Formal(
                            new IntegerType(),
                            new Identifier("y")
                        )
                    }),
                    new VarDeclList(),
                    new StatementList(),
                    new IdentifierExp("y")
                )
            })
        );
        c.accept(v);
        String[] expected = new String[] {
            "Start:",
            "return y"
        };
        for (int i = 0; i < v.getCode().size(); i++) {
            assertEquals(expected[i], v.getCode().get(i).toString());
        }
    }

    @Test
    public void thirdMilestone() {
        /* class Test {
            public static void main(String[] args) {
                System.out.println(new Test2().Start(9));
            }
        }
        class Test2 {
            public int Start(int y) {
                return y;
            }
        }*/
        Program p = new Program(
            new MainClass(
                new Identifier("Test"),
                new Identifier("args"),
                new Print(
                    new Call(
                        new NewObject(new Identifier("Test2")),
                        new Identifier("Start"),
                        listify(new Exp[] {
                            new IntegerLiteral(9)
                        })
                    )
                )
            ),
            listify(new ClassDecl[] {
                new ClassDeclSimple(
                    new Identifier("Test2"),
                    new VarDeclList(),
                    listify(new MethodDecl[] {
                        new MethodDecl(
                            new IntegerType(),
                            new Identifier("Start"),
                            listify(new Formal[] {
                                new Formal(
                                    new IntegerType(),
                                    new Identifier("y")
                                )
                            }),
                            new VarDeclList(),
                            new StatementList(),
                            new IdentifierExp("y")
                        )
                    })
                )
            })
        );
        p.accept(v);
        String[] expected = new String[] {
            "t0 := new Test2",
            "t1 := 9",
            "param t0",
            "param t1",
            "t2 := call Start, 2",
            "param t2",
            "t3 := call _system_out_println, 1",
            "Start:",
            "return y",
        };

        for (int i = 0; i < v.getCode().size(); i++) {
            assertEquals(expected[i], v.getCode().get(i).toString());
        }
    }

    private void print(List<Quad> code) {
        if (code.size() == 0)
            System.err.println("no code!");
        for (Quad inst: code) {
            System.err.println(inst);
        }
        fail();
    }

    private FormalList listify(Formal[] array) {
        FormalList fl = new FormalList();
        for (Formal f: array) {
            fl.addElement(f);
        }
        return fl;
    }

    private MethodDeclList listify(MethodDecl[] array) {
        MethodDeclList ml = new MethodDeclList();
        for (MethodDecl m: array) {
            ml.addElement(m);
        }
        return ml;
    }

    private ExpList listify(Exp[] array) {
        ExpList el = new ExpList();
        for (Exp e: array) {
            el.addElement(e);
        }
        return el;
    }

    private StatementList listify(Statement[] array) {
        StatementList sl = new StatementList();
        for (Statement s: array) {
            sl.addElement(s);
        }
        return sl;
    }

    private ClassDeclList listify(ClassDecl[] array) {
        ClassDeclList cl = new ClassDeclList();
        for (ClassDecl c: array) {
            cl.addElement(c);
        }
        return cl;
    }
}