import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java_cup.runtime.Symbol;

public class Assembler {

    private static final String example = 
        "add $r1, $r2, $r3\n" +
        "sub $r3, $r2, $r2\n" +
        "j $r65, $r22, $r99\n" +
        "alabel: ori $r1,$r43,$r2\n" + 
        "ori $r3,$r2,-23\n" +
        "syscall\n" +
        "lbu $r2, 12341 ( $r54 )\n";

    public static void main(String[] args) throws Exception {
        MipsParse p = new MipsParse(new MipsLex(new StringReader(example)));
        List<Instruction> prog = (List<Instruction>) p.parse().value;
        for (Instruction inst: prog) {
            System.out.println(inst);
        }
    }
}