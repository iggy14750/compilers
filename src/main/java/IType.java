

public class IType implements Instruction {
    private final int opcode;
    private final int rs;
    private final int rt;
    private final int imm;

    public IType(int op, int rs, int rt, int imm) {
        this.opcode = op;
        this.rs = rs;
        this.rt = rt;
        this.imm = imm;
    }

    public int opCode() {
        return this.opcode;
    }

    public String toString() {
        return String.format("%s $r%d, $r%d, %d", 
        MipsLex.operators[opcode], rs, rt, imm);
    }

    public String pack() {
        return "0xFACEBEEF";
    }
}