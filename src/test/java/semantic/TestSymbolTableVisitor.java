
package semantic;

import frontend.Parser;
import frontend.Position;
import java.util.HashMap;
import java.io.File;
import org.junit.*;
import syntaxtree.*;

public class TestSymbolTableVisitor {

    private SymbolTableVisitor v = new SymbolTableVisitor();

    private Program prog = new Program(
        new MainClass(new Identifier("Main"), new Identifier("args"), new Print(new IntegerLiteral(5))),
        new ClassDeclList()
    );

    private ClassDeclSimple clss = new ClassDeclSimple(
        new Identifier("MyClass"),
        varDecls2(),
        myMethodList()
    );

    private VarDeclList varDecls2() {
        VarDeclList vdl = new VarDeclList();
        vdl.addElement(
            new VarDecl(new IntegerType(), new Identifier("n"))
        );
        return vdl;
    }

    private MethodDeclList myMethodList() {
        MethodDeclList mdl = new MethodDeclList();
        mdl.addElement(new MethodDecl(
            new IntegerType(),
            new Identifier("fact"),
            getFormalList(),
            getVarDecls(),
            null,
            null
        ));
        return mdl;
    }

    private FormalList getFormalList() {
        FormalList fl = new FormalList();
        fl.addElement(new Formal(new IntegerType(), new Identifier("n")));
        return fl;
    }

    private VarDeclList getVarDecls() {
        VarDeclList vdl = new VarDeclList();
        vdl.addElement(new VarDecl(new BooleanType(), new Identifier("isTrue")));
        return vdl;
    }

    @Test
    public void callWithNullThenStop() {
        v.visit((Program) null);
    }

    @Test
    public void givenProgram() {
        v.visit(prog);
        Assert.assertEquals(Symbol.MAIN_CLASS, v.table.getSymbol("Main"));
        SymbolTable clss = v.table.getChildScope("Main");
        Assert.assertEquals(Symbol.METHOD, clss.getSymbol("main"));
        Assert.assertEquals(SymbolType.VOID, clss.getSymbol("main").getMethodSignature().returnType);
        Assert.assertArrayEquals(
            new SymbolType[] {SymbolType.STRING_ARRAY},
            clss.getSymbol("main").getMethodSignature().params
        );
    }

    @Test
    public void normalClass() {
        clss.accept(v);

        Symbol method = v.table.getSymbol("fact");
        Assert.assertEquals(Symbol.METHOD, method);
        Assert.assertEquals(SymbolType.INT, method.getMethodSignature().returnType);
        Assert.assertArrayEquals(
            new SymbolType[] {SymbolType.INT},
            method.getMethodSignature().params
        );
        Assert.assertNotNull(v.table.getChildScope("fact"));
        // Check some things about the method
    }

    @Test
    public void progWithNormalClass() {
        // TODO
    }


    @Test
    public void parseAndBuildSymbolTable() throws Exception {
        File f = new File("src/test/etc/Factorial.java");
        Parser parser = new Parser(f);
        Program top = parser.getProgram();
        SymbolTableVisitor vis = new SymbolTableVisitor(parser);
        vis.visit(top);
        SymbolTable table = vis.table;
        
        table = table.getChildScope("Fac").getChildScope("ComputeFac");
        Assert.assertNotNull(table);
        Assert.assertEquals(Symbol.CLASS, table.getSymbol("Fac"));
        Assert.assertEquals(Symbol.METHOD, table.getSymbol("ComputeFac"));
        Assert.assertEquals(Symbol.PARAM, table.getSymbol("num"));
        Assert.assertEquals(Symbol.VARIABLE, table.getSymbol("num_aux"));
    }

    @Test 
    public void getQuickSort() throws Exception {
        Parser parser = new Parser(new File("src/test/etc/QuickSort.java"));
        Program prog = parser.getProgram();
        SymbolTableVisitor vis = new SymbolTableVisitor(parser);
        vis.visit(prog);

        SymbolTable table = vis.table.getChildScope("QS").getChildScope("Start");
        // The signature of QS.Start
        Symbol method = table.getSymbol("Start");
        Assert.assertEquals(SymbolType.INT, method.getMethodSignature().returnType);
        Assert.assertArrayEquals(
            new SymbolType[] {SymbolType.INT},
            method.getMethodSignature().params
        );
    }
}