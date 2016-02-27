package samplee.Domain.Statements;

import samplee.Domain.DomainException;
import samplee.Domain.Expressions.Exp;
import samplee.Domain.Heap.IHeap;
import samplee.Domain.Map.IMap;
import samplee.Domain.ProgramState.PrgState;


public class NewStmt implements IStmt {
    private String id;
    private Exp exp;

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

    public NewStmt(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    public String toString() {
        return "new( " + id + ", " + exp.toString() + ") ";
    }

    @Override
    public PrgState execute(PrgState state) throws DomainException {
        IMap<String, Integer> symTbl = state.getSymTable();
        IHeap<Integer> heap =  state.getHeapTable();
        symTbl.put(getId(), heap.add(getExp().eval(symTbl, heap)));
        return state;
    }
}
