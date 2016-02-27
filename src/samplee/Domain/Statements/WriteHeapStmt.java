package samplee.Domain.Statements;

import samplee.Domain.DomainException;
import samplee.Domain.Expressions.Exp;
import samplee.Domain.Heap.IHeap;
import samplee.Domain.Map.IMap;
import samplee.Domain.ProgramState.PrgState;


public class WriteHeapStmt implements IStmt {
    private String id;
    private Exp exp;

    public WriteHeapStmt(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Exp getExp() {
        return exp;
    }

    public void setExp(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "writeHeap( " + id + ", " + exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws DomainException {
        IMap<String, Integer> symTbl = state.getSymTable();
        IHeap<Integer> heap =  state.getHeapTable();
        heap.update(symTbl.get(getId()), getExp().eval(symTbl, heap));
        return null;
    }
}
