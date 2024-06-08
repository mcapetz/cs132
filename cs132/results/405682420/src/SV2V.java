import java.io.InputStream;
import java.io.IOException;
import java.util.HashMap;

import IR.ParseException;
import IR.SparrowParser;
import IR.registers.Registers;
import IR.syntaxtree.Node;
import IR.visitor.SparrowVConstructor;
import sparrowv.Program;


public class SV2V {
  public static void main(String[] args) throws ParseException, IOException {
    
    
    try { 
        Registers.SetRiscVregs();
        InputStream in = System.in;
        new SparrowParser(in);
        Node root = SparrowParser.Program();
        SparrowVConstructor constructor = new SparrowVConstructor();
        root.accept(constructor);
        Program program = constructor.getProgram();

        EnvBuilder envBuilder = new EnvBuilder();
        envBuilder.visit(program);

        SV2RConverter sv2r = new SV2RConverter(envBuilder.getLocalMap(), envBuilder.getStackMap());
        sv2r.visit(program);

    }
    catch (Exception e) {
        System.err.println("Err: " + e);
    }


  }
}