
package semantic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import syntaxtree.*;

public class TestSymbolTable {

    SymbolTable st = new SymbolTable();

    @Test
    public void insertNothing() {
        Assert.assertNull(st.get("example"));
    }

    @Test
    public void insertSomething() {
        st.put("example", Symbol.CLASS);
        Assert.assertEquals(Symbol.CLASS, st.get("example"));
    }

    @Test
    public void insertVariable() {
        st.put("example", Symbol.VARIABLE.setVariableType(Type.IDENTIFIER.setIdentifier("id")));
        Symbol s = st.get("example");
        Assert.assertEquals(Symbol.VARIABLE, s);
        Assert.assertEquals(Type.IDENTIFIER, s.getVariableType());
        Assert.assertEquals("id", s.getVariableType().getIdentifier());
    }

    @Test
    public void twoLevels() {
        SymbolTable table = st.newScope();
        st.put("zero", Symbol.VARIABLE);
        table.put("one", Symbol.CLASS);
        
        Assert.assertEquals(Symbol.CLASS, table.get("one"));
        Assert.assertEquals(Symbol.VARIABLE, table.get("zero"));
        Assert.assertNull(table.get("two"));
    }

    @Test
    public void treeCantSeeSiblingsSymbols() {
        SymbolTable left = st.newScope();
        SymbolTable right = st.newScope();
        left.put("left", Symbol.METHOD);
        Assert.assertNull(right.get("left"));
    }
}