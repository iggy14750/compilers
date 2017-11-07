import java.io.IOException;
import java.io.FileReader;
import java.util.List;
import java_cup.runtime.Symbol;

public class Assembler {

    public static void main(String[] args) throws Exception {
        MipsParse p = new MipsParse(new MipsLex(new FileReader(args[0])));
        List<Instruction> prog = (List<Instruction>) p.parse().value;
        for (Instruction inst: prog) {
            System.out.println(inst.pack());
        }
    }
}