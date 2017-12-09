
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

    public static Quad plus(String res, String op1, String op2) {
        return new Quad(Operation.PLUS, op1, op2, res);
    }

    public static Quad minus(String res, String op1, String op2) {
        return new Quad(Operation.MINUS, op1, op2, res);
    }

    public static Quad times(String res, String op1, String op2) {
        return new Quad(Operation.TIMES, op1, op2, res);
    }

    public static Quad and(String res, String op1, String op2) {
        return new Quad(Operation.AND, op1, op2, res);
    }

    public static Quad lessThan(String res, String op1, String op2) {
        return new Quad(Operation.LESS_THAN, op1, op2, res);
    }

    public static Quad not(String res, String op) {
        return new Quad(Operation.NOT, op, "", res);
    }

    public static Quad copy(String res, String op) {
        return new Quad(Operation.COPY, op, "", res);
    }

    public static Quad jump(String label) {
        return new Quad(Operation.GOTO, "", "", label);
    }

    public static Quad ifFalse(String cond, String label) {
        return new Quad(Operation.IFFALSE, cond, "", label);
    }

    public static Quad param(String op) {
        return new Quad(Operation.PARAM, op, "", "");
    }

    public static Quad call(String res, String func, String numParams) {
        return new Quad(Operation.CALL, func, numParams, res);
    }

    public static Quad ret(String res) {
        return new Quad(Operation.RETURN, "", "", res);
    }

    public static Quad indexFrom(String res, String array, String index) {
        return new Quad(Operation.INDEX_FROM, array, index, res);
    }

    public static Quad indexTo(String res, String index, String elem) {
        return new Quad(Operation.INDEX_TO, index, elem, res);
    }

    public static Quad newObject(String res, String type) {
        return new Quad(Operation.NEW_OBJECT, type, "", res);
    }

    public static Quad newArray(String res, String type, String number) {
        return new Quad(Operation.NEW_ARRAY, type, number, res);
    }

    public static Quad length(String res, String array) {
        return new Quad(Operation.LENGTH, array, "", res);
    }

    public String toString() {
        switch (op) {
            case PLUS:
            case MINUS:
            case TIMES:
            case AND:
            case LESS_THAN:
                return String.format("%s := %s %s %s",
                    result, operand1, op.toString(), operand2
                );
            case NOT:
                return String.format(
                    "%s := ~%s", result, operand1
                );
            case COPY:
                return String.format(
                    "%s := %s", result, operand1
                );
            case GOTO:
                return String.format(
                    "goto %s", result
                );
            case IFFALSE:
                return String.format(
                    "iffalse %s goto %s", operand1, result
                );
            case PARAM:
                return String.format(
                    "param %s", operand1
                );
            case CALL:
                return String.format(
                    "%s := call %s, %s", result, operand1, operand2
                );
            case RETURN:
                return String.format(
                    "return %s", result
                );
            case INDEX_FROM:
                return String.format(
                    "%s := %s[%s]", result, operand1, operand2
                );
            case INDEX_TO:
                return String.format(
                    "%s[%s] := %s", result, operand1, operand2
                );
            case NEW_ARRAY:
                return String.format(
                    "%s := new %s, %s", result, operand1, operand2
                );
            case NEW_OBJECT:
                return String.format(
                    "%s := new %s", result, operand1
                );
            case LENGTH:
                return String.format(
                    "%s := %s.length", result, operand1
                );
        }
        return "invalid";
    }
}