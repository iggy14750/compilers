
package codegen;

import java.util.List;
import java.util.ArrayList;

import org.junit.*;
import static org.junit.Assert.*;

import semantic.*;
import syntaxtree.*;

public class TestGenerator {

    Generator gen = new Generator();
    List<Quad> irCode = new ArrayList<Quad>();

    @Test
    public void firstMilestone() {
        irCode.add(Quad.copy("t0", "9"));
        irCode.add(Quad.param("t0"));
        irCode.add(Quad.call("t1", "_system_out_println", "1"));
        List<String> mips = gen.gen(irCode);
        assertEquals("li $t0, 9", mips.get(0));
        assertEquals("move $a0, $t0", mips.get(1));
        assertEquals("jal _system_out_println", mips.get(2));
    }

    @Test
    public void secondMilestone() {
        irCode.add(Quad.copy("t0", "5"));
        irCode.add(Quad.copy("t1", "4"));
        irCode.add(Quad.plus("t2", "t0", "t1"));
        irCode.add(Quad.param("t2"));
        irCode.add(Quad.call("t3", "_system_out_println", "1"));

        List<String> mips = gen.gen(irCode);
        
        String[] expected = new String[] {
            "li $t0, 5",
            "li $t1, 4",
            "add $t2, $t0, $t1",
            "move $a0, $t2",
            "jal _system_out_println"
        };

        for (int i = 0; i < mips.size(); i++) {
            assertEquals(expected[i], mips.get(i));
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
        // write out the IR we generate...
        /*"t0 := new Test2",
        "t1 := 9",
        "param t0",
        "param t1",
        "t2 := call Start, 2",
        "param t2",
        "t3 := call _system_out_println, 1",
        "Test2.Start:",
        "return y",*/
        irCode.add(Quad.newObject("t0", "Test2"));
        irCode.add(Quad.copy("t1", "9"));
        irCode.add(Quad.param("t0"));
        irCode.add(Quad.param("t1"));
        irCode.add(Quad.call("t2", "Start", "2"));
        irCode.add(Quad.param("t2"));
        irCode.add(Quad.call("t3", "_system_out_println", "1"));
        irCode.add(Quad.label("Test2.Start:"));
        irCode.add(Quad.ret("y"));
        // And see that it creates reasonable assembly
    }

    private List<Quad> make(Quad[] literal) {
        List<Quad> res = new ArrayList<Quad>();
        for (Quad elem: literal) {
            res.add(elem);
        }
        return res;
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
}