

public class LabeledInstruction implements Instruction {
    private final Instruction inst;
    private final String label;

    public LabeledInstruction(String l, Instruction i) {
        label = l;
        inst = i;
    }

    public String toString() {
        return label + " " + inst.toString();
    }

    public String pack() {
        return inst.pack();
    }

    public int opCode() {
        return inst.opCode();
    }
}