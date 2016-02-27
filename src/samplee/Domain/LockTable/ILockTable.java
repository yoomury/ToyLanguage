package samplee.Domain.LockTable;

import samplee.Domain.DomainException;

/**
 * Created by mures on 1/19/2016.
 */
public interface ILockTable {
    int add(int e);
    int get(int address) throws DomainException;
    void update(int address, int value) throws DomainException;
    boolean isKey(int address) throws DomainException;
}
