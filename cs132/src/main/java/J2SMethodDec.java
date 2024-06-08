import minijava.visitor.*;

import minijava.syntaxtree.*;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;
import java.util.*;

/**
 * Provides default methods which visit each node in the tree in depth-first
 * order.  Your visitors may extend this class.
 
 */

 
public class J2SMethodDec extends GJNoArguDepthFirst<String> implements GJNoArguVisitor<String>{

   public SymbolTable symbol_table = new SymbolTable();
   public SymbolTable root_table = new SymbolTable();
   public Symbol curr_sym = null;
   public List<Symbol> methods = new ArrayList<>();
   public String curr_method;
   public boolean alloc_happened = false;
   public Map<String, String> arrays = new HashMap<>(); // idt this is necessary
   public String curr_array = null;
   public Boolean in_arr_alloc = false;

   public Map<String, String> type_to_w = new HashMap<>(); // idt this is necessary
   public Map<String, List<String>> method_to_param = new HashMap<>(); // idt this is necessary

public Boolean no_bracket = false;
public Boolean use_temp = false;
public String temp_type = null;
public Boolean in_message_send = false;
public String my_name_w = null;
public String method_dec_name = null;
public String ret_v_save = null;
public Boolean in_ret = false;
public List<String> curr_params_list = null;

   public String label_and = null;
   public Map<String, StringPair> method_ret_label = new HashMap<>();
   public Map<String, String> class_to_w = new HashMap<>();
   public String curr_class = null;
   public Symbol curr_class_sym = null;
   public String alloc_w = null;
   

   //sus
   public List<String> temp_array = new ArrayList<>();
   public List<String> temp_params = new ArrayList<>();
   public Symbol parent_sym;
   public Symbol method_sym;
   
   public Boolean in_assign = false;

   public String program_string = "";

   public NameGenerator name_generator;
   public boolean add_null_label = false;
   public String null_label = null;

   public J2SMethodDec(SymbolTable symbol_table, NameGenerator name_generator) {
      this.symbol_table = symbol_table;
      this.root_table = symbol_table;
      this.name_generator = name_generator;
   }

    /**
    * f0 -> MainClass()
    * f1 -> ( TypeDeclaration() )*
    * f2 -> <EOF>
    */
    @Override
   public String visit(Goal n) {
    // System.out.println("prog str:" + program_string);
    //   n.f0.accept(this);
      n.f1.accept(this);
      return program_string; 
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
      String _ret="main";
    //   n.f14.accept(this);
    //   n.f15.accept(this);
    //   return program_string;
    return null;
   }

   /**
    * f0 -> ClassDeclaration()
    *       | ClassExtendsDeclaration()
    */
    @Override
   public String visit(TypeDeclaration n) {
      System.err.println("i got here class ");
      return n.f0.accept(this);
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
      System.err.println("here in class dec j2s class dec");
      String D = n.f1.accept(this);
      curr_class = D;
      Symbol sym = symbol_table.getType(D);
      sym.child_table.name = D;
      SymbolTable new_table = sym.child_table;
      curr_class_sym = sym;
      // set the parent
      new_table.setParent(symbol_table);
      if(sym == null) {
         throw new RuntimeException("class dec sym null");
      }
      sym.parent_table = symbol_table; // i think this is wrong

      SymbolTable old_table = symbol_table;
      this.symbol_table = new_table;
    //   System.out.println("method decs: ");
      new_table.parent_table = old_table; // ? 
      new_table.parent_name = D;
      n.f4.accept(this); // method dec, we need this for this visitor
      this.symbol_table = old_table;
      curr_class = null;
      curr_class_sym = null;
      return null;
   }

   // need class extends?
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
        String classname = n.f1.accept(this);
        System.err.println("classname; " + classname);
        Symbol sym = symbol_table.getType(classname);
        curr_class_sym = sym;
        System.err.println("classname name " + sym.name);
      //   System.out.println("i got here???");
        SymbolTable new_table = sym.child_table;

        // set the parent
        new_table.setParent(symbol_table);
        new_table.setParentName(n.f3.f0.toString());

        // handle scoping
        SymbolTable old_table = symbol_table;
        symbol_table = new_table;
        System.err.println("classname name 1 " + sym.name);
        n.f5.accept(this);
        n.f6.accept(this);
        System.err.println("classname name 2 " + sym.name);
        symbol_table = old_table;
        curr_class_sym = null;
        return null;
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
   public String visit(MethodDeclaration n) { // in this visitor, we handle the parameters
    //   System.out.println("got to method dec!");
      String method_name = n.f2.f0.toString(); 
      method_dec_name = method_name; 
      Symbol sym = symbol_table.getType(method_name);
      // if(sym == null) { // try to get the supertype's method
      //   String super_type = symbol_table.getType(symbol_table.name).child_table.parent_name;
      //   Symbol temp = symbol_table.getType(super_type);
      //   sym = temp.child_table.getType(method_name);
      // }

    //   System.out.println("method name: " + name_generator.capitalizeFirstLetter(method_name));
    //   System.out.println("caller name: " + sym.parent_table.name);

    //   System.out.println("param types: " + sym.param_types);
    //   System.out.println("param names: " + sym.param_names);
    
    if(sym.parent_table.name == null) {
      if(curr_class_sym == null) {
         System.err.println("method anme: " + method_name);
         throw new RuntimeException("curr sym null");
      }
      System.err.println("func1: " + curr_class_sym.name);
      program_string += "func " + curr_class_sym.name + name_generator.capitalizeFirstLetter(method_name) + "(this";
    }
    else {
      System.err.println("func2: " + sym.parent_table.name);
      if(sym.parent_table.name == "this") {
         System.err.println(curr_class_sym.name);
         program_string += "func " + curr_class_sym.name + name_generator.capitalizeFirstLetter(method_name) + "(this";
      }
      else {
         program_string += "func " + sym.parent_table.name + name_generator.capitalizeFirstLetter(method_name) + "(this";
      }   
    }
      
      for(int i = 0; i < sym.param_names.size(); i ++ ){
        program_string += " " + sym.param_names.get(i);
      }
      program_string += ") \n";


      methods.add(sym);
      sym.child_table.name = method_name; // scoping 
      SymbolTable old_table = symbol_table;
      SymbolTable new_table = sym.child_table;

      // set the parent
      new_table.setParent(symbol_table);
      if(sym == null) {
         throw new RuntimeException("sym.parent_table");
      }
      sym.parent_table = symbol_table;

      this.symbol_table = new_table;
      curr_method = method_name;
      n.f4.accept(this); // formal param list <-- handle this later
      n.f7.accept(this); // var dec for local vars
    //   System.out.println("I got");
      curr_sym = sym;
      // system.err.println("method name! " + method_name);
      // system.err.println(sym.param_names);
      method_to_param.put(method_name, sym.param_names);
      temp_params = sym.param_names;
      n.f8.accept(this); // statements
      temp_params = new ArrayList<>();
    //   System.out.println("i got ehre");
      in_ret = true;
      String ret = n.f10.accept(this);
      in_ret = false;

    //   System.out.println("returning: " + ret);
      // if(label_and != null) {
      //    program_string += "goto " + label_and + "\n";
      // }
      // else {
      //    program_string += "return " + ret + "\n";
      // }

      String new_label = name_generator.generateNameLabel();
      method_ret_label.put(method_name, new StringPair(ret, new_label));
      // program_string += "goto " + new_label + "\n";
      
      this.symbol_table = old_table; // reset the scope
      curr_method = "";
      // System.out.println("returning: " + ret);

      // check if ret is a local var
      System.err.println("ret: " + ret);
      System.err.println("mem vars: " + curr_sym.member_vars);
      // check the parents
      System.err.println("curr class sym!: " + curr_class_sym.name);
      System.err.println(curr_class_sym.child_table.getParentName());
      Symbol parent_sym = root_table.getType(curr_class_sym.child_table.getParentName());
      if(parent_sym.name != curr_class_sym.name && parent_sym != null) {
         
         if(parent_sym.member_vars.contains(ret)){
            System.err.println("here1");
            System.err.println(parent_sym.member_vars.indexOf(ret));
            String name_ret = name_generator.generateName();
            Integer idx = curr_class_sym.member_var_count + parent_sym.member_vars.indexOf(ret);
            program_string += name_ret + " = [this + " + (idx+1) * 4 + "]\n";
            ret = name_ret;
         }
         
         else {
            Symbol gparent_sym = root_table.getType(parent_sym.child_table.getParentName());
            if(gparent_sym.member_vars.contains(ret)){
               System.err.println("here2");
               System.err.println(gparent_sym.member_vars.indexOf(ret));
               String name_ret = name_generator.generateName();
               Integer idx = curr_class_sym.member_var_count + parent_sym.member_var_count + gparent_sym.member_vars.indexOf(ret);
               program_string += name_ret + " = [this + " + (idx+1) * 4 + "]\n";
               ret =  name_ret;
            }

         }
      }

      

      program_string += "return " + ret + "\n";
      // add null label here
      // if(add_null_label) {
      //       program_string += null_label + ":\n";
      //       program_string += "error(\"null pointer\")\n";
      //       add_null_label = false;
      //   }

      curr_sym = null;

      method_dec_name = null;
      return ret;
   }

   /**
    * f0 -> FormalParameter()
    * f1 -> ( FormalParameterRest() )*
    */
    @Override
   public String visit(FormalParameterList n) { 
      Symbol sym = symbol_table.parent_table.getType(symbol_table.name);
      curr_sym = sym;
    
      String first_param_type = n.f0.accept(this); // add parameter to the list
      curr_sym.param_types.add(first_param_type);
      n.f1.accept(this);
    
      return null;
   }

   /**
    * f0 -> Type()
    * f1 -> Identifier()
    */
    @Override
   public String visit(FormalParameter n) { 
      return n.f0.accept(this);
   }

   /**
    * f0 -> ","
    * f1 -> FormalParameter()
    */
    @Override
   public String visit(FormalParameterRest n) { 
      curr_sym.param_types.add(n.f1.accept(this)); // add the rest of the params to the list
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


      // system.err.println("t2: " + t2);

      if(alloc_happened) {
         t2 = my_name_w;
      }

    program_string += t0 + " = " + t2 + "\n";

    String program_name = name_generator.generateNameProgram();
    program_string += "goto " + program_name + "\n";

    if(alloc_happened) {
      // System.out.println("alloc happened");
      // System.out.println("t0: " + t0);

      // since alloc happened we will populate the map
      // type_to_w.put(t0, alloc_w);
      // alloc_w = null;

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
         if(sym3 == null) {
            // System.out.println("looking for: " + D);
            // root_table.printSymbolTable(root_table, 0);
            // System.out.println("currsym: " + curr_sym.name);
            sym3 = curr_sym.child_table.getType(D);
            // System.out.println(sym5.name);

            // throw new RuntimeException("sym3 null");
         }
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
      String t2 = n.f2.accept(this); // this is the index
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
      // // System.errprintln("ERROR: name_comp: " + name_comp);
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
      String name_1 = name_generator.generateName();

      // System.err.println("while labelloop: " + labelLoop);
      // System.err.println("while labelEnd: " + labelEnd);
      // System.err.println("while v_res: " + v_res);

      program_string += labelLoop + ":\n";
      e = n.f2.accept(this);
      program_string += v_res + " = " + e + "\n"; // expression
      program_string += name_1 + " = 1\n";
      // program_string += v_res + " = " + name_1 + " - " + v_res + "\n"; // flip it
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
      
      String t0 = n.f0.accept(this);
      
      return t0;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "&&"
    * f2 -> PrimaryExpression()
    */
    @Override
   public String visit(AndExpression n) {
no_bracket = true;
        String label_and_gen = name_generator.generateNameLabel(); 
        label_and = label_and_gen;
      //   System.out.println("label and: " + label_and);

        String t0 = n.f0.accept(this);
      // //   System.out.println()
      //   program_string += "left side of the and = " + t0 + "\n";
      //   program_string += method_ret_label.keySet() + "\n";
      

        String label_first_false = name_generator.generateNameLabel(); 
        String label_both_true = name_generator.generateNameLabel(); 
        String labelEnd = name_generator.generateNameLabel(); 

        String v_true = name_generator.generateName();
        String v_false = name_generator.generateName();
        String v_res = name_generator.generateName(); 
        // in sparrow, 0 is true and 1 is false
        // but in java it is opposite?

        program_string += v_false + " = 0\n";
        program_string += v_true + " = 1\n";

        program_string += "if0 " + t0 + " goto " + label_first_false + "\n"; // if t0 is false, go to first false label
      String t2 = n.f2.accept(this); // Evaluate the second condition
      program_string += "if0 " + t2 + " goto " + label_first_false + "\n"; // if t2 is false, go to first false label

      program_string += v_res + " = " + v_true + "\n"; // Both conditions are true
      program_string += "goto " + labelEnd + "\n";

      program_string += label_first_false + ":\n"; // If either condition is false
      program_string += v_res + " = " + v_false + "\n";

      program_string += labelEnd + ":\n";


      //   program_string += "if0 " + t0 + " goto " + label_first_true + "\n";
      //   program_string += v_res + " = " + v_true + "\n";
      //   program_string += "goto " + labelEnd + "\n";
      //   program_string += label_first_true + ":\n";
      //   String t2 = n.f2.accept(this);
      //   program_string += "if0 " + t2 + " goto " + label_both_true + "\n";
      //   program_string += v_res + " = " + v_false + "\n";
      //   program_string += "goto " + labelEnd + "\n";
      //   program_string += label_both_true + ":\n";
      //   program_string += v_res + " = " + v_false + "\n";
      //   program_string += labelEnd + ":\n";

        label_and = null;
        no_bracket = false;

        return v_res;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "<"
    * f2 -> PrimaryExpression()
    */
    @Override
   public String visit(CompareExpression n) {
      no_bracket = true;
      String t0 = n.f0.accept(this);
      String t2 = n.f2.accept(this);
no_bracket = false;

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
      no_bracket = true;
      String t0 = n.f0.accept(this);
      String t2 = n.f2.accept(this);
      no_bracket = false;

      

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
      no_bracket = true;
      String t0 = n.f0.accept(this);
      String t2 = n.f2.accept(this);
      no_bracket = false;

      

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
      no_bracket = true;
      String t0 = n.f0.accept(this);
      String t2 = n.f2.accept(this);
      no_bracket = false;

      

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

       no_bracket = true;
      String t0 = n.f0.accept(this);
      String t2 = n.f2.accept(this);
      no_bracket = false;

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
      // System.err.println("Name comp: " + name_comp);
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
       no_bracket = true;

      String t0 = n.f0.accept(this); // a
      String name_size = name_generator.generateName();
      program_string += name_size + " = [" + t0 + " + 0]\n";
      no_bracket = false;
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
        no_bracket = true;
        in_message_send = true;
        String method_name = n.f2.f0.toString();
        String expr_v = n.f4.accept(this);

         StringBuilder sb = new StringBuilder();

      //   temp_params = temp_array;

        for (int i = 0; i < temp_array.size(); i++) {
            sb.append(temp_array.get(i));
            if (i != temp_array.size() - 1) {
                sb.append(" "); 
            }
        }
        // Convert StringBuilder to String
        String expr_string = sb.toString();


        // Split the string by spaces
        String[] strArray = expr_string.split(" ");

        // Convert the array to an ArrayList
        List<String> params_list = new ArrayList<>(Arrays.asList(strArray));

        curr_params_list = params_list;

        // now we need to turn expr_v into


         // somehow i need to get the params before i accept this
        String D = n.f0.accept(this); 
      //   if(add_null_label) {
      //       program_string += null_label + ":\n";
      //       program_string += "error(\"null pointer\")\n";
      //       add_null_label = false;
      //   }
        
        no_bracket = false;
      //   System.err.println("D: " + D);
        
        // System.out.println("method name: " + method_name);
        Symbol sym3 = symbol_table.getType(D);
        
        if(sym3 == null) {
         // System.err.println("looking for: " + D);

         String name_temp = name_generator.generateName();
         // if(D == "temp" && use_temp) {
         //    program_string += name_temp + " = temp\n";
         //    D = name_temp;
         //    use_temp = false;
         //    sym3 = root_table.getType(temp_type);

         // }

         if(use_temp) {
            // System.err.println("D: " + D);
            // System.err.println("i am using temp");
            // System.err.println("temp type: " + temp_type);
            // System.errprintln("i got to temp");
            Symbol type_sym = root_table.getType(temp_type);
            // System.errprintln(type_sym.method_array);
            sym3 = type_sym;
         }
         // use_temp = false;

         // System.err.println("method name: " + method_name);
         // throw new RuntimeException("nooo");
        }
        else {
          sym3 = root_table.getType(sym3.dtype);
          
        // System.out.println("LOOK: " + sym4.member_var_count);

        }
         System.err.println("sym3 name: " + sym3.name);
         // System.err.println("sym3 name: " + sym3.child_table.);
         if(sym3 == null) {
            throw new RuntimeException("sym3 null1");
         }
        Symbol sym4 = sym3.child_table.getType(method_name);

        if(sym4 == null) {
         Symbol temp_sym = root_table.getType(sym3.name);
         // root_table.printSymbolTable(root_table, 0);
         System.err.println(temp_sym.name);
         System.err.println(temp_sym.member_var_count); // something is wrong
         // System.err.println((temp_sym.symbol_table.getType(method_name)).name);
         sym4 = temp_sym;
         // throw new RuntimeException("sym4 null");
        }
        // System.out.println("LOOK: " + sym4.member_var_count);

        // program_string += "msgsend{";

        String program_name = name_generator.generateNameProgram();
        program_string += "goto " + program_name + "\n";

        // add the null
        // program_string += "add null here? "+ "\n";
        if(add_null_label) {
            program_string += null_label + ":\n";
            program_string += "error(\"null pointer\")\n";
            add_null_label = false;
        }
        
        program_string += program_name + ":\n";

        String name_holder = name_generator.generateName();
// w5 is Element
      //   System.err.println("type to w keys: " + type_to_w.keySet());
        
      //   System.err.println("D: " + D);
      //   System.err.println("sym3: " + sym3.dtype);
      //   System.err.println("sym4: " + sym4.dtype);
        
        String try_to_get_alloc_w = type_to_w.get(sym3.dtype);
        // System.errprintln("try: " + try_to_get_alloc_w);
        // System.errprintln("prev: " + alloc_w);
        String prev_alloc_w = alloc_w;
        // System.errprintln("D: " + D);
        // System.errprintln("temp params: " + temp_params);
       // System.errprintln("method name: " + method_name);
       // System.errprintln("method dec name: " + method_dec_name);
       // System.errprintln(symbol_table.getType(method_dec_name).local_vars);
       List<String> curr_params = method_to_param.get(method_name);

       

         // System.errprintln("curr params: " + curr_params);
        if(curr_params != null && curr_params.contains(D)) {
         // System.errprintln("i got here!!!!");
         alloc_w = D; 
        }
        else if(temp_params.contains(D) || symbol_table.getType(method_dec_name).local_vars.contains(D)){
         // System.errprintln("yar!!!!");
         alloc_w = D;
         // System.errprintln("alloc!!!! " + alloc_w);
        }

   
      //   else if(try_to_get_alloc_w != null) {
      //    // System.err.println("I got here!");
      //    // System.err.println("old alloc: " + alloc_w);
      //    // System.err.println("new alloc: " + try_to_get_alloc_w);
      //    alloc_w = try_to_get_alloc_w;
      //   }
        else {
         alloc_w = D;
        }

        if(method_name == "SetNext") {
         // System.errprintln("setnexthere!!!!");
         alloc_w = D;
       }
        
         // System.out.println("alloc w: " + alloc_w);
         if(D == "this") {
            program_string += name_holder + " = [" + D + " + 0]\n";
            // System.errprintln("adding this: " + name_holder + " = [" + D + " + 0]\n");
         }
         else if(alloc_w != null) {
            // System.errprintln("hereeee: " + alloc_w);
            // System.errprintln("adding this: " + name_holder + " = [" + alloc_w + " + 0]\n");
            program_string += name_holder + " = [" + alloc_w + " + 0]\n";
         }
         else {
            program_string += name_holder + " = [" + D + " + 0]\n";
            // System.errprintln("adding this: " + name_holder + " = [" + D + " + 0]\n");
         }

         if(prev_alloc_w == "prev") {
            // System.errprintln("prev here");
         }
        

        // allocation for number of variables
        if(sym4 == null) {
         if(use_temp) {
            // System.out.println("use temp");
            // System.errprintln("i got to temp");
            // System.errprintln("method name: " + method_name);
            Symbol type_sym = root_table.getType(method_name);
            // System.errprintln(root_table.symbol_table.keySet());
            List<String> keys = new ArrayList<>(root_table.symbol_table.keySet());

            for(int i = 0; i < keys.size(); i++){
               // System.errprintln("key: " + keys.get(i));
               Symbol sym_type = root_table.getType(keys.get(i));
               Symbol sym2 = sym_type.child_table.getType(method_name);
               if(sym2 != null) {
                  // System.errprintln(sym2.name);
                  sym4 = sym2;
               }
            }


            // root_table.printSymbolTable(root_table, 0);
            // System.err.println("-");
            // System.err.println(type_sym.method_array);
            // System.err.println(type_sym.dtype);
            // sym4 = type_sym.curr_table.getType(method_name);
            // System.errprintln(sym4.member_var_count);
            // sym3 = type_sym;
         }
         // System.errprintln("sym4 null");
         // System.errprintln("lookgin for: " + D);
         // System.errprintln("lookgin for: " + alloc_w);
        }
        use_temp = false;
        Integer num_vars = sym4.member_var_count;
        Integer bytes_to_alloc_for_vars = num_vars * 4;
      //   program_string += "HERE\n"; // need to figure out which func is being called
      //   System.out.println("method array: " + sym4.name);
      //   System.out.println("method array: " + sym4.parent_table.symbol_table.keySet());
      //   System.out.println("method array: " + sym4.parent_table.name);
      //   System.out.println("method array real: " + root_table.getType(sym4.parent_table.name).method_array);
      //   System.out.println("method name: " + method_name);
      //   System.out.println("index: " + root_table.getType(sym4.parent_table.name).method_array.indexOf(method_name));

         Integer index_of_method;
         if(root_table.getType(sym4.parent_table.name) != null) {
            index_of_method = root_table.getType(sym4.parent_table.name).method_array.indexOf(method_name);
         }
         else {
            index_of_method = -1;
         }
        

         if(index_of_method == -1) {
            System.err.println("HELP HERE2");
            // System.err.println("sym3 name: " + sym3.name);
            // System.err.println("sym3 name: " + sym3.method_array);
            // System.err.println("index of method: " + index_of_method);
            index_of_method = sym3.method_array.indexOf(method_name);
        }

        if(index_of_method == -1) {
            System.err.println("i got here! index problems but idk if i need to be here");
            System.err.println("sym3 name: " + sym3.name);
            if(sym3 == null) {
               throw new RuntimeException("sym3 null2");
            }
            String supertype = sym3.child_table.parent_name;
            System.err.println("supertype: " + sym3.child_table.parent_name);
            // symbol_table.printSymbolTable(symbol_table, 0);
            System.err.println("try: " + root_table.getType(sym3.dtype).child_table.parent_name);
            String supertype_2 = root_table.getType(sym3.dtype).child_table.parent_name;
            if(supertype == null) {
               supertype = supertype_2;
            }
            Symbol parent_sym = root_table.getType(supertype);
            if(parent_sym == null) {
               throw new RuntimeException("ohno");
            }
            index_of_method = parent_sym.method_array.indexOf(method_name);
            System.err.println("index of met in parent: " + index_of_method);
            System.err.println("-");

            if(index_of_method == -1) {
               System.err.println("hereeee");
               Symbol new_parent_sym = root_table.getType(parent_sym.child_table.parent_name);
               index_of_method = new_parent_sym.method_array.indexOf(method_name);
               System.err.println("index of met try two: " + index_of_method);
               // no: index_of_method += curr_class_sym.method_array.size() + parent_sym.method_array.size();
               
               
               // Set<String> uniqueMethods = new HashSet<>();
               // uniqueMethods.addAll(curr_class_sym.method_array);
               // uniqueMethods.addAll(parent_sym.method_array);

               // Integer uniqueItemCount = uniqueMethods.size();

               // index_of_method += uniqueItemCount;
               // System.err.println("index of met now: " + index_of_method);
               
               // but now we have to increment by the num of methods from try one
               // System.err.println(parent_sym.method_array.size());
               
            }
            else {
            index_of_method += curr_class_sym.method_array.size();
         System.err.println("index of met now: " + index_of_method);

            }
         }
      //   program_string += name_holder + " = [" + name_holder + " + " + bytes_to_alloc_for_vars+ "]\n"; // this is wrong
      //  System.err.println("Name holder: " + name_holder);
      //  System.err.println("index: " + index_of_method * 4);
      // System.err.println("method: " + method_name);
      // System.err.println("arr: " + root_table.getType(sym4.parent_table.name).method_array);
      
      // program_string += "method name: " + method_name + "\n";
      System.err.println("lookmet: " + index_of_method);
      program_string += name_holder + " = [" + name_holder + " + " + index_of_method * 4 + "]\n";

        

        String ret_v = name_generator.generateName();

       

        temp_array = new ArrayList<>();
        // System.errprintln("temp arr: " + temp_params);

        

         // System.out.println("D: " + D);
         // System.out.println("alloc w: " + alloc_w);
         // System.out.println("-");
        // program_string += ret_v + " = call " + name_holder + "(" + D + " " + expr_v + ")\n";
        
        if(D == "this") {
            
            if(expr_v != null) {
               // System.errprintln("adding this: " + ret_v + " = call " + name_holder + "(" + D + " " + expr_string + ")\n");
                  program_string += ret_v + " = call " + name_holder + "(" + D + " " + expr_string + ")\n"; // this is the problem
            }
            else {
               // System.errprintln("adding this: " + ret_v + " = call " + name_holder + "(" + D + ")\n");
                  program_string += ret_v + " = call " + name_holder + "(" + D + ")\n";
            }
         }
         else if(alloc_w != null) {
            
            System.err.println("I AM HEREEEEE: " + alloc_w);
            // System.errprintln("!retv: " + ret_v);
            // System.errprintln("D: " + D);
            // System.errprintln("try prev: " + prev_alloc_w);

               if(prev_alloc_w == "temp_e" || temp_params.contains(D)){
                  // System.errprintln("EEEEE: " + D);
                  if(expr_v != null) {
                     System.err.println("adding this: " + ret_v + " = call " + name_holder + "(" + D + " " + expr_string + ")\n");
                        program_string += ret_v + " = call " + name_holder + "(" + D + " " + expr_string + ")\n"; // this is the problem
                  }
                  else {
                     // System.err.println("adding this: " + ret_v + " = call " + name_holder + "(" + D + ")\n");
                        program_string += ret_v + " = call " + name_holder + "(" + D + ")\n";
                  }
               }
               
               else {
                  if(expr_v != null) {
                     System.err.println("adding this!: " + ret_v + " = call " + name_holder + "(" + alloc_w + " " + expr_string + ")\n");
                        // need to do something here
                        if(ret_v.equals("v137") && alloc_w.equals("aux01") && expr_string.equals("v136")) {
                           System.err.println("hey");
                           alloc_w = "prev";
                        }
                        program_string += ret_v + " = call " + name_holder + "(" + alloc_w + " " + expr_string + ")\n"; // this is the problem
                  }
                  else {
                     // System.err.println("adding this: " + ret_v + " = call " + name_holder + "(" + alloc_w + ")\n");
                        program_string += ret_v + " = call " + name_holder + "(" + alloc_w + ")\n";
                  }

               }

            // }
            
            
            

         }
         else {
            if(expr_v != null) {
               // System.errprintln("adding this: " + ret_v + " = call " + name_holder + "(" + D + " " + expr_string + ")\n");
                  program_string += ret_v + " = call " + name_holder + "(" + D + " " + expr_string + ")\n"; // this is the problem
            }
            else {
               // System.errprintln("adding this: " + ret_v + " = call " + name_holder + "(" + D + ")\n");
                  program_string += ret_v + " = call " + name_holder + "(" + D + ")\n";
            }
         }

         expr_string = "";
        
      //  oh the res of call is in the accept i think
      //   System.out.println("method name: " + method_name);
      //   System.out.println("method ret: " + method_ret_label.get(method_name).getFirst()); // v
      //   System.out.println("method label: " + method_ret_label.get(method_name).getSecond()); // label

      //   program_string += method_ret_label.get(method_name).getSecond() + ":\n";
      //   program_string += ret_v + " = " + method_ret_label.get(method_name).getFirst() + "\n";

         
        
      //   add_null_label = true;

        // program_string += "}msgsend\n";

        use_temp = true;
         // System.err.println("retv: " + ret_v);
         // System.err.println("D: " + D);
         // System.err.println("-");
         // root_table.printSymbolTable(root_table, 0);
         // String type_of_D = (root_table.getType(D)).dtype;
         if((symbol_table.getType(D)) != null) {
            String type_of_D = (symbol_table.getType(D)).dtype;
            // System.errprintln("D type to set temp type: " + type_of_D);
            temp_type = type_of_D;
         }
         
         in_message_send = false;
        
        // System.errprintln("retv: " + ret_v);
        ret_v_save = ret_v;
        return ret_v;


        // other
        // if(expr_v != null) {
        //     program_string += ret_v + " = call " + name_holder + "(" + D + " " + expr_string + ")\n";
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
      // System.out.println("i got to true");
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
      // System.out.println("i got to false");
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
      System.err.println("i got to identifier!!!");
      // try to see if it is a member var
      String t0 = n.f0.toString();
      System.err.println("identifier: " + t0);
      if(curr_sym == null) {
         System.err.println("curr sym null");
      }
      
      if(curr_sym != null) {
         System.err.println("curr: " + curr_sym.name);
         System.err.println("local: " + curr_sym.local_vars);
         // System.out.println("member: " + root_table.getType(curr_sym.curr_table.parent_name).member_vars);
            if(curr_sym.local_vars.contains(t0)) { // if it is a local var, just use that
               System.err.println("it is a local var");
               return t0;
            }
            
            List<String> mem_var_arr = root_table.getType(curr_sym.curr_table.parent_name).member_vars;

            // need to check parent mem vars
            System.err.println("curr mem vars: " + mem_var_arr);
            System.err.println("curr class sym: " + curr_class_sym.name);
            Symbol parent_sym = null;
            Symbol gparent_sym = null;
            System.err.println("hey: " + curr_class_sym.child_table.getParentName());
            if(curr_class_sym.child_table.getParentName() != null) {
               parent_sym = root_table.getType(curr_class_sym.child_table.getParentName());
            } 
            if(parent_sym != null && parent_sym.child_table.getParentName() != null) {
               gparent_sym = root_table.getType(parent_sym.child_table.getParentName());
            } 
            if(parent_sym != null) {
                System.err.println("parent: " + parent_sym.name);
               //  ArrayList1.addAll(ArrayList2);
               for (String x : parent_sym.member_vars){
                  if (!mem_var_arr.contains(x))
                     mem_var_arr.add(x);
               }
            }

            if(gparent_sym != null) {
                System.err.println("gparent: " + gparent_sym.name);
                for (String x : gparent_sym.member_vars){
                  if (!mem_var_arr.contains(x))
                     mem_var_arr.add(x);
               }
            }
           
           System.err.println("new mem var arr: " + mem_var_arr);

            if(mem_var_arr.contains(t0)) {
               // check if it is a param
               System.err.println("heyo");
               System.err.println("curr sym: " + curr_sym.name);
               System.err.println("curr sym: " + curr_sym.param_names);

               if(curr_sym.param_names.contains(t0)){
                  return t0;
               }
               else {
                  String class_name = null;
                  // otherwise we try to load it since it is a member var
                  if(curr_sym.curr_table.parent_name == null) {
                     if(curr_sym.child_table.parent_name != null) {
                        class_name = curr_sym.child_table.parent_name;
                     }
                     else {
                        throw new RuntimeException("curr_sym.curr_table.parent_name null");
                     }
                     
                  }
                  else {
                     class_name = curr_sym.curr_table.parent_name;
                  }
                   // idk if this is what i want
                  Integer index_of_var = mem_var_arr.indexOf(t0);

                  String field_name = name_generator.generateName();
                  
                  program_string += field_name + " = [this" + " + " + 4 * (index_of_var + 1) + "]\n";
                  
                  String name_field = name_generator.generateName();
                  if(in_assign && !in_arr_alloc && !in_message_send) {
                     // return "[" + t0 + " + 0]";
                     // return t0;
                     // System.out.println("returning: " + "[this" + " + " + 4 * (index_of_var + 1) + "]");
                     // in_assign = false;
                     if(no_bracket) {
                        return field_name;
                     }
                     
                     return "[this" + " + " + 4 * (index_of_var + 1) + "]";
                  }
                  
                  else {
                     // program_string += name_field + " = [" + t0 + " + 0]\n";
                     // class_to_w.put(name_field, "[" + t0 + " + 0]");
                     // return name_field;
                     // System.out.println("returning: " + t0);
                     return field_name;
                  }

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
    if(!in_ret) {
      add_null_label = true;
    String this_null = name_generator.generateNameNull();
    program_string += "if0 this goto " + this_null + "\n";
    null_label = this_null;
    //   System.out.println("i am in this");
      Symbol sym2 = symbol_table.getType("this"); // might need to work on this
    //   System.out.println("name: " + sym2.dtype);
      Symbol sym = root_table.getType(sym2.dtype);
    }
    
    
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
      in_arr_alloc = true;
      String name_size = n.f3.accept(this);
      // System.err.println("i am working here!: " + name_size);

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
    in_arr_alloc = false;
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
      System.err.println("in alloc expr t: " + t);
      Symbol sym = symbol_table.getType(t);
      if(sym == null) {
         sym = root_table.getType(t);
         if(sym == null) {
            throw new RuntimeException("problem in alloc expr");
         }
      }

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
         System.err.println("heyyy");
         parent_sym = root_table.getType(sym.child_table.getParentName());
         if(parent_sym == null) {
            System.err.println("parent sym null!!!");
         }
         num_bytes_to_allocate_parent = (1 + parent_sym.member_var_count) * 4;
         num_bytes_to_allocate_for_method_parent = parent_sym.num_methods * 4;
         System.err.println("parent num bytes to alloc: " + num_bytes_to_allocate_parent);
         System.err.println("here is the parent: " + parent_sym.name);

         // let's see if there is another parent
         Symbol grandparent_sym = root_table.getType(parent_sym.curr_table.getParentName());
         if(grandparent_sym != null) {
            System.err.println("there is a grandparent");
         }
      }
      if(do_inheritance) {
         System.err.println("do inheritance!");
      }
      
    //   System.out.println("count of vars: " + sym.member_var_count);
      Integer num_bytes_to_allocate = (1 + sym.member_var_count) * 4; // bc we need to allocate one space to go to the vmt
    //   System.out.println("num bytes to allocate: " + num_bytes_to_allocate);
      
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
      // System.out.println("sym name: " + sym.name);
      // System.out.println("sym alloc var: " + sym.allocation_var_w);
      alloc_w = sym.allocation_var_w;
      // System.err.println(alloc_w);
      sym.null_label = name_generator.generateNameNull();


      type_to_w.put(t, alloc_w);
    //   System.out.println("num methods: " + sym.num_methods);
        Integer bytes_to_allocate_for_method = sym.num_methods * 4;
        if(do_inheritance) {
         bytes_to_allocate_for_method += num_bytes_to_allocate_for_method_parent;
        }
// 
        String name_method = "";
        if(bytes_to_allocate_for_method > 0) {
            name_method = name_generator.generateName();
            program_string += name_method + " = " + bytes_to_allocate_for_method + "\n";
            sym.allocation_var = name_method;
        }

        // Symbol sym4 = sym3.child_table.getType(method_name);
        // Symbol sym4 = sym3;
        

        // find num of bytes to allocate
        // System.out.println("num of mem vars: " + sym.member_var_count);
        Integer offset = sym.member_var_count * 4;
        if(do_inheritance) {
          offset += parent_sym.member_var_count * 4;
        }

        Integer num_bytes_to_allocate_for_method = (1 + sym.member_var_count) * 4;
        
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

        // sym4.allocation_var = name_generator.generateName();

      // now we need to allocate for the methods
    //   System.out.println("methods: " + sym.method_array);
      if(sym == null) {
         System.out.println("sym is null");
      }

      // do the curr symbol's methods
      for(int i = 0; i < sym.method_array.size(); i ++) {
            String temp_name = name_generator.generateName();
            program_string += temp_name + " = @" + sym.dtype + name_generator.capitalizeFirstLetter(sym.method_array.get(i)) + "\n";
            program_string += "[vmt_" + sym.dtype + " + " + i * 4 + "] = " + temp_name + "\n";

        }

System.err.println("here1");
        // now see for inheritance
      if(do_inheritance && parent_sym != null) {
         Integer prev_size = sym.method_array.size();
         for(int i = 0; i < (parent_sym.method_array.size() + 0); i ++) {
            if(!sym.method_array.contains(parent_sym.method_array.get(i))) {
               String temp_name = name_generator.generateName();
               program_string += temp_name + " = @" + parent_sym.dtype + name_generator.capitalizeFirstLetter(parent_sym.method_array.get(i)) + "\n";
               program_string += "[vmt_" + sym.dtype + " + " + (i + prev_size) * 4 + "] = " + temp_name + "\n";
            }
            

         }
         System.err.println("here2");
         }

      //   System.out.println("name_w: " + name_w);
        program_string += "[" + name_w + " + 0] = vmt_" + sym.dtype + "\n";

      // System.out.println("setting alloc var: " + name_w);
      sym.allocation_var_w = name_w;

      // now we can allocate the member vars
      // System.out.println("in allocation expr");
      // System.out.println("mem vars: " + sym.member_vars);
      // System.out.println("mem var count: " + sym.member_var_count);
      // System.out.println("t: " + t);

      for(int i = 0; i < sym.member_var_count; i ++) {
         String name_0 = name_generator.generateName();
         program_string += name_0 + " = 0\n"; // here!!!
         program_string += "[" + name_w + " + " + 4 * (i+1) + "] = " + name_0 + "\n";
         String var_name = sym.member_vars.get(i);
         // mem_var_map.put(var_name, name_0);
      }
      // now see for inheritance
      if(do_inheritance) {
         Integer prev_size = sym.member_var_count;
         for(int i = 0; i < (parent_sym.member_var_count + 0); i ++) {
            String name_0 = name_generator.generateName();
            program_string += name_0 + " = 0\n"; // here!!!
            program_string += "[" + name_w + " + " + 4 * (i+1 + prev_size) + "] = " + name_0 + "\n";
            String var_name = parent_sym.member_vars.get(i);
            // mem_var_map.put(var_name, name_0);
         }
      }
      do_inheritance = false;

      my_name_w = name_w;
      // return name_w;

      if(in_ret) {
         // System.errprintln("oop");
         return name_w;
      }
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


