package samplee.Domain.Statements;

import samplee.Domain.DomainException;
import samplee.Domain.Expressions.Exp;
import samplee.Domain.ProgramState.PrgState;


public class IfThenStmt implements IStmt {
    private Exp exp;
    private IStmt thenS;

    public Exp getExp() {
        return exp;
    }

    public IStmt getThenS() {
        return thenS;
    }

    public void setExp(Exp exp) {
        this.exp = exp;
    }

    public void setThenS(IStmt thenS) {
        this.thenS = thenS;
    }

    public IfThenStmt(Exp e, IStmt t) {
        exp = e;
        thenS = t;
    }

    @Override
    public String toString() {
        return "IF(" + exp.toString() + ")THEN(" + thenS.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws DomainException {
        state.getExeStack().push(new IfStmt(getExp(), getThenS(), new SkipStmt()));
        return state;
    }
}
