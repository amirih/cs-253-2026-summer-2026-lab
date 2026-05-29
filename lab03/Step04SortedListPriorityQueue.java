package lab03;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.ListIterator;

/*
 * Sorted List Priority Queue
 * insert: O(n)
 * min and removeMin: O(1)
 */
public class Step04SortedListPriorityQueue<K, V> {
    public static class Entry<K, V> {
        private final K key;
        private final V value;

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

        @Override
        public String toString() {
            return "(" + key + ", " + value + ")";
        }
    }

    private final LinkedList<Entry<K, V>> list = new LinkedList<>();
    private final Comparator<K> comparator;

    public Step04SortedListPriorityQueue(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public Entry<K, V> insert(K key, V value) {
        Entry<K, V> newest = new Entry<>(key, value);
        ListIterator<Entry<K, V>> it = list.listIterator();
        while (it.hasNext()) {
            Entry<K, V> current = it.next();
            if (comparator.compare(key, current.getKey()) < 0) {
                it.previous();
                it.add(newest);
                return newest;
            }
        }
        list.addLast(newest);
        return newest;
    }

    public Entry<K, V> min() {
        return isEmpty() ? null : list.getFirst();
    }

    public Entry<K, V> removeMin() {
        return isEmpty() ? null : list.removeFirst();
    }

    public static void main(String[] args) {
        Step04SortedListPriorityQueue<Integer, String> pq = new Step04SortedListPriorityQueue<>(Integer::compareTo);
        pq.insert(5, "A");
        pq.insert(9, "C");
        pq.insert(3, "B");
        pq.insert(7, "D");

        while (!pq.isEmpty()) {
            System.out.println(pq.removeMin());
        }
    }
}
