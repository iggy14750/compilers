
import java_cup.runtime.Scanner;
import java_cup.runtime.Symbol;

%%

%public
%class MJLexer
%cup
%line
%column

%{
    private StringBuilder string = new StringBuilder();

    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

LineBreak  = \n|\r|\r\n
WhiteSpace = [ \n\r\t]+
InputCharacter = [^\r\n]

IntegerLiteral = 0 | [1-9][0-9]*
Identifier = [:jletter:] [:jletterdigit:]*

LineComment = "//" {InputCharacter}* {LineBreak}?
BlockComment = "/*" \*? [^*]* "*/" // Includes doc comments

%state STRING

%%

<YYINITIAL> {
    {WhiteSpace}            {}
    {LineBreak}             {}
    \"                      { string.setLength(0); yybegin(STRING); }
    "public"                { return symbol(sym.PUBLIC); }
    "private"               { return symbol(sym.PRIVATE); }
    "import"                { return symbol(sym.IMPORT); }
    "true"                  { return symbol(sym.TRUE); }
    "false"                 { return symbol(sym.FALSE); }
    "this"                  { return symbol(sym.THIS); }
    "new"                   { return symbol(sym.NEW); }
    "int"                   { return symbol(sym.INT_TYPE); }
    "boolean"               { return symbol(sym.BOOLEAN_TYPE); }
    "String"                { return symbol(sym.STRING_TYPE); }
    "void"                  { return symbol(sym.VOID_TYPE); }
    "return"                { return symbol(sym.RETURN); }
    "if"                    { return symbol(sym.IF); }
    "else"                  { return symbol(sym.ELSE); }
    "while"                 { return symbol(sym.WHILE); }
    "for"                   { return symbol(sym.FOR); }
    "do"                    { return symbol(sym.DO); }
    "switch"                { return symbol(sym.SWITCH); }
    "case"                  { return symbol(sym.CASE); }
    "class"                 { return symbol(sym.CLASS); }
    "extends"               { return symbol(sym.EXTENDS); }
    "static"                { return symbol(sym.STATIC); }

    "["                     { return symbol(sym.LEFT_SQUARE_BRACKET); }
    "]"                     { return symbol(sym.RIGHT_SQUARE_BRACKET); }
    "("                     { return symbol(sym.LEFT_PAREN); }
    ")"                     { return symbol(sym.RIGHT_PAREN); }
    "{"                     { return symbol(sym.LEFT_CURLY); }
    "}"                     { return symbol(sym.RIGHT_CURLY); }
    ";"                     { return symbol(sym.SEMICOLON); }
    ","                     { return symbol(sym.COMMA); }
    "."                     { return symbol(sym.DOT); }
    "?"                     { return symbol(sym.QUESTION_MARK); }
    ":"                     { return symbol(sym.COLON); }
    "="                     { return symbol(sym.EQ); }
    "=="                    { return symbol(sym.EQEQ); }
    "!="                    { return symbol(sym.BANGEQ); }
    "!"                     { return symbol(sym.BANG); }
    "++"                    { return symbol(sym.PLUSPLUS); }
    "--"                    { return symbol(sym.MINUSMINUS); }
    "&"                     { return symbol(sym.AND); }
    "&&"                    { return symbol(sym.ANDAND); }
    "|"                     { return symbol(sym.OR); }
    "||"                    { return symbol(sym.OROR); }
    "<"                     { return symbol(sym.LESS_THAN); }
    ">"                     { return symbol(sym.GREATER_THAN); }
    "+"                     { return symbol(sym.PLUS); }
    "-"                     { return symbol(sym.MINUS); }
    "*"                     { return symbol(sym.TIMES); }
    "/"                     { return symbol(sym.DIVIDE); }

    {LineComment}           {}
    {BlockComment}          { return symbol(sym.COMMENT, yytext()); }
    {IntegerLiteral}        { return symbol(sym.INT, Integer.parseInt(yytext())); }
    {Identifier}            { return symbol(sym.ID, yytext()); }

}

<STRING> {
    \"                      {
                                yybegin(YYINITIAL);
                                return symbol(sym.STRING_LITERAL, string.toString());
                            }
    \\.                     { string.append( yytext() ); }
    [^\n\r\"\\]+            { string.append( yytext() ); }
}

[^] { throw new Error("Invalid character: <" + yytext() + ">"); }