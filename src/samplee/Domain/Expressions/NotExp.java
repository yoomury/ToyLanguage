package samplee.Domain.Expressions;

import samplee.Domain.DomainException;
import samplee.Domain.Heap.IHeap;
import samplee.Domain.Map.IMap;


public class NotExp implements Exp {
    private Exp exp;

    public NotExp(Exp exp) {
        this.exp = exp;
    }

    @Override
    public Integer eval(IMap<String, Integer> tbl, IHeap<Integer> heap) throws DomainException {
        return exp.eval(tbl, heap) == 0 ? 1: 0;
    }

    @Override
    public String toString() {
        return "!(" + exp + ")";
    }
}
