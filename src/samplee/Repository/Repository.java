package samplee.Repository;

import samplee.Domain.ProgramState.PrgState;

import java.io.IOException;
import java.util.List;


public interface Repository {
    //PrgState getCrtProgram() throws RepositoryException;
   void serializePrgState();
    void setPrgStates(List<PrgState> states);
    List<PrgState> getPrgStates();
    void logPrgState();
    void deserializePrgState() throws IOException;
}