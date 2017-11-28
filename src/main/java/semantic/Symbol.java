
package semantic;

public enum Symbol {
    CLASS,
    METHOD,
    VARIABLE;

    private MethodSignature sig;
    private Type type;

    public Symbol setVariableType(Type t) {
        if (this != Symbol.VARIABLE) {
            throw new RuntimeException(
                "Cannot assign varible type to Symbol " + this
            );
        }
        type = t;
        return this;
    }

    public Type getVariableType() {
        if (this != Symbol.VARIABLE) {
            throw new RuntimeException(
                "Cannot get Variable type from Symbol " + this
            );
        }
        return type;
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