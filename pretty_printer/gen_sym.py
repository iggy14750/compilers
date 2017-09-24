
def array():
    print("{", end = '')
    for s in open("keywords.txt"):
        s = s.strip().split(",")[1]
        if s == 'COMMA':
            s = ","
        if s == "":
            continue
        print(' "{}",'.format(s), end = '')
    print("}")


def sym():
    with open("sym.java", "w") as out:
        out.write("public class sym {\n")
        out.write("    public static final int EOF = -1;\n")
        n = 0
        for word in open("keywords.txt"):
            word = word.strip().split(",")[0]
            out.write("    public static final int {} = {};\n".format(word, n))
            n += 1
        out.write("}")


import sys
if sys.argv[1] == 'sym':
    sym()
elif sys.argv[1] == 'array':
    array()