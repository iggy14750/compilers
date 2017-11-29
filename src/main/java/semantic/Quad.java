
package semantic;

public class Quad {
    
    public final Operation op;
    public final String result;
    public final String operand1;
    public final String operand2;

    public Quad(Operation o, String op1, String op2, String res) {
        op = o;
        operand1 = op1;
        operand2 = op2;
        result = res;
    }

    public String toString() {
        return String.format("%s := %s %s %s",
            result, operand1, op.toString(), operand2
        );
    }
}