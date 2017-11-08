
default: cup lex build

build: visitor syntax frontend top

test: default testr

testr: testc
	java -cp bin TestRunner.java

testc: src/test/java/*.java
	javac -cp bin src/test/java/*.java -d bin

top: src/main/java/*
	javac -cp bin src/main/java/*.java -d bin

frontend: src/main/java/frontend/*
	javac -cp bin src/main/java/frontend/*.java -d bin

syntax: src/main/java/syntaxtree/*
	javac -cp src/main/java/ src/main/java/syntaxtree/*.java -d bin

visitor: src/main/java/visitor/*
	javac -cp src/main/java/ src/main/java/visitor/*.java -d bin

cup: src/main/java/frontend/Parser.java

src/main/java/frontend/Parser.java: src/main/cup/minijava.cup
	java -jar storage/java-cup-11b.jar -parser Parser src/main/cup/minijava.cup
	mv sym.java src/main/java
	mv Parser.java src/main/java/frontend

lex: src/main/java/frontend/Lexer.java

src/main/java/frontend/Lexer.java: src/main/cup/mips.cup src/main/jflex/minijava.flex
	jflex --nobak -d src/main/java/frontend/ src/main/jflex/minijava.flex

run:
	java -cp bin Test