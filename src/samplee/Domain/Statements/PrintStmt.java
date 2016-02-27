package samplee.Domain.Statements;

import samplee.Domain.DomainException;
import samplee.Domain.Expressions.Exp;
import samplee.Domain.Heap.IHeap;
import samplee.Domain.List.IList;
import samplee.Domain.Map.IMap;
import samplee.Domain.ProgramState.PrgState;


public class PrintStmt implements IStmt {
    private Exp exp;

    public PrintStmt(Exp expression) {
        exp = expression;
    }

    public void setExp(Exp exp) {
        this.exp = exp;
    }

    public Exp getExp() {
        return exp;
    }

    @Override
    public String toString() {
        return "print(" + exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws DomainException {
        IList<Integer> output = state.getOut();
        IMap<String, Integer> symTbl = state.getSymTable();
        IHeap<Integer> heap =  state.getHeapTable();
        output.add(getExp().eval(symTbl, heap));
        return state;
    }
}
