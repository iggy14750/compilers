

CLASSPATH = ../java_cup/runtime:bin

run: build
	java -cp $(CLASSPATH) bin/PrettyPrinter

build: bin/PrettyPrinter.class

bin/PrettyPrinter.class: src/PrettyPrinter.java
	javac -cp $(CLASSPATH) src/PrettyPrinter.java -d bin