package samplee.Domain.List;

import samplee.Domain.DomainException;

import java.util.ArrayList;
import java.util.StringJoiner;


public class MyLibraryList<T> implements IList<T> {
    private ArrayList<T> elements;

    public MyLibraryList() {
        elements = new ArrayList<>();
    }

    @Override
    public boolean add(T e) {
        return elements.add(e);
    }

    @Override
    public boolean contains(T element) {
        return elements.contains(element);
    }

    @Override
    public T get(int index) throws DomainException {
        if (index >= 0 && index < elements.size())
        return elements.get(index);
            throw new DomainException("Index out of bound");
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public String toString() {
        StringJoiner string= new StringJoiner(" ");
        for (T elem: elements){
            string.add(elem.toString());
        }
        return string.toString();
       /* String toPrint = "";
        for (T elem : elements) {
            toPrint = elem.toString() +" " + toPrint;
        }
        return toPrint;*/
    }
}
