package lab03;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * Hash Map with Separate Chaining
 * A hash code is compressed to a bucket index.
 * Collisions are handled by a linked list at each bucket.
 */
public class Step09SeparateChainingHashMap<K, V> {
    public static class Entry<K, V> {
        private final K key;
        private V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V value) {
            V old = this.value;
            this.value = value;
            return old;
        }

        @Override
        public String toString() {
            return "(" + key + ", " + value + ")";
        }
    }

    private List<Entry<K, V>>[] table;
    private int size;
    private static final double MAX_LOAD_FACTOR = 0.75;

    @SuppressWarnings("unchecked")
    public Step09SeparateChainingHashMap(int capacity) {
        table = (List<Entry<K, V>>[]) new List[capacity];
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<>();
        }
    }

    public Step09SeparateChainingHashMap() {
        this(11);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // floorMod returns the remainder of floored division
    private int hash(K key) {
        int code = (key == null) ? 0 : key.hashCode();
        return Math.floorMod(code, table.length);
    }

    public V get(K key) {
        for (Entry<K, V> e : table[hash(key)]) {
            if ((key == null && e.key == null) || (key != null && key.equals(e.key))) {
                return e.value;
            }
        }
        return null;
    }

    public V put(K key, V value) {
        if ((size + 1.0) / table.length > MAX_LOAD_FACTOR)
            resize(nextPrime(table.length * 2));
        int bucket = hash(key);
        for (Entry<K, V> e : table[bucket]) {
            if ((key == null && e.key == null) || (key != null && key.equals(e.key))) {
                return e.setValue(value);
            }
        }
        table[bucket].add(new Entry<>(key, value));
        size++;
        return null;
    }

    public V remove(K key) {
        int bucket = hash(key);
        for (Entry<K, V> e : new ArrayList<>(table[bucket])) {
            if ((key == null && e.key == null) || (key != null && key.equals(e.key))) {
                table[bucket].remove(e);
                size--;
                return e.value;
            }
        }
        return null;
    }

    public List<Entry<K, V>> entrySet() {
        List<Entry<K, V>> all = new ArrayList<>();
        for (List<Entry<K, V>> bucket : table)
            all.addAll(bucket);
        return all;
    }

    private void resize(int newCapacity) {
        List<Entry<K, V>> old = entrySet();
        @SuppressWarnings("unchecked")
        List<Entry<K, V>>[] newTable = (List<Entry<K, V>>[]) new List[newCapacity];
        table = newTable;
        for (int i = 0; i < table.length; i++)
            table[i] = new LinkedList<>();
        size = 0;
        for (Entry<K, V> e : old)
            put(e.key, e.value);
    }

    private static int nextPrime(int n) {
        while (!isPrime(n))
            n++;
        return n;
    }

    private static boolean isPrime(int n) {
        if (n < 2)
            return false;
        for (int i = 2; i * i <= n; i++)
            if (n % i == 0)
                return false;
        return true;
    }

    public static void main(String[] args) {
        Step09SeparateChainingHashMap<Integer, String> map = new Step09SeparateChainingHashMap<>(5);
        map.put(11, "D");
        map.put(13, "C");
        map.put(23, "F");
        map.put(63, "Z");
        map.put(16, "A");
        map.put(56, "C");
        map.put(7, "Q");
        System.out.println("entries = " + map.entrySet());
        System.out.println("get(23) = " + map.get(23));
        System.out.println("remove(13) = " + map.remove(13));
        System.out.println("entries = " + map.entrySet());
    }
}
