package samplee.Domain.Statements;

import samplee.Domain.DomainException;
import samplee.Domain.Expressions.Exp;
import samplee.Domain.Heap.IHeap;
import samplee.Domain.Map.IMap;
import samplee.Domain.ProgramState.PrgState;


public class IfStmt implements IStmt {
    private Exp exp;
    private IStmt thenS;
    private IStmt elseS;

    public Exp getExp() {
        return exp;
    }

    public IStmt getThenS() {
        return thenS;
    }

    public IStmt getElseS() {
        return elseS;
    }

    public void setExp(Exp exp) {
        this.exp = exp;
    }

    public void setThenS(IStmt thenS) {
        this.thenS = thenS;
    }

    public void setElseS(IStmt elseS) {
        this.elseS = elseS;
    }

    public IfStmt(Exp e, IStmt t, IStmt el) {
        exp = e;
        thenS = t;
        elseS = el;
    }

    @Override
    public String toString() {
        return "IF(" + exp.toString() + ")THEN(" + thenS.toString() + ")ELSE(" + elseS.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws DomainException {
        IMap<String, Integer> symTbl = state.getSymTable();
        IHeap<Integer> heap =  state.getHeapTable();
        if (getExp().eval(symTbl, heap) != 0) {
            state.getExeStack().push(getThenS());
        } else {
            state.getExeStack().push(getElseS());
        }
        return state;
    }
}
