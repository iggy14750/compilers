
package semantic;

public enum Type {
    INT,
    INT_ARRAY,
    BOOLEAN,
    IDENTIFIER;

    private String id;

    public Type setIdentifier(String s) {
        if (this != Type.IDENTIFIER) {
            throw new RuntimeException(
                "Cannot assign identifier to Type " + this
            );
        }
        id = s;
        return this;
    }

    public String getIdentifier() {
        if (this != Type.IDENTIFIER) {
            throw new RuntimeException(
                "Cannot get identifier from Type " + this
            );
        }
        return id;
    }
}