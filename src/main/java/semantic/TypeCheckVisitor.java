
package semantic;

import java.util.HashMap;
import frontend.Parser;
import frontend.Position;
import syntaxtree.*;
import visitor.Visitor;

public class TypeCheckVisitor implements Visitor {

    private Parser parser; // For line numbers.
    private SymbolTable table;
    private HashMap<Object, SymbolType> typeCheck;
    private boolean compileError = false;

    public TypeCheckVisitor(Parser parser, SymbolTable table, Program prog) {
        this.parser = parser;
        this.table = table;
        prog.accept(this);
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