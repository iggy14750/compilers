

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
        // opcode 6 | rs 5 | rt 5 | rd 5 | shamt 5 | funct 6
        int o = (Opcode.value[this.opcode] & 0x3f) << 26;
        int rs = (this.rs & 0x1f) << 20;
        int rt = (this.rt & 0x1f) << 15;
        int rd = (this.rd & 0x1f) << 10;
        int funct = (Opcode.func[this.opcode] & 0x3f);
        int result = o | rs | rt | rd | funct;
        return String.format("0x%08x", result);
    }
}