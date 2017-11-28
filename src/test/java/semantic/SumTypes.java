
package semantic;

import org.junit.*;

public class SumTypes {

    @Test
    public void type_INT() {
        Type t = Type.INT;
        try {
            t.setIdentifier("example");
            t.getIdentifier();
            Assert.fail("Should not be able to assign or get identifier from int type");
        } catch (RuntimeException re) {
            Assert.assertTrue(true);
        }
        Assert.assertEquals(Type.INT, t);
    }

    @Test
    public void type_INT_ARRAY() {
        Type t = Type.INT_ARRAY;
        try {
            t.setIdentifier("example");
            t.getIdentifier();
            Assert.fail("Should not be able to assign or get identifier from int array type");
        } catch (RuntimeException re) {
            Assert.assertTrue(true);
        }
        Assert.assertEquals(Type.INT_ARRAY, t);
    }

    @Test
    public void type_BOOLEAN() {
        Type t = Type.BOOLEAN;
        try {
            t.setIdentifier("example");
            t.getIdentifier();
            Assert.fail("Should not be able to assign or get identifier from boolean type");
        } catch (RuntimeException re) {
            Assert.assertTrue(true);
        }
        Assert.assertEquals(Type.BOOLEAN, t);
    }

    @Test
    public void type_ID() {
        Type t = Type.IDENTIFIER;
        t.setIdentifier("example");
        Assert.assertEquals("example", t.getIdentifier());
        Assert.assertEquals(Type.IDENTIFIER, t);
    }

    @Test
    public void symbol_CLASS() {
        Symbol s = Symbol.CLASS;
        try {
            s.setVariableType(null);
            Assert.fail("Cannot set variable type to Symbol CLASS");
        } catch (RuntimeException re) {}
        try {
            s.getVariableType();
            Assert.fail("Cannot get variable type from Symbol CLASS");
        } catch (RuntimeException re) {}
        try {
            s.setMethodSignature(null);
            Assert.fail("Cannot get method signature to Symbol CLASS");
        } catch (RuntimeException re) {}
        try {
            s.getMethodSignature();
            Assert.fail("Cannot get method signature from Symbol CLASS");
        } catch (RuntimeException re) {}
        Assert.assertEquals(Symbol.CLASS, s);
    }

    @Test
    public void symbol_METHOD() {
        Symbol s = Symbol.METHOD;
        Assert.assertEquals(Symbol.METHOD, s);

        s.setMethodSignature(new MethodSignature(Type.INT, new Type[] {}));
        Assert.assertEquals(Type.INT, s.getMethodSignature().returnType);
        Assert.assertArrayEquals(new Type[] {}, s.getMethodSignature().params);
        
        try {
            s.setVariableType(null);
            Assert.fail("Cannot set varible type to Symbol METHOD");
        } catch (RuntimeException re) {}
        try {
            s.getVariableType();
            Assert.fail("Cannot get variable type from Symbol METHOD");
        } catch (RuntimeException re) {}
    }

    @Test
    public void symbol_VARIABLE() {
        Symbol s = Symbol.VARIABLE;
        Assert.assertEquals(Symbol.VARIABLE, s);
        
        s.setVariableType(Type.BOOLEAN);
        Assert.assertEquals(Type.BOOLEAN, s.getVariableType());
        
        try {
            s.setMethodSignature(null);
            Assert.fail("Cannot set method signature to Symbol VARIABLE");
        } catch (RuntimeException re) {}
        try {
            s.getMethodSignature();
            Assert.fail("Cannot get method signature from Symbol VARIABLE");
        } catch (RuntimeException re) {}
    }
}