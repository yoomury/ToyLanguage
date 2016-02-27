package samplee.Domain.Statements;

import samplee.Domain.DomainException;
import samplee.Domain.LockTable.ILockTable;
import samplee.Domain.Map.IMap;
import samplee.Domain.ProgramState.PrgState;

/**
 * Created by mures on 1/19/2016.
 */
public class unlock implements IStmt {
    private String var;


    public unlock(String var) {
        this.var = var;
    }

    public String getVar() {

        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    @Override
    public String toString() {
        return "unlock("+var+")";
    }

    @Override
    public PrgState execute(PrgState state) throws DomainException {
        IMap<String,Integer> mapp= state.getSymTable();
        ILockTable lockTable = state.getLockTable();



        int foundIndex=mapp.get(var);
        if (lockTable.get(foundIndex)==state.getId()){
            lockTable.update(foundIndex,-1);
        }


        return state;
    }
}
