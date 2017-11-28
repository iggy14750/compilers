
package semantic;

public enum SymbolType {
    INT,
    INT_ARRAY,
    BOOLEAN,
    STRING_ARRAY,
    VOID,
    IDENTIFIER;

    private String id;

    public SymbolType setIdentifier(String s) {
        if (this != SymbolType.IDENTIFIER) {
            throw new RuntimeException(
                "Cannot assign identifier to SymbolType " + this
            );
        }
        id = s;
        return this;
    }

    public String getIdentifier() {
        if (this != SymbolType.IDENTIFIER) {
            throw new RuntimeException(
                "Cannot get identifier from SymbolType " + this
            );
        }
        return id;
    }
}