import minijava.visitor.*;

import minijava.syntaxtree.*;

import java.util.Enumeration;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides default methods which visit each node in the tree in depth-first
 * order.  Your visitors may extend this class.
 */
public class Visitor2 extends GJNoArguDepthFirst<String> implements GJNoArguVisitor<String>{

   public SymbolTable symbol_table = new SymbolTable();
   public SymbolTable root_table = new SymbolTable();
   public Symbol curr_sym;
   public Symbol parent_sym;
   public Symbol method_sym;
   public List<String> temp_array = new ArrayList<>();
   public Integer member_var_count = 0;
   public List<String> member_vars = new ArrayList<>();
   public Integer main_member_var_count = 0;
   public List<String> main_member_vars = new ArrayList<>();
   public Boolean in_main = false;

   public Visitor2(SymbolTable symbol_table) {
      this.symbol_table = symbol_table;
      this.root_table = symbol_table;
   }

    /**
    * f0 -> MainClass()
    * f1 -> ( TypeDeclaration() )*
    * f2 -> <EOF>
    */
    @Override
   public String visit(Goal n) {
      System.err.println("in vis 2 goal");
      String main = n.f0.accept(this);
      String typeDecs = n.f1.accept(this);
      System.err.println("done vis 2 goal");
      return main; // maybe?
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
      System.err.println("in vis 2 main");
      // System.out.println("name of class: " + ((Identifier)n.f1).f0.toString()); // help
      String class_name = ((Identifier)n.f1).f0.toString();
      this.curr_sym = root_table.getType(class_name);
      in_main = true;
      Symbol sym = this.curr_sym;
      n.f14.accept(this); // var decs 
      if(sym == null) {
         // System.out.println("Nooo");
         root_table.printSymbolTable(root_table, 0);
      }
      sym.member_var_count = main_member_var_count;
      sym.member_vars = main_member_vars;
      // System.out.println("Main mem vars: " + sym.member_vars);
      main_member_var_count = 0;
      main_member_vars = new ArrayList<>();
      System.err.println("before statements");
      n.f15.accept(this); // statements
      System.err.println("after statements");
      in_main = false;
      this.curr_sym = null;
      System.err.println("done vis 2 main");
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
      System.err.println("in vis 2 ClassDeclaration");
      String D = n.f1.accept(this);
      Symbol sym = symbol_table.getType(D);
      SymbolTable new_table = sym.child_table;
      
      // set the parent
      new_table.setParent(symbol_table);
      sym.parent_table = symbol_table;

      SymbolTable old_table = symbol_table;
      this.symbol_table = new_table;
    //   curr_sym = sym;
      n.f3.accept(this); // var dec
      
      sym.member_var_count = member_var_count;
      sym.member_vars = member_vars;
      member_var_count = 0;
      member_vars = new ArrayList<>();
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
      System.err.println("in vis 2 ClassExtendsDeclaration");
        n.f1.accept(this);
        String classname = visit(n.f1);
        Symbol sym = symbol_table.getType(classname);
        SymbolTable new_table = sym.child_table;

        // set the parent
        new_table.setParent(symbol_table);
        new_table.setParentName(n.f3.f0.toString());

        // handle scoping
        SymbolTable old_table = symbol_table;
        symbol_table = new_table;

        visit(n.f5); // var dec
        sym.member_var_count = member_var_count;
         sym.member_vars = member_vars;
         member_var_count = 0;
         member_vars = new ArrayList<>();
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
   public String visit(VarDeclaration n) {
      String _ret=null;
      n.f0.accept(this);
      String t1 = n.f1.accept(this); // identifier
      n.f2.accept(this);
      if(!in_main) {
         member_var_count += 1;
         String var_name = n.f1.f0.toString();
         member_vars.add(var_name);
      }
      else {
         main_member_var_count += 1;
         String var_name = n.f1.f0.toString();
         main_member_vars.add(var_name);
      }
      
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
      String method_name = n.f2.f0.toString();
      Symbol sym = symbol_table.getType(method_name);
      sym.child_table.name = method_name;
      SymbolTable old_table = symbol_table;
      SymbolTable new_table = sym.child_table;

      // set the parent
      new_table.setParent(symbol_table);
      sym.parent_table = symbol_table;

      this.symbol_table = new_table; // handle scoping
      n.f7.accept(this); // var dec
      n.f8.accept(this); // statements
      String return_type = n.f10.accept(this);
      // if(return_type != sym.dtype) {
      //   throw new RuntimeException("In method dec, return types don't match");
      // }
   
      this.symbol_table = old_table;
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
      return n.f0.accept(this);
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
      String t0 = n.f0.accept(this);
      String t2 = n.f2.accept(this);

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
      String t2 = n.f2.accept(this);
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
      String t2 = n.f2.accept(this);
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
      String t0 = n.f0.accept(this);
      String t2 = n.f2.accept(this);

      return "bool";
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

      return "bool";
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

      return "int";
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

      return "int";
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

      return "int";
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

      return "int";
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "."
    * f2 -> "length"
    */
    @Override
   public String visit(ArrayLength n) {
      String t0 = n.f0.accept(this);

      return "int";
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
        System.err.println("in vis 2 msgsend");
        List<String> old_array = temp_array;
        temp_array = new ArrayList<>(); // save the scope of the expr list data structure

        String D = n.f0.accept(this); 
      //   System.err.println("D: " + D);
        String method_name = n.f2.f0.toString();
        Symbol sym3 = root_table.getType(D);
        System.err.println("D: " + D);
      //   System.err.println("sym3: " + sym3.name);

      //   System.out.println("method name: " + method_name);
        
        Symbol method;
        this.curr_sym = sym3;
        this.parent_sym = sym3;
        if(sym3 == null) {
         throw new RuntimeException("sym3 null3");
        }
        SymbolTable new_table = sym3.child_table;
        method = new_table.getType(method_name);
        if(method == null) {
            SymbolTable new_new_table = sym3.curr_table;
            method = new_new_table.getType(method_name);
        }
        if(method == null) {

         // try to get from superclass
         SymbolTable met_table = symbol_table.getType(D).child_table;
         while(met_table.getType(method_name) != null) {
            if(met_table.getType(method_name) != null) {
               method = met_table.getType(method_name);
            }
            met_table = symbol_table.getType(met_table.getParentName()).child_table;
         }
         if(met_table.getType(method_name) != null) {
            method = met_table.getType(method_name);
         }
         if(method == null) {

            // now we will try something else

            if(sym3 == null) {
            throw new RuntimeException("sym3 null4");
         }
         System.err.println("method name: " + method_name);
         System.err.println("sym name: " + sym3.name);
         System.err.println("parent name: " + sym3.child_table.parent_name);
         
         String parent_name = null;
         Symbol parent_sym = null;
         Symbol met_sym = null;

         parent_name = sym3.child_table.parent_name;
         parent_sym = root_table.getType(parent_name);
         System.err.println(parent_sym.method_array);
         met_sym = parent_sym.child_table.getType(method_name);
         // System.err.println(met_sym.name);
         method = met_sym;
         if(method == null) {
            // try again
            Symbol temp_sym = parent_sym;
            parent_name = temp_sym.child_table.parent_name;
            parent_sym = root_table.getType(parent_name);
            System.err.println(parent_sym.method_array);
            met_sym = parent_sym.child_table.getType(method_name);
            // System.err.println(met_sym.name);
            method = met_sym;


            if(method == null) 
               throw new RuntimeException("met not found");
         }
         
            
         }

         
        }
        this.method_sym = method;
        Symbol now_sym = this.curr_sym.child_table.getType(method_name);
        this.curr_sym = method;
        String exprList = n.f4.accept(this); // expr list
        
        
        if(n.f4.present()) { // check if there are args
            if(method == null) {
               System.err.println("i got here no method");
            }
            if(!method.param_types.equals(temp_array)) {
               //  throw new RuntimeException("Error in msg send: Argument types don't match parameter types");
            }
        }
        else { // there are no args
            if (method.param_types.size() != 0) {
               //  throw new RuntimeException("Error in msg send: Number of arguments doesn't match number of parameters");
            }
        }

      temp_array = old_array; // reset the scope of the expr list data structure
      // System.out.println("method dtype: " + method.dtype);
      return method.dtype;
   }

   /**
    * f0 -> Expression()
    * f1 -> ( ExpressionRest() )*
    */
    @Override
   public String visit(ExpressionList n) {  // add expr to the temp array
        temp_array.add(n.f0.accept(this));
        n.f1.accept(this);
        return null;
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
      return "int";
   }

   /**
    * f0 -> "true"
    */
    @Override
   public String visit(TrueLiteral n) {
      return "bool";
   }

   /**
    * f0 -> "false"
    */
    @Override
   public String visit(FalseLiteral n) {
      return "bool";
   }

   /**
    * f0 -> <IDENTIFIER>
    */
    @Override
   public String visit(Identifier n) {
      // System.out.println("looking for: " + n.f0.toString());
      Symbol sym = symbol_table.getType(n.f0.toString());
      String t = null;
      if( sym != null && sym.dtype != null) {
        t = sym.dtype;
      }      
      // else if(this.curr_sym != null) { // trying to get the supertype
      //   Symbol sym2 = this.curr_sym.child_table.getType(n.f0.toString());
      //   if(sym2 != null ) {
      //       t = sym2.dtype;
      //   }
        
      // }
      else if(symbol_table.parent_table != null && symbol_table.parent_table.getType(n.f0.toString()) != null) {
        Symbol sym3 = symbol_table.parent_table.getType(n.f0.toString());
        t = sym3.dtype;
      }
      else {
        // couldn't find t
      //   this.root_table.printSymbolTable(root_table, 0);
      //   System.out.println("-");
      //   symbol_table.printSymbolTable(symbol_table, 0);
        Symbol root_sym = root_table.getType(n.f0.toString());
        String name = n.f0.toString();
      //   System.out.println("keys: " + root_table.symbol_table.keySet());
      //   System.out.println(root_sym.dtype);
         List<String> arr = new ArrayList<>(root_table.symbol_table.keySet());

         // System.out.println("arr: " + arr);

         for(int i = 0; i < arr.size(); i ++) {
               Symbol temp_sym = root_table.getType(arr.get(i));
               // System.out.println("temp sym: " + temp_sym.name);
               // System.out.println(temp_sym.child_table.symbol_table.keySet());
               // System.out.println("name: " + name);
               // System.out.println(temp_sym.child_table.getType(name));
               if(temp_sym != null && temp_sym.child_table.getType(name) != null) {
                  // System.out.println("found? " + temp_sym.child_table.getType(name).name);
                  return temp_sym.child_table.getType(name).dtype;
               }
         }
        throw new RuntimeException("Identifier not found");
      }
      return t; 
   }

   /**
    * f0 -> "this"
    */
    @Override
   public String visit(ThisExpression n) { 
      Symbol sym = symbol_table.getType("this");
      return sym.dtype;
   }

   /**
    * f0 -> "new"
    * f1 -> "int"
    * f2 -> "["
    * f3 -> Expression()
    * f4 -> "]"
    */
    @Override
   public String visit(ArrayAllocationExpression n) {
      String t3 = n.f3.accept(this);
      if(t3 == "int") {
        return "int[]";
      }
      else {
      //   throw new RuntimeException("Error in arr alloc");
        return null;
      }
   }

   /**
    * f0 -> "new"
    * f1 -> Identifier()
    * f2 -> "("
    * f3 -> ")"
    */
    @Override
   public String visit(AllocationExpression n) {
      String t = n.f1.accept(this);
      return t;
   }

   /**
    * f0 -> "!"
    * f1 -> Expression()
    */
    @Override
   public String visit(NotExpression n) {
      String t1 = n.f1.accept(this);

      if(t1 == "bool") {
        return "bool";
      }
      else {
        throw new RuntimeException("Error-in-not-expr-vis2");
      }
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
