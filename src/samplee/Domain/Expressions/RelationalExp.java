package samplee.Domain.Expressions;

import samplee.Domain.DomainException;
import samplee.Domain.Heap.IHeap;
import samplee.Domain.Map.IMap;


public class RelationalExp implements Exp{
    private Exp e1;
    private Exp e2;
    private String op;

    public RelationalExp(Exp e1, String op, Exp e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public Integer eval(IMap<String, Integer> tbl, IHeap<Integer> heap) throws DomainException {
        if (op.equals("<"))  return (e1.eval(tbl, heap) < e2.eval(tbl, heap))? 1 : 0;
        if (op.equals("<=")) return (e1.eval(tbl, heap) <= e2.eval(tbl, heap))? 1 : 0;
        if (op.equals("==")) return (e1.eval(tbl, heap).equals(e2.eval(tbl, heap)))? 1 : 0;
        if (op.equals("!=")) return (!e1.eval(tbl, heap).equals(e2.eval(tbl, heap)))? 1 : 0;
        if (op.equals(">"))  return (e1.eval(tbl, heap) > e2.eval(tbl, heap))? 1 : 0;
        if (op.equals(">=")) return (e1.eval(tbl, heap) >= e2.eval(tbl, heap))? 1 : 0;
        return 0;
    }

    @Override
    public String toString() {
        return "(" + e1 + " " + op + " " + e2 + ")";
    }

}