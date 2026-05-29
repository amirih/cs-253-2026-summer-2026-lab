package lab03;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
 * Heap-based Priority Queue
 * This combines key-value entries with a min heap.
 */
public class Step07HeapPriorityQueue<K, V> {
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

    private final List<Entry<K, V>> heap = new ArrayList<>();
    private final Comparator<K> comparator;

    public Step07HeapPriorityQueue(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    private int compare(Entry<K, V> a, Entry<K, V> b) {
        return comparator.compare(a.key, b.key);
    }

    private void swap(int i, int j) {
        Entry<K, V> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public Entry<K, V> insert(K key, V value) {
        Entry<K, V> newest = new Entry<>(key, value);
        heap.add(newest);
        upHeap(heap.size() - 1);
        return newest;
    }

    private void upHeap(int index) {
        while (index > 0) {
            int p = parent(index);
            if (compare(heap.get(index), heap.get(p)) >= 0)
                break;
            swap(index, p);
            index = p;
        }
    }

    public Entry<K, V> min() {
        return isEmpty() ? null : heap.get(0);
    }

    public Entry<K, V> removeMin() {
        if (isEmpty())
            return null;
        Entry<K, V> answer = heap.get(0);
        Entry<K, V> last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            downHeap(0);
        }
        return answer;
    }

    private void downHeap(int index) {
        while (left(index) < heap.size()) {
            int smallChild = left(index);
            if (right(index) < heap.size() && compare(heap.get(right(index)), heap.get(smallChild)) < 0) {
                smallChild = right(index);
            }
            if (compare(heap.get(index), heap.get(smallChild)) <= 0)
                break;
            swap(index, smallChild);
            index = smallChild;
        }
    }

    public static void main(String[] args) {
        Step07HeapPriorityQueue<Integer, String> pq = new Step07HeapPriorityQueue<>(Integer::compareTo);
        pq.insert(5, "A");
        pq.insert(9, "C");
        pq.insert(3, "B");
        pq.insert(7, "D");
        while (!pq.isEmpty()) {
            System.out.println(pq.removeMin());
        }
    }
}
