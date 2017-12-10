
package codegen;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import semantic.Quad;
import semantic.Operation;

public class Generator {

    public List<String> gen(List<Quad> code) {
        List<String> res = new ArrayList<String>();
        for (Quad ir: code) {
            res.add(convert(ir));
        }
        return res;
    }

    private String convert(Quad ir) {
        switch (ir.op) {
            case PARAM:
                return Instruction.li("a0", ir.operand1);
            case CALL:
                return Instruction.jal(ir.operand1);
        }
        return null;
    }
}