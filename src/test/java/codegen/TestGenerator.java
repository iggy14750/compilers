
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
        assertEquals("jal _system_out_println\njal _system_exit", mips.get(2));
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
            "jal _system_out_println\njal _system_exit"
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
        irCode.add(Quad.call("t2", "Test2.Start", "2"));
        irCode.add(Quad.param("t2"));
        irCode.add(Quad.call("t3", "_system_out_println", "1"));
        irCode.add(Quad.label("Test2.Start"));
        irCode.add(Quad.ret("y"));
        
        // ...not to mention the symbol table we want.
        SymbolTable symbolTable = new SymbolTable();
        symbolTable.put("Test", Symbol.CLASS);
        SymbolTable clss = symbolTable.put("Test2", Symbol.CLASS);
        SymbolTable method = clss.put("Start", Symbol.METHOD);
        method.put("y", Symbol.PARAM.setVariableType(SymbolType.INT).setParamPosition(0));

        // And see that it creates reasonable assembly
        List<String> output = gen.gen(irCode, symbolTable);

        String[] expected = new String[] {
            "",
            "li $t1, 9",
            "move $a0, $t0",
            "move $a1, $t1",
            "jal Test2_Start\nmove $t2, $v0",
            "move $a0, $t2",
            "jal _system_out_println\njal _system_exit",
            "Test2_Start:",
            "move $v0, $a1\njr $ra",
        };

        for (int i = 0; i < output.size(); i++) {
            assertEquals(expected[i], output.get(i));
        }
    }

    @Test
    public void setTableShouldSet() {
        SymbolTable symbolTable = new SymbolTable();
        symbolTable.put("Test", Symbol.CLASS);
        SymbolTable clss = symbolTable.put("Test2", Symbol.CLASS);
        SymbolTable method = clss.put("Start", Symbol.METHOD);
        method.put("y", Symbol.PARAM.setVariableType(SymbolType.INT).setParamPosition(0));

        assertNotNull(symbolTable.getChildScope("Test2").getChildScope("Start"));

        gen.set(symbolTable);

        assertTrue(gen.setTable("Test2.Start"));
    }

    @Test
    public void goodLabelNoDots() {
        assertEquals("label", Instruction.goodLabel("label"));
    }

    @Test
    public void goodLabelOneDot() {
        assertEquals("one_two", Instruction.goodLabel("one.two"));
    }

    private void print(List<String> list) {
        for (String s: list) {
            System.err.println(s);
        }
        fail();
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