import java_cup.runtime.Symbol;
import java_cup.runtime.Scanner;

%%

%{
    private void print(String s) {
        System.out.println(s);
    }
%}

%public
%class MipsLex
%cup
%line
%column

LineBreak  = \n|\r|\r\n
WhiteSpace = [ \n\r\t]+
InputCharacter = [^\r\n]

DecIntegerLiteral = [+-]? (0 | [1-9][0-9]*)
HexIntegerLiteral = [+-]? 0[xX][0-9a-zA-Z]+
Label = [:jletter:] [:jletterdigit:]* ":"
Register = "$r" \d+

LineComment = "#" {InputCharacter}* {LineBreak}?

%%

","                 { return new Symbol(sym.COMMA, yyline, yycolumn); }
"("                 { return new Symbol(sym.LPAREN, yyline, yycolumn); }
")"                 { return new Symbol(sym.RPAREN, yyline, yycolumn); }
"add"               { return new Symbol(sym.OPCODE, yyline, yycolumn, Opcode.ADD); }
"addi"              { return new Symbol(sym.OPCODE, yyline, yycolumn, Opcode.ADDI); }
"sub"               { return new Symbol(sym.OPCODE, yyline, yycolumn, Opcode.SUB); }
"slt"               { return new Symbol(sym.OPCODE, yyline, yycolumn, Opcode.SLT); }
"beq"               { return new Symbol(sym.OPCODE, yyline, yycolumn, Opcode.BEQ); }
"bne"               { return new Symbol(sym.OPCODE, yyline, yycolumn, Opcode.BNE); }
"syscall"           { return new Symbol(sym.SYSCALL, yyline, yycolumn, Opcode.SYSCALL); }
"lbu"               { return new Symbol(sym.OPCODE, yyline, yycolumn, Opcode.LBU); }
"sb"                { return new Symbol(sym.OPCODE, yyline, yycolumn, Opcode.SB); }
"j"                 { return new Symbol(sym.OPCODE, yyline, yycolumn, Opcode.J); }
"jal"               { return new Symbol(sym.OPCODE, yyline, yycolumn, Opcode.JAL); }
"lui"               { return new Symbol(sym.OPCODE, yyline, yycolumn, Opcode.LUI); }
"and"               { return new Symbol(sym.OPCODE, yyline, yycolumn, Opcode.AND); }
"ori"               { return new Symbol(sym.OPCODE, yyline, yycolumn, Opcode.ORI); }
"xor"               { return new Symbol(sym.OPCODE, yyline, yycolumn, Opcode.XOR); }
{Register}          { 
    String s = yytext();
    int num = Integer.parseInt(s.substring(2));
    return new Symbol(sym.REGISTER, yyline, yycolumn, num); 
}
{DecIntegerLiteral}     {
    int imm = Integer.parseInt(yytext());
    return new Symbol(sym.INT, yyline, yycolumn, imm); 
}
{HexIntegerLiteral}     {
    String s = yytext();
    // Getting rid of the 0x in the literal
    if (s.charAt(0) == '-' || s.charAt(0) == '+') {
        s = s.charAt(0) + s.substring(3); // want to include the sign for parseInt
    } else {
        s = s.substring(2);
    }
    int imm = Integer.parseInt(s, 16);
    return new Symbol(sym.INT, yyline, yycolumn, imm);
}
{Label}             { return new Symbol(sym.LABEL, yyline, yycolumn, yytext()); }

{LineBreak}         { }//return new Symbol(sym.NEWLINE, yyline, yycolumn); }
{LineComment}       { }
{WhiteSpace}        { }

[^] {System.err.println("Invalid input character: " + yytext());}