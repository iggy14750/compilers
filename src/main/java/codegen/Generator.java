
package codegen;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import semantic.*;

public class Generator {

    private int paramNum;
    private SymbolTable root;
    private SymbolTable methodTable;

    public List<String> gen(List<Quad> code) {
        List<String> res = new ArrayList<String>();
        for (Quad ir: code) {
            res.add(convert(ir));
        }
        return res;
    }

    public List<String> gen(List<Quad> code, SymbolTable symbolTable) {
        root = symbolTable;
        return gen(code);
    }

    private String convert(Quad ir) {
        if (ir.op != Operation.PARAM) {
            paramNum = 0;
        }
        switch (ir.op) {
            case PLUS:
                return Instruction.add(reg(ir.result), reg(ir.operand1), reg(ir.operand2));
            case PARAM:
                paramNum++;
                return Instruction.move("a" + (paramNum - 1), reg(ir.operand1));
            case COPY:
                if (isInt(ir.operand1))
                    return Instruction.li(reg(ir.result), ir.operand1);
                else
                    return Instruction.move(reg(ir.result), reg(ir.operand1));
            case CALL:
                if (ir.operand1.equals("_system_out_println"))
                    return Instruction.jal("_system_out_println");
                return Instruction.jal(ir.operand1) + "\n" + 
                    Instruction.move(reg(ir.result), "v0");
            case LABEL:
                // change current symboltable to this method's
                setTable(ir.result);
                return Instruction.label(ir.result);
            case RETURN:
                return Instruction.move("v0", reg(ir.result));
            case NEW_OBJECT:
                return "";
        }
        return null;
    }

    void set(SymbolTable symbolTable) {
        root = symbolTable;
    }

    boolean setTable(String label) {
        if (root == null) return false;
        String[] parts = label.split("\\.");
        if (parts.length < 2) return false;

        String method = parts[1].substring(0, parts[1].length() - 1); // get rid of the colon
        methodTable = root.getChildScope(parts[0]).getChildScope(method);
        assert methodTable != null;
        return true;
    }

    String reg(String input) {
        if (methodTable == null) return input;
        Symbol s = methodTable.getSymbol(input);
        if (s == null) return input;
        switch (s) {
            case PARAM:
                return "a" + (s.getParamPosition() + 1);
            case VARIABLE:
                return input;
        }
        return String.format("INVALID<%s>", input);
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