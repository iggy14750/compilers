
package semantic;

public class MethodSignature {
    public final Type returnType;
    public final Type[] params;

    public MethodSignature(Type r, Type[] p) {
        returnType = r;
        params = p;
    }
}