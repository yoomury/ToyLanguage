package samplee.Domain.Heap;

import samplee.Domain.DomainException;

import java.io.Serializable;


public interface IHeap<T> extends Serializable {
    int add(T e);
    T get(int address) throws DomainException;
    void update(int address, T value) throws DomainException;
}
