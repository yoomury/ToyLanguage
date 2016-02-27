package samplee.Domain.Stack;

import samplee.Domain.DomainException;

import java.io.Serializable;


public interface IStack<T> extends Serializable {
    void push(T e);

    T pop() throws DomainException;

    boolean isEmpty();

    T peek() throws DomainException;

    int search(T e);
}
