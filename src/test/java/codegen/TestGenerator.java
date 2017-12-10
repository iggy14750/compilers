
package codegen;

import java.util.List;
import java.util.ArrayList;

import org.junit.*;
import static org.junit.Assert.*;

import semantic.*;

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
}