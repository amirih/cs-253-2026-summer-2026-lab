package lab03;

import java.util.ArrayList;
import java.util.List;

/*
 * ADT: Abstract Data Type
 * This starter implementation is intentionally simple and uses an unsorted list.
 */
public class Step01PriorityQueue {
    public static class Entry<K, V> {
        private final K key;
        private final V value;

        public Entry(K key, V value) {
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

    public interface PriorityQueueADT<K, V> {
        int queueSize();

        boolean isEmpty();

        Entry<K, V> insert(K key, V value);

        Entry<K, V> getMin();

        Entry<K, V> removeMin();
    }

    public static class SimplePriorityQueue<K extends Comparable<K>, V>
            implements PriorityQueueADT<K, V> {
        private final List<Entry<K, V>> entries = new ArrayList<>();

        @Override
        public int queueSize() {
            return entries.size();
        }

        @Override
        public boolean isEmpty() {
            return entries.isEmpty();
        }

        @Override
        public Entry<K, V> insert(K key, V value) {
            Entry<K, V> newest = new Entry<>(key, value);
            entries.add(newest);
            return newest;
        }

        @Override
        public Entry<K, V> getMin() {
            if (isEmpty())
                return null;
            Entry<K, V> min = entries.get(0);
            for (Entry<K, V> e : entries) {
                if (e.getKey().compareTo(min.getKey()) < 0) {
                    min = e;
                }
            }
            return min;
        }

        @Override
        public Entry<K, V> removeMin() {
            Entry<K, V> min = getMin();
            if (min != null)
                entries.remove(min);
            return min;
        }
    }

    public static void main(String[] args) {
        SimplePriorityQueue<Integer, String> pq = new SimplePriorityQueue<>();
        pq.insert(5, "A");
        pq.insert(4, "A");
        pq.insert(3, "A");
        pq.insert(9, "C");
        pq.insert(4, "B");
        pq.insert(5, "B");

        System.out.println("min = " + pq.getMin());
        System.out.println("removeMin = " + pq.removeMin());
        System.out.println("new min = " + pq.getMin());
    }
}
