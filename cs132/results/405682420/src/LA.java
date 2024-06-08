
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import IR.token.Identifier;
import sparrow.*;
import sparrow.visitor.*;

public class LA extends DepthFirst {

    private List<Instruction> instructions = new ArrayList<>();
    private Map<String, Map<String, LI>> func_to_interval = new HashMap<>();
    private Map<String, LI> curr_func_intervals;

 // solve looping problem
    private Map<String, Integer> visited_labels = new HashMap<>();
    private List<int[]> loop_ranges = new ArrayList<>();

// looping prolbem
    public void visit(LabelInstr n) {
        visited_labels.put(n.label.toString(), instructions.size());
    }

    // loop adjust

    public void adjust_loops() {
        for(Map.Entry<String, Map<String, LI>> func_ : func_to_interval.entrySet()) {
            Map<String, LI> func_ints = func_.getValue();
            for(LI interval : func_ints.values()) {
                for(int[] range : loop_ranges) {
                    Integer loop_begin = range[0];
                    Integer loop_end = range[1];

                    if(interval.getEndTime() >= loop_begin && interval.getEndTime() <= loop_end && interval.getStartTime() < loop_begin) {
                        // interval is partially inside loop range
                        // so we set the end early to adjust
                        interval.setEndTime(loop_end);
                        break; // don't check the rest
                    }
                }
            }
        }
    }

    /* List<FunctionDecl> funDecls; */
    @Override
    public void visit(Program n) {
        for (FunctionDecl fd : n.funDecls) {
            curr_func_intervals = new HashMap<>();
            func_to_interval.put(fd.functionName.toString(), curr_func_intervals);
            fd.accept(this);
        }

        adjust_loops();
    }

    /*
     * Program parent;
     * FunctionName functionName;
     * List<Identifier> formalParameters;
     * Block block;
     */
    @Override

    public void visit(FunctionDecl n) {
        for (Identifier fp : n.formalParameters) {
            // ... fp ...
            recordIDUsage(fp);
        }
        n.block.accept(this);
    }

    /*
     * FunctionDecl parent;
     * List<Instruction> instructions;
     * Identifier return_id;
     */
    @Override

    public void visit(Block n) {
        for (Instruction i : n.instructions) {
            instructions.add(i);
            i.accept(this);
        }
        // update end point of its interval
        recordIDUsage(n.return_id);
        // System.err.println("Printing==========");
        printIntervalsMap();
        printLiveIntervals();
    }

    // /* Label label; */
    // public void visit(LabelInstr n) {
    // }

    /*
     * Identifier lhs;
     * int rhs;
     */
    @Override

    public void visit(Move_Id_Integer n) {
        // close and start a new one
        tryCloseCurrentInterval(n.lhs);
        tryStartNewInterval(n.lhs);
    }

    /*
     * Identifier lhs;
     * FunctionName rhs;
     */
    @Override

    public void visit(Move_Id_FuncName n) {
        tryCloseCurrentInterval(n.lhs);
        tryStartNewInterval(n.lhs);
    }

    /*
     * Identifier lhs;
     * Identifier arg1;
     * Identifier arg2;
     */
    @Override

    public void visit(Add n) {
        tryCloseCurrentInterval(n.lhs);
        tryStartNewInterval(n.lhs);
        recordIDUsage(n.arg1);
        recordIDUsage(n.arg2);
    }

    /*
     * Identifier lhs;
     * Identifier arg1;
     * Identifier arg2;
     */
    @Override

    public void visit(Subtract n) {
        tryCloseCurrentInterval(n.lhs);
        tryStartNewInterval(n.lhs);
        recordIDUsage(n.arg1);
        recordIDUsage(n.arg2);
    }

    /*
     * Identifier lhs;
     * Identifier arg1;
     * Identifier arg2;
     */
    @Override

    public void visit(Multiply n) {
        tryCloseCurrentInterval(n.lhs);
        tryStartNewInterval(n.lhs);
        recordIDUsage(n.arg1);
        recordIDUsage(n.arg2);
    }

    /*
     * Identifier lhs;
     * Identifier arg1;
     * Identifier arg2;
     */
    @Override

    public void visit(LessThan n) {
        tryCloseCurrentInterval(n.lhs);
        tryStartNewInterval(n.lhs);
        recordIDUsage(n.arg1);
        recordIDUsage(n.arg2);
    }

    /*
     * Identifier lhs;
     * Identifier base;
     * int offset;
     */
    @Override

    public void visit(Load n) {
        tryCloseCurrentInterval(n.lhs);
        tryStartNewInterval(n.lhs);
        recordIDUsage(n.base);
    }

    /*
     * Identifier base;
     * int offset;
     * Identifier rhs;
     */
    @Override

    public void visit(Store n) {
        tryCloseCurrentInterval(n.base);
        tryCloseCurrentInterval(n.rhs);
        tryStartNewInterval(n.base);
        recordIDUsage(n.rhs);
    }

    /*
     * Identifier lhs;
     * Identifier rhs;
     */
    @Override

    public void visit(Move_Id_Id n) {
        tryCloseCurrentInterval(n.lhs);
        tryStartNewInterval(n.lhs);
        recordIDUsage(n.rhs);
    }

    /*
     * Identifier lhs;
     * Identifier size;
     */
    @Override

    public void visit(Alloc n) {
        tryCloseCurrentInterval(n.lhs);
        tryStartNewInterval(n.lhs);
        recordIDUsage(n.size);
    }

    /* Identifier content; */
    @Override

    public void visit(Print n) {
        recordIDUsage(n.content);
    }

    @Override

    public void visit(IfGoto n) {
        recordIDUsage(n.condition);
        String label_ = n.label.toString();
        if(visited_labels.containsKey(label_)) {
            // we found a loop
            Integer instr_ct = visited_labels.get(label_);
            int[] range_ = new int[2];
            range_[0] = instr_ct;
            range_[1] = instructions.size();
            loop_ranges.add(range_);
        }
    }

    public void visit(Goto n) {
        String label_ = n.label.toString();
        if(visited_labels.containsKey(label_)) {
            // we found a loop
            Integer instr_ct = visited_labels.get(label_);
            int[] range_ = new int[2];
            range_[0] = instr_ct;
            range_[1] = instructions.size();
            loop_ranges.add(range_);
        }
    }

    

    /*
     * Identifier lhs;
     * Identifier callee;
     * List<Identifier> args;
     */
    @Override

    public void visit(Call n) {
        tryCloseCurrentInterval(n.lhs);
        tryStartNewInterval(n.lhs);
        recordIDUsage(n.callee);
        for (Identifier arg : n.args) {
            recordIDUsage(arg);
        }
    }

    private void recordIDUsage(Identifier id) {
        if (id == null) {
            return;
        }
            
        Integer curr_idx = instructions.size() - 1;
        String id_name = id.toString();

        LI interval = curr_func_intervals.get(id_name);
        if (interval == null) {
            interval = new LI();
            interval.startInstructionTime(curr_idx);
            curr_func_intervals.put(id_name, interval);
        }

        interval.endInstructionTime(curr_idx);
    }

    private void tryStartNewInterval(Identifier id) {
        Integer curr_idx = instructions.size() - 1;
        String id_name = id.toString();
        LI interval = curr_func_intervals.get(id_name);
        

        if (interval == null) {
            interval = new LI();
            interval.startInstructionTime(curr_idx);
            interval.endInstructionTime(curr_idx);
            curr_func_intervals.put(id_name, interval);
        }
    }

    private void tryCloseCurrentInterval(Identifier id) {
        Integer curr_idx = instructions.size() - 1;
        String id_name = id.toString();
        LI interval = curr_func_intervals.get(id_name);
        

        if (interval != null && interval.getEndTime() != curr_idx) {
            interval.endInstructionTime(curr_idx);
        }
    }

    // delete later 

    public void printLiveIntervalsStraightforward() {
        for (Map<String, LI> functionIntervals : func_to_interval.values()) {
            for (Map.Entry<String, LI> entry : functionIntervals.entrySet()) {
                String id = entry.getKey();
                LI interval = entry.getValue();
                if (interval.getStartTime() != Integer.MAX_VALUE && interval.getEndTime() != Integer.MIN_VALUE) {
                    System.err.println(id + ": " + interval.getStartTime() + " - " + interval.getEndTime());
                }
            }
        }
    }

    

    public void printIntervalsMap() {
        for (Map<String, LI> functionIntervals : func_to_interval.values()) {
            for (Map.Entry<String, LI> entry : functionIntervals.entrySet()) {
                String id = entry.getKey();
                LI interval = entry.getValue();
                System.err.println(id + ": " + interval.getStartTime() + " - " + interval.getEndTime());
            }
        }
    }

    public void printLiveIntervals() {
        for (Map.Entry<String, Map<String, LI>> functionEntry : func_to_interval.entrySet()) {
            String functionName = functionEntry.getKey();
            Map<String, LI> functionIntervals = functionEntry.getValue();
            Integer max_time = getMaxTimeOfAllIntervals(functionIntervals);

            // Print function name
            System.err.println("Function: " + functionName);

            // Print column headers
            System.err.print("interval time ");
            for (Integer i = 0; i <= max_time; i++) {
                System.err.print(String.format("%-5d", i));
            }
            System.err.println();

            // Print intervals for each identifier
            for (Map.Entry<String, LI> entry : functionIntervals.entrySet()) {
                String id = entry.getKey();
                LI interval = entry.getValue();

                // Print identifier
                System.err.print(String.format("%-10s", id + ": "));
                System.err.print("    ");

                // Print stars for each time point
                for (Integer i = 0; i <= max_time; i++) {
                    if (interval.containsTime(i)) {
                        System.err.print("*");
                    } else {
                        System.err.print(" ");
                    }
                }
                System.err.println();
            }
            System.err.println();
        }
    }
    // end of delete later

    // Helper method to find the maximum time in all intervals
    private Integer getMaxTimeOfAllIntervals(Map<String, LI> functionIntervals) {
        Integer max_time = 0;
        for (LI interval : functionIntervals.values()) {
            max_time = Math.max(max_time, interval.getEndTime());
        }
        return max_time;
    }

    public Map<String, Map<String, LI>> getAllIntervals() {
        return func_to_interval;
    }

}
