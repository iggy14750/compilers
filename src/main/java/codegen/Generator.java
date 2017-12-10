
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
            case PLUS:
                return Instruction.add(ir.result, ir.operand1, ir.operand2);
            case PARAM:
                return Instruction.move("a0", ir.operand1);
            case COPY:
                if (isInt(ir.operand1))
                    return Instruction.li(ir.result, ir.operand1);
                else
                    return Instruction.move(ir.result, ir.operand1);
            case CALL:
                return Instruction.jal(ir.operand1);
        }
        return null;
    }

    private boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}