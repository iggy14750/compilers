
package semantic;

public enum Symbol {
    MAIN_CLASS,
    CLASS,
    METHOD,
    VARIABLE,
    PARAM;

    private MethodSignature sig;
    private SymbolType type;
    private int paramPosition;

    public Symbol setVariableType(SymbolType t) {
        if (this != Symbol.VARIABLE && this != Symbol.PARAM) {
            throw new RuntimeException(
                "Cannot assign varible type to Symbol " + this
            );
        }
        type = t;
        return this;
    }

    public SymbolType getVariableType() {
        if (this != Symbol.VARIABLE && this != Symbol.PARAM) {
            throw new RuntimeException(
                "Cannot get variable type from Symbol " + this
            );
        }
        return type;
    }

    public Symbol setParamPosition(int pos) {
        if (this != Symbol.PARAM) {
            throw new RuntimeException(
                "Cannot assign parameter position from Symbol " + this
            );
        }
        paramPosition = pos;
        return this;
    }

    public int getParamPosition() {
        if (this != Symbol.PARAM) {
            throw new RuntimeException(
                "Cannot get parameter position from Symbol " + this
            );
        }
        return paramPosition;
    }

    public Symbol setMethodSignature(MethodSignature s) {
        if (this != Symbol.METHOD) {
            throw new RuntimeException(
                "Cannot assign method signature to Symbol " + this
            );
        }
        sig = s;
        return this;
    }

    public MethodSignature getMethodSignature() {
        if (this != Symbol.METHOD) {
            throw new RuntimeException(
                "Cannot get method signature from Symbol " + this
            );
        }
        return sig;
    }
}