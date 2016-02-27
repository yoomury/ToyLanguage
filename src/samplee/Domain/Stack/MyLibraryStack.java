package samplee.Domain.Stack;

import samplee.Domain.DomainException;

import java.util.Stack;


public class MyLibraryStack<T> implements IStack<T> {
    private Stack<T> elements;

    public MyLibraryStack() {
        elements = new Stack<>();
    }

    @Override
    public void push(T e) {
        elements.push(e);
    }

    @Override
    public T pop() throws  DomainException {
        if (this.isEmpty()) throw new DomainException("Stack empty");
        return elements.pop();
    }


    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public T peek() throws DomainException {
        if (this.isEmpty()) throw new DomainException("Stack empty");
        return elements.peek();
    }

    @Override
    public int search(T e) {
        return elements.search(e);
    }

    @Override
    public String toString() {
        String toPrint = "";
        for (T elem : elements) {
            toPrint = elem.toString() + toPrint;
        }
        return toPrint;
    }
}
