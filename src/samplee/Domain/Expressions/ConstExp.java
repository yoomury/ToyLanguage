package samplee.Domain.Expressions;

import samplee.Domain.Heap.IHeap;
import samplee.Domain.Map.IMap;


public class ConstExp implements Exp {
    private Integer number;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public ConstExp(Integer number) {
        this.number = number;
    }

    @Override
    public Integer eval(IMap<String, Integer> tbl, IHeap<Integer> heap) {
        return number;
    }

    @Override
    public String toString() {
        return " " + String.valueOf(number) + " ";
    }
}
