
package codegen;

import java.util.List;
import java.util.Map;

import semantic.Quad;
import semantic.Operation;

public class Generator {

    private List<Quad> code;
    private Map<Object, String> table;

    public Generator(List<Quad> code, Map<Object, String> table) {
        this.code = code;
        this.table = table;
    }

    public List<Instruction> gen() {
        return null;
    }
}