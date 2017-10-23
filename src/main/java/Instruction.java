

public class Instruction {
    private final int type;
    private final int rs;
    private final int rt;
    private final int rd;

    public Instruction(int type, int rd, int rs, int rt) {
        this.type = type;
        this.rs = rs;
        this.rt = rt;
        this.rd = rd;
    }

    public String toString() {
        return String.format("add $r%d, $r%d, $r%d", rd, rs, rt);
    }
}