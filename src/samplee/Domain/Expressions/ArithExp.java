package samplee.Domain.Expressions;

import samplee.Domain.DomainException;
import samplee.Domain.Heap.IHeap;
import samplee.Domain.Map.IMap;


public class ArithExp implements Exp {
    private Exp e1;
    private Exp e2;
    private String op;

    public Exp getE1() {
        return e1;
    }

    public void setE1(Exp e1) {
        this.e1 = e1;
    }

    public Exp getE2() {
        return e2;
    }

    public void setE2(Exp e2) {
        this.e2 = e2;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public ArithExp(Exp e1, String op, Exp e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public Integer eval(IMap<String, Integer> tbl, IHeap<Integer> heap) throws DomainException {
        if (op.equals("+")) return (e1.eval(tbl, heap) + e2.eval(tbl, heap));
        if (op.equals("-")) return (e1.eval(tbl, heap) - e2.eval(tbl, heap));
        if (op.equals("*")) return (e1.eval(tbl, heap) * e2.eval(tbl, heap));
        if (op.equals("/")) {
            if (e2.eval(tbl, heap) == 0) throw new DomainException("Divide by zero");
            return (e1.eval(tbl, heap) / e2.eval(tbl, heap));
        }
        return 0;
    }

    @Override
    public String toString() {
        return "(" + e1 + " " + op + " " + e2 + ")";
    }
}
