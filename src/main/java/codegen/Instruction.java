
package codegen;

public class Instruction {

    public static String add(String res, String op1, String op2) {
        return String.format("add $%s, $%s, $%s", res, op1, op2);
    }

    public static String li(String reg, String imm) {
        return String.format("li $%s, %s", reg, imm);
    }

    public static String jal(String label) {
        return String.format("jal %s", goodLabel(label));
    }

    public static String move(String reg1, String reg2) {
        return String.format("move $%s, $%s", reg1, reg2);
    }

    public static String label(String label) {
        return goodLabel(label);
    }

    static String goodLabel(String label) {
        String[] parts = label.split("\\.");
        if (parts.length >= 2) {
            return parts[0] + "_" + parts[1];
        }
        return label;
    }
}