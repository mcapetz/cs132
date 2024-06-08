import sparrow.visitor.*;

import java.io.PrintStream;
import java.util.*;
import java.util.function.Function;

import javax.swing.GroupLayout;

import sparrow.*;
import sparrowv.*;
import IR.token.*;

public class SVC extends DepthFirst {
    private RA allocator;
    Map<String, String> curr_allocator;
    sparrowv.Program program = new sparrowv.Program(new ArrayList<sparrowv.FunctionDecl>());
    sparrowv.Block curr_block = new sparrowv.Block();
    String curr_func = null;
    private Set<String> labelSet = new HashSet<>();

    public SVC(RA allocator) {
        this.allocator = allocator;
        curr_block.instructions = new ArrayList<sparrowv.Instruction>();
    }

    /*   List<FunctionDecl> funDecls; */
     @Override
    public void visit(sparrow.Program n) {
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

        curr_block = block;
        for (Identifier fp : n.formalParameters) {
            params.add(fp);
        }
        sparrowv.FunctionDecl fundecl = new sparrowv.FunctionDecl(func_name, params, block);
        program.funDecls.add(fundecl);

        // save the regs
      if (!n.functionName.toString().equals("Main")) {
            Set<Register> hash_Set = new HashSet<Register>();
            for (String str : curr_allocator.values()) {
                // Perform operations with each register
                // if(str.substring(0, 1).equals("s")) {
                  hash_Set.add(new Register(str));
                // }
            }
            
            // System.out.println(hash_Set);
            int stack_idx = 1;
            for (Register reg : hash_Set) {
                Identifier stack = new Identifier("stack_" + stack_idx);
                sparrowv.Move_Id_Reg move = new sparrowv.Move_Id_Reg(stack, reg);
                if(curr_block.instructions == null) {
                  curr_block.instructions = new ArrayList<sparrowv.Instruction>();
                }
                curr_block.instructions.add(move);
                stack_idx++;
            }
        }

        // go thru the args
    for(Identifier arg: n.formalParameters) {
      sparrowv.Move_Reg_Id move = new sparrowv.Move_Reg_Id(new Register(curr_allocator.get(arg.toString())), arg);
      curr_block.instructions.add(move);
    }

        super.visit(n);

        // now do the return id
        // System.err.println("look: " + curr_allocator.get((n.block.return_id.toString())));
        // System.err.println("look2: " + n.block.return_id);
        curr_block.instructions.add(new sparrowv.Move_Id_Reg(n.block.return_id, new Register(curr_allocator.get((n.block.return_id.toString())))));
        curr_func = null;

// restore the regs
        if (!n.functionName.toString().equals("Main")) {
            Set<Register> hash_Set = new HashSet<Register>();
            for (String str : curr_allocator.values()) {
                // Perform operations with each register
                // if(str.substring(0, 1).equals("s")) {
                  hash_Set.add(new Register(str));
                // }
            }
            
            // System.out.println(hash_Set);
            int stack_idx = 1;
            for (Register reg : hash_Set) {
                Identifier stack = new Identifier("stack_" + stack_idx);
                sparrowv.Move_Reg_Id move = new sparrowv.Move_Reg_Id(reg, stack);
                curr_block.instructions.add(move);
                stack_idx++;
            }
        }
    }

    /*   FunctionDecl parent;
    *   List<Instruction> instructions;
    *   Identifier return_id; */
     @Override
    public void visit(sparrow.Block n) {
        // for (sparrow.Instruction i: n.instructions) {
        //     i.accept(this);
        // }

        curr_block.return_id = n.return_id;
        super.visit(n);
    }


    /*   Label label; */
  public void visit(sparrow.LabelInstr n) {
    if (!labelSet.contains(n.label.toString())) {
        curr_block.instructions.add(new sparrowv.LabelInstr(n.label));
        labelSet.add(n.label.toString());
    }
    // curr_block.instructions.add(new sparrowv.LabelInstr(n.label));
  }


    /*   Identifier lhs;
   *   int rhs; */
    @Override
  public void visit(sparrow.Move_Id_Integer n) {
    Register lhs_reg = allocator.getRegister(curr_func, n.lhs.toString());
    sparrowv.Move_Reg_Integer move_id_int = new sparrowv.Move_Reg_Integer(lhs_reg, n.rhs);
    sparrowv.Move_Id_Reg move = new sparrowv.Move_Id_Reg(n.lhs, lhs_reg);
    if(curr_block.instructions == null) {
        curr_block.instructions = new ArrayList<sparrowv.Instruction>();
    }
    curr_block.instructions.add(move_id_int);
    // curr_block.instructions.add(move);
  }

  /*   Identifier lhs;
   *   FunctionName rhs; */
    @Override
  public void visit(Move_Id_FuncName n) { 
    Register lhs_reg = allocator.getRegister(curr_func, n.lhs.toString());
    sparrowv.Move_Reg_FuncName move_id_func = new sparrowv.Move_Reg_FuncName(lhs_reg, n.rhs);
    sparrowv.Move_Id_Reg move = new sparrowv.Move_Id_Reg(n.lhs, lhs_reg);
    if(curr_block.instructions == null) {
        curr_block.instructions = new ArrayList<sparrowv.Instruction>();
    }
    curr_block.instructions.add(move_id_func);
    // curr_block.instructions.add(move);
  }

  /*   Identifier lhs;
   *   Identifier arg1;
   *   Identifier arg2; */
    @Override
  public void visit(sparrow.Add n) {
    Register lhs_reg = allocator.getRegister(curr_func, n.lhs.toString());
    Register arg1_reg = allocator.getRegister(curr_func, n.arg1.toString());
    Register arg2_reg = allocator.getRegister(curr_func, n.arg2.toString());
    sparrowv.Add add = new sparrowv.Add(lhs_reg, arg1_reg, arg2_reg);
    sparrowv.Move_Id_Reg move = new sparrowv.Move_Id_Reg(n.lhs, lhs_reg);
    if(curr_block.instructions == null) {
        curr_block.instructions = new ArrayList<sparrowv.Instruction>();
    }
    curr_block.instructions.add(add);
    // curr_block.instructions.add(move);
  }

  /*   Identifier lhs;
   *   Identifier arg1;
   *   Identifier arg2; */
    @Override
  public void visit(sparrow.Subtract n) {
    Register lhs_reg = allocator.getRegister(curr_func, n.lhs.toString());
    Register arg1_reg = allocator.getRegister(curr_func, n.arg1.toString());
    Register arg2_reg = allocator.getRegister(curr_func, n.arg2.toString());
    sparrowv.Subtract sub = new sparrowv.Subtract(lhs_reg, arg1_reg, arg2_reg);
    sparrowv.Move_Id_Reg move = new sparrowv.Move_Id_Reg(n.lhs, lhs_reg);
    if(curr_block.instructions == null) {
        curr_block.instructions = new ArrayList<sparrowv.Instruction>();
    }
    curr_block.instructions.add(sub);
    // curr_block.instructions.add(move);

  }

  /*   Identifier lhs;
   *   Identifier arg1;
   *   Identifier arg2; */
    @Override
  public void visit(sparrow.Multiply n) {
    Register lhs_reg = allocator.getRegister(curr_func, n.lhs.toString());
    Register arg1_reg = allocator.getRegister(curr_func, n.arg1.toString());
    Register arg2_reg = allocator.getRegister(curr_func, n.arg2.toString());
    sparrowv.Multiply mult = new sparrowv.Multiply(lhs_reg, arg1_reg, arg2_reg);
    sparrowv.Move_Id_Reg move = new sparrowv.Move_Id_Reg(n.lhs, lhs_reg);
    if(curr_block.instructions == null) {
        curr_block.instructions = new ArrayList<sparrowv.Instruction>();
    }
    curr_block.instructions.add(mult);
    // curr_block.instructions.add(move);
  }

  /*   Identifier lhs;
   *   Identifier arg1;
   *   Identifier arg2; */
    @Override
  public void visit(sparrow.LessThan n) {
    Register lhs_reg = allocator.getRegister(curr_func, n.lhs.toString());
    Register arg1_reg = allocator.getRegister(curr_func, n.arg1.toString());
    Register arg2_reg = allocator.getRegister(curr_func, n.arg2.toString());
    sparrowv.LessThan less_than = new sparrowv.LessThan(lhs_reg, arg1_reg, arg2_reg);
    sparrowv.Move_Id_Reg move = new sparrowv.Move_Id_Reg(n.lhs, lhs_reg);
    if(curr_block.instructions == null) {
        curr_block.instructions = new ArrayList<sparrowv.Instruction>();
    }
    curr_block.instructions.add(less_than);
    // curr_block.instructions.add(move);
  }

  /*   Identifier lhs;
   *   Identifier base;
   *   int offset; */
    @Override
  public void visit(sparrow.Load n) {
    Register lhs_reg = allocator.getRegister(curr_func, n.lhs.toString());
    Register base_reg = allocator.getRegister(curr_func, n.base.toString());
    sparrowv.Load load = new sparrowv.Load(lhs_reg, base_reg, n.offset);
    sparrowv.Move_Id_Reg move = new sparrowv.Move_Id_Reg(n.lhs, lhs_reg);
    if(curr_block.instructions == null) {
        curr_block.instructions = new ArrayList<sparrowv.Instruction>();
    }
    curr_block.instructions.add(load);
    // curr_block.instructions.add(move);
  }

  /*   Identifier base;
   *   int offset;
   *   Identifier rhs; */
    @Override
  public void visit(sparrow.Store n) {
    Register rhs_reg = allocator.getRegister(curr_func, n.rhs.toString());
    Register base_reg = allocator.getRegister(curr_func, n.base.toString());
    sparrowv.Store store = new sparrowv.Store(base_reg, n.offset, rhs_reg);
    sparrowv.Move_Id_Reg move_rhs = new sparrowv.Move_Id_Reg(n.rhs, rhs_reg);
    sparrowv.Move_Id_Reg move_base = new sparrowv.Move_Id_Reg(n.base, base_reg);
    if(curr_block.instructions == null) {
        curr_block.instructions = new ArrayList<sparrowv.Instruction>();
    }
    // curr_block.instructions.add(move_rhs);
    curr_block.instructions.add(store);
    // curr_block.instructions.add(move_base);
  }

  /*   Identifier lhs;
   *   Identifier rhs; */
    @Override
  public void visit(sparrow.Move_Id_Id n) {
    Register lhs_reg = allocator.getRegister(curr_func, n.lhs.toString());
    Register rhs_reg = allocator.getRegister(curr_func, n.rhs.toString());
    sparrowv.Move_Reg_Reg move_reg_reg = new sparrowv.Move_Reg_Reg(lhs_reg, rhs_reg);
    sparrowv.Move_Id_Reg move = new sparrowv.Move_Id_Reg(n.lhs, lhs_reg);
    if(curr_block.instructions == null) {
        curr_block.instructions = new ArrayList<sparrowv.Instruction>();
    }
    curr_block.instructions.add(move_reg_reg);
    // curr_block.instructions.add(move);
  }

  /*   Identifier lhs;
   *   Identifier size; */
    @Override
  public void visit(sparrow.Alloc n) {
    Register lhs_reg = allocator.getRegister(curr_func, n.lhs.toString());
    Register sz_reg = allocator.getRegister(curr_func, n.size.toString());
    sparrowv.Alloc alloc = new sparrowv.Alloc(lhs_reg, sz_reg);
    sparrowv.Move_Id_Reg move = new sparrowv.Move_Id_Reg(n.lhs, lhs_reg);
    sparrowv.Move_Id_Reg move_sz = new sparrowv.Move_Id_Reg(n.size, lhs_reg);
    if(curr_block.instructions == null) {
        curr_block.instructions = new ArrayList<sparrowv.Instruction>();
    }
    // curr_block.instructions.add(move_sz);
    curr_block.instructions.add(alloc);
    // curr_block.instructions.add(move);
    
  }

  /*   Identifier content; */
   @Override
  public void visit(sparrow.Print n) { // idk if needs change
    sparrowv.Print print = new sparrowv.Print(allocator.getRegister(curr_func, n.content.toString()));
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
    Register condition_reg = allocator.getRegister(curr_func, n.condition.toString());
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

    // save the registers
    Set<Register> hash_Set = new HashSet<Register>();
    for (String str : curr_allocator.values()) {
        // Perform operations with each register
        if(str.substring(0, 1).equals("t")) {
          hash_Set.add(new Register(str));
        }
        
    }
    
    // System.out.println(hash_Set);
    // int stack_idx = 1;
    // for (Register reg : hash_Set) {
    //     Identifier stack = new Identifier("save_" + stack_idx);
    //     sparrowv.Move_Id_Reg move = new sparrowv.Move_Id_Reg(stack, reg);
    //     if(curr_block.instructions == null) {
    //       curr_block.instructions = new ArrayList<sparrowv.Instruction>();
    //     }
    //     curr_block.instructions.add(move);
    //     stack_idx++;
    // }

    Register lhs_reg = allocator.getRegister(curr_func, n.lhs.toString());
    Register callee_reg = allocator.getRegister(curr_func, n.callee.toString());

    for(Identifier arg: n.args) {
      sparrowv.Move_Id_Reg move = new sparrowv.Move_Id_Reg(arg, new Register(curr_allocator.get(arg.toString())));
      curr_block.instructions.add(move);
    }

    sparrowv.Call call = new sparrowv.Call(lhs_reg, callee_reg, n.args);
    if(curr_block.instructions == null) {
        curr_block.instructions = new ArrayList<sparrowv.Instruction>();
    }
    curr_block.instructions.add(call);

    // restore the registers
    // stack_idx = 1;
    // for (Register reg : hash_Set) {
    //     Identifier stack = new Identifier("save" + stack_idx);
    //     sparrowv.Move_Reg_Id move = new sparrowv.Move_Reg_Id(reg, stack);
    //     if(curr_block.instructions == null) {
    //       curr_block.instructions = new ArrayList<sparrowv.Instruction>();
    //     }
    //     curr_block.instructions.add(move);
    //     stack_idx++;
    // }
    
  }
}
