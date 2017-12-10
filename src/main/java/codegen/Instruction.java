
package codegen;

public class Instruction {

    public static String li(String reg, String imm) {
        return String.format("li $%s, %s", reg, imm);
    }

    public static String jal(String label) {
        return String.format("jal %s", label);
    }
}