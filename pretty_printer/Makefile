

CLASSPATH = .#./:./#java_cup/runtime
INPUT_FILE = examples/long.java

default: build run

run:
	java -cp $(CLASSPATH) PrettyPrinter $(INPUT_FILE)

force: touch build

touch:
	touch gen_sym.py minijava.flex keywords.txt PrettyPrinter.java $(INPUT_FILE)

build: sym.class MJLexer.class PrettyPrinter.class

sym.class: sym.java
	javac sym.java

sym.java: keywords.txt gen_sym.py
	python gen_sym.py sym
	echo "Copy this into PrettyPrinter.java"
	python gen_sym.py array

PrettyPrinter.class: PrettyPrinter.java
	javac -cp $(CLASSPATH) PrettyPrinter.java

MJLexer.class: MJLexer.java
	javac -cp $(CLASSPATH) MJLexer.java

MJLexer.java: minijava.flex
	jflex --nobak minijava.flex