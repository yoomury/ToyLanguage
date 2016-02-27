package samplee.Domain.LockTable;

import samplee.Domain.DomainException;

import java.util.HashMap;

/**
 * Created by mures on 1/19/2016.
 */
public class LockTable implements ILockTable {
    private HashMap<Integer,Integer> elems;
    private int nextFree;

    public LockTable(){
        elems=new HashMap<>();
        nextFree=0;
    }


    @Override
    public synchronized int add(int e) {
        elems.put(nextFree, e);
        return nextFree++;
    }

    @Override
    public synchronized int get(int address) throws DomainException {
        if (elems.get(address) == null) {
            throw new DomainException("Index out of bound");
        }
        return elems.get(address);
    }

    @Override
    public synchronized boolean isKey(int address) throws DomainException{
        return elems.containsKey(address);
    }

    @Override
    public synchronized void update(int address, int value) throws DomainException {
     /*   if (elems.get(address) == null) {
            throw new DomainException("Index out of bound");
        }*/
        elems.put(address, value);
    }

    @Override
    public String toString() {
        String toPrint = "";
        for (Integer elem : elems.keySet()) {
            toPrint = elem.toString() + " -> " + elems.get(elem) +" "  +toPrint;
        }
        return toPrint;
    }
}
