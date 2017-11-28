
package semantic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SymbolTable {
    
    private HashMap<String, Symbol> table;
    private SymbolTable parent;

    /** Constructs an empty table, with no parent scopes. */
    public SymbolTable() {
        table = new HashMap<String, Symbol>();
        parent = null;
    }

    public Symbol get(String name) {
        return null;
    }

    public boolean put(String name, Symbol symbol) {
        return false;
    }
}