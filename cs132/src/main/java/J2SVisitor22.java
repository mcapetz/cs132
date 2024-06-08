import minijava.visitor.*;

import minijava.syntaxtree.*;

import java.util.Enumeration;

import java.util.ArrayList;
import java.util.List;

import sparrow.Program;

import java.io.PrintStream;

import java.util.HashMap;
import java.util.Map;
import java.util.*;




/**
 * Provides default methods which visit each node in the tree in depth-first
 * order.  Your visitors may extend this class.
 */
public class J2SVisitor22 extends GJNoArguDepthFirst<String> implements GJNoArguVisitor<String>{

   public SymbolTable symbol_table = new SymbolTable();
   public SymbolTable root_table = new SymbolTable();
   public Symbol curr_sym;
   public Symbol parent_sym;
   public Symbol method_sym;
   public List<String> temp_array = new ArrayList<>();
   public boolean add_null_label = false;
   public String null_label = "";
   public boolean alloc_happened = false;
   public String alloc_w = null;

   public Map<String, StringPair> method_ret_label = new HashMap<>();

   public Map<String, String> mem_var_map = new HashMap<>();

   public Boolean in_assign = false;
   public Boolean in_main = false;


   // new stuff
   Program program;
//    int name_count = 0;
   String program_string = "";

   NameGenerator name_generator;


   public J2SVisitor22(SymbolTable symbol_table, NameGenerator name_generator, Map<String, StringPair> method_ret_label) {
    //   this.program = program;
    this.name_generator = name_generator;
    this.symbol_table = symbol_table;
    this.root_table = symbol_table;
    this.method_ret_label = method_ret_label;
    //   PrintStream output = new PrintStream("P.sparrow");
   }

   

    /**
    * f0 -> MainClass()
    * f1 -> ( TypeDeclaration() )*
    * f2 -> <EOF>
    */
    @Override
   public String visit(Goal n) {
      program_string += "func Main()\n";
      String main = n.f0.accept(this);
      String typeDecs = n.f1.accept(this);
      program_string += "goto main_end\n";
    //   if(add_null_label) {
    //     program_string += null_label + ":\n";
    //     program_string += "error(\"null pointer\")\n";
    //   }
      program_string += "main_end:\n";
      String name = name_generator.generateName();
      program_string += name + " = 0 \n";
      program_string += "return " + name;
      return program_string; // maybe?
   }

   /**
    * f0 -> "class"
    * f1 -> Identifier()
    * f2 -> "{"
    * f3 -> "public"
    * f4 -> "static"
    * f5 -> "void"
    * f6 -> "main"
    * f7 -> "("
    * f8 -> "String"
    * f9 -> "["
    * f10 -> "]"
    * f11 -> Identifier()
    * f12 -> ")"
    * f13 -> "{"
    * f14 -> ( VarDeclaration() )*
    * f15 -> ( Statement() )*
    * f16 -> "}"
    * f17 -> "}"
    */
    @Override
   public String visit(MainClass n) {
      in_main = true;
      
      String class_name = ((Identifier)n.f1).f0.toString();
      this.curr_sym = symbol_table.getType(class_name);
      n.f14.accept(this);
      n.f15.accept(this);
      this.curr_sym = null;
      in_main = false;
      return null;
   }

   /**
    * f0 -> ClassDeclaration()
    *       | ClassExtendsDeclaration()
    */
    @Override
   public String visit(TypeDeclaration n) {
      n.f0.accept(this);
      return null;
   }

   /**
    * f0 -> "class"
    * f1 -> Identifier()
    * f2 -> "{"
    * f3 -> ( VarDeclaration() )*
    * f4 -> ( MethodDeclaration() )*
    * f5 -> "}"
    */
    @Override
   public String visit(ClassDeclaration n) {
      String D = n.f1.accept(this);
      Symbol sym = symbol_table.getType(D);
      
      SymbolTable new_table = sym.child_table;
      // System.out.println("here");
      
      // set the parent
      new_table.setParent(symbol_table);
      if(sym == null) {
         throw new RuntimeException("sym is null in class dec");
      }
      sym.parent_table = symbol_table;

      SymbolTable old_table = symbol_table;
      this.symbol_table = new_table;
    //   curr_sym = sym;
      n.f3.accept(this); // var dec
      n.f4.accept(this); // method dec
      this.symbol_table = old_table;
      return null;
   }

   /**
    * f0 -> "class"
    * f1 -> Identifier()
    * f2 -> "extends"
    * f3 -> Identifier()
    * f4 -> "{"
    * f5 -> ( VarDeclaration() )*
    * f6 -> ( MethodDeclaration() )*
    * f7 -> "}"
    */
    @Override
   public String visit(ClassExtendsDeclaration n) {
      System.err.println("i got here class extends");
        n.f1.accept(this);
        String classname = visit(n.f1);
        Symbol sym = symbol_table.getType(classname);
      //   System.out.println("i got here???");
        SymbolTable new_table = sym.child_table;

        // set the parent
        new_table.setParent(symbol_table);
        new_table.setParentName(n.f3.f0.toString());

        // handle scoping
        SymbolTable old_table = symbol_table;
        symbol_table = new_table;
        visit(n.f5);
        visit(n.f6);
        symbol_table = old_table;
        return null;
   }

   /**
    * f0 -> Type()
    * f1 -> Identifier()
    * f2 -> ";"
    */
    @Override
   public String visit(VarDeclaration n) { // pass over
    // System.out.println("I got to var dec!!!!!!!!!");
      String _ret=null;
      n.f0.accept(this);
      String t1 = n.f1.accept(this);
    //   System.out.println("t1: " + t1);
      n.f2.accept(this);
      return _ret;
   }

   /**
    * f0 -> "public"
    * f1 -> Type()
    * f2 -> Identifier()
    * f3 -> "("
    * f4 -> ( FormalParameterList() )? // params
    * f5 -> ")"
    * f6 -> "{"
    * f7 -> ( VarDeclaration() )* // local vars need to accept this
    * f8 -> ( Statement() )* // check the type of this - so do an accept
    * f9 -> "return"
    * f10 -> Expression() // check the type of this - so do an accept
    * f11 -> ";"
    * f12 -> "}"
    */
    @Override
   public String visit(MethodDeclaration n) { 
    //   String method_name = n.f2.f0.toString();
    //   Symbol sym = symbol_table.getType(method_name);
    //   sym.child_table.name = method_name;
    //   SymbolTable old_table = symbol_table;
    //   SymbolTable new_table = sym.child_table;

    // //   // set the parent
    // //   new_table.setParent(symbol_table);
    // //   sym.parent_table = symbol_table;

    //   this.symbol_table = new_table; // handle scoping
    //   n.f7.accept(this); // var dec
    //   n.f8.accept(this); // statements
    //   String return_type = n.f10.accept(this);
    // //   if(return_type != sym.dtype) {
    // //     System.out.println("return type: " + return_type);
    // //     throw new RuntimeException("In method dec, return types don't match");
    // //   }
   
    //   this.symbol_table = old_table;
      return null;
   }

   /**
    * f0 -> ArrayType()
    *       | BooleanType()
    *       | IntegerType()
    *       | Identifier()
    */
    @Override
   public String visit(Type n) { 
      return n.f0.accept(this);
   }

   /**
    * f0 -> "int"
    * f1 -> "["
    * f2 -> "]"
    */
    @Override
   public String visit(ArrayType n) {
      return "int[]";
   }

   /**
    * f0 -> "boolean"
    */
    @Override
   public String visit(BooleanType n) {
      return "bool";
   }

   /**
    * f0 -> "int"
    */
    @Override
   public String visit(IntegerType n) {
      return "int";
   }

   /**
    * f0 -> Block()
    *       | AssignmentStatement()
    *       | ArrayAssignmentStatement()
    *       | IfStatement()
    *       | WhileStatement()
    *       | PrintStatement()
    */
    @Override
   public String visit(Statement n) {
      String ret = n.f0.accept(this);
      return ret;
   }

   /**
    * f0 -> "{"
    * f1 -> ( Statement() )*
    * f2 -> "}"
    */
    @Override
   public String visit(Block n) { 
      return n.f1.accept(this);
   }

   /**
    * f0 -> Identifier()
    * f1 -> "="
    * f2 -> Expression()
    * f3 -> ";"
    */
    @Override
   public String visit(AssignmentStatement n) {
    in_assign = true;
      String t0 = n.f0.accept(this);
      String t2 = n.f2.accept(this);

    program_string += t0 + " = " + t2 + "\n";

    String program_name = name_generator.generateNameProgram();
    program_string += "goto " + program_name + "\n";


    if(alloc_happened) {
      // System.out.println("alloc happened");
      // System.out.println("t0: " + t0);

      // now we try to find the sym
      String D = t0;

      Symbol sym3 = root_table.getType(D);

      //   System.out.println("I got here");
      //   System.out.println("D: " + D);

         
         if(sym3 == null) {
            // System.out.println("aiya");
            // System.out.println("D: " + D);
            Symbol root_sym = root_table.getType(D);
         //   System.out.println("keys: " + root_table.symbol_table.keySet());
         //   System.out.println(root_sym.dtype);
            List<String> arr = new ArrayList<>(root_table.symbol_table.keySet());

            // System.out.println("arr: " + arr);
            Boolean found = false;

            for(int i = 0; i < arr.size(); i ++) {
                  Symbol temp_sym = root_table.getType(arr.get(i));
                  // System.out.println("temp sym: " + temp_sym.name);
                  // System.out.println("hereee");
                  if(temp_sym.child_table != null) {
                     // System.out.println("hi");
                     // System.out.println(temp_sym.child_table.symbol_table.keySet());
                  }
                  
                  if(temp_sym != null && temp_sym.child_table != null && temp_sym.child_table.getType(D) != null) {
                     // System.out.println("found? " + temp_sym.child_table.getType(name).name);
                     found = true;
                     // System.out.println("found?");
                     sym3 = temp_sym.child_table.getType(D);
                     // System.out.println(sym3.name);
                  }
                  
            }

            if(!found) {
                     // System.out.println("ERROR finding: " + D);
                     sym3 = null;
                  }
         }

         // System.out.println("name: " + sym3.name);

         // String name_w = name_generator.generateNameW();
         // System.out.println("alloc_w: " + alloc_w);
         sym3.allocation_var_w = alloc_w;


        null_label = name_generator.generateNameNull();
      //   program_string += "if0 " + t0 + " goto " + null_label + "\n";
        alloc_happened = false;
    }

    if(add_null_label) {
            program_string += null_label + ":\n";
            program_string += "error(\"null pointer\")\n";
            add_null_label = false;
        }
      program_string += program_name + ":\n";


    in_assign = false;
    return null;
   }

   /**
    * f0 -> Identifier()
    * f1 -> "["
    * f2 -> Expression()
    * f3 -> "]"
    * f4 -> "="
    * f5 -> Expression()
    * f6 -> ";"
    */
    @Override
   public String visit(ArrayAssignmentStatement n) {
      String t0 = n.f0.accept(this);
      String t2 = n.f2.accept(this);
      String t5 = n.f5.accept(this);

      // need to check for neg index
      String name_zero = name_generator.generateName();
      program_string += name_zero + " = 0\n";
      String name_negative_check = name_generator.generateName();
      program_string += name_negative_check + " = " + t2 + " < " + name_zero + "\n";
      String negative_check_label = name_generator.generateNameLabel();
      program_string += "if0 " + name_negative_check + " goto " + negative_check_label + "\n";
      program_string += "error(\"array index out of bounds\")\n";
      program_string += negative_check_label + ":\n";

      String name_size = name_generator.generateName();
      program_string += name_size + " = [" + t0 + " + 0]\n";
      String name_1 = name_generator.generateName();
      program_string += name_1 + " = 1\n";
      String name_minus = name_generator.generateName();
      String name_comp = name_generator.generateName();
      program_string += name_minus + " = " + name_size + " - " + name_1 + "\n";
      program_string += name_comp + " = " + name_minus + " < " + t2 + "\n"; // oppositie?
      String program_name = name_generator.generateNameProgram();
      program_string += "if0 " + name_comp + " goto " + program_name + "\n";
      program_string += "error(\"array index out of bounds\")\n";
      program_string += program_name + ":\n";

      // now is where we assign it
      String name_index = name_generator.generateName();
      String name_for_assign = name_generator.generateName();
      String name_4 = name_generator.generateName();
      program_string += name_4 + " = 4\n";
      String index_4 = name_generator.generateName();
      String t2_plus_1 = name_generator.generateName();
      String name_one = name_generator.generateName();
      program_string += name_one + " = 1\n";
      program_string += t2_plus_1 + " = " + name_one +" + " + t2 + "\n";
      program_string += index_4 + " = " + name_4 + " * " + t2_plus_1 + "\n";
      program_string += name_index + " = " + t0 + " + " + index_4 + "\n";
      program_string += "[" + name_index + " + 0] = " + t5 + "\n";
      
      return null; 


      
   }


   /**
    * f0 -> "if"
    * f1 -> "("
    * f2 -> Expression()
    * f3 -> ")"
    * f4 -> Statement()
    * f5 -> "else"
    * f6 -> Statement()
    */
    @Override
   public String visit(IfStatement n) {
      String e = n.f2.accept(this);
      String t0 = name_generator.generateName();
      String if_label = name_generator.generateNameLabel();
      String next_label = name_generator.generateNameLabel();
      program_string += t0 + " = " + e + "\n";
    //   System.out.println("e: " + e);
      program_string += "if0 " + t0 + " goto " + if_label + "\n";
      n.f4.accept(this); // s1
      program_string += "goto " + next_label + "\n";
      program_string += if_label + ":\n";
      n.f6.accept(this); // s2
      program_string += next_label + ":\n";
      return null;
   }

   /**
    * f0 -> "while"
    * f1 -> "("
    * f2 -> Expression()
    * f3 -> ")"
    * f4 -> Statement()
    */
    @Override
   public String visit(WhileStatement n) {
      String e;
      
      String labelLoop = name_generator.generateNameLabel();
      String labelEnd = name_generator.generateNameLabel();
      String v_res = name_generator.generateName();


      program_string += labelLoop + ":\n";
      e = n.f2.accept(this);
      program_string += v_res + " = " + e + "\n"; // expression
      program_string += "if0 " + v_res + " goto " + labelEnd + "\n";
      n.f4.accept(this); // statement
      program_string += "goto " + labelLoop + "\n";
      program_string += labelEnd + ":\n";

      return null;

      
   }

   /**
    * f0 -> "System.out.println"
    * f1 -> "("
    * f2 -> Expression()
    * f3 -> ")"
    * f4 -> ";"
    */
    @Override
   public String visit(PrintStatement n) {
      String t2 = n.f2.accept(this);
      program_string += "print(" + t2 + ")\n";
      return null;
   }

   /**
    * f0 -> AndExpression()
    *       | CompareExpression()
    *       | PlusExpression()
    *       | MinusExpression()
    *       | TimesExpression()
    *       | ArrayLookup()
    *       | ArrayLength()
    *       | MessageSend()
    *       | PrimaryExpression()
    */
    @Override
   public String visit(Expression n) { 
      return n.f0.accept(this);
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "&&"
    * f2 -> PrimaryExpression()
    */
    @Override
   public String visit(AndExpression n) {

      //   System.out.println("label and: " + label_and);

        String t0 = n.f0.accept(this);
      // //   System.out.println()
      //   program_string += "left side of the and = " + t0 + "\n";
      //   program_string += method_ret_label.keySet() + "\n";
      

        String label_first_true = name_generator.generateNameLabel(); 
        String label_both_true = name_generator.generateNameLabel(); 
        String labelEnd = name_generator.generateNameLabel(); 

        String v_true = name_generator.generateName();
        String v_false = name_generator.generateName();
        String v_res = name_generator.generateName(); 
        // in sparrow, 0 is true and 1 is false
        // but in java it is opposite?

        program_string += v_false + " = 0\n";
        program_string += v_true + " = 1\n";

        program_string += "if0 " + t0 + " goto " + label_first_true + "\n";
        program_string += v_res + " = " + v_false + "\n";
        program_string += "goto " + labelEnd + "\n";
        program_string += label_first_true + ":\n";
        String t2 = n.f2.accept(this);
        program_string += "if0 " + t2 + " goto " + label_both_true + "\n";
        program_string += v_res + " = " + v_false + "\n";
        program_string += "goto " + labelEnd + "\n";
        program_string += label_both_true + ":\n";
        program_string += v_res + " = " + v_true + "\n";
        program_string += labelEnd + ":\n";

        return v_res;
   }
   /**
    * f0 -> PrimaryExpression()
    * f1 -> "<"
    * f2 -> PrimaryExpression()
    */
    @Override
   public String visit(CompareExpression n) {
      String t0 = n.f0.accept(this);
      String t2 = n.f2.accept(this);

      String name = name_generator.generateName();
      program_string += name + " = " + t0 + " < " + t2 + "\n";
      return name;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "+"
    * f2 -> PrimaryExpression()
    */
    @Override
   public String visit(PlusExpression n) {
      String t0 = n.f0.accept(this);
      String t2 = n.f2.accept(this);

      String name = name_generator.generateName();
      program_string += name + " = " + t0 + " + " + t2 + "\n";
      return name;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "-"
    * f2 -> PrimaryExpression()
    */
    @Override
   public String visit(MinusExpression n) {
      String t0 = n.f0.accept(this);
      String t2 = n.f2.accept(this);

      String name = name_generator.generateName();
      program_string += name + " = " + t0 + " - " + t2 + "\n";
      return name;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "*"
    * f2 -> PrimaryExpression()
    */
    @Override
   public String visit(TimesExpression n) {
      String t0 = n.f0.accept(this);
      String t2 = n.f2.accept(this);

      String name = name_generator.generateName();
      program_string += name + " = " + t0 + " * " + t2 + "\n";
      return name;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "["
    * f2 -> PrimaryExpression()
    * f3 -> "]"
    */
     @Override
   public String visit(ArrayLookup n) { 

       
      String t0 = n.f0.accept(this);
      String t2 = n.f2.accept(this);

    //   System.out.println("t0: " + t0); // a 
    //   System.out.println("t2: " + t2); // v5

    // need to check for neg index
      String name_zero = name_generator.generateName();
      program_string += name_zero + " = 0\n";
      String name_negative_check = name_generator.generateName();
      program_string += name_negative_check + " = " + t2 + " < " + name_zero + "\n";
      String negative_check_label = name_generator.generateNameLabel();
      program_string += "if0 " + name_negative_check + " goto " + negative_check_label + "\n";
      program_string += "error(\"array index out of bounds\")\n";
      program_string += negative_check_label + ":\n";


      String name_size = name_generator.generateName();
      program_string += name_size + " = [" + t0 + " + 0]\n";
      String name_1 = name_generator.generateName();
      program_string += name_1 + " = 1\n";
      String name_minus = name_generator.generateName();
      String name_comp = name_generator.generateName();
      program_string += name_minus + " = " + name_size + " - " + name_1 + "\n";
      program_string += name_comp + " = " + name_minus + " < " + t2 + "\n";
      String program_name = name_generator.generateNameProgram();
      program_string += "if0 " + name_comp + " goto " + program_name + "\n";
      program_string += "error(\"array index out of bounds\")\n";
      program_string += program_name + ":\n";
      String name_add = name_generator.generateName();
      String name_res = name_generator.generateName();
      String name_4 = name_generator.generateName();
      program_string += name_4 + " = 4\n";
      String index_4 = name_generator.generateName();

      String t2_plus_1 = name_generator.generateName();
      String name_one = name_generator.generateName();
      program_string += name_one + " = 1\n";
      program_string += t2_plus_1 + " = " + name_one +" + " + t2 + "\n";
      program_string += index_4 + " = " + name_4 + " * " + t2_plus_1 + "\n";
      program_string += name_add + " = " + t0 + " + " + index_4 + "\n";
      program_string += name_res + " = [" + name_add + " + 0]\n";
      return name_res; 

   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "."
    * f2 -> "length"
    */
   @Override
   public String visit(ArrayLength n) { // edit this, need to get first val
      String t0 = n.f0.accept(this); // a
      String name_size = name_generator.generateName();
      program_string += name_size + " = [" + t0 + " + 0]\n";
      return name_size;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "."
    * f2 -> Identifier()
    * f3 -> "("
    * f4 -> ( ExpressionList() )?
    * f5 -> ")"
    */
    @Override
   public String visit(MessageSend n) { // for the message send, look at the expr list
        // System.out.println("in message send-----------");
        // List<String> old_array = temp_array;
        // temp_array = new ArrayList<>(); // save the scope of the expr list data structure
         // in_message_send = true;
        String D = n.f0.accept(this); 
        String method_name = n.f2.f0.toString();
        Symbol sym3 = root_table.getType(D);

        if(method_name == "SetNext") {
         System.err.println("2: setnext!!!!");
       }

      //   System.out.println("I got here");
      //   System.out.println("D: " + D);


         if(sym3 == null) {
            // System.out.println("aiya");
            // System.out.println("D: " + D);
            Symbol root_sym = root_table.getType(D);
         //   System.out.println("keys: " + root_table.symbol_table.keySet());
         //   System.out.println(root_sym.dtype);
            List<String> arr = new ArrayList<>(root_table.symbol_table.keySet());

            // System.out.println("arr: " + arr);
            Boolean found = false;

            for(int i = 0; i < arr.size(); i ++) {
                  Symbol temp_sym = root_table.getType(arr.get(i));
                  // System.out.println("temp sym: " + temp_sym.name);
                  // System.out.println("hereee");
                  if(temp_sym.child_table != null) {
                     // System.out.println("hi");
                     // System.out.println(temp_sym.child_table.symbol_table.keySet());
                  }
                  
                  if(temp_sym != null && temp_sym.child_table != null && temp_sym.child_table.getType(D) != null) {
                     // System.out.println("found? " + temp_sym.child_table.getType(name).name);
                     found = true;
                     // System.out.println("found?");
                     sym3 = temp_sym.child_table.getType(D);
                     // System.out.println(sym3.name);
                  }
                  
            }

            if(!found) {
                     // System.out.println("ERROR finding: " + D);
                     sym3 = null;
                  }
         }
         // System.out.println("i am here");

        // System.out.println("method name: " + method_name);
        // System.out.println("caller: " + sym3.name);
        // System.out.println("allocation var: " + sym3.allocation_var);

        // program_string += "vmt_" + sym3.name + " = alloc(" + sym3.allocation_var + ")\n";
        // program_string += sym3.allocation_var + " = @" + sym3.name + name_generator.capitalizeFirstLetter(method_name) + "\n";
        // program_string += "[vmt_" + sym3.name + " + 0] = " + sym3.allocation_var + "\n";
        // program_string += sym3.allocation_var + " = " + "vmt_" + sym3.name + "\n";
        // program_string += "[" + sym3.allocation_var_w + " + 0] = " + sym3.allocation_var + "\n";
        if(sym3 == null) {
         // System.out.println("sym3 null");
        }
        if(sym3.allocation_var_w == "") {
         // sym3.allocation_var_w = name_generator.generateNameW();
         sym3.allocation_var_w = D; // not sure about this
         // System.out.println("HEREEEE is the prblem");
        }
        
      sym3.null_label = name_generator.generateNameNull();
        
        program_string += "if0 " + sym3.allocation_var_w + " goto " + sym3.null_label + "\n";
        add_null_label = true;
        null_label = sym3.null_label;

        String program_name = name_generator.generateNameProgram();
        program_string += "goto " + program_name + "\n";
        program_string += null_label + ":\n";
        program_string += "error(\"null pointer\")\n";

        program_string += program_name + ":\n";
        String name_w = name_generator.generateNameW();
      //   System.out.println("alloc var: " + sym3.allocation_var_w);
        program_string += name_w + " = [" + sym3.allocation_var_w + " + 0] \n";

      //   System.out.println("sym3: " + sym3.name);
      //   System.out.println("sym3 methods: " + root_table.getType(sym3.dtype).method_array);
      //   System.out.println(sym3.method_array.indexOf(method_name));

         Integer index_of_method;


        if(root_table.getType(sym3.parent_table.name) == null) {
         System.err.println("here");
            index_of_method = root_table.getType(sym3.dtype).method_array.indexOf(method_name);
        }
        else {
            index_of_method  = root_table.getType(sym3.parent_table.name).method_array.indexOf(method_name);
        }

        if(index_of_method == -1) {
            // System.err.println("sym3 name: " + sym3.name);
            // System.err.println("sym3 name: " + sym3.method_array);
            // System.err.println("index of method: " + index_of_method);
            index_of_method = sym3.method_array.indexOf(method_name);
        }

        if(index_of_method == -1) {
            System.err.println("i got here! index problems but idk if i need to be here");
            System.err.println("sym3 name: " + sym3.name);
            System.err.println("supertype: " + sym3.child_table.parent_name);
            Symbol parent_sym = root_table.getType(sym3.child_table.parent_name);
            index_of_method = parent_sym.method_array.indexOf(method_name);
            System.err.println("index of met: " + index_of_method);
            System.err.println("-");
         }
       
      //   System.out.println("here");

         program_string += name_w + " = [" + name_w + " + " + index_of_method * 4 + "]\n";
      //   program_string += name_w + " = [" + name_w + " + 0] \n";

        //new
        String expr_v = n.f4.accept(this);


        String ret_v = name_generator.generateName();
        // program_string += ret_v + " = call " + name_holder + "(" + D + " " + expr_v + ")\n";

        // System.out.println("expr_v: " + expr_v);
        // System.out.println("temp arr: " + temp_array);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < temp_array.size(); i++) {
            sb.append(temp_array.get(i));
            if (i != temp_array.size() - 1) {
                sb.append(" "); 
            }
        }

        temp_array = new ArrayList<>();

        // Convert StringBuilder to String
        String expr_string = sb.toString();

      //   System.out.println("expr string in visitor 2: " +expr_string );


        // System.out.println("expr str: " + expr_string);

      
        
      //   add_null_label = true;

      //   // program_string += "}msgsend\n";
      //   return ret_v;

        // old this is where the problems are
        String name_v = name_generator.generateName();
        String name_w_2 = name_generator.generateNameW(); // this should correlate the the print res
        Integer num_bytes_to_allocate = (1 + sym3.member_var_count) * 4;
        program_string += name_v + " = " + num_bytes_to_allocate + "\n"; // IDK if this is right
      //   program_string += name_w_2 + " = call " + name_w + "(" + sym3.allocation_var_w + ")\n";

        if(expr_v != null) {
            program_string += name_w_2 + " = call " + name_w + "(" + sym3.allocation_var_w  + " " + expr_string + ")\n";
        }
        else {
            program_string += name_w_2 + " = call " + name_w + "(" + sym3.allocation_var_w  + ")\n";
        }

      //   System.out.println("method name: " + method_name);

      //   program_string += method_ret_label.get(method_name).getSecond() + ":\n";
      //   program_string += ret_v + " = " + method_ret_label.get(method_name).getFirst() + "\n";

      //   program_string += "return " + ret_v + "\n";

      //   in_message_send = false;
        return name_w_2;

        // String D = n.f0.accept(this); 
        // // System.out.println("D: " + D);
        // String method_name = n.f2.f0.toString();
        // // System.out.println("method name: " + method_name);
        // Symbol sym3 = symbol_table.getType(D);
        // sym3 = root_table.getType(sym3.dtype);
        // Symbol sym4 = sym3.child_table.getType(method_name);
        // // System.out.println("LOOK: " + sym4.member_var_count);

        // // program_string += "msgsend{";

        // String program_name = name_generator.generateNameProgram();
        // program_string += "goto " + program_name + "\n";

        // // add the null
        // // program_string += "add null here? "+ "\n";
        // if(add_null_label) {
        //     program_string += null_label + ":\n";
        //     program_string += "error(\"null pointer\")\n";
        //     add_null_label = false;
        // }
        // program_string += program_name + ":\n";

        // String name_holder = name_generator.generateName();

        // program_string += name_holder + " = [" + D + " + 0]\n";

        // // allocation for number of varaiables
        // Integer num_vars = sym4.member_var_count;
        // Integer bytes_to_alloc_for_vars = num_vars * 4;
        // program_string += name_holder + " = [" + name_holder + " + " + bytes_to_alloc_for_vars+ "]\n";

        // String expr_v = n.f4.accept(this);

        // String ret_v = name_generator.generateName();
        // // program_string += ret_v + " = call " + name_holder + "(" + D + " " + expr_v + ")\n";
        // if(expr_v != null) {
        //     program_string += ret_v + " = call " + name_holder + "(" + D + " " + expr_v + ")\n";
        // }
        // else {
        //     program_string += ret_v + " = call " + name_holder + "(" + D + ")\n";
        // }
        
        // add_null_label = true;

        // // program_string += "}msgsend\n";
        // return ret_v;
   }

   /**
    * f0 -> Expression()
    * f1 -> ( ExpressionRest() )*
    */
    @Override
   public String visit(ExpressionList n) {  // add expr to the temp array
        String t = n.f0.accept(this);
        temp_array.add(t);
        n.f1.accept(this);
        return t;
   }

   /**
    * f0 -> ","
    * f1 -> Expression()
    */
    @Override
   public String visit(ExpressionRest n) { // add the rest to the temp array
        temp_array.add(n.f1.accept(this));
        return null;
   }

   /**
    * f0 -> IntegerLiteral()
    *       | TrueLiteral()
    *       | FalseLiteral()
    *       | Identifier()
    *       | ThisExpression()
    *       | ArrayAllocationExpression()
    *       | AllocationExpression()
    *       | NotExpression()
    *       | BracketExpression()
    */
    @Override
   public String visit(PrimaryExpression n) { 
      return n.f0.accept(this);
   }

   /**
    * f0 -> <INTEGER_LITERAL>
    */
    @Override
   public String visit(IntegerLiteral n) {
      String val = n.f0.toString();
      String name = name_generator.generateName();
      program_string += name + " = " + val + "\n";
      return name;
   }

   /**
    * f0 -> "true"
    */
    @Override
   public String visit(TrueLiteral n) {
      String val = "1";
      String name = name_generator.generateName();
      program_string += name + " = " + val + "\n";
      return name;
   }

   /**
    * f0 -> "false"
    */
   @Override
   public String visit(FalseLiteral n) {
      String val = "0";
      String name = name_generator.generateName();
      program_string += name + " = " + val + "\n";
      return name;
   }

   /**
    * f0 -> <IDENTIFIER>
    */
    @Override
   public String visit(Identifier n) { 
      // try to see if it is a member var
      String t0 = n.f0.toString();
      
      if(curr_sym != null) {
         // System.out.println("curr: " + curr_sym.name);
         // System.out.println("local: " + curr_sym.local_vars);
         // System.out.println("member: " + root_table.getType(curr_sym.curr_table.parent_name).member_vars);
            if(curr_sym.local_vars.contains(t0)) { // if it is a local var, just use that
               return t0;
            }

            List<String> mem_var_arr;

            // System.out.println("t0: " + t0);
            if(in_main) {
               // System.out.println("in main");
               // mem_var_arr = root_table.getType("Main").member_vars;
               return t0;
            }
            // System.out.println("curr_sym.curr_table.parent_name: " + curr_sym.curr_table.parent_name);
            // System.out.println(root_table.getType(curr_sym.curr_table.parent_name).name);
            
            else {
               mem_var_arr = root_table.getType(curr_sym.curr_table.parent_name).member_vars;
            }
            if(mem_var_arr.contains(t0)) {
               // otherwise we try to load it since it is a member var
               String class_name = curr_sym.curr_table.parent_name; // idk if this is what i want
               Integer index_of_var = mem_var_arr.indexOf(t0);

               String field_name = name_generator.generateName();

               program_string += t0 + " = [this" + " + " + 4 * (index_of_var + 1) + "]\n";
               
               String name_field = name_generator.generateName();
               if(in_assign) {
                  // return "[" + t0 + " + 0]";
                  // return t0;
                  // System.out.println("returning: " + "[this" + " + " + 4 * (index_of_var + 1) + "]");
                  return "[this" + " + " + 4 * (index_of_var + 1) + "]";
               }
               else {
                  // program_string += name_field + " = [" + t0 + " + 0]\n";
                  // class_to_w.put(name_field, "[" + t0 + " + 0]");
                  // return name_field;
                  // System.out.println("returning: " + t0);
                  return t0;
               }
               // program_string += name_field + " = [" + t0 + " + 0]\n"; // idk abt this
               
               // return t0;
            }
         }

      return t0;
   }

   /**
    * f0 -> "this"
    */
    @Override
   public String visit(ThisExpression n) { 
    // alloc_happened = true;
    add_null_label = true;
    String this_null = name_generator.generateNameNull();
    program_string += "if0 this goto " + this_null + "\n";
    null_label = this_null;
    //   System.out.println("i am in this");
      Symbol sym2 = symbol_table.getType("this"); // might need to work on this
    //   System.out.println("name: " + sym2.dtype);
      Symbol sym = root_table.getType(sym2.dtype);
    
    return "this";
   }

   /**
    * f0 -> "new"
    * f1 -> "int"
    * f2 -> "["
    * f3 -> Expression()
    * f4 -> "]"
    */
    @Override
   public String visit(ArrayAllocationExpression n) { // work on this
   // alloc_happened = true;
      String name_size = n.f3.accept(this);
    //   System.out.println("i am working here!: " + name_size);

      String name_4 = name_generator.generateName();
      program_string += name_4 + " = 4\n";
      String name_alloc = name_generator.generateName();
      program_string += name_alloc + " = " + name_size + " * " + name_4 + "\n";
      String name_add = name_generator.generateName();
      program_string += name_add + " = " + name_alloc + " + " + name_4 + "\n";
      String name_array = name_generator.generateName();
      program_string += name_array + " = alloc(" + name_add + ")\n";
      program_string += "[" + name_array + " + 0] = " + name_size + "\n";
      
    //   curr_array = name_array;
      return name_array;
   }

   /**
    * f0 -> "new"
    * f1 -> Identifier()
    * f2 -> "("
    * f3 -> ")"
    */
    @Override
   public String visit(AllocationExpression n) { // work on this
//    System.out.println("I GOT HERE");
      alloc_happened = true;
      String t = n.f1.accept(this);
      
      Symbol sym = symbol_table.getType(t);

      System.err.println("in alloc");
      System.err.println("sym name: " + sym.name);
      Integer num_bytes_to_allocate_parent = 0;
      Integer num_bytes_to_allocate_for_method_parent = 0;
      Boolean do_inheritance = false;
      Symbol parent_sym = null;
      if(sym != null && sym.child_table.getParentName() != null) {
         // inheritance must happen now
         do_inheritance = true;
         if(sym.name == sym.child_table.getParentName()) {
            do_inheritance = false;
         }
         System.err.println("parent name: " + sym.child_table.getParentName());
         parent_sym = root_table.getType(sym.child_table.getParentName());
         num_bytes_to_allocate_parent = (1 + parent_sym.member_var_count) * 4;
         num_bytes_to_allocate_for_method_parent = parent_sym.num_methods * 4;
         System.err.println("parent num bytes to alloc: " + num_bytes_to_allocate_parent);
      }
      if(do_inheritance) {
         System.err.println("do inheritance!");
      }
      // do_inheritance = false;
      
      Integer num_bytes_to_allocate = (1 + sym.member_var_count) * 4; // bc we need to allocate one space to go to the vmt
      System.err.println(" num bytes to alloc: " + num_bytes_to_allocate);
      if(do_inheritance) {
         num_bytes_to_allocate = num_bytes_to_allocate + num_bytes_to_allocate_parent - 4;
      }
      System.err.println("now num bytes to alloc: " + num_bytes_to_allocate);
      
      String name = name_generator.generateName();
      program_string += name + " = " + num_bytes_to_allocate + "\n";
      String name_w = name_generator.generateNameW();
      program_string += name_w + " = alloc(" + name + ")\n";
      sym.allocation_var = name;
      sym.allocation_var_w = name_w;
      alloc_w = sym.allocation_var_w;
      sym.null_label = name_generator.generateNameNull();

        Integer bytes_to_allocate_for_method = sym.num_methods * 4;
        if(do_inheritance) {
         bytes_to_allocate_for_method += num_bytes_to_allocate_for_method_parent;
        }

        System.err.println("bytes for met: " + bytes_to_allocate_for_method);

        String name_method = "";
        if(bytes_to_allocate_for_method > 0) {
            name_method = name_generator.generateName();
            program_string += name_method + " = " + bytes_to_allocate_for_method + "\n";
            sym.allocation_var = name_method;
        }
        // find num of bytes to allocate
        // System.out.println("num of mem vars: " + sym.member_var_count);
        Integer offset = sym.member_var_count * 4;
        if(do_inheritance) {
          offset += parent_sym.member_var_count * 4;
        }
      //   Integer num_bytes_to_allocate_for_method = (1 + sym.member_var_count) * 4;
        
        // check if caller has nonzero methods
        if(do_inheritance) {
            if((sym.num_methods + parent_sym.num_methods) > 0) {
                  program_string += "vmt_" + sym.name + " = alloc(" + name_method + ")\n";
            }
            else {
                  program_string += "vmt_" + sym.name + " = alloc(" + sym.allocation_var + ")\n";
            }
        }
        else {
            if(sym.num_methods > 0) {
               program_string += "vmt_" + sym.name + " = alloc(" + name_method + ")\n";
         }
         else {
               program_string += "vmt_" + sym.name + " = alloc(" + sym.allocation_var + ")\n";
         }
        }
        

     if(sym == null) {
         System.out.println("sym is null");


      }

      if(sym.method_array.size() == 0) {
         
      }

      // make a met array bc the prev one is not working?
      List<String> new_met_arr = new ArrayList<>();
      for (String key : sym.child_table.symbol_table.keySet()) {
         System.err.println(key);
         Symbol key_sym = sym.child_table.getType(key);
         if(key_sym.object_type == "method") {
            new_met_arr.add(key);
         }
      }
      System.err.println("new met arr: " + new_met_arr);
      Collections.reverse(new_met_arr);

      // do the current symbol's methods

      // maybe use new_met_arr
      if(sym.method_array.size() == 0) {
         for(int i = 0; i < new_met_arr.size(); i ++) {
            String temp_name = name_generator.generateName();
            program_string += temp_name + " = @" + sym.dtype + name_generator.capitalizeFirstLetter(new_met_arr.get(i)) + "\n";
            program_string += "[vmt_" + sym.dtype + " + " + i * 4 + "] = " + temp_name + "\n";

        }

      }
      else {
         for(int i = 0; i < sym.method_array.size(); i ++) {
            String temp_name = name_generator.generateName();
            program_string += temp_name + " = @" + sym.dtype + name_generator.capitalizeFirstLetter(sym.method_array.get(i)) + "\n";
            program_string += "[vmt_" + sym.dtype + " + " + i * 4 + "] = " + temp_name + "\n";

        }
      }
      

      // now see for inheritance
      if(do_inheritance) {
      Integer prev_size = new_met_arr.size();
      for(int i = prev_size; i < (parent_sym.method_array.size() + prev_size); i ++) {
         System.err.println("cur met: " + parent_sym.method_array.get(i));
         System.err.println(sym.method_array);
         System.err.println(parent_sym.method_array);
         root_table.printSymbolTable(root_table, 0);
         
         if(!new_met_arr.contains(parent_sym.method_array.get(i))) {
            System.err.println("adding met");
            String temp_name = name_generator.generateName();
            program_string += temp_name + " = @" + parent_sym.dtype + name_generator.capitalizeFirstLetter(parent_sym.method_array.get(i)) + "\n";
            program_string += "[vmt_" + sym.dtype + " + " + i * 4 + "] = " + temp_name + "\n";
         }
         

      }
      }

        program_string += "[" + name_w + " + 0] = vmt_" + sym.dtype + "\n";

      sym.allocation_var_w = name_w;

      for(int i = 0; i < sym.member_var_count; i ++) {
         String name_0 = name_generator.generateName();
         program_string += name_0 + " = 0\n"; // here!!!
         program_string += "[" + name_w + " + " + 4 * (i+1) + "] = " + name_0 + "\n";
         String var_name = sym.member_vars.get(i);
         mem_var_map.put(var_name, name_0);
      }
      // now see for inheritance
      if(do_inheritance) {
         Integer prev_size = sym.member_var_count;
         for(int i = prev_size; i < (parent_sym.member_var_count + prev_size); i ++) {
            String name_0 = name_generator.generateName();
            program_string += name_0 + " = 0\n"; // here!!!
            program_string += "[" + name_w + " + " + 4 * (i+1) + "] = " + name_0 + "\n";
            String var_name = parent_sym.member_vars.get(i);
            mem_var_map.put(var_name, name_0);
         }
      }
      do_inheritance = false;
      return t;
   }


   /**
    * f0 -> "!"
    * f1 -> Expression()
    */
    @Override
   public String visit(NotExpression n) {
      String t1 = n.f1.accept(this);

        String labelTrue = name_generator.generateNameLabel(); 
        String labelEnd = name_generator.generateNameLabel(); 

        String v_true = name_generator.generateName();
        String v_false = name_generator.generateName();
        String v_res = name_generator.generateName();

        program_string += v_true + " = 1\n";
        program_string += v_false + " = 0\n";

        program_string += "if0 " + t1 + " goto " + labelTrue + "\n";
        program_string += v_res + " = " + v_false + "\n"; 
        program_string += "goto " + labelEnd + "\n";
        program_string += labelTrue + ":\n";
        program_string += v_res + " = " + v_true + "\n"; 
        program_string += labelEnd + ":\n";

      return v_res;

      
   }

   /**
    * f0 -> "("
    * f1 -> Expression()
    * f2 -> ")"
    */
    @Override
   public String visit(BracketExpression n) {
      String t1 = n.f1.accept(this);
      return t1;
   }

}
