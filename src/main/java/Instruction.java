

public class Instruction {
    private final int type;
    private final int rs;
    private final int rt;
    private final int rd;

    public Instruction(int type, int rs, int rt, int rd) {
        this.type = type;
        this.rs = rs;
        this.rt = rt;
        this.rd = rd;
    }

    public String toString() {
        return "add $r0, $r1, $r2";
    }
}