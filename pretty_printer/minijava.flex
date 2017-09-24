
import java_cup.runtime.Scanner;
import java_cup.runtime.Symbol;

%%

%public
%class MJLexer
%cup
%line
%column

%{
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

LineBreak  = \n|\r|\r\n
WhiteSpace = [ \n\r\t]+

IntegerLiteral = 0 | [1-9][0-9]*
Identifier = [:jletter:] [:jletterdigit:]*

%%

<YYINITIAL> {
    {WhiteSpace} {}
    {LineBreak} {}
    "public" { return symbol(sym.PUBLIC); }
    {IntegerLiteral} { return symbol(sym.INT, Integer.parseInt(yytext())); }
    {Identifier} { return symbol(sym.ID, yytext()); }
}