package samplee.Domain.Stack;

import samplee.Domain.Statements.IStmt;


public class MyArrayStack implements IStack<IStmt> {
    private IStmt elements[];
    private int nrElements;

    public MyArrayStack() {
        nrElements = 0;
        elements = new IStmt[10];
    }

    @Override
    public void push(IStmt e) {
        if (nrElements == elements.length) {
            resize();
        }
        elements[nrElements++] = e;
    }

    private void resize() {
        IStmt[] tmp = new IStmt[elements.length * 2];
        System.arraycopy(elements, 0, tmp, 0, elements.length);
        elements = tmp;
    }

    @Override
    public IStmt pop() {
        if (nrElements > 0){
            return  elements[--nrElements];
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return nrElements == 0;
    }

    @Override
    public IStmt peek() {
        if (nrElements > 0) {
            return elements[nrElements - 1];
        }
        return null;
    }

    @Override
    public int search(IStmt e) {
        for(int i = 1; i <= nrElements; i++ ) if (elements[nrElements - i].equals(e)) return i;
        return -1;
    }
}
