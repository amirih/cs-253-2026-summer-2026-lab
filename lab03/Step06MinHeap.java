package lab03;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
 * Min Heap
 * insert uses up-heap bubbling.
 * removeMin removes the root and fixes the heap with down-heap bubbling.
 */
public class Step06MinHeap<T> {
    private final List<T> heap = new ArrayList<>();
    private final Comparator<T> comparator;

    public Step06MinHeap(Comparator<T> comparator) {
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

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public void insert(T value) {
        heap.add(value);
        upHeap(heap.size() - 1);
    }

    private void upHeap(int index) {
        while (index > 0) {
            int p = parent(index);
            if (comparator.compare(heap.get(index), heap.get(p)) >= 0)
                break;
            swap(index, p);
            index = p;
        }
    }

    public T min() {
        return isEmpty() ? null : heap.get(0);
    }

    public T removeMin() {
        if (isEmpty())
            return null;
        T answer = heap.get(0);
        T last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            downHeap(0);
        }
        return answer;
    }

    private void downHeap(int index) {
        while (left(index) < heap.size()) {
            int smallChild = left(index);
            if (right(index) < heap.size()
                    && comparator.compare(heap.get(right(index)), heap.get(smallChild)) < 0) {
                smallChild = right(index);
            }
            if (comparator.compare(heap.get(index), heap.get(smallChild)) <= 0)
                break;
            swap(index, smallChild);
            index = smallChild;
        }
    }

    public String toString() {
        return heap.toString();
    }

    public static void main(String[] args) {
        Step06MinHeap<Integer> heap = new Step06MinHeap<>(Integer::compareTo);
        for (int x : new int[] { 30, 10, 40, 5, 15, 20 }) {
            heap.insert(x);
            System.out.println("after insert " + x + ": " + heap);
        }
        while (!heap.isEmpty()) {
            System.out.println("removeMin = " + heap.removeMin() + ", heap = " + heap);
        }
    }
}
