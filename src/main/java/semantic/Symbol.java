
package semantic;

public enum Symbol {
    CLASS,
    METHOD,
    VARIABLE;

    private MethodSignature sig;
    private Type type;

    public void setVariableType(Type t) {
        if (this != Symbol.VARIABLE) {
            throw new RuntimeException(
                "Cannot assign varible type to Symbol " + this
            );
        }
        type = t;
    }

    public Type getVariableType() {
        if (this != Symbol.VARIABLE) {
            throw new RuntimeException(
                "Cannot get Variable type from Symbol " + this
            );
        }
        return type;
    }

    public void setMethodSignature(MethodSignature s) {
        if (this != Symbol.METHOD) {
            throw new RuntimeException(
                "Cannot assign method signature to Symbol " + this
            );
        }
        sig = s;
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