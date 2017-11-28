
package semantic;

import java.util.HashMap;

public class SymbolTable<T> {
    
    private HashMap<String, T> table;
    private SymbolTable parent;

    /** Constructs an empty table, with no parent scopes. */
    public SymbolTable() {
        table = new HashMap<String, T>();
        parent = null;
    }

    public T get(String name) {
        return null;
    }

    public boolean put(String name, T obj) {
        return false;
    }
}