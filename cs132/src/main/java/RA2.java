import java.util.*;
import IR.token.*;

public class RA2 {

    List<Register> registers = new ArrayList<>();
    public Deque<Register> availableRegisters = new ArrayDeque<>();

    public Map<String, Map<String, Register>> allocationMap = new HashMap<>();
    public Map<Register, Boolean> reusedRegs = new HashMap<>();
    private LA livenessAnalyzer;

    public RA2(LA livenessAnalyzer) {
        
        this.livenessAnalyzer = livenessAnalyzer;
        initRegs();

    }

    public void initRegs() {
        registers.clear();
        registers.add(new Register("t0"));
        registers.add(new Register("t1"));
        registers.add(new Register("t2"));
        registers.add(new Register("t3"));
        registers.add(new Register("t4"));
        registers.add(new Register("t5"));
        registers.add(new Register("s1"));
        registers.add(new Register("s2"));
        registers.add(new Register("s3"));
        registers.add(new Register("s4"));
        registers.add(new Register("s5"));
        registers.add(new Register("s6"));
        registers.add(new Register("s7"));
        registers.add(new Register("s8"));
        registers.add(new Register("s9"));
        registers.add(new Register("s10"));
        registers.add(new Register("s11"));
        // registers.add(new Register("a2"));
        // registers.add(new Register("a3"));
        // registers.add(new Register("a4"));
        // registers.add(new Register("a5"));
        // registers.add(new Register("a6"));
        // registers.add(new Register("a7"));

        availableRegisters.clear();

        Collections.addAll(availableRegisters, new Register("t0"),new Register("t1"),new Register("t2"),
                new Register("t3"), new Register("t4"), new Register("t5"), new Register("s1"), new Register("s2"),
                new Register("s3"), new Register("s4"), new Register("s5"), new Register("s6"), new Register("s7"),
                new Register("s8"), new Register("s9"), new Register("s10"), new Register("s11"));
                
                // , new Register("a2"),
                // new Register("a3"), new Register("a4"), new Register("a5"), new Register("a6"), new Register("a7"));
    }

    public void allocateRegisters() {
        Map<String, Map<String, LI>> allIntervals = livenessAnalyzer.getAllIntervals();
        
        Map<String, Map<String, Register>> functionAllocation = new HashMap<>();

        for (Map.Entry<String, Map<String, LI>> functionEntry : allIntervals.entrySet()) {
            String functionName = functionEntry.getKey();
            Map<String, LI> functionIntervals = functionEntry.getValue();
            // Map<String, Register> allocation = allocateRegistersForFunction(functionIntervals);
            Map<String, Register> allocation = allocateRegistersForFunctionGraph(functionIntervals);
            functionAllocation.put(functionName, allocation);

            // move used regs to back of dequeue
            for(Register r : allocation.values()) {
                availableRegisters.remove(r);
                availableRegisters.addLast(r);
            }
        }

        initRegs();
        allocationMap = functionAllocation;
    }

    private Map<String, Register> allocateRegistersForFunction(Map<String, LI> intervals) {
        Map<String, Register> allocation = new HashMap<>();
        List<Map.Entry<String, LI>> intervalEntries = new ArrayList<>(intervals.entrySet());

        // Sort intervals by start time
        intervalEntries.sort(Comparator.comparingInt(e -> e.getValue().getStartTime()));

        // Allocate registers to intervals
        PriorityQueue<Map.Entry<String, LI>> activeIntervals = new PriorityQueue<>(
                Comparator.comparingInt(e -> e.getValue().getEndTime()));

        for (Map.Entry<String, LI> intervalEntry : intervalEntries) {
            // Expire old intervals
            while (!activeIntervals.isEmpty()
                    && activeIntervals.peek().getValue().getEndTime() < intervalEntry.getValue().getStartTime()) {
                Map.Entry<String, LI> expired = activeIntervals.poll();
                Register reg = allocation.get(expired.getKey());
                availableRegisters.addFirst(reg); // Add expired register back to the front of the deque
            }

            if (!availableRegisters.isEmpty()) {
                Register reg = availableRegisters.pollFirst(); // Use the most recently available register
                allocation.put(intervalEntry.getKey(), reg);
                activeIntervals.add(intervalEntry);
            } else {
                // Spill the interval with the latest end time
                Map.Entry<String, LI> spill = activeIntervals.poll();
                Register reg = allocation.remove(spill.getKey());
                allocation.put(intervalEntry.getKey(), reg);
                activeIntervals.add(intervalEntry);

                // Spilling variable to stack
                System.err.println("Spilling variable: " + spill.getKey());
            }
        }

        return allocation;
    }

    private Map<String, Register> allocateRegistersForFunctionGraph(Map<String, LI> intervals) {
        
        
        // init graph
        IntGraph int_graph = new IntGraph();

        // add to graph
        for( String v : intervals.keySet()) {
            int_graph.addNode(v);
        }

        // add edges based on overlapping intervals
        for (Map.Entry<String, LI> ent_1 : intervals.entrySet()) {
            String v1 = ent_1.getKey();
            LI int_1 = ent_1.getValue();

            for(Map.Entry<String, LI> ent_2 : intervals.entrySet()) {
                String v2 = ent_2.getKey();
                if(v1.equals(v2)) {
                    continue; // same var, don't need to look at overlap
                }

                LI int_2 = ent_2.getValue();
                if(int_1.overlapsWith(int_2)) {
                    int_graph.addEdge(v1, v2); // add since the edges overlap
                }
            }
        }

        return graphColoringOpt2(int_graph, new ArrayList<>(availableRegisters));

    }

    
    public Map<String, Register> graphColoringOpt2(IntGraph graph, List<Register> regs) {
        Map<String, Register> allocation = new HashMap<>();
        Deque<String> stack = new ArrayDeque<>();
        Set<String> spilled = new HashSet<>();
        Map<String, Integer> degree = new HashMap<>();
        Map<String, Double> spillCost = new HashMap<>();

        // Calculate initial degree and spill cost
        for (String node : graph.getNodes()) {
            degree.put(node, graph.getNeighbors(node).size());
            spillCost.put(node, calculateSpillCost(node)); // Implement this method to calculate spill cost
        }

        List<String> nodes = new ArrayList<>(graph.getNodes());
        nodes.sort(Comparator.comparingDouble(spillCost::get).reversed());

        // Simplification phase
        while (!nodes.isEmpty()) {
            String node = nodes.remove(nodes.size() - 1);
            if (degree.get(node) < regs.size()) {
                stack.push(node);
            } else {
                spilled.add(node);
            }
        }

        // Spilling phase (if necessary)
        while (!spilled.isEmpty()) {
            String node = spilled.iterator().next();
            spilled.remove(node);
            stack.push(node);
        }

        // Selection phase
        while (!stack.isEmpty()) {
            String node = stack.pop();
            Set<Register> dont_use = new HashSet<>();
            for (String neighbor : graph.getNeighbors(node)) {
                if (allocation.containsKey(neighbor)) {
                    dont_use.add(allocation.get(neighbor));
                }
            }

            for (Register reg : regs) {
                if (!dont_use.contains(reg)) {
                    allocation.put(node, reg);
                    break;
                }
            }

            if (!allocation.containsKey(node)) {
                // All registers are used, spill one
                System.err.println("Spilling variable: " + node);
                allocation.put(node, regs.get(0)); // Assign to a spill register or handle accordingly
            }
        }

        return allocation;
    }

    private double calculateSpillCost(String variable) {
        // Implement your spill cost calculation based on variable usage and other factors
        return 1.0; // Placeholder
    }

    public void printAllocation() {
        for (Map.Entry<String, Map<String, Register>> functionEntry : allocationMap.entrySet()) {
            String functionName = functionEntry.getKey();
            Map<String, Register> variableAllocation = functionEntry.getValue();
            System.err.println("Function: " + functionName);
            for (Map.Entry<String, Register> varEntry : variableAllocation.entrySet()) {
                System.err.println(
                        "  Variable: " + varEntry.getKey() + " -> Register: " + varEntry.getValue().toString());
            }
        }
    }

    public List<Register> getUnusedRegisters() {
        return new ArrayList<>(availableRegisters);
    }

    public class IntGraph {
        private Map<String, Set<String>> graph = new HashMap<>();

        public void addNode(String n) {
            graph.putIfAbsent(n, new HashSet<>());
        }

        public void addEdge(String n1, String n2) {
            graph.get(n1).add(n2);
            graph.get(n2).add(n1);
        }

        public Set<String> getNeighbors(String n) {
            return graph.getOrDefault(n, Collections.emptySet());
        }

        public Set<String> getNodes() {
            return graph.keySet();
        }
    }

}