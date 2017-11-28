
package semantic;

import org.junit.*;
import syntaxtree.*;

public class TestSymbolTableVisitor {

    private SymbolTableVisitor v = new SymbolTableVisitor();

    private Program prog = new Program(
        new MainClass(new Identifier("Main"), new Identifier("args"), new Print(new IntegerLiteral(5))),
        new ClassDeclList()
    );

    private ClassDeclSimple clss = new ClassDeclSimple(
        null, null, null // TODO
    );

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
        // TODO
    }

    @Test
    public void progWithNormalClass() {
        // TODO
    }

}