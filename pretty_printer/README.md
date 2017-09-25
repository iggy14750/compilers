
User's Guide
------------

As this project is packaged, you can run with `java PrettyPrinter <inputfile>`; the output will be sent to stdout, so must be piped to a file. But you can also rebuild with `make force` to force a rebuild of everything, `make build` to only rebuild from the source files which have changed, `make run` to run the current binary, or just `make` to build and run.


Things I couldn't do
--------------------

 1. I don't believe that it is possible to adequately deal with if/while/else without something equivalent to a parser. That is, I can neively break the line and increase the indentation level whenever I find an opening curly brace, but this fails to indent `if (condition) statement;` style code. I need the context of that statement following an if to know to indent.

 2. Likewise, my pretty printer with break the line and increase indent when given an array literal. Really, this is the same problem. We are using the open curly as a heuristic to inidcate deeper scope, but that heuristic is not always correct.