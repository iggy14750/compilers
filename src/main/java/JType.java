

public class JType implements Instruction {
    private final int opcode;
    private final int imm;

    public JType(int op, int imm) throws SyntaxException {
        this.opcode = op;
        this.imm = imm;
        if (Opcode.type[opcode] != OpType.JUMP) {
            throw new SyntaxException("Invalid use of pnemonic <" + Opcode.name[opcode] + "> as J-Type instruction");
        }
    }

    public String toString() {
        return String.format("%s %d", 
        Opcode.name[this.opcode], this.imm);
    }

    public String pack() {
        return "0xFACEBEEF";
    }

    public int opCode() {
        return this.opcode;
    }
}