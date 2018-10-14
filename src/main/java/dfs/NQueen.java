package dfs;

import java.util.ArrayList;
import java.util.List;

public class NQueen {

    public List<List<String>> solveNQueens(int n) {
        if (n < 0) {
            return new ArrayList<>();
        }
        List<List<String>> results = new ArrayList<>();
        dfs(n, 0, new boolean[n], new boolean[2*n-1], new boolean[2*n-1], new ArrayList<>(), results);
        return results;
    }

    private void dfs(int n, int index,
                     boolean[] cols, boolean[] diags, boolean[] antiDiags,
                     List<Integer> path,  List<List<String>> results) {
        if (index == n) {
            List<String> placement = new ArrayList<>();
            for (Integer col: path) {
                StringBuilder row = new StringBuilder();
                for (int i = 0; i < n; i++) {
                    if (col == i) {
                        row.append("Q");
                    } else {
                        row.append(".");
                    }
                }
                placement.add(row.toString());
            }
            results.add(placement);
            return;
        }
        for (int i = 0; i < n; i++) {
            int col = i;
            int diag = i - index + n - 1;
            int antiDiag = i + index;
            if (cols[col] || diags[diag] || antiDiags[antiDiag]) {
                continue;
            }
            cols[col] = true;
            diags[diag] = true;
            antiDiags[antiDiag] = true;
            path.add(col);
            dfs(n, index+1, cols, diags, antiDiags, path, results);
            path.remove(path.size()-1);
            cols[col] = false;
            diags[diag] = false;
            antiDiags[antiDiag] = false;
        }
    }

    public List<List<Integer>> placeQueens(int m, int n) {
        List<List<Integer>> results = new ArrayList<>();
        dfs(m, n, 0, new boolean[n], new boolean[n + m - 1],
                new boolean[m + n - 1], new ArrayList<>(), results);
        return results;
    }

    private void dfs(int m, int n, int index,
                     boolean[] cols, boolean[] diags,
                     boolean[] antiDiags,
                     List<Integer> path,
                     List<List<Integer>> results) {
        if (index == m) {
            results.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (cols[i] || diags[i - index + m - 1]
                    || antiDiags[index + i]) {
                continue;
            }
            path.add(i);
            boolean tmpCol = cols[i];
            boolean tmpDiag = diags[i - index + m - 1];
            boolean tmpAntiDiag = antiDiags[index + i];
            cols[i] = true;
            diags[i - index + m - 1] = true;
            antiDiags[index + i] = true;
            dfs(m, n, index + 1, cols, diags, antiDiags, path, results);
            path.remove(path.size()-1);
            cols[i] = tmpCol;
            diags[i - index + m - 1] = tmpDiag;
            antiDiags[index + i] = tmpAntiDiag;
        }
    }

    public static void main(String[] args) {
        NQueen nQueen = new NQueen();
        // System.out.println(nQueen.placeQueens(3,4));
        char x = 0x7E;
        System.out.println(x);
    }

}