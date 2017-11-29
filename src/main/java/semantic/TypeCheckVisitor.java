
package semantic;

import java.util.HashMap;
import frontend.Parser;
import frontend.Position;
import syntaxtree.*;
import visitor.Visitor;

public class TypeCheckVisitor implements Visitor {

    private SymbolTable table;
    private static Parser parser; // For line numbers.
    private static boolean compileError = false;
    public static HashMap<Object, SymbolType> typeCheck;

    public TypeCheckVisitor(Parser parse, SymbolTable table, Program prog) {
        this(parse, table);
        prog.accept(this);
    }

    public TypeCheckVisitor(Parser parse, SymbolTable table) {
        typeCheck = new HashMap<Object, SymbolType>();
        this.table = table;
        parser = parse;
    }

    private TypeCheckVisitor(SymbolTable table) {
        this.table = table;
    }

    public boolean compileError() {
        return compileError;
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

    public void visit(NewArray n) {
        n.e.accept(this);
        // TODO
    }

    public void visit(NewObject n) {
        n.i.accept(this);
        if (table.getSymbol(n.i.s) != Symbol.CLASS) {
            Position pos = parser.getPosition(n.i);
            System.err.printf(
                "Attempt to construct object from non-class identifier %s, at line %d, column %d",
                n.i.s, pos.line, pos.column
            );
            compileError = true;
        }
        typeCheck.put(n, SymbolType.IDENTIFIER.setIdentifier(n.i.s));
    }

    public void visit(Not n) {
        n.e.accept(this);
        if (typeCheck.get(n.e) != SymbolType.BOOLEAN) {
            Position pos = parser.getPosition(n.e);
            System.err.printf(
                "Attempt to use boolean operator not on non-boolean operands at line %d, character %d\n",
                pos.line, pos.column
            );
            compileError = true;
        }
        typeCheck.put(n, SymbolType.BOOLEAN);
    }

    public void visit(Identifier n) {
        Symbol sym = table.getSymbol(n.s);
        if (sym == null) {
            Position pos = parser.getPosition(n);
            System.err.printf(
                "Use of undefined identifier %s at line %d, character %d\n",
                n.s, pos.line, pos.column
            );
            compileError = true;
        } else if (sym == Symbol.VARIABLE) {
            typeCheck.put(n, sym.getVariableType());
        }
    }
}