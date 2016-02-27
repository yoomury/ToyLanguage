package samplee.Domain.Statements;

import samplee.Domain.DomainException;
import samplee.Domain.Map.IMap;
import samplee.Domain.Map.MyLibraryDictionary;
import samplee.Domain.ProgramState.PrgState;
import samplee.Domain.Stack.IStack;
import samplee.Domain.Stack.MyLibraryStack;


public class forkStmt implements IStmt {
    IStmt stmt;

    public forkStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    public IStmt getStmt() {
        return stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws DomainException {
        IStack<IStmt> stackkk= new MyLibraryStack<>();
        IMap<String,Integer> mapp=new MyLibraryDictionary<>((MyLibraryDictionary<String,Integer>)state.getSymTable());
        return new PrgState(stackkk,mapp,state.getHeapTable(),state.getOut(),state.getLockTable(),stmt);


    }

    @Override
    public String toString() {
        return "fork("+stmt.toString()+")";
    }
}
