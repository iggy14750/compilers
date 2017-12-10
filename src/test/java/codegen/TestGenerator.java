
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
        irCode.add(Quad.param("9"));
        irCode.add(Quad.call("t0", "_system_out_println", "1"));
        List<String> mips = gen.gen(irCode);
        assertEquals("li $a0, 9", mips.get(0));
        assertEquals("jal _system_out_println", mips.get(1));
    }
}