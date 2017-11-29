
package semantic;

import syntaxtree.*;
import visitor.Visitor;

public class SymbolTableVisitor implements Visitor {
    
    public SymbolTable table;

    /** The public constructor constructs a default SymbolTable. */
    public SymbolTableVisitor() {
        this(new SymbolTable());
    }

    /**
     * Accepts a SymbolTable for two reasons:
     * <ol>
     *      <li> Ease of Testing. A mock can be thrown in.
     *      <li> When we enter a new scope, we can create a new SymbolTableVisitor
     *          to do the work with that scope, instead of having to keep track of the tree of scopes.
     * </ol>
     * @param global the SymbolTable which this will build, for reasons above.
     */
    SymbolTableVisitor(SymbolTable global) {
        table = global;
    }

    private String getId(ClassDecl cd) {
        if (cd instanceof ClassDeclSimple) {
            return ( (ClassDeclSimple) cd ).i.s;
        }
        return ((ClassDeclExtends) cd).i.s;
    }

    public void visit(Program n) {
        if (n == null) return;
        SymbolTable mainClass = table.put(n.m.i1.toString(), Symbol.MAIN_CLASS);
        n.m.accept(new SymbolTableVisitor(mainClass));
        // Followed by the class list....
        for (int i = 0; i < n.cl.size(); i++) {
            SymbolTable aClass = table.put(
                getId(n.cl.elementAt(i)),
                Symbol.CLASS
            );
            n.cl.elementAt(i).accept(new SymbolTableVisitor(aClass));
        }
    }

    public void visit(MainClass mc) {
        SymbolTable mainMethod = table.put(
            "main",
            Symbol.METHOD.setMethodSignature(
                new MethodSignature(
                    SymbolType.VOID, new SymbolType[] {SymbolType.STRING_ARRAY}
                )
            )
        );
        mainMethod.put(mc.i2.toString(), Symbol.VARIABLE.setVariableType(SymbolType.STRING_ARRAY));
    }

    private static SymbolType getType(Type t) {
        if (t instanceof BooleanType) {
            return SymbolType.BOOLEAN;
        }
        if (t instanceof IntArrayType) {
            return SymbolType.INT_ARRAY;
        }
        if (t instanceof IntegerType) {
            return SymbolType.INT;
        }
        if (t instanceof IdentifierType) {
            return SymbolType.IDENTIFIER.setIdentifier( ((IdentifierType) t).s );
        }
        return null;
    }

    private static SymbolType[] getFormalTypes(FormalList fl) {
        SymbolType[] arr = new SymbolType[fl.size()];
        for (int i = 0; i < fl.size(); i++) {
            arr[i] = getType( fl.elementAt(i).t );
        }
        return arr;
    }

    public void visit(ClassDeclSimple n) {
        // Variable declarations
        for (int i = 0; i < n.vl.size(); i++) {
            VarDecl var = n.vl.elementAt(i);
            table.put(var.i.s, Symbol.VARIABLE.setVariableType(getType(var.t)));
        }
        // Method declarations
        for (int i = 0; i < n.ml.size(); i++) {
            MethodDecl meth = n.ml.elementAt(i);
            SymbolTable methodScope = table.put(
                meth.i.s,
                Symbol.METHOD.setMethodSignature(new MethodSignature(
                    getType(meth.t), getFormalTypes(meth.fl)
                ))
            );
            meth.accept(new SymbolTableVisitor(methodScope));
        }
    }

    public void visit(ClassDeclExtends n) {
        // Variable declarations
        for (int i = 0; i < n.vl.size(); i++) {
            VarDecl var = n.vl.elementAt(i);
            table.put(var.i.s, Symbol.VARIABLE.setVariableType(getType(var.t)));
        }
        // Method declarations
        for (int i = 0; i < n.ml.size(); i++) {
            MethodDecl meth = n.ml.elementAt(i);
            SymbolTable methodScope = table.put(
                meth.i.s,
                Symbol.METHOD.setMethodSignature(new MethodSignature(
                    getType(meth.t), getFormalTypes(meth.fl)
                ))
            );
            meth.accept(new SymbolTableVisitor(methodScope));
        }
    }

    public void visit(VarDecl n) {}
    public void visit(MethodDecl n) {}
    public void visit(Formal n) {}
    public void visit(IntArrayType n) {}
    public void visit(BooleanType n) {}
    public void visit(IntegerType n) {}
    public void visit(IdentifierType n) {}
    public void visit(Block n) {}
    public void visit(If n) {}
    public void visit(While n) {}
    public void visit(Print n) {}
    public void visit(Assign n) {}
    public void visit(ArrayAssign n) {}
    public void visit(And n) {}
    public void visit(LessThan n) {}
    public void visit(Plus n) {}
    public void visit(Minus n) {}
    public void visit(Times n) {}
    public void visit(ArrayLookup n) {}
    public void visit(ArrayLength n) {}
    public void visit(Call n) {}
    public void visit(IntegerLiteral n) {}
    public void visit(True n) {}
    public void visit(False n) {}
    public void visit(IdentifierExp n) {}
    public void visit(This n) {}
    public void visit(NewArray n) {}
    public void visit(NewObject n) {}
    public void visit(Not n) {}
    public void visit(Identifier n) {}
}