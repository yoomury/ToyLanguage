package samplee.Domain.Statements;

import samplee.Domain.DomainException;
import samplee.Domain.LockTable.ILockTable;
import samplee.Domain.Map.IMap;
import samplee.Domain.ProgramState.PrgState;
import samplee.Domain.Stack.IStack;

/**
 * Created by mures on 1/19/2016.
 */
public class lockStatement implements IStmt {
    private String var;

    public lockStatement(String var) {
        this.var = var;
    }

    public String getVar() {

        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }



    @Override
    public PrgState execute(PrgState state) throws DomainException {

        IMap<String,Integer> mapp= state.getSymTable();
        ILockTable table= state.getLockTable();
        IStack<IStmt> stack=state.getExeStack();
        int foundIndex=mapp.get(var);




        System.out.println("HERE AM I: "+table.get(foundIndex));
        if (table.get(foundIndex)==-1){
            table.update(foundIndex,state.getId());
        }
        else{
            stack.push(this);
        }


        return state;
    }

    @Override
    public String toString(){
        return "lockStatement("+var+")";
    }
}
