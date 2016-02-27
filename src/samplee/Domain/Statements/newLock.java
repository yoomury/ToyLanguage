package samplee.Domain.Statements;

import samplee.Domain.DomainException;
import samplee.Domain.LockTable.ILockTable;
import samplee.Domain.Map.IMap;
import samplee.Domain.ProgramState.PrgState;

/**
 * Created by mures on 1/19/2016.
 */
public class newLock implements IStmt {
    private String var;

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public newLock(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws DomainException {
        IMap<String,Integer> mapp= state.getSymTable();
        ILockTable l = state.getLockTable();
            int location=l.add(-1) ;
            mapp.put(var,location);


        return state;
    }

    @Override
    public String toString(){
        return "newLock("+var+")";
    }

}
