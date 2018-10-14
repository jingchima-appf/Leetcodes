package recursionII;

public class RecursionAndCalculation {
    // a = 0
// b < 0 overflow
// b > 0 divide and conquer

    public double pow(int a, int b) throws Exception {
        if (a == 0) {
            if (b > 0) {
                return 0;
            } else {
                throw new Exception(); // illegal math
            }
        }
// MIN = -(MAX + 1)
// 1 / pow(a, MAX) / pow(a, 1)
        if (b >= 0) {
            return powHelper(a, b);
        } else if (b == Integer.MIN_VALUE) {
            return 1 / pow(a, Integer.MAX_VALUE) / a;
        } else {
            return 1 / pow(a, -b);
        }
    }

    // a != 0 && b >= 0
// b / 2
    private double powHelper(int a, int b) {
        if (b == 0) {
            return 1;
        }
        double half = powHelper(a, b / 2);
        if (b % 2 == 0) {
            return half * half;
        } else {
            return a * half * half;
        }
    }

}
