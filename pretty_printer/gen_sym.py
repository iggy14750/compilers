
with open("sym.java", "w") as out:
    out.write("public class sym {\n")
    out.write("    public static final int EOF = -1;\n")
    n = 0
    for word in open("keywords.txt"):
        word = word.strip()
        out.write("    public static final int {} = {};\n".format(word, n))
        n += 1
    out.write("}")