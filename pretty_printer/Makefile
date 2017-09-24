

CLASSPATH = .#./:./#java_cup/runtime

run: build
	java -cp $(CLASSPATH) PrettyPrinter examples/main.java

build: PrettyPrinter.class sym.class MJLexer.class

sym.class: sym.java
	javac sym.java

PrettyPrinter.class: PrettyPrinter.java
	javac -cp $(CLASSPATH) PrettyPrinter.java

MJLexer.class: MJLexer.java
	javac -cp $(CLASSPATH) MJLexer.java

MJLexer.java: minijava.flex
	jflex --nobak minijava.flex