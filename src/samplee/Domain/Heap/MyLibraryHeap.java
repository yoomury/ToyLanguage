package samplee.Domain.Heap;

import samplee.Domain.DomainException;

import java.util.HashMap;


public class MyLibraryHeap<T> implements IHeap<T> {
    private HashMap<Integer, T> elements;
    private int nextFree;

    public MyLibraryHeap() {
        elements = new HashMap<>();
        nextFree = 1;
    }

    @Override
    public int add(T e) {
        elements.put(nextFree, e);
        return nextFree++;
    }

    @Override
    public T get(int address) throws DomainException{
        if (elements.get(address) == null) {
            throw new DomainException("Index out of bound");
        }
        return elements.get(address);
    }

    @Override
    public void update(int address, T value) throws DomainException {
        if (elements.get(address) == null) {
            throw new DomainException("Index out of bound");
        }
        elements.put(address, value);
    }

    @Override
    public String toString() {
        String toPrint = "";
        for (Integer elem : elements.keySet()) {
            toPrint = elem.toString() + " -> " + elements.get(elem) +" "  +toPrint;
        }
        return toPrint;
    }
}
