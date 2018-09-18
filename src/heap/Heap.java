package heap;

import java.util.ArrayDeque;
import java.util.Queue;

public class Heap<E extends Comparable<E>> {

    // 先满足满树性，再满足堆序性
    private static final int EXPAND_FACTOR = 2;
    private static final int INIT_CAPACITY = 10;

    private E[] array;
    private int size;

    public Heap() {
        array = (E[]) new Comparable[INIT_CAPACITY];
        size = 0;
    }

    public Heap(E[] array) {
        if (array == null || array.length == 0) {
            this.array = (E[]) new Comparable[INIT_CAPACITY];
            size = 0;
        } else {
            this.array = (E[]) new Comparable[array.length];
            System.arraycopy(array, 0, this.array, 0, array.length);
            size = array.length;
            heapify();
            System.out.println("length = " + this.array.length);
        }
    }

    public void offer(E e) {
        if (size == array.length) {
            expand();
        }
        array[size] = e;
        percolateUp(size);
        size++;
    }

    public E poll() {
        if (size == 0) {
            return null;
        }
        E res = array[0];
        array[0] = array[size-1];
        size--;
        percolateDown(0);
        return res;
    }

    public E peek() {
        if (size == 0) {
            return null;
        }
        return array[0];
    }

    // return old value
    public E update(E e, int idx) {
        if (idx < 0 || idx >= this.size) {
            return null;
        }
        E old = array[idx];
        array[idx] = e;
        if (array[idx].compareTo(old) < 0) {
            percolateUp(idx);
        } else {
            percolateDown(idx);
        }
        return old;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public String toString() {
        if (size == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int qsize = queue.size();
            for (int i = 0; i < qsize; i++) {
                int cur = queue.poll();
                sb.append(array[cur]).append(" ");
                int left = cur * 2 + 1;
                int right = cur * 2 + 2;
                if (left < this.size) {
                    queue.offer(left);
                }
                if (right < this.size) {
                    queue.offer(right);
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private void percolateUp(int idx) {
        int cur = idx;
        int parent = (cur - 1) / 2;
        while (cur > 0 && array[cur].compareTo(array[parent]) < 0) {
            swap(array, cur, parent);
            cur = parent;
            parent = (cur - 1) / 2;
        }
    }

    private void percolateDown(int idx) {
        int cur = idx;
        int swapCandidate = 2 * cur + 1;
        while (swapCandidate < this.size) {
            swapCandidate = 2 * cur + 1; // each time move to swapCandidate! ( the smallest!! )
            if (2 * cur + 2 < this.size && array[2 * cur + 2].compareTo(array[swapCandidate]) < 0) {
                swapCandidate = 2 * cur + 2;
            }
            if (array[cur].compareTo(array[swapCandidate]) > 0) {
                swap(array, cur, swapCandidate);
                cur = swapCandidate;
                swapCandidate = 2 * cur + 1;
            } else {
                break;
            }
        }
    }

    private void expand() {
        E[] tmp = (E[]) new Comparable[this.array.length * EXPAND_FACTOR];
        System.arraycopy(this.array, 0, tmp, 0, this.array.length);
        this.array = tmp;
    }

    private void heapify() {
        int idx = (size - 1 - 1) / 2;
        while (idx >= 0) {
            percolateDown(idx);
            idx--;
        }
    }

    private void swap(E[] array, int i, int j) {
        E tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }


    public static void main(String[] args) {
//        Heap<Integer> heap = new Heap<>();
//        heap.offer(1);
//        heap.offer(2);
//        heap.offer(3);
//        System.out.println(heap);
//        heap.offer(1);
//        heap.offer(10);
//        heap.offer(100);
//        System.out.println(heap);
//        for (int i = 0; i < 5; i++) {
//            System.out.println(heap.poll());
//            System.out.println("====");
//            System.out.println(heap);
//        }

        Integer[] toHeap = {1,2,5,6,3,6,7,3,6,4,7};
        Heap<Integer> heap2 = new Heap<>(toHeap);
        System.out.println(heap2);
        heap2.update(1000, 4);
        System.out.println(heap2);
        heap2.update(-1, 4);
        System.out.println(heap2);
        heap2.offer(1000);
        heap2.offer(100000);
        System.out.println(heap2);
    }
}
