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

IntegerLiteral = 0 | [1-9][0-9]*
Identifier = [:jletter:] [:jletterdigit:]*
Register = "$r" \d+

LineComment = "#" {InputCharacter}* {LineBreak}?

%%

","                 { return new Symbol(sym.COMMA, yyline, yycolumn); }
"add"               { return new Symbol(sym.ADD, yyline, yycolumn); }
{Register}          { 
    String s = yytext();
    int num = Integer.parseInt(s.substring(2));
    return new Symbol(sym.REGISTER, yyline, yycolumn, num); 
}
{LineBreak}         { print("linebreak"); }
{IntegerLiteral}    { print("integer literal"); }
{Identifier}        { print("identifier"); }
{LineComment}       { print("Line comment"); }
{WhiteSpace}        { print("whitespace"); }

[^] {System.err.println("Invalid input character");}