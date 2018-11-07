package IXL.prepare;

import java.util.HashMap;
import java.util.Map;

public class RecursionInFraction {
    public String findDuplicateInFraction(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        int dividend = 1; // current dividend
        int index = 0; // index of to-add quotient
        StringBuilder sb = new StringBuilder();
        while (!map.containsKey(dividend)) {
            map.put(dividend, index);
            sb.append(dividend / n);
            index++;
            dividend = dividend % n * 10;
            if (dividend == 0) {
                return "0";
            }
        }
        return sb.substring(map.get(dividend), index);
    }

    static class Solution {
        // assumption: denominator is not zero
        public static String fractionToDecimal(int dividend, int divisor) {
            // if we see a same numberator, or numeratoro is 0, then we can stop
            long numerator = dividend;
            long denominator = divisor;
            String sign;
            if (numerator == 0
                    || numerator > 0 && denominator > 0
                    || numerator < 0 && denominator < 0) {
                sign = "";
            } else {
                sign = "-";
            }
            //if (numerator or denominaotr is MIN_VALUE)
            numerator = Math.abs(numerator);
            denominator = Math.abs(denominator);
            // Assumption: numerator >= 0, denominator > 0
            // Map<Numerator, Index>
            // termination condition: numerator == 0 || see this numerator before
            // if (numerator == 0) return sb
            // otherwise, add parentheses at index at numerator, and at then end

            long integerPart = numerator / denominator;
            numerator = numerator % denominator * 10;
            if (numerator == 0) {
                return sign + integerPart + "";
            }
            StringBuilder sb = new StringBuilder(); // sb for caculate the decimal parts
            Map<Long, Integer> map = new HashMap<>();
            int index = 0;
            // numerator < denominator
            while (numerator != 0 && !map.containsKey(numerator)) {
                map.put(numerator, index);
                sb.append(numerator / denominator);
                index++;
                numerator = numerator % denominator * 10;
            }
            if (numerator != 0) {
                sb.insert(map.get(numerator),"(");
                sb.append(")");
            }
            return sign + integerPart + "." + sb;
        }
    }

    public static void main(String[] args) {
        System.out.println(Solution.fractionToDecimal(1, 2));
    }

}
