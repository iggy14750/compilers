

CLASSPATH = .#./:./#java_cup/runtime

default: build run

run:
	java -cp $(CLASSPATH) PrettyPrinter examples/main.java

force: touch build

touch:
	touch gen_sym.py minijava.flex keywords.txt PrettyPrinter.java examples/main.java

build: sym.class PrettyPrinter.class MJLexer.class

sym.class: sym.java
	javac sym.java

sym.java: keywords.txt gen_sym.py
	python gen_sym.py sym

PrettyPrinter.class: PrettyPrinter.java
	javac -cp $(CLASSPATH) PrettyPrinter.java

MJLexer.class: MJLexer.java
	javac -cp $(CLASSPATH) MJLexer.java

MJLexer.java: minijava.flex
	jflex --nobak minijava.flex