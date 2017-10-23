
default: cup lex build

build: src/main/java/*
	javac -cp bin src/main/java/*.java -d bin

cup: src/main/java/MipsParse.java

src/main/java/MipsParse.java: src/main/cup/mips.cup
	java -jar storage/java-cup-11b.jar -parser MipsParse src/main/cup/mips.cup
	mv sym.java src/main/java
	mv MipsParse.java src/main/java

lex: src/main/java/MipsLex.java

src/main/java/MipsLex.java: src/main/jflex/mips.flex
	jflex --nobak -d src/main/java/ src/main/jflex/mips.flex

run:
	sh run.sh