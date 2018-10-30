package thumbtackPrepare;

import java.util.ArrayList;
import java.util.List;

public class AmicablePair {
    public List<List<Integer>> getAmicablePairs(int k) {

        int[] sums = getTrueFactorSums(k);
        List<List<Integer>> results = new ArrayList<>();
        for (int i = 1; i <= k; i++) {
            if (sums[i] != 0 && sums[i] <= k && sums[i] < i) { // make sure to use sums[i] < i to remove duplicates!!!!
                if (sums[sums[i]] == i) {
                    List<Integer> pair = new ArrayList<>();
                    pair.add(sums[i]);
                    pair.add(i);
                    results.add(pair);
                }
            }
        }
        return results;
    }

    private int[] getTrueFactorSums(int k) {
        int[] sums = new int[k + 1];
        for (int i = 2; i <= k; i++) {
            sums[i]++;
            for (int j = 2 * i; j <= k; j += i) {
                sums[j] += i;
            }
        }
        return sums;
    }


    public int factorSum(int n) {
        int sum = 1, i;
        for (i = 2; i * i < n; ++i) {
            if (n % i == 0) {
                sum += i + n / i;
            }
        }
        if (i * i == n) {
            sum += i;
        }
        return sum;
    }

    public List<List<Integer>> correctAnswer(int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (int i = 2; i <= k; ++i) {
            int amicable = factorSum(i);
            if (amicable <= i || amicable > k) {
                continue;
            }
            if (factorSum(amicable) == i) {
                ArrayList<Integer> pair = new ArrayList<Integer>();
                pair.add(i);
                pair.add(amicable);
                result.add(pair);
            }
        }
        return result;
    }

}
