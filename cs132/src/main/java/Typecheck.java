
// package minijava.visitor;

import minijava.MiniJavaParser;

import minijava.syntaxtree.*;


public class Typecheck{
  public static void main(String [] args) {
        Goal root;

        try {
          root = new MiniJavaParser(System.in).Goal();
          Visitor1 visitor1 = new Visitor1(); // visitor 1 builds the symbol table
          root.accept(visitor1);
          SymbolTable symbol_table = visitor1.getSymbolTable();
          VisitorMethodDec visitorMethodDec = new VisitorMethodDec(symbol_table);
          root.accept(visitorMethodDec); // 
          Visitor2 visitor2 = new Visitor2(symbol_table);
          root.accept(visitor2);
          System.out.println("Program type checked successfully");
        }
        catch(Exception e) {
          System.out.println("Type error");
        }
        

        
  }
}
