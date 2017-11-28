
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
    }

    @Test
    public void type_ID() {
        Type t = Type.IDENTIFIER;
        t.setIdentifier("example");
        Assert.assertEquals("example", t.getIdentifier());
    }
}