import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java_cup.runtime.Symbol;

public class Assembler {
    public static void main(String[] args) throws Exception {
        MipsParse p = new MipsParse(new MipsLex(new StringReader("add $r1 $r2 $r3")));
        Symbol prog = p.parse();
        System.out.println(prog);
    }
}