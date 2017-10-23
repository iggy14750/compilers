import java_cup.runtime.Symbol;
import java_cup.runtime.Scanner;

%%

%{
    private void print(String s) {
        System.out.println(s);
    }

    public static final String[] operators = new String[] {
        "add",
        "addi",
        "sub",
        "slt",
        "beq",
        "bne",
        "syscall",
        "lbu",
        "sb",
        "j",
        "jal",
        "lui",
        "and",
        "ori",
        "xor"
    };
    public static final int ADD = 0;
    public static final int ADDI = 1;
    public static final int SUB = 2;
    public static final int SLT = 3;
    public static final int BEQ = 4;
    public static final int BNE = 5;
    public static final int SYSCALL = 6;
    public static final int LBU = 7;
    public static final int SB = 8;
    public static final int J = 9;
    public static final int JAL = 10;
    public static final int LUI = 11;
    public static final int AND = 12;
    public static final int ORI = 13;
    public static final int XOR = 14;
    
%}

%public
%class MipsLex
%cup
%line
%column

LineBreak  = \n|\r|\r\n
WhiteSpace = [ \n\r\t]+
InputCharacter = [^\r\n]

IntegerLiteral = 0 | [1-9][0-9]*
Label = [:jletter:] [:jletterdigit:]* ":"
Register = "$r" \d+

LineComment = "#" {InputCharacter}* {LineBreak}?

%%

","                 { return new Symbol(sym.COMMA, yyline, yycolumn); }
"add"               { return new Symbol(sym.OPCODE, yyline, yycolumn, ADD); }
"addi"              { return new Symbol(sym.OPCODE, yyline, yycolumn, ADDI); }
"sub"               { return new Symbol(sym.OPCODE, yyline, yycolumn, SUB); }
"slt"               { return new Symbol(sym.OPCODE, yyline, yycolumn, SLT); }
"beq"               { return new Symbol(sym.OPCODE, yyline, yycolumn, BEQ); }
"bne"               { return new Symbol(sym.OPCODE, yyline, yycolumn, BNE); }
// "syscall"           { return new Symbol(sym.SYSCALL, yyline, yycolumn, SYSCALL); }
"lbu"               { return new Symbol(sym.OPCODE, yyline, yycolumn, LBU); }
"sb"                { return new Symbol(sym.OPCODE, yyline, yycolumn, SB); }
"j"                 { return new Symbol(sym.OPCODE, yyline, yycolumn, J); }
"jal"               { return new Symbol(sym.OPCODE, yyline, yycolumn, JAL); }
"lui"               { return new Symbol(sym.OPCODE, yyline, yycolumn, LUI); }
"and"               { return new Symbol(sym.OPCODE, yyline, yycolumn, AND); }
"ori"               { return new Symbol(sym.OPCODE, yyline, yycolumn, ORI); }
"xor"               { return new Symbol(sym.OPCODE, yyline, yycolumn, XOR); }
{Register}          { 
    String s = yytext();
    int num = Integer.parseInt(s.substring(2));
    return new Symbol(sym.REGISTER, yyline, yycolumn, num); 
}
{Label}             { return new Symbol(sym.LABEL, yyline, yycolumn, yytext()); }
{LineBreak}         { }//return new Symbol(sym.NEWLINE, yyline, yycolumn); }
{IntegerLiteral}    { }
{LineComment}       { }
{WhiteSpace}        { }

[^] {System.err.println("Invalid input character");}