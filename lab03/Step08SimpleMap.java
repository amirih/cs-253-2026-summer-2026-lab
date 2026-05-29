package lab03;

import java.util.ArrayList;
import java.util.List;

/*
 * Map is a collection of key-value pairs with unique keys.
 * Simple Map ADT with an unsorted list
 * get, put, and remove use linear search.
 */
public class Step08SimpleMap<K, V> {
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

    private final List<Entry<K, V>> entries = new ArrayList<>();

    public int size() {
        return entries.size();
    }

    public boolean isEmpty() {
        return entries.isEmpty();
    }

    private int findIndex(K key) {
        for (int i = 0; i < entries.size(); i++) {
            if ((key == null && entries.get(i).key == null) || (key != null && key.equals(entries.get(i).key))) {
                return i;
            }
        }
        return -1;
    }

    public V get(K key) {
        int i = findIndex(key);
        return i == -1 ? null : entries.get(i).value;
    }

    public V put(K key, V value) {
        int i = findIndex(key);
        if (i == -1) {
            entries.add(new Entry<>(key, value));
            return null;
        }
        return entries.get(i).setValue(value);
    }

    public V remove(K key) {
        int i = findIndex(key);
        if (i == -1)
            return null;
        return entries.remove(i).value;
    }

    public List<K> keySet() {
        List<K> keys = new ArrayList<>();
        for (Entry<K, V> e : entries)
            keys.add(e.key);
        return keys;
    }

    public List<V> values() {
        List<V> values = new ArrayList<>();
        for (Entry<K, V> e : entries)
            values.add(e.value);
        return values;
    }

    public List<Entry<K, V>> entrySet() {
        return new ArrayList<>(entries);
    }

    public static void main(String[] args) {
        Step08SimpleMap<Integer, String> map = new Step08SimpleMap<>();
        map.put(5, "A");
        map.put(7, "B");
        map.put(2, "C");
        System.out.println("get(7) = " + map.get(7));
        System.out.println("replace 7 old value = " + map.put(7, "D"));
        System.out.println("entries = " + map.entrySet());
        System.out.println("remove(5) = " + map.remove(5));
        System.out.println("keys = " + map.keySet());
    }
}
