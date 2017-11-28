
package semantic;

public class MethodSignature {
    public final SymbolType returnType;
    public final SymbolType[] params;

    public MethodSignature(SymbolType r, SymbolType[] p) {
        returnType = r;
        params = p;
    }
}