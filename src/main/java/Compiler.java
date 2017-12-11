
import java.io.*;
import frontend.*;
import semantic.*;
import codegen.*;
import syntaxtree.Program;

public class Compiler {
    public static void main(String[] args) throws Exception {
        System.err.println("Welcome to Goss Copmiler, v0.7.6");
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
        gen.visit(prog);
        Generator g = new Generator();
        for (String line: g.gen(gen.getCode(), vis.table)) {
            System.out.println(line);
        }
        String[] sysCode = new String[] {
            "_system_exit:",
            "li $v0, 10 #exit",
            "syscall",

            "# Integer to print is in $a0. ",
            "# Kills $v0 and $a0",
            "_system_out_println:",
            "# print integer",
            "li  $v0, 1 ",
            "syscall",
            "# print a newline",
            "li $a0, 10",
            "li $v0, 11",
            "syscall",
            "jr $ra",

            "# $a0 = number of bytes to allocate",
            "# $v0 contains address of allocated memory",
            "_new_object:",
            "# sbrk",
            "li $v0, 9 ",
            "syscall",

            "#initialize with zeros",
            "move $t0, $a0",
            "move $t1, $v0",
            "_new_object_loop:",
            "beq $t0, $zero, _new_object_exit",
            "sb $zero, 0($t1)",
            "addi $t1, $t1, 1",
            "addi $t0, $t0, -1",
            "j _new_object_loop",
            "_new_object_exit:",
            "jr $ra",

            "# $a0 = number of bytes to allocate ",
            "# $v0 contains address of allocated memory (with offset 0 being the size)	",
            "_new_array:",
            "# add space for the size (1 integer)",
            "addi $a0, $a0, 4",
            "# sbrk",
            "li $v0, 9",
            "syscall",
            "#initialize to zeros",
            "move $t0, $a0",
            "move $t1, $v0",
            "_new_array_loop:",
            "beq $t0, $zero, _new_array_exit",
            "sb $zero, 0($t1)",
            "addi $t1, $t1, 1",
            "addi $t0, $t0, -1",
            "j _new_array_loop",
            "_new_array_exit:",
            "#store the size (number of ints) in offset 0",
            "addi $t0, $a0, -4",
            "sra $t0, $t0, 2",
            "sw $t0, 0($v0)",
            "jr $ra"
        };
        for (String line: sysCode) {
            System.out.println(line);
        }
    }
}