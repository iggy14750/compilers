

public class RType implements Instruction {
    
    private final int opcode;
    private final int rs;
    private final int rt;
    private final int rd;

    public RType(int op, int rd, int rs, int rt) throws SyntaxException {
        this.opcode = op;
        this.rs = rs;
        this.rt = rt;
        this.rd = rd;
        if (Opcode.type[opcode] != OpType.REGISTER) {
            throw new SyntaxException("Invalid use of pnemonic <" + Opcode.name[opcode] + "> as R-Type instruction");
        }
    }

    public int opCode() {
        return this.opcode;
    }

    public String toString() {
        if (opcode == Opcode.SYSCALL) {
            return Opcode.name[opcode];
        }
        return String.format("%s $r%d, $r%d, $r%d", 
        Opcode.name[opcode], rd, rs, rt);
    }

    public String pack() {
        return "0xFACEBEEF";
    }
}