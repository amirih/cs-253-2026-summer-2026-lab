package lab03;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
 * Sorted Array Ordered Map
 * Exact search uses binary search: O(log n).
 * insert and remove may shift array elements: O(n).
 * Nearest-key operations: first, last, floor, ceiling, lower, upper.
 */
public class Step10SortedArrayOrderedMap<K, V> {
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

        public String toString() {
            return "(" + key + ", " + value + ")";
        }
    }

    private final List<Entry<K, V>> table = new ArrayList<>();
    private final Comparator<K> comparator;

    public Step10SortedArrayOrderedMap(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    public int size() {
        return table.size();
    }

    public boolean isEmpty() {
        return table.isEmpty();
    }

    private int compare(K a, K b) {
        return comparator.compare(a, b);
    }

    // Returns index if found. Otherwise returns -(insertionPoint + 1).
    private int binarySearch(K key) {
        int low = 0;
        int high = table.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = compare(key, table.get(mid).key);
            if (cmp == 0)
                return mid;
            if (cmp < 0)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return -(low + 1);
    }

    public V get(K key) {
        int i = binarySearch(key);
        return i >= 0 ? table.get(i).value : null;
    }

    public V put(K key, V value) {
        int i = binarySearch(key);
        if (i >= 0)
            return table.get(i).setValue(value);
        int insertionPoint = -(i + 1);
        table.add(insertionPoint, new Entry<>(key, value));
        return null;
    }

    public V remove(K key) {
        int i = binarySearch(key);
        return i >= 0 ? table.remove(i).value : null;
    }

    public Entry<K, V> firstEntry() {
        return isEmpty() ? null : table.get(0);
    }

    public Entry<K, V> lastEntry() {
        return isEmpty() ? null : table.get(table.size() - 1);
    }

    public Entry<K, V> ceilingEntry(K key) {
        int i = binarySearch(key);
        int index = i >= 0 ? i : -(i + 1);
        return index < table.size() ? table.get(index) : null;
    }

    public Entry<K, V> floorEntry(K key) {
        int i = binarySearch(key);
        int index = i >= 0 ? i : -(i + 1) - 1;
        return index >= 0 ? table.get(index) : null;
    }

    public Entry<K, V> upperEntry(K key) {
        int i = binarySearch(key);
        int index = i >= 0 ? i + 1 : -(i + 1);
        return index < table.size() ? table.get(index) : null;
    }

    public Entry<K, V> lowerEntry(K key) {
        int i = binarySearch(key);
        int index = i >= 0 ? i - 1 : -(i + 1) - 1;
        return index >= 0 ? table.get(index) : null;
    }

    public List<Entry<K, V>> entrySet() {
        return new ArrayList<>(table);
    }

    public static void main(String[] args) {
        Step10SortedArrayOrderedMap<Integer, String> map = new Step10SortedArrayOrderedMap<>(Integer::compareTo);
        map.put(12, "A");
        map.put(17, "B");
        map.put(20, "C");
        map.put(25, "D");
        map.put(31, "E");
        map.put(38, "F");

        System.out.println("entries = " + map.entrySet());
        System.out.println("first = " + map.firstEntry());
        System.out.println("last = " + map.lastEntry());
        System.out.println("floor(24) = " + map.floorEntry(24));
        System.out.println("ceiling(24) = " + map.ceilingEntry(24));
        System.out.println("lower(25) = " + map.lowerEntry(25));
        System.out.println("upper(25) = " + map.upperEntry(25));
    }
}
