
package semantic;

import org.junit.*;
import syntaxtree.*;
import frontend.Parser;
import frontend.Position;
import java.io.File;

public class TestTypeCheckVisitor {

    @Test
    public void testFactorial() throws Exception {
        Parser parser = new Parser(new File("src/test/etc/Factorial.java"));
        Program prog = parser.getProgram();
        SymbolTableVisitor vis = new SymbolTableVisitor(parser);
        vis.visit(prog);
        TypeCheckVisitor check = new TypeCheckVisitor(parser, vis.table, prog);
        Assert.assertFalse(check.compileError());
        
        // Fac.ComputeFac.statement[1](If).elseBranch(Assign).expr(Times)
        // will be of type SymbolType.INT
        ClassDeclSimple fac = (ClassDeclSimple) prog.cl.elementAt(0);
        If iffy = (If) fac.ml.elementAt(0).sl.elementAt(0);
        Times times = (Times) ((Assign) iffy.s2).e;
        //Assert.assertEquals(SymbolType.INT, check.typeCheck.get(times));
    }
}