package samplee.Domain.ProgramState;

import samplee.Domain.DomainException;
import samplee.Domain.Heap.IHeap;
import samplee.Domain.List.IList;
import samplee.Domain.LockTable.ILockTable;
import samplee.Domain.Map.IMap;
import samplee.Domain.Stack.IStack;
import samplee.Domain.Statements.IStmt;

import java.io.Serializable;


public class PrgState implements Serializable {
    private static int generator = 1;
    private int id;
    private IStack<IStmt> exeStack;
    private IMap<String, Integer> symTable;
    private IHeap<Integer> heapTable;
    private IList<Integer> out;
    private ILockTable lockTable;
    private IStmt originalProgram; //optional field, but good to have

    public ILockTable getLockTable() {
        return lockTable;
    }


    public PrgState(IStack<IStmt> stack, IMap<String, Integer> dictionary, IHeap<Integer> heap, IList<Integer> list, ILockTable lt, IStmt prg) {
        id = generator++;
        exeStack = stack;
        symTable = dictionary;
        heapTable = heap;
        out = list;
        lockTable=lt;

        originalProgram = prg;
        exeStack.push(originalProgram);
    }

    public int getId() {
        return id;
    }

    public IStack<IStmt> getExeStack() {
        return exeStack;
    }

    public IMap<String, Integer> getSymTable() {
        return symTable;
    }

    public IHeap<Integer> getHeapTable() {
        return heapTable;
    }

    public IList<Integer> getOut() {
        return out;
    }


    public boolean isNotCompleted(){
        return !exeStack.isEmpty();
    }

    public PrgState oneStep() throws DomainException {
           IStmt crtStmt=exeStack.pop();
           return crtStmt.execute(this);
    }


    public String printID(){
        return String.valueOf(id);
    }

    public String printStack(){
        return exeStack.toString();
    }
    public String printHeap(){
        return heapTable.toString();
    }
    public String printOutput(){
        return out.toString();
    }
    public String printSymTable(){
        return symTable.toString();
    }
    public String printLockTable() {return lockTable.toString();}



    @Override
    public String toString() {
        return "--------------------------------\r\n" + "id: " + id +
        "\r\nExec Stack: " + exeStack.toString() +
                "\r\nSymbol table: " + symTable.toString() + "\r\nHeap table: " + heapTable.toString() +
                "\r\nOutput List: " + out.toString() + "\r\n--------------------------------\r\n";
    }

}
