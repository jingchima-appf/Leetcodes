//implement Deque with array

public class MyArrayDeque<E> {
    private E[] array;
    private int arraySize;
    private int start;
    private int end; // (start, end) is the queue

    public MyArrayDeque() {
        arraySize = 3;
        array = (E[]) new Object[arraySize];
        start = 0;
        end = 1;
    }

    public E pollFirst() {
        if (isEmpty()) {
            return null;
        }
        E cur = array[getIdx(start)];
        start = getIdx(start + 1);
        return cur;
    }

    public void offerFirst(E e) {
        if (start == end) {
            expand();
        }
        array[getIdx(start)] = e;
        start = getIdx(start - 1);
    }

    public E peekFirst() {
        if (isEmpty()) {
            return null;
        }
        E cur = array[getIdx(start)];
        return cur;
    }

    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        E cur = array[getIdx(end-1)];
        end = getIdx(end-1);
        return cur;
    }

    public void offerLast(E e) {
        if (start == end) {
            expand();
        }
        array[end] = e;
        end = getIdx(end + 1);
    }

    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        E cur = array[getIdx(end-1)];
        return cur;
    }

    public int size() {
        if (start >= end) {
            return end + arraySize - start - 1;
        } else {
            return end - start - 1;
        }
    }

    public boolean isEmpty() {
        return getIdx(start) + 1 == getIdx(end);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = start + 1; i != end; i++) {
            i = getIdx(i);
            sb.append(array[i]).append(" ");
        }
        return sb.toString();
    }

    private void expand() {
        E[] newArray = (E[]) new Object[arraySize * 2];
        int newIdx = 1;
        for (int i = start + 1; i != end; i++) {
            i = getIdx(i);
            newArray[newIdx] = array[i];
            newIdx++;
        }
        start = 0;
        end = arraySize;
        arraySize *= 2;
        array = newArray;
    }

    private int getIdx(int idx) {
        if (idx == arraySize) {
            return 0;
        } else if (idx == -1) {
            return arraySize-1;
        } else {
            return idx;
        }
    }

    // for debug
    private void display() {
        for (int i = 0; i < arraySize; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MyArrayDeque<Integer> ad = new MyArrayDeque<>();
        ad.offerFirst(1);
        ad.offerFirst(2);
        System.out.println(ad);
        System.out.println(ad.size());
        ad.offerFirst(3);
        System.out.println(ad);
        ad.pollFirst();
        System.out.println(ad);
        ad.pollFirst();
        System.out.println(ad);
        for (int i = 4; i < 4 + 6; i++) {
            ad.offerLast(i);
        }
        System.out.println(ad);
        ad.pollLast();
        System.out.println(ad);
        ad.pollFirst();
        System.out.println(ad);
        System.out.println(ad.size());
    }
}
