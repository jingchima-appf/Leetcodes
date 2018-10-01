package recursionII;

import java.util.ArrayList;
import java.util.List;

public class SpiralTraverse {

    // Question 1: spiral traverse a N * N matrix

    public List<Integer> spiral(int[][] matrix) {
        List<Integer> results = new ArrayList<>();
        spiralTraversal(matrix, matrix.length, results);
        return results;
    }

    // Method I: use recursion
    private void  spiralTraversal(int[][] matrix, int length, List<Integer> results) {
        if (length <= 0) {
            return;
        }
        circularIterate(matrix, length, results);
        spiralTraversal(matrix, length - 2, results);
    }

    /*
    @Assumption: matrix.lenth - length is even
    */
    private void circularIterate(int[][] matrix, int length, List<Integer> results) {
        // 1 2 3 4 5  length = 3
        //     3
        // 1 2 3 4 length = 2
        //   2 3
        int n = matrix.length;
        int start = (n - length) / 2;
        int end = start + length - 1; // inclusive
        if (length == 1) {
            results.add(matrix[start][start]);
            return;
        }
        for (int i = start; i < end; i++) {
            results.add(matrix[start][i]);
        }
        for (int i = start; i < end; i++) {
            results.add(matrix[i][end]);
        }
        for (int i = end; i > start; i--) {
            results.add(matrix[end][i]);
        }
        for (int i = end; i > start; i--) {
            results.add(matrix[i][start]);
        }
    }

    // Question 2: Spiral Order traversal in M * N matrix
    // now the base case becomes : rowNum <= 0 || colNum <=0 || rowNum == 1 || colNum == 1

    public List<Integer> spiralII(int[][] matrix) {
        // Write your solution here
        List<Integer> results = new ArrayList<>();
        traverse(matrix, matrix[0].length, matrix.length, results);
        return results;
    }

    /*
    @Assumption: m - colNum is even, and n - colNum is even
    */
    private void traverse(int[][] matrix, int colNum, int rowNum, List<Integer> results) {
        if (colNum <= 0 || rowNum <= 0) {
            return;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int colStart = (n - colNum) / 2;
        int colEnd = colStart + colNum - 1; // inclusive
        int rowStart = (m - rowNum) / 2;
        int rowEnd = rowStart + rowNum - 1; // inclusive

        if (rowNum == 1) {
            for (int i = colStart; i <= colEnd; i++) {
                results.add(matrix[rowStart][i]);
            }
            return;
        }
        if (colNum == 1) {
            for (int i = rowStart; i <= rowEnd; i++) {
                results.add(matrix[i][colStart]);
            }
            return;
        }

        for (int i = colStart; i < colEnd; i++) {
            results.add(matrix[rowStart][i]);
        }
        for (int i = rowStart; i < rowEnd; i++) {
            results.add(matrix[i][colEnd]);
        }
        for (int i = colEnd; i > colStart; i--) {
            results.add(matrix[rowEnd][i]);
        }
        for (int i = rowEnd; i > rowStart; i--) {
            results.add(matrix[i][colStart]);
        }

        traverse(matrix, colNum-2, rowNum-2, results);
    }


    public static void main(String[] args) {
        SpiralTraverse instance = new SpiralTraverse();
        // 1  2  3  4
        // 5  6  7  8
        // 9  10 11 12
        // 13 14 15 16
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        System.out.println(instance.spiral(matrix));
    }
}
