
package semantic;

import syntaxtree.*;
import visitor.Visitor;

public class SymbolTableVisitor implements Visitor {
    
    private SymbolTable table;

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

    public void visit(Program n) {}
    public void visit(MainClass n) {}
    public void visit(ClassDeclSimple n) {}
    public void visit(ClassDeclExtends n) {}
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