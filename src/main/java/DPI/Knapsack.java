package DPI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Knapsack {
    // Question 1: LinkedIn OA
    // Given a list of umbrellas, each umbrella has a size representing the number of people
    // it covers. Give the minimum number of umbrellas to cover exactly n people. If there's
    // no such solution, return -1

    public int getUmbrellas(List<Integer> umbrellas, int n) {
        // dp[i][j]: the minimum number of umbrellas to cover exactly j people using only the
        // first i-th umbrellas.

        // dp[m][n+1] where m is size of umbrellas.
        // dp[i][j] = Math.min(dp[i-1][j], dp[i][j - umbrellas.get(i)] + 1) // each component needs to be >= 0

        int[] prev = new int[n+1];
        int[] cur = new int[n+1];
        Iterator<Integer> iterator = umbrellas.iterator();
        int uSize = iterator.next();
        for (int j = 1; j <= n; j++) {
            prev[j] = (j % uSize == 0) ? j / uSize : -1;
        }

        for (int i = 1; i < umbrellas.size(); i++) {
            cur[0] = 0;
            uSize = iterator.next();
            for (int j = 1; j <= n; j++) {
                cur[j] = -1;
                if (prev[j] != -1) {
                    cur[j] = prev[j];
                }
                if (j - uSize >= 0 && cur[j - uSize] >= 0) {
                    if (cur[j] == -1) {
                        cur[j] = cur[j - uSize] + 1; // always forgot to add 1
                    } else {
                        cur[j] = Math.min(cur[j], cur[j - uSize] + 1);
                    }
                }
            }
            int[] tmp = prev;
            prev = cur;
            cur = tmp;
        }
        return prev[n];
    }

    public static void main(String[] args) {
        Knapsack knapsack = new Knapsack();
        List<Integer> umbrellas = new ArrayList<>();
        umbrellas.add(2);
        umbrellas.add(2);
        umbrellas.add(4);
        System.out.println(knapsack.getUmbrellas(umbrellas, 100));
    }
}
