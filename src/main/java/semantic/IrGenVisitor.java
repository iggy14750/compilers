
package semantic;

import syntaxtree.*;
import visitor.Visitor;
import java.util.*;

public class IrGenVisitor implements Visitor {

    private int tempNum = 0;
    private int labelNum = 0;
    private List<Quad> code;
    private Map<Object, String> name;
    private Map<Object, String> type;
    private String currentClass;

    public IrGenVisitor() {
        code = new ArrayList<Quad>();
        name = new HashMap<Object, String>();
        type = new HashMap<Object, String>();
    }

    public List<Quad> getCode() {
        return code;
    }

    private String newTemp() {
        tempNum++;
        return "t" + (tempNum - 1);
    }

    private String newLabel() {
        labelNum++;
        return "L" + (labelNum - 1);
    }

    public void visit(Program n) {
        n.m.accept(this);
        for (int i = 0; i < n.cl.size(); i++) {
            n.cl.elementAt(i).accept(this);
        }
    }

    public void visit(MainClass n) {
        n.s.accept(this);
    }

    public void visit(ClassDeclSimple n) {
        currentClass = n.i.s;
        for (int i = 0; i < n.ml.size(); i++) {
            n.ml.elementAt(i).accept(this);
        }
    }

    public void visit(ClassDeclExtends n) {
        currentClass = n.i.s;
        for (int i = 0; i < n.ml.size(); i++) {
            n.ml.elementAt(i).accept(this);
        }
    }
    public void visit(VarDecl n) {}

    public void visit(MethodDecl n) {
        code.add(Quad.label(
            String.format("%s.%s", currentClass, n.i.s)
        ));
        for (int i = 0; i < n.sl.size(); i++) {
            n.sl.elementAt(i).accept(this);
        }
        n.e.accept(this);
        code.add(Quad.ret(name.get(n.e)));
    }

    public void visit(Formal n) {}
    public void visit(IntArrayType n) {}
    public void visit(BooleanType n) {}
    public void visit(IntegerType n) {}
    public void visit(IdentifierType n) {}

    public void visit(Block n) {
        for (int i = 0; i < n.sl.size(); i++) {
            n.sl.elementAt(i).accept(this);
        }
    }

    public void visit(If n) {
        n.e.accept(this);
        String elseBranch = newLabel();
        String endIf = newLabel();
        code.add(Quad.ifFalse(
            name.get(n.e),
            elseBranch
        ));
        n.s1.accept(this);
        code.add(Quad.jump(endIf));
        code.add(Quad.label(elseBranch));
        n.s2.accept(this);
        code.add(Quad.label(endIf));
    }

    public void visit(While n) {
        String beforeCond = newLabel();
        String afterLoop = newLabel();
        code.add(Quad.label(beforeCond));
        n.e.accept(this);
        code.add(Quad.ifFalse(
            name.get(n.e),
            afterLoop
        ));
        n.s.accept(this);
        code.add(Quad.jump(beforeCond));
        code.add(Quad.label(afterLoop));
    }

    public void visit(Print n) {
        n.e.accept(this);
        code.add(new Quad(
            Operation.PARAM,
            name.get(n.e), "",""
        ));
        code.add(new Quad(
            Operation.CALL,
            "_system_out_println",
            "1",
            newTemp()
        ));
    }

    public void visit(Assign n) {
        n.i.accept(this);
        n.e.accept(this);
        code.add(new Quad(
            Operation.COPY,
            name.get(n.e),
            "",
            name.get(n.i)
        ));
    }

    public void visit(ArrayAssign n) {
        n.i.accept(this);
        n.e1.accept(this);
        n.e2.accept(this);
        code.add(new Quad(
            Operation.INDEX_TO,
            name.get(n.e1),
            name.get(n.e2),
            name.get(n.i)
        ));
    }

    public void visit(And n) {
        n.e1.accept(this);
        n.e2.accept(this);
        name.put(n, newTemp());
        code.add(new Quad(
            Operation.AND,
            name.get(n.e1),
            name.get(n.e2),
            name.get(n)
        ));
    }

    public void visit(LessThan n) {
        n.e1.accept(this);
        n.e2.accept(this);
        name.put(n, newTemp());
        code.add(new Quad(
            Operation.LESS_THAN,
            name.get(n.e1),
            name.get(n.e2),
            name.get(n)
        ));
    }

    public void visit(Plus n) {
        n.e1.accept(this);
        n.e2.accept(this);
        name.put(n, newTemp());
        code.add(new Quad(
            Operation.PLUS,
            name.get(n.e1),
            name.get(n.e2),
            name.get(n)
        ));
    }

    public void visit(Minus n) {
        n.e1.accept(this);
        n.e2.accept(this);
        name.put(n, newTemp());
        code.add(new Quad(
            Operation.MINUS,
            name.get(n.e1),
            name.get(n.e2),
            name.get(n)
        ));
    }

    public void visit(Times n) {
        n.e1.accept(this);
        n.e2.accept(this);
        name.put(n, newTemp());
        code.add(new Quad(
            Operation.TIMES,
            name.get(n.e1),
            name.get(n.e2),
            name.get(n)
        ));
    }

    public void visit(ArrayLookup n) {
        n.e1.accept(this);
        n.e2.accept(this);
        name.put(n, newTemp());
        code.add(new Quad(
            Operation.INDEX_FROM,
            name.get(n.e1),
            name.get(n.e2),
            name.get(n)
        ));
    }

    public void visit(ArrayLength n) {
        n.e.accept(this);
        name.put(n, newTemp());
        code.add(new Quad(
            Operation.LENGTH,
            name.get(n.e),
            "",
            name.get(n)
        ));
    }

    public void visit(Call n) {
        n.e.accept(this);
        n.i.accept(this);

        for (int i = 0; i < n.el.size(); i++) {
            n.el.elementAt(i).accept(this);
        }

        code.add(new Quad(
            Operation.PARAM,
            name.get(n.e),
            "",""
        ));

        for (int i = 0; i < n.el.size(); i++) {
            code.add(new Quad(
                Operation.PARAM,
                name.get(n.el.elementAt(i)),
                "",""
            ));
        }
        name.put(n, newTemp());
        String className = type.get(n.e);
        String methodName = name.get(n.i);
        String calledName = className == null ?
            methodName : className + "." + methodName;
        code.add(new Quad(
            Operation.CALL,
            calledName, // name
            Integer.toString(n.el.size() + 1), // num params
            name.get(n)
        ));
    }

    public void visit(IntegerLiteral n) {
        name.put(n, newTemp());
        code.add(Quad.copy(
            name.get(n),
            Integer.toString(n.i)
        ));
    }
    
    public void visit(True n) {
        name.put(n, "1");
    }

    public void visit(False n) {
        name.put(n, "0");
    }

    public void visit(IdentifierExp n) {
        name.put(n, n.s);
    }

    public void visit(This n) {
        name.put(n, "this");
    }

    public void visit(NewArray n) {
        n.e.accept(this);
        name.put(n, newTemp());
        code.add(new Quad(
            Operation.NEW_ARRAY,
            "", // type
            name.get(n.e), // size
            name.get(n) // assigned to..
        ));
    }

    public void visit(NewObject n) {
        n.i.accept(this);
        type.put(n, n.i.s);
        name.put(n, newTemp());
        code.add(new Quad(
            Operation.NEW_OBJECT, 
            name.get(n.i), 
            "", 
            name.get(n)
        ));
    }

    public void visit(Not n) {
        n.e.accept(this);
        String str = newTemp();
        name.put(n, str);
        Quad q = new Quad(Operation.NOT, name.get(n.e), "", str);
        code.add(q);
    }
    
    public void visit(Identifier n) {
        name.put(n, n.s);
    }
}