
package codegen;

public class Instruction {

    public static String li(String reg, String imm) {
        return String.format("li $%s, %s", reg, imm);
    }

    public static String jal(String label) {
        return String.format("jal %s", label);
    }

    public static String move(String reg1, String reg2) {
        return String.format("move $%s, $%s", reg1, reg2);
    }
}