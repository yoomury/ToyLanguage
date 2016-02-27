package samplee.Domain.Statements;

import samplee.Domain.DomainException;
import samplee.Domain.ProgramState.PrgState;

/**
 * Created by victor on 10/12/15.
 */
public class CompStmt implements IStmt {
    private IStmt first;
    private IStmt second;

    public IStmt getFirst() {
        return first;
    }

    public void setFirst(IStmt first) {
        this.first = first;
    }

    public IStmt getSecond() {
        return second;
    }

    public void setSecond(IStmt second) {
        this.second = second;
    }

    public CompStmt(IStmt left, IStmt right) {
        first = left;
        second = right;
    }

    @Override
    public String toString() {
        return "(" + first + ";" + second + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws DomainException {
            state.getExeStack().push(second);
            state.getExeStack().push(first);
        return state;
    }
}
