
import IR.token.*;
import sparrowv.*;
import java.util.*;
import sparrowv.visitor.*;

public class EnvBuilder extends DepthFirst {
    Integer local_var_offset;
    String curr_func;
    // using strings instead of identifiers
    private Map<String, Map<String, Integer>> localMap = new HashMap<>();
    private Map<String, Integer> stackMap = new HashMap<>();
    private Map<String, Integer> funcMap = new HashMap<>();

    // default constructor
    public EnvBuilder() {

    }

    public void visit(Program n) {
        for (FunctionDecl fd : n.funDecls) {
            this.visit(fd);
        }

        // printMaps();
    }

    public void visit(FunctionDecl n) {
        funcMap = new HashMap<>();
        curr_func = n.functionName.toString();
        if(curr_func == "main") {
        curr_func = "Main";
        }

        // go thru params
        local_var_offset = 8;
        int p_offset = 0;
        for (Identifier p : n.formalParameters) {
            funcMap.put(p.toString(), p_offset);
            p_offset += 4;
        }
        this.visit(n.block);


        stackMap.put(curr_func, local_var_offset);
        localMap.put(curr_func, funcMap);
    }

    public void visit(Block n) {
        for (Instruction i : n.instructions) {
            if (i instanceof Move_Id_Reg) {
                String id_string = ((Move_Id_Reg) i).lhs.toString();
                if (!funcMap.containsKey(id_string)) { 
                    local_var_offset += 4; 
                    funcMap.put(id_string, -1 * local_var_offset);
                }
            }
        }
    }

    // Getters
    public Map<String, Map<String, Integer>> getLocalMap() {
        return localMap;
    }
    public Map<String, Integer> getStackMap() {
        return stackMap;
    }

    // printer to delete later
    public void printMaps() {
        System.err.println("Local Variable Map:");
        for (Map.Entry<String, Map<String, Integer>> entry : localMap.entrySet()) {
            System.err.println("Function: " + entry.getKey());
            System.err.println("Local Variables:");
            for (Map.Entry<String, Integer> varEntry : entry.getValue().entrySet()) {
                System.err.println(varEntry.getKey() + " -> " + varEntry.getValue());
            }
        }
        System.err.println("Stack Allocation Map:");
        for (Map.Entry<String, Integer> entry : stackMap.entrySet()) {
            System.err.println("Function: " + entry.getKey() + ", Stack Size: " + entry.getValue());
        }
    }


}
