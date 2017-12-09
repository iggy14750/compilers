
package semantic;

public enum Operation {
    PLUS,
    MINUS,
    TIMES,
    AND,
    LESS_THAN,
    NOT,
    COPY,
    GOTO,
    IFFALSE,
    PARAM,
    CALL,
    RETURN,
    INDEX_FROM,
    INDEX_TO,
    NEW_ARRAY,
    NEW_OBJECT,
    LENGTH,
    LABEL;

    public String toString() {
        switch (this) {
            case PLUS:      return "+";
            case MINUS:     return "-";
            case TIMES:     return "*";
            case AND:       return "&&";
            case LESS_THAN: return "<";
            default:        return "";
        }
    }
}