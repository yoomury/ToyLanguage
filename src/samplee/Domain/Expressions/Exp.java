package samplee.Domain.Expressions;

import samplee.Domain.DomainException;
import samplee.Domain.Heap.IHeap;
import samplee.Domain.Map.IMap;

import java.io.Serializable;


public interface Exp extends Serializable {
    Integer eval(IMap<String, Integer> tbl, IHeap<Integer> heap) throws DomainException;
    String toString();
}

