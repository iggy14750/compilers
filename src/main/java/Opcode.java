

public class Opcode {
    public static final int ADD = 0;
    public static final int ADDI = 1;
    public static final int SUB = 2;
    public static final int SLT = 3;
    public static final int BEQ = 4;
    public static final int BNE = 5;
    public static final int SYSCALL = 6;
    public static final int LBU = 7;
    public static final int SB = 8;
    public static final int J = 9;
    public static final int JAL = 10;
    public static final int LUI = 11;
    public static final int AND = 12;
    public static final int ORI = 13;
    public static final int XOR = 14;

    public static final int[] value = new int[] {
        0, 0x8, 0x0, 0x0, 0x4, 0x5, 0x0, 0x24, 0x28, 0x2, 0x3, 0xf, 0, 0xd, 0
    };

    public static final int[] func = new int[] {
        0x20, -1, 0x22, 0x2a, -1, -1, 0xc, -1, -1, -1, -1, 0x8, -1, 0x24, -1, 0x26
    };

    public static final String[] name = new String[] {
        "add", "addi", "sub", "slt", "beq", "bne", "syscall",
        "lbu", "sb", "j", "jal", "lui", "and", "ori", "xor"
    };
}