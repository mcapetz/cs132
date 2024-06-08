import sparrow.visitor.*;

import java.io.PrintStream;
import java.util.*;
import java.util.function.Function;

import javax.swing.GroupLayout;

import sparrow.*;
import sparrowv.*;
import IR.token.*;

public class SVC2 extends DepthFirst {
    private RA2 allocator;
    Map<String, Register> curr_allocator;
    sparrowv.Program program = new sparrowv.Program(new ArrayList<sparrowv.FunctionDecl>());
    sparrowv.Block curr_block = new sparrowv.Block();
    String curr_func = null;
    private Set<String> labelSet = new HashSet<>();
    Register temp = new Register("t0");
    Register temp1 = new Register("t1");
    Boolean use_temp = false;
    Integer stack_counter = 0;
    Boolean is_first_func = true;
    Map<String, Map<String, LI>> liveness;
    int instr_ct = 0; 

    public SVC2(RA2 allocator, Map<String, Map<String, LI>> liveness) {
        this.allocator = allocator;
        curr_block.instructions = new ArrayList<sparrowv.Instruction>();
        this.liveness = liveness;
    }

    /*   List<FunctionDecl> funDecls; */
     @Override
    public void visit(sparrow.Program n) {
      curr_block.instructions = new ArrayList<sparrowv.Instruction>();
        for (sparrow.FunctionDecl fd: n.funDecls) {
            this.visit(fd);
        }
        System.out.println(program.toString());

    }

    /*   Program parent;
    *   FunctionName functionName;
    *   List<Identifier> formalParameters;
    *   Block block; */
     @Override
    public void visit(sparrow.FunctionDecl n) {
        sparrowv.Block block = new sparrowv.Block();
        FunctionName func_name = new FunctionName(n.functionName.toString());
        curr_func = n.functionName.toString();
        curr_allocator = allocator.allocationMap.get(curr_func);
        // System.err.println("curr func: " + curr_func);
        ArrayList<Identifier> params = new ArrayList<>();
        block.instructions = new ArrayList<sparrowv.Instruction>();
        curr_block = block;
        // for (Identifier fp : n.formalParameters) {
        //     params.add(fp);
        // }

        List<Identifier> curr_params = new ArrayList<Identifier>();
        if(n.formalParameters.size() > 6) {
          curr_params = n.formalParameters.subList(6, n.formalParameters.size());
        }
        sparrowv.FunctionDecl fundecl = new sparrowv.FunctionDecl(func_name, curr_params, block);
        program.funDecls.add(fundecl);

         // save the regs
      if (!is_first_func) {
        String[] param_regs = {"a2", "a3", "a4", "a5", "a6", "a7"};
        for(int i = 0; i < n.formalParameters.size(); i++) {
          Identifier id = n.formalParameters.get(i);
          String id_string = id.toString();
          Register register;

          if( i < param_regs.length) { // assign if it is within the six limit
            register = new Register(param_regs[i]);
            Register og_reg = curr_allocator.get(id_string);
            sparrowv.Move_Reg_Reg move_reg_reg = new sparrowv.Move_Reg_Reg(og_reg, register);
            curr_block.instructions.add(move_reg_reg);
          }
          else { // otherwise just use what u have
            register = curr_allocator.get(id_string);
            sparrowv.Move_Reg_Id move_reg_id = new sparrowv.Move_Reg_Id(register, id);
            curr_block.instructions.add(move_reg_id);
          }
        }
        }

        super.visit(n);
        curr_block.instructions.add(new sparrowv.Move_Id_Reg(n.block.return_id, curr_allocator.get((n.block.return_id.toString()))));
        curr_func = null;
        is_first_func = false;

    }

    /*   FunctionDecl parent;
    *   List<Instruction> instructions;
    *   Identifier return_id; */
     @Override
    public void visit(sparrow.Block n) {
        for (sparrow.Instruction i: n.instructions) {
            instr_ct ++;
            i.accept(this);
        }
        curr_block.return_id = n.return_id;
        // super.visit(n);
    }

    /*   Label label; */
  public void visit(sparrow.LabelInstr n) {
    // if (!labelSet.contains(n.label.toString())) {
        curr_block.instructions.add(new sparrowv.LabelInstr(n.label));
    //     labelSet.add(n.label.toString());
    // }
  }


    /*   Identifier lhs;
   *   int rhs; */
    @Override
  public void visit(sparrow.Move_Id_Integer n) {
    // System.err.println("i got to move id int");
    Register lhs_reg = curr_allocator.get(n.lhs.toString());
    // if(lhs_reg == null) {
    //   if(use_temp == false) {
    //     sparrowv.Move_Reg_Id move = new sparrowv.Move_Reg_Id(temp, n.lhs);
    //     lhs_reg = temp;
    //     curr_block.instructions.add(move);
    //     curr_allocator.put(n.lhs.toString(), temp);
    //     use_temp = true;
    //   }
    //   else {
    //     sparrowv.Move_Reg_Id move = new sparrowv.Move_Reg_Id(temp1, n.lhs);
    //     lhs_reg = temp1;
    //     curr_block.instructions.add(move);
    //     curr_allocator.put(n.lhs.toString(), temp1);
    //     use_temp = false;
    //   }
      // }
      // else {
      //   // // we have to check the registers that are available
      //   List<Register> unused = allocator.getUnusedRegisters();
      //   // System.err.println("unused: " + unused);
      //   if(unused.size() == 0) {
      //     // System.err.println("need to use the stack"); // help
      //     Identifier new_stack = new Identifier("stack_" + stack_counter++);
      //     sparrowv.Move_Id_Reg move1 = new sparrowv.Move_Id_Reg(new_stack, temp1);
      //     sparrowv.Move_Reg_Id move2 = new sparrowv.Move_Reg_Id(temp1, n.lhs);
      //     // lhs_reg = new_stack;
      //     curr_block.instructions.add(move1);
      //     curr_block.instructions.add(move2);
      //     // curr_allocator.put(n.lhs.toString(), new Register(new_stack.toString()));
      //     // use_temp = false;
      //   }
      // }
      
    // }
    sparrowv.Move_Reg_Integer move_id_int = new sparrowv.Move_Reg_Integer(lhs_reg, n.rhs);
    if(curr_block.instructions == null) {
        curr_block.instructions = new ArrayList<sparrowv.Instruction>();
    }
    // if(curr_block.instructions.size() > 0) {
    // // System.err.println("prev instruction: " + curr_block.instructions.get(curr_block.instructions.size() - 1));
    // // System.err.println("prev instruction: " + curr_block.instructions.get(curr_block.instructions.size() - 1).toString().substring(0, 2));
    
    //   if(lhs_reg.toString().equals(curr_block.instructions.get(curr_block.instructions.size() - 1).toString().substring(0, 2))) {
    //     // System.err.println("heyo");
    //     curr_block.instructions.remove(curr_block.instructions.size() - 1);
    //   }
    // }
    curr_block.instructions.add(move_id_int);
  }

  /*   Identifier lhs;
   *   FunctionName rhs; */
    @Override
  public void visit(Move_Id_FuncName n) { 
    Register lhs_reg = curr_allocator.get(n.lhs.toString());
    sparrowv.Move_Reg_FuncName move_id_func = new sparrowv.Move_Reg_FuncName(lhs_reg, n.rhs);
    if(curr_block.instructions == null) {
        curr_block.instructions = new ArrayList<sparrowv.Instruction>();
    }
    curr_block.instructions.add(move_id_func);
  }

  /*   Identifier lhs;
   *   Identifier arg1;
   *   Identifier arg2; */
    @Override
  public void visit(sparrow.Add n) {
    Register lhs_reg = curr_allocator.get(n.lhs.toString());
    Register arg1_reg = curr_allocator.get(n.arg1.toString());
    Register arg2_reg = curr_allocator.get(n.arg2.toString());

    if(arg1_reg == null) {
      sparrowv.Move_Reg_Id move = new sparrowv.Move_Reg_Id(temp, n.arg1);
      arg1_reg = temp;
      curr_block.instructions.add(move);
    }

    if(arg2_reg == null) {
      sparrowv.Move_Reg_Id move = new sparrowv.Move_Reg_Id(temp1, n.arg2);
      arg2_reg = temp1;
      curr_block.instructions.add(move);
    }

    sparrowv.Add add = new sparrowv.Add(lhs_reg, arg1_reg, arg2_reg);
    if(curr_block.instructions == null) {
        curr_block.instructions = new ArrayList<sparrowv.Instruction>();
    }
    curr_block.instructions.add(add);
  }

  /*   Identifier lhs;
   *   Identifier arg1;
   *   Identifier arg2; */
    @Override
  public void visit(sparrow.Subtract n) {
    Register lhs_reg = curr_allocator.get(n.lhs.toString());
    Register arg1_reg = curr_allocator.get(n.arg1.toString());
    Register arg2_reg = curr_allocator.get(n.arg2.toString());

    if(arg1_reg == arg2_reg) {
      System.err.println("what");
      sparrowv.Move_Reg_Id move1 = new sparrowv.Move_Reg_Id(temp, n.arg1);
      arg1_reg = temp;
      curr_block.instructions.add(move1);
      sparrowv.Move_Reg_Id move2 = new sparrowv.Move_Reg_Id(temp1, n.arg2);
      arg2_reg = temp1;
      curr_block.instructions.add(move2);
    }
    else {
      if(arg1_reg == null) {
        sparrowv.Move_Reg_Id move = new sparrowv.Move_Reg_Id(temp, n.arg1);
        arg1_reg = temp;
        curr_block.instructions.add(move);
      }

      if(arg2_reg == null) {
        sparrowv.Move_Reg_Id move = new sparrowv.Move_Reg_Id(temp1, n.arg2);
        arg2_reg = temp1;
        curr_block.instructions.add(move);
      }
    }
    sparrowv.Subtract sub = new sparrowv.Subtract(lhs_reg, arg1_reg, arg2_reg);
    if(curr_block.instructions == null) {
        curr_block.instructions = new ArrayList<sparrowv.Instruction>();
    }
    curr_block.instructions.add(sub);

  }

  /*   Identifier lhs;
   *   Identifier arg1;
   *   Identifier arg2; */
    @Override
  public void visit(sparrow.Multiply n) {
    Register lhs_reg = curr_allocator.get(n.lhs.toString());
    Register arg1_reg = curr_allocator.get(n.arg1.toString());
    Register arg2_reg = curr_allocator.get(n.arg2.toString());

    if(arg1_reg == arg2_reg) {
      System.err.println("what");
      sparrowv.Move_Reg_Id move1 = new sparrowv.Move_Reg_Id(temp, n.arg1);
      arg1_reg = temp;
      curr_block.instructions.add(move1);
      sparrowv.Move_Reg_Id move2 = new sparrowv.Move_Reg_Id(temp1, n.arg2);
      arg2_reg = temp1;
      curr_block.instructions.add(move2);
    }
    else {
      if(arg1_reg == null) {
        sparrowv.Move_Reg_Id move = new sparrowv.Move_Reg_Id(temp, n.arg1);
        arg1_reg = temp;
        curr_block.instructions.add(move);
      }

      if(arg2_reg == null) {
        sparrowv.Move_Reg_Id move = new sparrowv.Move_Reg_Id(temp1, n.arg2);
        arg2_reg = temp1;
        curr_block.instructions.add(move);
      }
    }

    sparrowv.Multiply mult = new sparrowv.Multiply(lhs_reg, arg1_reg, arg2_reg);
    if(curr_block.instructions == null) {
        curr_block.instructions = new ArrayList<sparrowv.Instruction>();
    }
    curr_block.instructions.add(mult);
  }

  /*   Identifier lhs;
   *   Identifier arg1;
   *   Identifier arg2; */
    @Override
  public void visit(sparrow.LessThan n) {
    Register lhs_reg = curr_allocator.get(n.lhs.toString());
    Register arg1_reg = curr_allocator.get(n.arg1.toString());
    Register arg2_reg = curr_allocator.get(n.arg2.toString());

    if(arg1_reg == arg2_reg) {
      System.err.println("what");
      sparrowv.Move_Reg_Id move1 = new sparrowv.Move_Reg_Id(temp, n.arg1);
      arg1_reg = temp;
      curr_block.instructions.add(move1);
      sparrowv.Move_Reg_Id move2 = new sparrowv.Move_Reg_Id(temp1, n.arg2);
      arg2_reg = temp1;
      curr_block.instructions.add(move2);
    }
    else {
      if(arg1_reg == null) {
        sparrowv.Move_Reg_Id move = new sparrowv.Move_Reg_Id(temp, n.arg1);
        arg1_reg = temp;
        curr_block.instructions.add(move);
      }

      if(arg2_reg == null) {
        sparrowv.Move_Reg_Id move = new sparrowv.Move_Reg_Id(temp1, n.arg2);
        arg2_reg = temp1;
        curr_block.instructions.add(move);
      }
    }

    

    sparrowv.LessThan less_than = new sparrowv.LessThan(lhs_reg, arg1_reg, arg2_reg);
    if(curr_block.instructions == null) {
        curr_block.instructions = new ArrayList<sparrowv.Instruction>();
    }
    curr_block.instructions.add(less_than);
  }

  /*   Identifier lhs;
   *   Identifier base;
   *   int offset; */
    @Override
  public void visit(sparrow.Load n) {
    Register lhs_reg = curr_allocator.get(n.lhs.toString());
    Register base_reg = curr_allocator.get(n.base.toString());

    // if(n.base.toString() == "this") {
    //   System.err.println("hey " + lhs_reg);
    //   // sparrowv.Move_Reg_Id move_this = new sparrowv.Move_Reg_Id(base_reg, n.base);
    //   // curr_block.instructions.add(move_this);
    // }

    // if(base_reg == null) {
    //   sparrowv.Move_Reg_Id move = new sparrowv.Move_Reg_Id(temp, n.base);
    //   base_reg = temp;
    //   curr_block.instructions.add(move);
    //   curr_allocator.put(n.base.toString(), temp);
    // }

    // if(lhs_reg == null) {
    //   sparrowv.Move_Reg_Id move = new sparrowv.Move_Reg_Id(temp1, n.lhs);
    //   lhs_reg = temp1;
    //   curr_block.instructions.add(move);
    //   curr_allocator.put(n.lhs.toString(), temp1);
    // }

    sparrowv.Load load = new sparrowv.Load(lhs_reg, base_reg, n.offset);
    if(curr_block.instructions == null) {
        curr_block.instructions = new ArrayList<sparrowv.Instruction>();
    }
    curr_block.instructions.add(load);
  }

  /*   Identifier base;
   *   int offset;
   *   Identifier rhs; */
    @Override
  public void visit(sparrow.Store n) {
    Register rhs_reg = curr_allocator.get(n.rhs.toString());
    Register base_reg = curr_allocator.get(n.base.toString());

    // if(base_reg == null) {
    //   sparrowv.Move_Reg_Id move = new sparrowv.Move_Reg_Id(temp, n.base);
    //   base_reg = temp;
    //   curr_block.instructions.add(move);
    //   curr_allocator.put(n.base.toString(), temp);
    // }

    // if(rhs_reg == null) {
    //   sparrowv.Move_Reg_Id move = new sparrowv.Move_Reg_Id(temp1, n.rhs);
    //   rhs_reg = temp1;
    //   curr_block.instructions.add(move);
    //   curr_allocator.put(n.rhs.toString(), temp1);
    // }

    sparrowv.Store store = new sparrowv.Store(base_reg, n.offset, rhs_reg);
    if(curr_block.instructions == null) {
        curr_block.instructions = new ArrayList<sparrowv.Instruction>();
    }
    curr_block.instructions.add(store);
  }

  /*   Identifier lhs;
   *   Identifier rhs; */
    @Override
  public void visit(sparrow.Move_Id_Id n) {
    Register lhs_reg = curr_allocator.get(n.lhs.toString());
    Register rhs_reg = curr_allocator.get(n.rhs.toString());

  // if(lhs_reg == null) {
  //     sparrowv.Move_Reg_Id move = new sparrowv.Move_Reg_Id(temp, n.lhs);
  //     lhs_reg = temp;
  //     curr_block.instructions.add(move);
  //     System.err.println(lhs_reg);
  //     System.err.println(rhs_reg);
  //     throw new RuntimeException("heyo1");
  //   }

  //   if(rhs_reg == null) {
  //     sparrowv.Move_Reg_Id move = new sparrowv.Move_Reg_Id(temp1, n.rhs);
  //     rhs_reg = temp1;
  //     curr_block.instructions.add(move);
  //     System.err.println(n.lhs.toString());
  //     System.err.println(n.rhs.toString());
  //     System.err.println(lhs_reg);
  //     System.err.println(rhs_reg);
  //     // throw new RuntimeException("heyo");
  //   }

    sparrowv.Move_Reg_Reg move_reg_reg = new sparrowv.Move_Reg_Reg(lhs_reg, rhs_reg);
    if(curr_block.instructions == null) {
        curr_block.instructions = new ArrayList<sparrowv.Instruction>();
    }
    curr_block.instructions.add(move_reg_reg);
  }

  /*   Identifier lhs;
   *   Identifier size; */
    @Override
  public void visit(sparrow.Alloc n) {
    Register lhs_reg = curr_allocator.get(n.lhs.toString());
    Register sz_reg = curr_allocator.get(n.size.toString());

    // if(lhs_reg == null) {
    //   sparrowv.Move_Reg_Id move = new sparrowv.Move_Reg_Id(temp, n.lhs);
    //   lhs_reg = temp;
    //   curr_block.instructions.add(move);
    //   curr_allocator.put(n.lhs.toString(), temp);
    // }

    sparrowv.Alloc alloc = new sparrowv.Alloc(lhs_reg, sz_reg);
    if(curr_block.instructions == null) {
        curr_block.instructions = new ArrayList<sparrowv.Instruction>();
    }
    curr_block.instructions.add(alloc);
  }

  /*   Identifier content; */
   @Override
  public void visit(sparrow.Print n) { // idk if needs change
    sparrowv.Print print = new sparrowv.Print(curr_allocator.get(n.content.toString()));
    if(curr_block.instructions == null) {
        curr_block.instructions = new ArrayList<sparrowv.Instruction>();
    }
    curr_block.instructions.add(print);
  }

  /*   String msg; */
   @Override
  public void visit(sparrow.ErrorMessage n) {
    sparrowv.ErrorMessage error_msg = new sparrowv.ErrorMessage(n.msg);
    if(curr_block.instructions == null) {
        curr_block.instructions = new ArrayList<sparrowv.Instruction>();
    }
    curr_block.instructions.add(error_msg);
}

@Override   
    public void visit(sparrow.Goto n) {
        sparrowv.Goto goto_ = new sparrowv.Goto(n.label);
        if(curr_block.instructions == null) {
            curr_block.instructions = new ArrayList<sparrowv.Instruction>();
        }
        curr_block.instructions.add(goto_);
    }

  /*   Identifier condition;
   *   Label label; */
   @Override
  public void visit(sparrow.IfGoto n) {
    Register condition_reg = curr_allocator.get(n.condition.toString());
    sparrowv.IfGoto if_goto_ = new sparrowv.IfGoto(condition_reg, n.label);
    if(curr_block.instructions == null) {
        curr_block.instructions = new ArrayList<sparrowv.Instruction>();
    }
    curr_block.instructions.add(if_goto_);
  }

  /*   Identifier lhs;
   *   Identifier callee;
   *   List<Identifier> args; */
    @Override
  public void visit(sparrow.Call n) { 
    Register lhs_reg = curr_allocator.get(n.lhs.toString());
    Register callee_reg = curr_allocator.get(n.callee.toString());

    Identifier prev_arg = null;

    Map<String, LI> intervals = liveness.get(curr_func);

    // go thru the intervals and see which overlap the call
    for(Map.Entry<String, LI> ent : intervals.entrySet()) {
      String var = ent.getKey();
      LI interval = ent.getValue();

      if(interval.getEndTime() > instr_ct - 1 && interval.getStartTime() < instr_ct - 1) {
        Register r = curr_allocator.get(var);
        if( lhs_reg != r) {
          Identifier var_id = new Identifier(var);
          sparrowv.Move_Id_Reg move1 = new sparrowv.Move_Id_Reg(var_id, r);
          curr_block.instructions.add(move1);
        }
      }
    }

    String[] param_regs = { "a2", "a3", "a4", "a5", "a6", "a7" };
    Map<String, Register> callee_allocs = new HashMap<>();
    for(int i = 0; i < n.args.size(); i ++) {
      Identifier curr_param = n.args.get(i);
      String curr_param_str = curr_param.toString();
      Register r;
      if(i < param_regs.length) {
        Register og_r = curr_allocator.get(curr_param_str);
        r = new Register(param_regs[i]);
        sparrowv.Move_Reg_Reg move_reg_reg = new sparrowv.Move_Reg_Reg(r, og_r);
        curr_block.instructions.add(move_reg_reg);
        callee_allocs.put(curr_param_str, r);
      }
      else {
        Register temp = curr_allocator.get(curr_param_str);
        sparrowv.Move_Id_Reg else_move = new sparrowv.Move_Id_Reg(curr_param,temp );
        curr_block.instructions.add(else_move);
      }
    }

    List<Identifier> args_for_pass = new ArrayList<Identifier>();

    if(n.args.size() > 6) { // size is 6
      args_for_pass = n.args.subList(6, n.args.size());
    } 

    sparrowv.Call call = new sparrowv.Call(lhs_reg, callee_reg, args_for_pass);
    if(curr_block.instructions == null) {
        curr_block.instructions = new ArrayList<sparrowv.Instruction>();
    }
    curr_block.instructions.add(call);

    //now we might need to restore
    for(Map.Entry<String, LI> ent : intervals.entrySet()) {
      String var = ent.getKey();
      LI interval = ent.getValue();

      if(interval.getEndTime() > instr_ct - 1 && interval.getStartTime() < instr_ct - 1) {
        Register r = curr_allocator.get(var);
        if( lhs_reg != r) {
          Identifier var_id = new Identifier(var);
          sparrowv.Move_Reg_Id move1 = new sparrowv.Move_Reg_Id(r, var_id);
          curr_block.instructions.add(move1);
        }
      }
    }
  }
}
