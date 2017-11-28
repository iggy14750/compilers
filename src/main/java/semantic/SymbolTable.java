
package semantic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SymbolTable {
    
    private HashMap<String, Symbol> table;
    private SymbolTable parent;
    private List<SymbolTable> children;

    /** Constructs an empty table, with no parent scopes. */
    public SymbolTable() {
        this(null);
    }

    private SymbolTable(SymbolTable parent) {
        table = new HashMap<String, Symbol>();
        children = new ArrayList<SymbolTable>();
        this.parent = parent;
    }

    public Symbol get(String name) {
        Symbol temp = table.get(name);
        if (temp != null) {
            return temp;
        } else if (parent != null) {
            return parent.get(name);
        }
        return null;
    }

    public boolean put(String name, Symbol symbol) {
        return table.put(name, symbol) != null;
    }

    public SymbolTable newScope() {
        children.add(new SymbolTable(this));
        return children.get(children.size() - 1);
    }
}