
package semantic;

import frontend.Position;
import frontend.Parser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SymbolTable {
    
    private HashMap<String, SymbolTable> children;
    private HashMap<String, Symbol> table;
    private SymbolTable parent;
    private Parser parser;

    /** Constructs an empty table, with no parent scopes. */
    public SymbolTable() {
        this(null, null);
    }

    public SymbolTable(Parser parser) {
        this(null, parser);
    }

    private SymbolTable(SymbolTable parent, Parser parser) {
        children = new HashMap<String, SymbolTable>();
        table = new HashMap<String, Symbol>();
        this.parser = parser;
        this.parent = parent;
    }

    /**
     * Get the abstract symbol which was associated with the given String.
     * If the requested symbol is not found in this scope, 
     * it will search in its parent's scope.
     * @param name the String which denotes this symbol.
     * @return the abstract Symbol which the String signifies.
     */
    public Symbol getSymbol(String name) {
        Symbol temp = table.get(name);
        if (temp != null) {
            return temp;
        } else if (parent != null) {
            return parent.getSymbol(name);
        }
        return null;
    }

    /**
     * Register a new Symbol with this ST.
     * If this symbol is a CLASS or METHOD, a child scope will be created.
     * This child scope can be accessed at any time with the <pre>getChild()</pre> method.
     * @param name the String which denotes this symbol.
     * @param symbol kind of abstract symbol which the string signifies.
     * @return the child scope which was created, if it was.
     */
    public SymbolTable put(String name, Symbol symbol) {
        if (table.containsKey(name) && parser != null) {
            Position pos = parser.getPosition(name);
            System.err.println(String.format(
                "Multiply defined identifier %s at line %d, column %d",
                name, pos.line, pos.column
            ));
        }
        table.put(name, symbol);
        SymbolTable child = null;
        switch (symbol) {
            case MAIN_CLASS:
            case CLASS:
            case METHOD:
                child = new SymbolTable(this, parser);
                children.put(name, child);
        }
        return child;
    }

    /**
     * Get the child scope associated with the given String.
     * Null if no such scope exists; does not check parents.
     * @param name String with which this scope was registered.
     * @return the child scope in question, if it exists.
     */
    public SymbolTable getChildScope(String name) {
        return children.get(name);
    }
}