

public class IType implements Instruction {
    private final int opcode;
    private final int rs;
    private final int rt;
    private final int imm;

    public IType(int op, int rs, int rt, int imm) throws SyntaxException {
        this.opcode = op;
        this.rs = rs;
        this.rt = rt;
        this.imm = imm;
        if (Opcode.type[opcode] != OpType.IMMEDIATE) {
            throw new SyntaxException("Invalid use of pnemonic <" + Opcode.name[opcode] + "> as I-Type instruction");
        }
    }

    public int opCode() {
        return this.opcode;
    }

    public String toString() {
        return String.format("%s $r%d, $r%d, %d", 
        Opcode.name[opcode], rs, rt, imm);
    }

    public String pack() {
        // opcode 6 | rs 5 | rt 5 | immediate 16
        int o = this.opcode & 0x3f;
        o <<= 26;
        int rs = this.rs & 0x1f;
        rs <<= 20;
        int rt = this.rt & 0x1f;
        rt <<= 15;
        int imm = this.imm & 0xff;
        int result = o | rs | rt | imm;
        return "0x" + Integer.toHexString(result);
    }
}