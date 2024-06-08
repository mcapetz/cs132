import minijava.visitor.*;

import minijava.syntaxtree.*;

import java.util.Enumeration;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides default methods which visit each node in the tree in depth-first
 * order.  Your visitors may extend this class.
 */
public class VisitorMethodDec extends GJNoArguDepthFirst<String> implements GJNoArguVisitor<String>{

   public SymbolTable symbol_table = new SymbolTable();
   public SymbolTable root_table = new SymbolTable();
   public Symbol curr_sym;
   public List<Symbol> methods = new ArrayList<>();
   public String curr_method;
   public Integer member_var_count = 0;
   public List<String> local_vars = new ArrayList<>();
   public Integer num_methods = 0;
   public List<String> method_array = new ArrayList<>();
   public Boolean is_local = false;

   public VisitorMethodDec(SymbolTable symbol_table) {
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
      n.f0.accept(this);
      n.f1.accept(this);
      // System.out.println("Methods:")
      return null; 
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
      n.f14.accept(this);
      n.f15.accept(this);
      return _ret;
   }

   /**
    * f0 -> ClassDeclaration()
    *       | ClassExtendsDeclaration()
    */
    @Override
   public String visit(TypeDeclaration n) {
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
      String D = n.f1.accept(this);
      Symbol sym = symbol_table.getType(D);
      curr_sym = sym;
      sym.child_table.name = D;
      SymbolTable new_table = sym.child_table;
      
      // set the parent
      new_table.setParent(symbol_table);
      sym.parent_table = symbol_table;

      SymbolTable old_table = symbol_table;
      this.symbol_table = new_table;
      n.f4.accept(this); // method dec, we need this for this visitor
      sym.num_methods = num_methods;
      // System.out.println("class: " + D);
      // System.out.println("new table num methods: " + num_methods);
      sym.method_array = method_array;
      num_methods = 0;
      method_array = new ArrayList<>();
      System.err.println(D + "'s method arr: " + sym.method_array);
      this.symbol_table = old_table;
      curr_sym = null;
      return null;
   }

   @Override
   public String visit(ClassExtendsDeclaration n) {
      System.err.println("i got here class extends");
        n.f1.accept(this);
        String classname = visit(n.f1);
        Symbol sym = symbol_table.getType(classname);
        curr_sym = sym;
      //   System.out.println("i got here???");
        SymbolTable new_table = sym.child_table;

        // set the parent
        new_table.setParent(symbol_table);
        new_table.setParentName(n.f3.f0.toString());

        // handle scoping
        SymbolTable old_table = symbol_table;
        symbol_table = new_table;
        n.f6.accept(this); // method dec, we need this for this visitor
         sym.num_methods = num_methods;
         // System.out.println("class: " + D);
         // System.out.println("new table num methods: " + num_methods);
         sym.method_array = method_array;
         num_methods = 0;
         method_array = new ArrayList<>();
         System.err.println(classname + "'s method arr: " + sym.method_array);
      //   visit(n.f5);
      //   visit(n.f6);
        symbol_table = old_table;
        curr_sym = null;
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
      num_methods += 1;
      
      String method_name = n.f2.f0.toString();    
      // System.out.println("method name: " + method_name);
      Symbol sym = symbol_table.getType(method_name);
      sym.parent_table.name = curr_sym.name;
      if(sym == null) { // try to get the supertype's method
        String super_type = symbol_table.getType(symbol_table.name).child_table.parent_name;
        Symbol temp = symbol_table.getType(super_type);
        sym = temp.child_table.getType(method_name);
      }

      methods.add(sym);
      method_array.add(method_name);
      sym.child_table.name = method_name; // scoping 
      SymbolTable old_table = symbol_table;
      SymbolTable new_table = sym.child_table;

      // set the parent
      new_table.setParent(symbol_table);
      sym.parent_table = symbol_table;

      this.symbol_table = new_table;
      curr_method = method_name;
      n.f4.accept(this); // formal param list
      is_local = true;
      n.f7.accept(this); // var dec  these are local vars
      is_local = false;
      if(sym == null) {
         // System.out.println("Nooo");
      }
      sym.member_var_count = member_var_count;
      sym.local_vars = local_vars;
      // System.out.println("sym name: " + sym.name);
      // System.out.println("local vars: " + sym.local_vars);
      local_vars = new ArrayList<>();
      member_var_count = 0;
      n.f8.accept(this); // statements
      String return_type = n.f10.accept(this);
      // System.out.println("return type: " + return_type);
      
      this.symbol_table = old_table; // reset the scope
      curr_method = "";
      return null;
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
      String first_param_name = ((Identifier)n.f0.f1).f0.toString(); 
      curr_sym.param_types.add(first_param_type);
      curr_sym.param_names.add(first_param_name);
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
      curr_sym.param_names.add(((Identifier)n.f1.f1).f0.toString()); 
      // System.out.println(curr_sym.param_names);
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
        return null;
   }

   /**
    * f0 -> "{"
    * f1 -> ( Statement() )*
    * f2 -> "}"
    */
    @Override
   public String visit(Block n) {
        return null;
   }

   /**
    * f0 -> Identifier()
    * f1 -> "="
    * f2 -> Expression()
    * f3 -> ";"
    */
    @Override
   public String visit(AssignmentStatement n) {
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

      if(t0 == "bool" && t2 == "bool") {
        return "bool";
      }
      else {
        throw new RuntimeException("Error-in-and-expr");
      }
      // return "bool";
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

      if(t0 == "int" && t2 == "int") {
        return "bool";
      }
      else {
        throw new RuntimeException("Error-in-compare");
      }
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

      if(t0 == "int" && t2 == "int") {
        return "int";
      }
      else {
        throw new RuntimeException("Error-in-plus");
      }
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

      if(t0 == "int" && t2 == "int") {
        return "int";
      }
      else {
        throw new RuntimeException("Error-in-minus");
      }
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

      if(t0 == "int" && t2 == "int") {
        return "int";
      }
      else {
        throw new RuntimeException("Error-in-times");
      }
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

      if(t0 == "int[]" && t2 == "int") {
        return "int";
      }
      else {
         return null;
      //   throw new RuntimeException("Error in arr lookup");
      }
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "."
    * f2 -> "length"
    */
    @Override
   public String visit(ArrayLength n) {
      String t0 = n.f0.accept(this);

      if(t0 == "int[]") {
        return "int";
      }
      else {
         return null;
      //   throw new RuntimeException("Error in arr len");
      }
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
   public String visit(MessageSend n) { 
      return null;
   }

   /**
    * f0 -> Expression()
    * f1 -> ( ExpressionRest() )*
    */
    @Override
   public String visit(ExpressionList n) { 
      return null;
   }

   /**
    * f0 -> ","
    * f1 -> Expression()
    */
    @Override
   public String visit(ExpressionRest n) { 
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
      Symbol sym = symbol_table.getType(n.f0.toString());
      String t = null;
      if( sym != null && sym.dtype != null) {
        t = sym.dtype;
      }      
      return t; 
   }

   /**
    * f0 -> "this"
    */
    @Override
   public String visit(ThisExpression n) { 
      return null;
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
      return null;
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
        throw new RuntimeException("Error-in-not-expr");
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
   
    /**
    * f0 -> Type()
    * f1 -> Identifier()
    * f2 -> ";"
    */
   @Override
   public String visit(VarDeclaration n) {
      String _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      String var_name = n.f1.f0.toString();
      member_var_count += 1;
      if(is_local) {
         // add to the list of local vars
         // System.out.println("adding a local var! " + var_name);
         local_vars.add(var_name);
      }
      return _ret;
   }

}
