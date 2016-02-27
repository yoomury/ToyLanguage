package samplee.Domain.Statements;

import samplee.Domain.DomainException;
import samplee.Domain.Expressions.Exp;
import samplee.Domain.Heap.IHeap;
import samplee.Domain.Map.IMap;
import samplee.Domain.ProgramState.PrgState;


public class WhileStmt implements IStmt {
    private Exp exp;
    private IStmt stmt;

    public WhileStmt(Exp e, IStmt stmt) {
        this.exp = e;
        this.stmt = stmt;
    }

    public Exp getExp() {
        return exp;
    }

    public IStmt getStmt() {
        return stmt;
    }

    public void settExp(Exp exp) {
        this.exp = exp;
    }

    public void setStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    @Override
    public String toString() {
        return "While( " + exp.toString() + ") { " + stmt.toString() + " }";
    }

    @Override
    public PrgState execute(PrgState state) throws DomainException {
        IMap<String, Integer> symTbl = state.getSymTable();
        IHeap<Integer> heap =  state.getHeapTable();
        if (getExp().eval(symTbl, heap) != 0) {
            state.getExeStack().push(this);
            state.getExeStack().push(this.getStmt());
        }
        return state;
    }
}