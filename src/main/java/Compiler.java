
import java.io.*;
import frontend.*;
import semantic.*;
import syntaxtree.Program;

public class Compiler {
    public static void main(String[] args) throws Exception {
        Parser parser = new Parser(new File(args[0]));
        Program prog = parser.getProgram();
        SymbolTableVisitor vis = new SymbolTableVisitor(parser);
        vis.visit(prog);
        TypeCheckVisitor check = new TypeCheckVisitor(parser, vis.table, prog);
        if (check.compileError()) {
            System.err.print("Compilation has been terminated due to previous errors");
            System.exit(1);
        }
        IrGenVisitor gen = new IrGenVisitor();
        for (Quad line: gen.getCode()) {
            System.out.println(line.toString());
        }
    }
}