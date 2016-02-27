package samplee.Domain.Statements;

import samplee.Domain.DomainException;
import samplee.Domain.ProgramState.PrgState;

import java.io.Serializable;


public interface IStmt extends Serializable {
    String toString();
    PrgState execute(PrgState state) throws DomainException;
}