package samplee.Domain.Statements;

import samplee.Domain.DomainException;
import samplee.Domain.Expressions.ArithExp;
import samplee.Domain.Expressions.Exp;
import samplee.Domain.ProgramState.PrgState;


public class SwitchStmt implements IStmt{
    private Exp exp;
    private Exp expCase1;
    private IStmt case1;
    private Exp expCase2;
    private IStmt case2;
    private IStmt defaultCase;

    public SwitchStmt(Exp exp, Exp opCase1, IStmt case1, Exp opCase2, IStmt case2, IStmt defaultCase) {
        this.exp = exp;
        this.expCase1 = opCase1;
        this.case1 = case1;
        this.case2 = case2;
        this.expCase2 = opCase2;
        this.defaultCase = defaultCase;
    }

    public Exp getExp() {
        return exp;
    }

    public Exp getExpCase1() {
        return expCase1;
    }

    public IStmt getCase1() {
        return case1;
    }

    public IStmt getCase2() {
        return case2;
    }

    public Exp getExpCase2() {
        return expCase2;
    }

    public IStmt getDefaultCase() {
        return defaultCase;
    }

    @Override
    public String toString() {
        return "SWITCH(" + exp.toString() + ") " + " case " + expCase1.toString() + ": " + case1.toString()
                + " case " + expCase2.toString() + ": " + case2.toString() + " default: " + defaultCase.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws DomainException {
            Exp difSwitch = new ArithExp(getExp(), "-", getExpCase2());
            Exp difSwitch2 = new ArithExp(getExp(), "-", getExpCase1());
            IStmt ifSwitch = new IfStmt(difSwitch2, getDefaultCase(), getCase1());
            IStmt switchStmt = new IfStmt(difSwitch, ifSwitch, getCase2());
            state.getExeStack().push(switchStmt);
        return state;
    }
}
