
package frontend;

import java_cup.runtime.Scanner;
import java_cup.runtime.Symbol;

%%

%public
%class Lexer
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

DecIntegerLiteral = [+-]? 0 | [1-9][0-9]*
HexIntegerLiteral = [+-]? 0x [0-9a-zA-Z]+
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
    "true"                  { return symbol(sym.TRUE); }
    "false"                 { return symbol(sym.FALSE); }
    "this"                  { return symbol(sym.THIS); }
    "new"                   { return symbol(sym.NEW); }
    "int"                   { return symbol(sym.INT_T); }
    "boolean"               { return symbol(sym.BOOLEAN_T); }
    "void"                  { return symbol(sym.VOID); }
    "return"                { return symbol(sym.RETURN); }
    "if"                    { return symbol(sym.IF); }
    "else"                  { return symbol(sym.ELSE); }
    "while"                 { return symbol(sym.WHILE); }
    "class"                 { return symbol(sym.CLASS); }
    "extends"               { return symbol(sym.EXTENDS); }
    "static"                { return symbol(sym.STATIC); }
    "System.out.println"    { return symbol(sym.SYSOUT); }
    "length"                { return symbol(sym.LENGTH); }

    "["                     { return symbol(sym.LEFT_SQUARE_BRACKET); }
    "]"                     { return symbol(sym.RIGHT_SQUARE_BRACKET); }
    "("                     { return symbol(sym.LEFT_PAREN); }
    ")"                     { return symbol(sym.RIGHT_PAREN); }
    "{"                     { return symbol(sym.LEFT_CURLY); }
    "}"                     { return symbol(sym.RIGHT_CURLY); }
    ";"                     { return symbol(sym.SEMICOLON); }
    ","                     { return symbol(sym.COMMA); }
    "."                     { return symbol(sym.DOT); }
    "="                     { return symbol(sym.EQ); }
    "=="                    { return symbol(sym.EQEQ); }
    "!"                     { return symbol(sym.BANG); }
    "&&"                    { return symbol(sym.AND); }
    "|"                     { return symbol(sym.OR); }
    "<"                     { return symbol(sym.LESS_THAN); }
    "+"                     { return symbol(sym.PLUS); }
    "-"                     { return symbol(sym.MINUS); }
    "*"                     { return symbol(sym.TIMES); }
    "/"                     { return symbol(sym.DIVIDE); }

    {LineComment}           { /* ignore */ }
    {BlockComment}          { /* ignore */ }
    {DecIntegerLiteral}     { return symbol(sym.INT, Integer.parseInt(yytext())); }
    {HexIntegerLiteral}     { 
        String n = String.join("", yytext().split("0x"));
        return symbol(sym.INT, Integer.parseInt(n, 16)); 
    }
    {Identifier}            { return symbol(sym.ID, yytext()); }

}

<STRING> {
    \"                      {
                                yybegin(YYINITIAL);
                                return symbol(sym.STRING, string.toString());
                            }
    \\.                     { string.append( yytext() ); }
    [^\n\r\"\\]+            { string.append( yytext() ); }
}

[^] { throw new Error("Invalid character: <" + yytext() + ">"); }