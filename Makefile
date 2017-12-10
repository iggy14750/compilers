
bundle:
	cp -r src/main/java projects/ibg4_project3c
	mv projects/ibg4_project3c/java projects/ibg4_project3c/src

default: build test

run:
	java -cp build/classes/java/main/ Compiler $1

build: cup lex gradle

test:
	gradlew test

gradle:
	gradlew build

cup: src/main/java/frontend/Parser.java

src/main/java/frontend/Parser.java: src/main/cup/minijava.cup
	java -jar storage/java-cup-11b.jar -destdir src/main/java/frontend -parser Parser src/main/cup/minijava.cup 

lex: src/main/java/frontend/Lexer.java

src/main/java/frontend/Lexer.java: src/main/jflex/minijava.flex
	jflex --nobak -d src/main/java/frontend/ src/main/jflex/minijava.flex