
package codegen;

public class Instruction {

    public static String add(String res, String op1, String op2) {
        return String.format("add $%s, $%s, $%s", res, op1, op2);
    }

    public static String sub(String res, String op1, String op2) {
        return String.format("sub $%s, $%s, $%s");
    }

    public static String mult(String op1, String op2) {
        return String.format("mult $%s, $%s", op1, op2);
    }

    public static String mflo(String res) {
        return String.format("mflo $%s", res);
    }

    public static String and(String res, String op1, String op2) {
        return String.format("and $%s, $%s, $%s", res, op1, op2);
    }

    public static String slt(String res, String op1, String op2) {
        return String.format("slt $%s, $%s, $%s", res, op1, op2);
    }

    public static String not(String res, String op1) {
        return String.format("not $%s, $%s", res, op1);
    }

    public static String jump(String label) {
        return String.format("jump %s", goodLabel(label));
    }

    public static String jr(String reg) {
        return String.format("jr $%s", reg);
    }

    public static String beqz(String op1, String label) {
        return String.format("beqz $%s, %s", op1, goodLabel(label));
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
        return goodLabel(label) + ":";
    }

    static String goodLabel(String label) {
        String[] parts = label.split("\\.");
        if (parts.length >= 2) {
            return parts[0] + "_" + parts[1];
        }
        return label;
    }
}