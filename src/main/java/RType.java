

public class RType implements Instruction {
    
    private final int type;
    private final int rs;
    private final int rt;
    private final int rd;

    public RType(int type, int rd, int rs, int rt) {
        this.type = type;
        this.rs = rs;
        this.rt = rt;
        this.rd = rd;
    }

    public int opCode() {
        return this.type;
    }

    public String toString() {
        return String.format("%s $r%d, $r%d, $r%d", MipsLex.operators[type], rd, rs, rt);
    }

    public String pack() {
        return "0xFACEBEEF";
    }
}