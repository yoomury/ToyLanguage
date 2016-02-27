package samplee.Domain.Expressions;

import samplee.Domain.DomainException;
import samplee.Domain.Heap.IHeap;
import samplee.Domain.Map.IMap;


public class VarExp implements Exp {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public VarExp(String id) {
        this.id = id;
    }

    @Override
    public Integer eval(IMap<String, Integer> tbl, IHeap<Integer> heap) throws DomainException {
        if (tbl.containsKey(id)) return tbl.get(id);
        throw new DomainException("Uninitialized value");
    }

    @Override
    public String toString() {
        return " " + id + " ";
    }
}
