
import IR.token.Identifier;
import sparrowv.*;
import java.util.*;
import sparrowv.visitor.*;
import java.util.function.Consumer;



public class SV2RConverter extends DepthFirst {

    StringBuilder str_builder = new StringBuilder("");
    StringBuilder str_builder_alloc = new StringBuilder("");
    StringBuilder str_builder_data = new StringBuilder("");
    private Map<String, Map<String, Integer>> localMap;
    private Map<String, Integer> stackMap;
    String curr_func = null;
    Boolean first_func = true;
    private Set<String> labelSet = new HashSet<>();
    private final Set<Instruction> visitedNodes = new HashSet<>();
    int short_ct = 0;
    int custom_err_ct = 0;

    public SV2RConverter(Map<String, Map<String, Integer>> localMap, Map<String, Integer> stackMap) {
        this.localMap = localMap;
        this.stackMap = stackMap;
        // add the boilerplate code
        str_builder.append(".equiv @sbrk, 9\n");
        str_builder.append(".equiv @print_string, 4\n");
        str_builder.append(".equiv @print_char, 11\n");
        str_builder.append(".equiv @print_int, 1\n");
        str_builder.append(".equiv @exit 10\n");
        str_builder.append(".equiv @exit2, 17\n");
        // str_builder.append(".text\n\n\n");
        // str_builder.append("jal Main\n");
        // str_builder.append("li a0, @exit\n");
        // str_builder.append("ecall\n\n");
        // str_builder.append(".globl Main\n");
        // str_builder.append("Main:\n"); // idk if need this

        str_builder_alloc.append(".globl alloc\n");
        str_builder_alloc.append("alloc:\n");
        str_builder_alloc.append("\tmv a1, a0\n");
        str_builder_alloc.append("\tli a0, @sbrk\n");
        str_builder_alloc.append("\tecall\n");
        str_builder_alloc.append("\tjr ra\n");

        str_builder_data.append(".data\n\n\n");
        str_builder_data.append(".globl msg_0\n");
        str_builder_data.append("msg_0:\n");
        str_builder_data.append("\t.asciiz \"null pointer\"\n");

        str_builder_data.append(".globl msg_1\n");
        str_builder_data.append("msg_1:\n");
        str_builder_data.append("\t.asciiz \"array index out of bounds\"\n");
        // str_builder_data.append("\t.align 2\n"); // maybe don't need?
        
    }

  
  /*   List<FunctionDecl> funDecls; */
  @Override
  public void visit(Program n) {
    for (FunctionDecl fd: n.funDecls) {
        fd.accept(this);
    }

    // add the error
    str_builder.append(".globl error\n");
    str_builder.append("error:\n");
    str_builder.append("\tmv a1, a0\n");
    str_builder.append("\tli a0, @print_string\n"); 
    str_builder.append("\tecall\n");
    str_builder.append("\tli a1, 10\n");
    str_builder.append("\tli a0, @print_char\n");
    str_builder.append("\tecall\n");
    str_builder.append("\tli a0, @exit\n");
    str_builder.append("\tecall\n");
    str_builder.append("abort_17:\n");
    str_builder.append("\tj abort_17\n\n");

    str_builder.append(str_builder_alloc.toString());
    str_builder.append(str_builder_data.toString());
    
    System.out.println(str_builder.toString());
  }

  /*   Program parent;
   *   FunctionName functionName;
   *   List<Identifier> formalParameters;
   *   Block block; */
  @Override
  public void visit(FunctionDecl n) {
    curr_func = n.functionName.toString();
    if(curr_func == "main") {
      curr_func = "Main";
    }
    if(first_func) {
      str_builder.append(".text\n\n\n");
        str_builder.append("\tjal " + curr_func + "\n");
        str_builder.append("\tli a0, @exit\n");
        str_builder.append("\tecall\n\n");
    }
    str_builder.append(".globl " + curr_func + "\n");
    str_builder.append("\t" + curr_func + ":\n"); // idk if need this
    str_builder.append("\tsw fp, -8(sp)\n");
    str_builder.append("\tmv fp, sp\n");

    // formal params
    int curr_offset = stackMap.get(curr_func);
    str_builder.append("\tli t6, " + curr_offset + "\n");
    str_builder.append("\tsub sp, sp, t6\n");
    str_builder.append("\tsw ra, -4(fp)\n");



    // for (Identifier fp: n.formalParameters) {
    //     // ... fp ...
    // }
    n.block.accept(this); // change to visit?

    int id_offset = localMap.get(curr_func).get(n.block.return_id.toString());
    str_builder.append("\tlw a0, " + id_offset + "(fp)\n");
    str_builder.append("\tlw ra, -4(fp)\n");
    str_builder.append("\tlw fp, -8(fp)\n");
    str_builder.append("\taddi sp, sp, " + curr_offset + "\n");
    str_builder.append("\taddi sp, sp, " + n.formalParameters.size() * 4 + "\n");
    // if (first_func) {
    //     str_builder.append("\tli a1, 10\n");
    //     str_builder.append("\tli a0, 11\n");
    //     str_builder.append("\tecall\n\n");
    // }
    str_builder.append("\tjr ra\n\n");
    first_func = false;


  }

  /*   FunctionDecl parent;
   *   List<Instruction> instructions;
   *   Identifier return_id; */
  @Override
  // public void visit(Block n) {
  //   // for (Instruction i: n.instructions) {
  //   //     i.accept(this);
  //   // }
  //   // super.visit(n);
  // }
  public void visit(Block n) {
    // Create a map of instruction classes to their corresponding visit methods
    Map<Class<? extends Instruction>, Consumer<Instruction>> visitMethods = new HashMap<>();
    visitMethods.put(LabelInstr.class, i -> visit((LabelInstr) i));
    visitMethods.put(Move_Reg_Integer.class, i -> visit((Move_Reg_Integer) i));
    visitMethods.put(Move_Reg_FuncName.class, i -> visit((Move_Reg_FuncName) i));
    visitMethods.put(Add.class, i -> visit((Add) i));
    visitMethods.put(Subtract.class, i -> visit((Subtract) i));
    visitMethods.put(Multiply.class, i -> visit((Multiply) i));
    visitMethods.put(LessThan.class, i -> visit((LessThan) i));
    visitMethods.put(Load.class, i -> visit((Load) i));
    visitMethods.put(Store.class, i -> visit((Store) i));
    visitMethods.put(Move_Reg_Reg.class, i -> visit((Move_Reg_Reg) i));
    visitMethods.put(Move_Id_Reg.class, i -> visit((Move_Id_Reg) i));
    visitMethods.put(Move_Reg_Id.class, i -> visit((Move_Reg_Id) i));
    visitMethods.put(Alloc.class, i -> visit((Alloc) i));
    visitMethods.put(Print.class, i -> visit((Print) i));
    visitMethods.put(ErrorMessage.class, i -> visit((ErrorMessage) i));
    visitMethods.put(Goto.class, i -> visit((Goto) i));
    visitMethods.put(IfGoto.class, i -> visit((IfGoto) i));
    visitMethods.put(Call.class, i -> visit((Call) i));

        for (Instruction i : n.instructions) {
            // Check if the instruction has already been visited
            if (!visitedNodes.contains(i)) {
                // Mark the instruction as visited
                visitedNodes.add(i);
                // System.err.println("Visiting instruction: " + i.getClass().getSimpleName()); // Debug statement

                Consumer<Instruction> visitMethod = visitMethods.get(i.getClass());
                if (visitMethod != null) {
                    visitMethod.accept(i);
                } else {
                    throw new RuntimeException("Unknown instruction type: " + i.getClass().getName());
                }
            } else {
                // System.err.println("Skipping already visited instruction: " + i.getClass().getSimpleName()); // Debug statement
            }
        }
    
}

  /*   Label label; */
  @Override
  public void visit(LabelInstr n) {
    if (!labelSet.contains(curr_func + n.label.toString())) {
        str_builder.append(curr_func + n.label.toString() + ":\n");
        labelSet.add(curr_func + n.label.toString());
    }
  }

  /*   Register lhs;
   *   int rhs; */
  @Override
  public void visit(Move_Reg_Integer n) {
    str_builder.append("\tli " + n.lhs.toString() + ", " + n.rhs + "\n");
  }

  /*   Register lhs;
   *   FunctionName rhs; */
  @Override
  public void visit(Move_Reg_FuncName n) { // what is a FunctionName?
  str_builder.append("\tla " + n.lhs.toString() + ", " + n.rhs.toString() + "\n");
  }

  /*   Register lhs;
   *   Register arg1;
   *   Register arg2; */
  @Override
  public void visit(Add n) {
    str_builder.append("\tadd " + n.lhs.toString() + ", " + n.arg1.toString() + ", " + n.arg2.toString() + "\n");
  }

  /*   Register lhs;
   *   Register arg1;
   *   Register arg2; */
  @Override
  public void visit(Subtract n) {
    str_builder.append("\tsub " + n.lhs.toString() + ", " + n.arg1.toString() + ", " + n.arg2.toString() + "\n");
  }

  /*   Register lhs;
   *   Register arg1;
   *   Register arg2; */
  @Override
  public void visit(Multiply n) {
    str_builder.append("\tmul " + n.lhs.toString() + ", " + n.arg1.toString() + ", " + n.arg2.toString() + "\n");
  }

  /*   Register lhs;
   *   Register arg1;
   *   Register arg2; */
  @Override
  public void visit(LessThan n) {
    str_builder.append("\tslt " + n.lhs.toString() + ", " + n.arg1.toString() + ", " + n.arg2.toString() + "\n");
  }

  /*   Register lhs;
   *   Register base;
   *   int offset; */
  @Override
  public void visit(Load n) { // load word for 32
    str_builder.append("\tlw " + n.lhs.toString() + ", " + n.offset + "(" + n.base.toString() + ")\n");
  }

  /*   Register base;
   *   int offset;
   *   Register rhs; */
  @Override
  public void visit(Store n) { // might be wrong order
    str_builder.append("\tsw " + n.rhs.toString() + ", " + n.offset + "(" + n.base.toString() + ")\n");
  }

  /*   Register lhs;
   *   Register rhs; */
  @Override
  public void visit(Move_Reg_Reg n) {
    str_builder.append("\tmv " + n.lhs.toString() + ", " + n.rhs.toString() + "\n");
  } 

  /*   Identifier lhs;
   *   Register rhs; */
  @Override
  public void visit(Move_Id_Reg n) {
    Integer offset = localMap.get(curr_func).get(n.lhs.toString());
    str_builder.append("\tsw " + n.rhs.toString() + ", " + offset + "(fp)\n");
  }

  /*   Register lhs;
   *   Identifier rhs; */
  @Override
  public void visit(Move_Reg_Id n) {
    Integer offset = localMap.get(curr_func).get(n.rhs.toString());
    str_builder.append("\tlw " + n.lhs.toString() + ", " + offset + "(fp)\n");
  }

  /*   Register lhs;
   *   Register size; */
  @Override
  public void visit(Alloc n) {
    // str_builder.append("\tli a7, 9\n"); // Syscall number for sbrk
    // str_builder.append("\tmv a0, " + n.size.toString() + "\n"); // Move size to a0
    // str_builder.append("\tecall\n"); // Make the syscall to allocate memory

    // // Store the allocated memory address in lhs
    // str_builder.append("\tmv " + n.lhs.toString() + ", a0\n"); // Store the return value in lhs

    // try something else
    str_builder.append("\tmv a0, " + n.size.toString() + "\n");
    str_builder.append("\tjal alloc\n");
    str_builder.append("\tmv " + n.lhs.toString() + ", a0\n");
  }

  /*   Register content; */
  @Override
  public void visit(Print n) { // double check this
    str_builder.append("\tmv a1, " + n.content.toString() + "\n");
    str_builder.append("\tli a0, @print_int\n");
    str_builder.append("\tecall\n");
    // Print the newline character
    str_builder.append("\tli a1, 10\n"); // 10 is the ASCII code for newline '\n'
    str_builder.append("\tli a0, @print_char\n"); // Assuming @print_char prints a single character
    str_builder.append("\tecall\n");
  }

  /*   String msg; */
  @Override
  public void visit(ErrorMessage n) { // this is good
  // System.err.println("msg: " + n.msg);
  // str_builder.append("\tla a0, msg_0\n");
  //   str_builder.append("\tj error\n");


  if(n.msg.equals("\"null pointer\"")) {
    str_builder.append("\tla a0, msg_0\n");
    str_builder.append("\tj error\n");
  }
  else if(n.msg.equals("\"array index out of bounds\"")) {
    str_builder.append("\tla a0, msg_1\n");
    str_builder.append("\tj error\n");
  }
  else { // custom
  System.err.println("custom error: " + n.msg);
    str_builder.append("\tla a0, msg_custom_" + custom_err_ct + "\n");
    str_builder.append("\tj error\n");

    str_builder_data.append(".globl msg_custom_" + custom_err_ct +"\n");
    str_builder_data.append("msg_custom_" + custom_err_ct + ":\n");
    str_builder_data.append("\t.asciiz \"" + n.msg + "\"\n");
  }
    
  }

  /*   Label label; */
  @Override
  public void visit(Goto n) {
    str_builder.append("\tjal " + curr_func + n.label.toString() + "\n");
  }

  /*   Register condition;
   *   Label label; */
  @Override
  public void visit(IfGoto n) { // double check this
    String short_lbl =  curr_func + n.label.toString() + "_short_"+ short_ct;
    short_ct++;
    str_builder.append("\tbnez " + n.condition.toString() + ", " + short_lbl + "\n");
    str_builder.append("\tjal " + curr_func + n.label.toString() + "\n");
    str_builder.append(short_lbl).append(":\n");

  // now try this
  // old: beq r1, r2, distant_block

  // new: bne r1, r2, end
// JAL zero distant_block
// end:

//bnez s2, continue
// JAL zero, null_err
// continue:

// trying this but it's not working
  // str_builder.append("\tbnez " + n.condition.toString() + ", " + n.label.toString() + "_continue\n");
  // str_builder.append("\tjal zero, " + n.label.toString() + "\n");
  // // str_builder.append("\t" + n.label.toString() + "_continue:\n");
  // if (!labelSet.contains(n.label.toString() + "_continue")) {
  //       str_builder.append(n.label.toString() + "_continue" + ":\n");
  //       labelSet.add(n.label.toString() + "_continue");
  //   }

  // System.err.println("ifgoto");
  // try non short
  // str_builder.append("\tbeqz " + n.condition.toString() + ", " + n.label.toString() + "\n");
    // short label 
    // str_builder.append("\tbeqz " + n.condition.toString() + ", short_" + n.label.toString() + "\n");
    // str_builder.append("\tj " + n.label.toString() + "\n");
    // if (!labelSet.contains("short_" + n.label.toString())) {
    //     str_builder.append("short_" + n.label.toString() + ":\n");
    //     labelSet.add("short_" + n.label.toString());
    // }
    // if(n.label.toString().equals("null_err")) {
    //   str_builder.append("\tbeqz " + n.condition.toString() + ", " + n.label.toString() + "\n");
    // }
    // else {
    //   str_builder.append("\tbeqz " + n.condition.toString() + ", " + n.label.toString() + "1" + "\n");
    //   str_builder.append("\tjal " + n.label.toString() + "2" + "\n");
    //   str_builder.append(n.label.toString() + "1" + ":\n");
    //   str_builder.append("\tjal " + n.label.toString() + "\n");
    //   str_builder.append(n.label.toString() + "2" + ":\n");
    //   labelSet.add(n.label.toString() + "1");
    //   labelSet.add(n.label.toString() + "2");
    // }

    
  }
    

  /*   Register lhs;
   *   Register callee;
   *   List<Identifier> args; */
  @Override
  public void visit(Call n) {
    int offset = n.args.size();
    str_builder.append("\tli t6, " + offset * 4 + "\n");
    str_builder.append("\tsub sp, sp, t6\n");
    
    // iterate thru the args
    for(int i = 0; i < n.args.size(); i ++) {
      String arg = n.args.get(i).toString();
      int curr_offset = localMap.get(curr_func).get(arg);
      str_builder.append("\tlw t6, " + curr_offset + "(fp)\n");
      str_builder.append("\tsw t6, " + i*4 + "(sp)\n");
    }

    // now do the jalr and mv
    str_builder.append("\tjalr " + n.callee.toString() + "\n");
    str_builder.append("\tmv " + n.lhs.toString() + ", a0\n");
  }
}
