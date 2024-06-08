import java.util.*;
import IR.token.*;
import sparrowv.*;
import java.util.*;
import sparrowv.visitor.*;

public class Environment {
    private int numLocalVariables;
    private int numFormalParameters;
    private Map<Identifier, Register> formalParameterMap;
    private Map<Identifier, Register> localVariableMap;

    public Environment(int numLocalVariables, int numFormalParameters) {
        this.numLocalVariables = numLocalVariables;
        this.numFormalParameters = numFormalParameters;
        this.formalParameterMap = new HashMap<>();
        this.localVariableMap = new HashMap<>();
    }

    // Getters and setters
    public int getNumLocalVariables() {
        return numLocalVariables;
    }

    public void setNumLocalVariables(int numLocalVariables) {
        this.numLocalVariables = numLocalVariables;
    }

    public int getNumFormalParameters() {
        return numFormalParameters;
    }

    public void setNumFormalParameters(int numFormalParameters) {
        this.numFormalParameters = numFormalParameters;
    }

    public Map<Identifier, Register> getFormalParameterMap() {
        return formalParameterMap;
    }

    public Map<Identifier, Register> getLocalVariableMap() {
        return localVariableMap;
    }

    public void setFormalParameterMap(Map<Identifier, Register> formalParameterMap) {
        this.formalParameterMap = formalParameterMap;
    }

    public void setLocalVariableMap(Map<Identifier, Register> localVariableMap) {
        this.localVariableMap = localVariableMap;
    }
}
