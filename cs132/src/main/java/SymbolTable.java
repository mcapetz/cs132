// package minijava.visitor;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;


public class SymbolTable {
    public Map<String, Symbol> symbol_table;
    public SymbolTable parent_table; 

    public String name;
    public String object_type;
    public String parent_name = null; // this is the superclass


    public SymbolTable() {
        this.symbol_table = new HashMap<>();
    }

    public String getParentName() {
        return this.parent_name;
    }

    public void setParentName(String name) {
        this.parent_name = name;
    }

    public Symbol getType(String name) {
        // System.out.println(superclass);
        Symbol sym = symbol_table.get(name);
        try {
            if (sym != null) {
                return sym; }
            else if (parent_table != null) {
            return parent_table.getType(name);
        }
             else if (parent_name != null) {
                return (this.parent_table.symbol_table.get(parent_name).child_table.getType(name));
            }  else {
                throw new RuntimeException("Symbol '" + name + "' not found");
            }
        } catch (Exception e) {
            return null;
        }
    }

    // public Symbol getType(String name) {
    //     Symbol sym = symbol_table.get(name); // try to find it in the current table
    //         if (sym != null) { 
    //             return sym;
    //         }  
    //         // else if (parent_name != null) { // try to find it in the superclass
    //         //    return (symbol_table.get(parent_name).child_table.getType(name));
    //         // }
    //         else if (parent_table != null) { // try to find it in the parent table
    //             return parent_table.getType(name);
    //         }
    //          else {
    //             // // try to look through children
    //             // List<String> arr = new ArrayList<>(this.symbol_table.keySet());

    //             // for(int i = 0; i < arr.size(); i ++) {
    //             //     Symbol temp_sym = symbol_table.get(arr.get(i));
    //             //     if(temp_sym != null && temp_sym.curr_table.getType(name) != null) {
    //             //         return temp_sym.curr_table.getType(name);
    //             //     }
    //             // }
    //             return null;
    //         }
    // }


    public void setParent(SymbolTable parent_table) {
        this.parent_table = parent_table;
    }

    public SymbolTable getParent() {
        return this.parent_table;
    }

    public void addClass(String className, String dtype, SymbolTable curr_table) {
        this.name = className;
        Symbol new_symbol = new Symbol(className, "class", dtype, curr_table, new ArrayList<>());
        symbol_table.put(className, new_symbol);
    }

    public void addField(String fieldName, String type, String dtype, SymbolTable curr_table) {
        Symbol new_symbol = new Symbol(fieldName, type, dtype, curr_table, new ArrayList<>());
        symbol_table.put(fieldName, new_symbol);
    }

    public void addMethod(String methodName, String dtype, SymbolTable curr_table, List<String> param_types) {
        Symbol new_symbol = new Symbol(methodName, "method", dtype, curr_table, param_types);
        symbol_table.put(methodName, new_symbol);
    }

    public void addParameter(String paramName, String type, String dtype, SymbolTable curr_table) {
        Symbol new_symbol = new Symbol(paramName, type, dtype, curr_table, new ArrayList<>());
        symbol_table.put(paramName, new_symbol);
    }

    public void addVariable(String varName, String dtype, SymbolTable curr_table) {
        Symbol new_symbol = new Symbol(varName, "var", dtype, curr_table, new ArrayList<>());
        symbol_table.put(varName, new_symbol);
    }

    public boolean isSubtypeOf(String type, String super_type) {
        if (type == super_type) {
            return true;
        }

        Symbol sym = symbol_table.get(type);
        if (sym == null) {
            throw new RuntimeException("class not found");
        }

        String super_name = sym.child_table.getParentName();
        if (super_name == null) {
            return false; // no superclass, not a subtype
        }

        // check the superclass recursively 
        return isSubtypeOf(super_name, super_type);
    }
    
    // del later
    public void printSymbolTable(SymbolTable table, int depth) {
        

        for (String key : table.symbol_table.keySet()) {
            Symbol sym = table.symbol_table.get(key);

            if (sym != null && sym.child_table.parent_name != null) {
                System.err.println("i am in: " + sym.name);
                System.err.println(" extends class " + sym.child_table.parent_name);
            }

            printIndent(depth);

        
            if(sym.parent_table != null) {
                System.err.print(sym.name + " : " + sym.object_type + " : " + sym.dtype +  "\n");
            }
            else {
                System.err.print(sym.name + " : " + sym.object_type + " : " + sym.dtype + ", no parent " + "\n");
            }
            
            if (sym.child_table != null) {
                printSymbolTable(sym.child_table, depth + 1);
            }
        }
    }

    private void printIndent(int depth) {
        for (int i = 0; i < depth; i++) {
            System.err.print("     ");
        }
    }
}
