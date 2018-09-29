package strings;

import java.util.Collections;

public class ReorderArray {
    public int[] reorder(int[] array) {
        // 1 2 3 4 5 6 7 8
        // 1 2 5 6 3 4 7 8
        reorder(array, 0, array.length - 1 - ( 1 & array.length ));
        return array;
    }

    private void reorder(int[] array, int start, int end) {
        // 1  2  3  4      5  6  7  8
        // s  lm    m     m+1 rm    e
        if (end - start <= 2) {
            return;
        }
        int mid = (start + end) / 2;
        int lm = (start + mid) / 2;
        int rm = (mid + 1 + end) / 2;
        reverse(array, lm + 1, mid);
        reverse(array, mid + 1, rm);
        reverse(array, lm + 1, rm);
        // 1 2 5 6   3 4 7 8
        reorder(array, start, lm + (rm - mid)); // 1 + (5 - 4) = 2
        reorder(array, lm + (rm - mid) + 1, end);
    }

    private void reverse(int[] array, int start, int end) {
        while (start < end) {
            swap(array, start, end);
            start++;
            end--;
        }
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static void display(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ReorderArray reorderArray = new ReorderArray();
        int[] array = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
        reorderArray.reorder(array);
        display(array);

    }
    
}

