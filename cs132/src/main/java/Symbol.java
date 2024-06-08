// package minijava.visitor;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class Symbol {
    public String name;
    public String object_type;
    public String dtype;
    public SymbolTable parent_table = new SymbolTable();
    public SymbolTable curr_table = new SymbolTable(); 
    public SymbolTable child_table = new SymbolTable(); 
    public List<String> param_types = new ArrayList<>(); 
    public List<String> param_names = new ArrayList<>(); 
    public List<String> member_vars = new ArrayList<>(); 
    public Integer member_var_count = 0;
    public String allocation_var = "";
    public String allocation_var_w = "";
    public String null_label = "";
    public Integer num_methods = 0;
    public List<String> method_array = new ArrayList<>();
    public List<String> local_vars = new ArrayList<>();

    public Symbol(String name, String object_type, String dtype, SymbolTable curr_table, List<String> param_types) {
        this.name = name;
        this.object_type = object_type;
        this.dtype = dtype;
        this.curr_table = curr_table;
        this.child_table = new SymbolTable();

        if(object_type == "class" || object_type == "method") {
            try {
                this.child_table = new SymbolTable();
                this.child_table.setParent(curr_table);
            }
            catch (Exception e) {
            }
            
        }

        if(object_type == "method") {
            this.param_types = param_types;
        }
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.object_type;
    }
}