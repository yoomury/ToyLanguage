package samplee.Domain.Map;

import samplee.Domain.DomainException;

import java.util.HashMap;
import java.util.Set;

public class MyLibraryDictionary<K, V> implements IMap<K, V> {
    private HashMap<K, V> hashMap;

    public MyLibraryDictionary() {
        hashMap = new HashMap<>();
    }

    public MyLibraryDictionary(MyLibraryDictionary<String, Integer> tmp) {
        hashMap = (HashMap<K, V>) tmp.hashMap.clone();
    }

    @Override
    public void clear() {
        hashMap = new HashMap<>();
    }

    @Override
    public boolean containsKey(K key) {
        return hashMap.containsKey(key);
    }

    @Override
    public boolean containsValue(V value) {
        return hashMap.containsValue(value);
    }

    @Override
    public V get(K key) throws DomainException {
        if (!this.containsKey(key)) throw new DomainException("No such key");
        return hashMap.get(key);
    }

    @Override
    public boolean isEmpty() {
        return hashMap.isEmpty();
    }

    @Override
    public void put(K key, V value) {
        hashMap.put(key, value);
    }

    @Override
    public V remove(K key) throws DomainException {
        if (!this.containsKey(key)) throw new DomainException("No such key");
        return hashMap.remove(key);
    }

    @Override
    public int size() {
        return hashMap.size();
    }

    @Override
    public Set<K> keySet() {
        return hashMap.keySet();
    }

    @Override
    public String toString() {
        String toPrint = "";
        for (K elem : hashMap.keySet()) {
            toPrint = elem.toString() + " -> " + hashMap.get(elem) +" "   + toPrint;
        }
        return toPrint;
    }


}
