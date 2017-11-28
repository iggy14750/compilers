
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
}