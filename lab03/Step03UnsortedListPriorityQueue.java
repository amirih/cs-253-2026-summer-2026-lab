package lab03;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
 * Unsorted List Priority Queue
 * insert: O(1)
 * min and removeMin: O(n)
 */
public class Step03UnsortedListPriorityQueue<K, V> {
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

    private final List<Entry<K, V>> list = new ArrayList<>();
    private final Comparator<K> comparator;

    public Step03UnsortedListPriorityQueue(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public Entry<K, V> insert(K key, V value) {
        Entry<K, V> e = new Entry<>(key, value);
        list.add(e);
        return e;
    }

    private int indexOfMin() {
        if (isEmpty())
            return -1;
        int best = 0;
        for (int i = 1; i < list.size(); i++) {
            if (comparator.compare(list.get(i).getKey(), list.get(best).getKey()) < 0) {
                best = i;
            }
        }
        return best;
    }

    public Entry<K, V> min() {
        int index = indexOfMin();
        return index == -1 ? null : list.get(index);
    }

    public Entry<K, V> removeMin() {
        int index = indexOfMin();
        return index == -1 ? null : list.remove(index);
    }

    public static void main(String[] args) {
        Step03UnsortedListPriorityQueue<Integer, String> pq = new Step03UnsortedListPriorityQueue<>(Integer::compareTo);
        pq.insert(5, "A");
        pq.insert(9, "C");
        pq.insert(3, "B");
        pq.insert(7, "D");

        while (!pq.isEmpty()) {
            System.out.println(pq.removeMin());
        }
    }
}
