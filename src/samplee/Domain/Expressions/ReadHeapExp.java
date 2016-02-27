package samplee.Domain.Expressions;

import samplee.Domain.DomainException;
import samplee.Domain.Heap.IHeap;
import samplee.Domain.Map.IMap;


public class ReadHeapExp implements Exp{
    private String id;

    public ReadHeapExp(String id) {
        this.id = id;
    }

    @Override
    public Integer eval(IMap<String, Integer> tbl, IHeap<Integer> heap) throws DomainException {
        Integer address = tbl.get(id);
        return heap.get(address);
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "readHeap( " + id + ")";
    }
}
