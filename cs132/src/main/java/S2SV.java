import java.io.InputStream;
import java.util.*;
import IR.ParseException;
import IR.SparrowParser;
import IR.syntaxtree.Node;
import IR.visitor.SparrowConstructor;
import sparrow.Program;

public class S2SV {
    public static void main(String[] args) {

        try {
            new SparrowParser(System.in);
            Node root = SparrowParser.Program();
            SparrowConstructor constructor = new SparrowConstructor();
            root.accept(constructor);
            Program program = constructor.getProgram();

            LA liveness_analysis = new LA();
            liveness_analysis.visit(program);

            // Map<String, Map<String, LI>> func_to_interval = liveness_analysis.getAllIntervals();

            RA2 register_allocator = new RA2(liveness_analysis);
            register_allocator.allocateRegisters();

            register_allocator.printAllocation();

            SVC2 sparrow_v_converter = new SVC2(register_allocator, liveness_analysis.getAllIntervals());
            sparrow_v_converter.visit(program);

            System.err.println("Done!");

        } catch (Exception e) {
            System.err.println("Error: " + e);
        }

    }

}