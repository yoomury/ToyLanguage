//package samplee.Domain.Map;

/*
public class MyArrayDictionary implements IMap<String, Integer> {
    private String keys[];
    private Integer values[];
    private int nrElements;

    public MyArrayDictionary() {
        keys = new String[10];
        values = new Integer[10];
        nrElements = 0;
    }

    @Override
    public void clear() {
        keys = new String[10];
        values = new Integer[10];
        nrElements = 0;
    }

    @Override
    public boolean containsKey(String key) {
        for (int i = 0; i < nrElements; i++) {
            if (keys[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Integer value) {
        for (int i = 0; i < nrElements; i++) {
            if (values[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer get(String key) {
        for (int i = 0; i < nrElements; i++) {
            if (keys[i].equals(key)) {
                return values[i];
            }
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return nrElements == 0;
    }

    private void resize() {
        String[] tmpKeys = new String[keys.length * 2];
        Integer[] tmpValues = new Integer[values.length * 2];
        System.arraycopy(keys, 0, tmpKeys, 0, keys.length);
        System.arraycopy(values, 0, tmpValues, 0, values.length);
        keys = tmpKeys;
        values = tmpValues;
    }

    @Override
    public void put(String key, Integer value) {
        if (!this.containsKey(key)) {
            if(keys.length >= nrElements) {
                resize();
            }
            keys[nrElements] = key;
            values[nrElements] = value;
            nrElements++;
        }
    }

    @Override
    public Integer remove(String key) {
        for (int i = 0; i < nrElements; i++) {
            if (keys[i].equals(key)) {
                Integer value = values[i];
                System.arraycopy(keys, i + 1, keys, i, keys.length - i - 1);
                System.arraycopy(values, i + 1, values, i, values.length - i - 1);
                nrElements--;
                return value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return nrElements;
    }

    @Override
    public Set<String> keySet() {
        return null;
    }



}
*/