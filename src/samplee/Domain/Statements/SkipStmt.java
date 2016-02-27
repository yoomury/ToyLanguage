package samplee.Domain.Statements;

import samplee.Domain.DomainException;
import samplee.Domain.ProgramState.PrgState;


public class SkipStmt implements IStmt {
    @Override
    public String toString() {
        return "SKIP";
    }

    @Override
    public PrgState execute(PrgState state) throws DomainException {
        return state;
    }

}
