package samplee.Domain.Expressions;

import samplee.Domain.DomainException;
import samplee.Domain.Heap.IHeap;
import samplee.Domain.Map.IMap;

import java.util.Scanner;


public class ReadExp implements Exp {
    private int number;




    public int getNumber() {
        return number;
    }
    @Override
    public Integer eval(IMap<String,Integer> tbl,IHeap<Integer> heap) throws DomainException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the number");
        try {
            String i = scan.next();
            int p = Integer.parseInt(i);
            return p;
        } catch (NumberFormatException exp) {
            throw new DomainException("Please enter a valid number!");
        }

    }

    @Override
    public String toString(){
        return "read()";
    }


}
