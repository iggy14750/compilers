
package semantic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import syntaxtree.*;

public class TestSymbolTable {

    SymbolTable st = new SymbolTable();

    @Test
    public void insertNothing() {
        Assert.assertNull(st.getSymbol("example"));
    }

    @Test
    public void insertVariableNoNewScope() {
        st.put("first", Symbol.VARIABLE);
        Assert.assertNull(st.getChildScope("first"));
    }

    @Test
    public void insertVariableGetItBack() {
        st.put("first", Symbol.VARIABLE.setVariableType(Type.INT));
        Assert.assertEquals(Symbol.VARIABLE, st.getSymbol("first"));
        Assert.assertEquals(Type.INT, st.getSymbol("first").getVariableType());
    }
}