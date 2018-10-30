package thumbtackPrepare;

import java.util.Comparator;
import java.util.PriorityQueue;

public class ClosestPalindrome {
    public String nearestPalindromic(String n) {

        PriorityQueue<String> candidates = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                long diff1 = getDiff(s1, n);
                long diff2 = getDiff(s2, n);
                if (diff1 == diff2) {
                    if (s1.length() < s2.length()) {
                        return -1;
                    } else if (s1.length() > s2.length()) {
                        return 1;
                    } else {
                        return s1.compareTo(s2);
                    }
                } else if (diff1 < diff2) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });

        generateCandidates(candidates, n);
        return candidates.peek();
    }

    private void generateCandidates(PriorityQueue<String> candidates, String s) {
        StringBuilder allNines = new StringBuilder();
        for (int i = 0; i < s.length() - 1; i++) {
            allNines.append(9);
        }
        if (allNines.length() != 0) {
            candidates.offer(allNines.toString());
        }
        StringBuilder OneZeros = new StringBuilder();
        for (int i = 0; i <= s.length(); i++) {
            if (i == 0 || i == s.length()) {
                OneZeros.append(1);
            } else {
                OneZeros.append(0);
            }
        }
        candidates.offer(OneZeros.toString());
        int index = (s.length() - 1) / 2;
        StringBuilder replaced = new StringBuilder(s);
        String palindrome = getPalindrome(replaced);
        if (!palindrome.equals(s)) {
            candidates.offer(getPalindrome(replaced));
        }
        if (s.charAt(index) != '9') {
            replaced.setCharAt(index, (char) (s.charAt(index) + 1));
            candidates.offer(getPalindrome(replaced));
        }
        if (s.charAt(index) != '0') {
            replaced.setCharAt(index, (char) (s.charAt(index) - 1));
            candidates.offer(getPalindrome(replaced));
        }
    }

    private String getPalindrome(StringBuilder s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            s.setCharAt(j, s.charAt(i));
            i++;
            j--;
        }
        return s.toString();
    }


    private long getDiff(String s, String target) {
        long diff = 0;
        int i = s.length() - 1;
        int j = target.length() - 1;
        long scale = 1; // scale can overflow
        while (i >= 0 || j >= 0) {
            char sChar = i >= 0 ? s.charAt(i) : '0';
            char targetChar = j >= 0 ? target.charAt(j) : '0';
            diff += (sChar - targetChar) * scale;
            scale *= 10;
            i--;
            j--;
        }
        return Math.abs(diff);
    }
}
