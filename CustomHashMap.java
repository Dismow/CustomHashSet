package Lesson5;

import java.util.Objects;

public class CustomHashMap<K, V> {
    private MapBucket[] bucket;
    private int CAPACITY = 10;
    private int size = 0;

    public CustomHashMap() {
        this.bucket = new MapBucket[CAPACITY];
    }

    public int getSize() {
        return size;
    }

    private int getHash(K key) {
        return key.hashCode() & (CAPACITY - 1);
    }

    private KeyValueEntry getEntry(K key) {
        int hash = getHash(key);

        for (int i = 0; i < bucket[hash].getEntries().size(); i++) {
            KeyValueEntry keyValueEntry = bucket[hash].getEntries().get(i);
            if (keyValueEntry.getKey().equals(key))
                return keyValueEntry;
        }
        return null;
    }

    public V put(K key, V value) {
        if (containsKey(key)) {
           KeyValueEntry keyValueEntry = getEntry(key);
           Object temp = keyValueEntry.getValue();
           keyValueEntry.setValue(value);

           return (V) temp;
        } else {
            int hash = getHash(key);

            if (bucket[hash] == null) {
                bucket[hash] = new MapBucket();
            }
            bucket[hash].addEntry(new KeyValueEntry(key, value));
            size++;

            return null;
        }
    }

    public V delete(K key) {
        if (containsKey(key)) {
            int hash = getHash(key);
            size--;
            V temp = (V) getEntry(key).getValue();
            bucket[hash].removeEntry(getEntry(key));
            return temp;
        }
        return null;
    }

    public V get(K key) {return containsKey(key) ? (V) getEntry(key).getValue() : null;}

    public boolean containsKey(K key) {
        int hash = getHash(key);

        if (Objects.isNull(bucket[hash]))
            return false;

        return !(Objects.isNull(getEntry(key)));
    }

    public static void main(String[] args) {
        CustomHashMap<String, Integer> chm = new CustomHashMap<>();
        System.out.println(chm.containsKey("1"));
    }
}
