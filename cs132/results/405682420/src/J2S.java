import minijava.MiniJavaParser;

import minijava.syntaxtree.*;

import sparrow.Program;

import java.util.HashMap;
import java.util.Map;


public class J2S{
  public static void main(String [] args) {
        Goal root;
        Program program = new sparrow.Program();
        String program_string = "";
        String method_string = "";
        Map<String, StringPair> method_ret_label = new HashMap<>();
         Map<String, String> type_to_w = new HashMap<>();

        NameGenerator name_generator = new NameGenerator();
        

        try {
          root = new MiniJavaParser(System.in).Goal();
          
          // build symbol table
          Visitor1 visitor1 = new Visitor1(); // visitor 1 builds the symbol table
          root.accept(visitor1);
          
          SymbolTable symbol_table = visitor1.getSymbolTable();
          VisitorMethodDec visitorMethodDec = new VisitorMethodDec(symbol_table);
          root.accept(visitorMethodDec); // 
          System.err.println("I got here 1");
          Visitor2 visitor2 = new Visitor2(symbol_table);
          root.accept(visitor2);
          System.err.println("I got here now");

        //   System.out.println("sym table");
        //   symbol_table.printSymbolTable(symbol_table, 0);
        //   System.out.println("-");

          // now try to generate sparrow
        //   symbol_table.printSymbolTable(symbol_table, 0);
        //   System.err.println("-");
          J2SMethodDec visitorMet = new J2SMethodDec(symbol_table, name_generator);
          method_string = root.accept(visitorMet);
        //   System.out.println("I got here 2");
        //   System.out.println("method res:");
        //   System.out.println(method_string);
        System.err.println("i am after method dec");
          method_ret_label = visitorMet.method_ret_label;
        //   System.out.println(method_ret_label.keySet());
          J2SVisitor2 j2svisitor2 = new J2SVisitor2(symbol_table, name_generator, method_ret_label); // visitor 1 builds the symbol table
          System.err.println("I got here 3");
          program_string += root.accept(j2svisitor2);
        //   System.out.println("I got here 2");
          program_string += "\n" + method_string;
          
        //   System.out.println("res: \n-");
          System.out.println(program_string);

        }
        catch(Exception e) {
          System.out.println("Error: " + e);
        }
        

        
  }

  
}
