import java.util.*;
import sparrowv.*;
import sparrowv.visitor.*;
import IR.token.*;

public class RA {
    private static final String[] PARAMETER_REGISTERS = {"a2", "a3", "a4", "a5", "a6", "a7"};
    private static final String[] CALLEE_SAVE_REGISTERS = {"s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s10", "s11"};
    private static final String[] CALLER_SAVE_REGISTERS = {"t0", "t1", "t2", "t3", "t4", "t5"};
    
    public Map<String, Map<String, String>> allocationMap = new HashMap<>();
    private LA livenessAnalyzer;

    public RA(LA livenessAnalyzer) {
        this.livenessAnalyzer = livenessAnalyzer;
    }

    public void allocate() {
        Map<String, Map<String, LI>> allIntervals = livenessAnalyzer.getAllIntervals();

        for (String functionName : allIntervals.keySet()) {
            Map<String, LI> intervals = allIntervals.get(functionName);
            allocateForFunction(functionName, intervals);
        }
    }

    private void allocateForFunction(String functionName, Map<String, LI> intervals) {
        List<VariableInterval> variableIntervals = new ArrayList<>();
        for (Map.Entry<String, LI> entry : intervals.entrySet()) {
            String variableType = getVariableType(entry.getKey()); // Implement this method
            variableIntervals.add(new VariableInterval(entry.getKey(), entry.getValue(), variableType));
        }

        // Sort intervals by start time
        Collections.sort(variableIntervals, Comparator.comparingInt(o -> o.interval.getStartTime()));

        Map<String, String> registerAllocation = new HashMap<>();
        PriorityQueue<VariableInterval> active = new PriorityQueue<>(Comparator.comparingInt(o -> o.interval.getEndTime()));

        Set<String> availableParameterRegisters = new LinkedHashSet<>(Arrays.asList(PARAMETER_REGISTERS));
        Set<String> availableCalleeSaveRegisters = new LinkedHashSet<>(Arrays.asList(CALLEE_SAVE_REGISTERS));
        Set<String> availableCallerSaveRegisters = new LinkedHashSet<>(Arrays.asList(CALLER_SAVE_REGISTERS));

        for (VariableInterval vi : variableIntervals) {
            // Expire old intervals
            Iterator<VariableInterval> it = active.iterator();
            while (it.hasNext()) {
                VariableInterval activeVar = it.next();
                if (activeVar.interval.getEndTime() < vi.interval.getStartTime()) {
                    String register = registerAllocation.get(activeVar.variable);
                    if (Arrays.asList(PARAMETER_REGISTERS).contains(register)) {
                        availableParameterRegisters.add(register);
                    } else if (Arrays.asList(CALLEE_SAVE_REGISTERS).contains(register)) {
                        availableCalleeSaveRegisters.add(register);
                    } else if (Arrays.asList(CALLER_SAVE_REGISTERS).contains(register)) {
                        availableCallerSaveRegisters.add(register);
                    }
                    it.remove();
                }
            }

            String assignedRegister = null;
            if (vi.variableType.equals("parameter")) {
                if (!availableParameterRegisters.isEmpty()) {
                    assignedRegister = availableParameterRegisters.iterator().next();
                    availableParameterRegisters.remove(assignedRegister);
                } else {
                    // Handle register spilling if needed
                }
            } else if (vi.variableType.equals("calleeSave")) {
                if (!availableCalleeSaveRegisters.isEmpty()) {
                    assignedRegister = availableCalleeSaveRegisters.iterator().next();
                    availableCalleeSaveRegisters.remove(assignedRegister);
                } else {
                    // Handle register spilling if needed
                }
            } else if (vi.variableType.equals("callerSave")) {
                if (!availableCallerSaveRegisters.isEmpty()) {
                    assignedRegister = availableCallerSaveRegisters.iterator().next();
                    availableCallerSaveRegisters.remove(assignedRegister);
                } else {
                    // Handle register spilling if needed
                }
            }

            if (assignedRegister == null) {
                // Spill if no registers are available
                VariableInterval spill = active.poll();
                assignedRegister = registerAllocation.get(spill.variable);
                registerAllocation.put(vi.variable, assignedRegister);
            }

            registerAllocation.put(vi.variable, assignedRegister);
            active.add(vi);
        }

        allocationMap.put(functionName, registerAllocation);
    }


    public Register getRegister(String functionName, String variable) {
        // System.err.println("in get reg: " + functionName + " : " + variable);
        return new Register(allocationMap.get(functionName).get(variable));
    }

    public void printAllocationMap() {
        for (Map.Entry<String, Map<String, String>> functionEntry : allocationMap.entrySet()) {
            String functionName = functionEntry.getKey();
            Map<String, String> registerAllocation = functionEntry.getValue();

            System.err.println("Function: " + functionName);
            for (Map.Entry<String, String> variableEntry : registerAllocation.entrySet()) {
                String variable = variableEntry.getKey();
                String register = variableEntry.getValue();
                System.err.println("  " + variable + " -> " + register);
            }
        }
        System.err.println("---");
    }

    private String getVariableType(String variable) {
    // Implement logic to determine the type of the variable.
    // This could involve looking up the variable in some metadata structure.
    // For example:
    // if (metadata.isParameter(variable)) {
    //     return "parameter";
    // } else if (metadata.isLocal(variable)) {
    //     return "calleeSave";
    // } else {
    //     return "callerSave";
    // }

    // System.err.println("variable: " + variable);

    // Dummy implementation for illustration:
    if (variable.startsWith("v")) {
        return "callerSave";
    } else if (variable.startsWith("w")) {
        return "calleeSave";
    } else {
        return "parameter";
    }

    // return "callerSave";
}




    private static class VariableInterval {
        String variable;
        LI interval;
        String variableType;

        VariableInterval(String variable, LI interval, String variableType) {
            this.variable = variable;
            this.interval = interval;
            this.variableType = variableType;
        }
    }

}
