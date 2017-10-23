import java.io.IOException;
import java.io.FileReader;
import java.util.List;
import java_cup.runtime.Symbol;

public class Assembler {

    public static void main(String[] args) throws Exception {
        MipsParse p = new MipsParse(new MipsLex(new FileReader(args[0])));
        List<Instruction> prog = (List<Instruction>) p.parse().value;
        for (Instruction inst: prog) {
            String type = "";
            if (inst instanceof RType) {
                type = "rtype";
            } else if (inst instanceof JType) {
                type = "jtype";
            } else if (inst instanceof IType) {
                type = "itype";
            } else if (inst instanceof LabeledInstruction) {
                type = "label";
            } else {
                type = "UNKNOWN";
            }
            System.out.println(type + ": " + inst);
        }

        System.out.println("\n\n================================");
        System.out.println("========= REAL OUTPUT ==========");
        System.out.println("================================");
        for (Instruction inst: prog) {
            System.out.println(inst.pack());
        }
    }
}