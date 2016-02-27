package samplee.Controller;


import samplee.Domain.ProgramState.PrgState;
import samplee.Repository.Repository;
import samplee.Repository.RepositoryException;

import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;



public class Controller {
    private Repository repo;
    private ExecutorService executor=Executors.newFixedThreadPool(10);
    private boolean printFlag;
    private boolean logFlag;

    public Controller(Repository thisRepo) throws RepositoryException {
        printFlag = false;
        logFlag = false;
        repo = thisRepo;
//        crtPrgState = repo.getCrtProgram();
    }

    public boolean isLogFlag() {
        return logFlag;
    }

    public void setLogFlag(boolean logFlag) {
        this.logFlag = logFlag;
    }

    public boolean isPrintFlag() {
        return printFlag;
    }

    public void setPrintFlag(boolean printFlag) {
        this.printFlag = printFlag;
    }

    public List<PrgState> getPrgStates() throws RepositoryException {
        return repo.getPrgStates();
    }

    public void serializeProgramState () {
        repo.serializePrgState ();
    }


    public List<PrgState> removesampleepleted(List<PrgState> inPrgList){
        return inPrgList.stream().filter(PrgState::isNotCompleted).collect(Collectors.toList());

    }


    public void oneStepForAllPrg(List<PrgState> lista) throws InterruptedException {
        List<Callable<PrgState>> callList=lista.stream().map(p->(Callable<PrgState>)p::oneStep).collect(Collectors.toList());
        List<PrgState> newPrgs=executor.invokeAll(callList)
                .stream()
                .map(future-> {try{return future.get();}
                              catch (InterruptedException |ExecutionException|ConcurrentModificationException e){
                                  System.out.println("oneStepForAll "+e.getMessage());
                              return null;
                              }}).filter(p->p!=null)
                .collect(Collectors.toList());


       //lista.addAll(newPrgs);
        lista.forEach(p -> {if(!newPrgs.stream().anyMatch(s -> s.getId() == p.getId())) newPrgs.add(p);});
        if(printFlag){
            newPrgs.forEach(System.out::println);
        }
        if(logFlag){
            repo.logPrgState();
        }
        repo.setPrgStates(newPrgs);

    }


    public void oneStep() throws InterruptedException {
        List<PrgState> prgList = removesampleepleted(repo.getPrgStates());
        oneStepForAllPrg(prgList);
    }

    public void allStep() throws InterruptedException {
        while(true){
            List<PrgState> prgList=removesampleepleted(repo.getPrgStates());
            if (prgList.size()==0){
                return;
            }
            oneStepForAllPrg(prgList);
        }
    }

    public void deserializeProgramState() throws IOException {
        repo.deserializePrgState();
    }

    public void setPrgList(List<PrgState> programs) {
        repo.setPrgStates(programs);
    }
}
