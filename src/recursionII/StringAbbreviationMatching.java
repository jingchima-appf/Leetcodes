package recursionII;

public class StringAbbreviationMatching {
    public boolean match1(String input, String pattern) {
        // book
        // 4
        // b3
        // b2k

        // [0, i)  slow in input, substring matched
        // p in pattern

        int i = 0;
        int p = 0;
        char cur;
        while (p < pattern.length()) {
            cur = pattern.charAt(p);
            if (Character.isDigit(cur)) {
                int num = 0;
                while (p < pattern.length() && Character.isDigit(pattern.charAt(p))) {
                    num = num * 10 + pattern.charAt(p) - '0';
                    p++;
                }
                int end = i + num;
                while (i < input.length() && i < end) { // 并不需要循环呀！这里只是在累加之后看大小
                    i++;
                }

                // a b c
                // 2 3 4 5
                // cur = 3 => end = 5
                if (i < end) {
                    return false; // not matched
                }
            } else {
                if (cur == input.charAt(i)) {
                    i++;
                    p++;
                } else {
                    return false;
                }
            }
        }
        return i == input.length();
    }

    // 比较好的写法
    public boolean match(String input, String pattern) {
        // 所谓match，就是指两个人都到了最后也都一样
        int i = 0; // index for input [0, i) matched
        int j = 0; // index for pattern [0, j) matched

        while (i < input.length() && j < pattern.length()) {
            if (Character.isDigit(pattern.charAt(j))) {
                int num = 0;
                while ( j < pattern.length() && Character.isDigit(pattern.charAt(j)) ) {
                    num = num * 10 + pattern.charAt(j) - '0';
                    j++;
                }
                i += num;
            } else {
                if (input.charAt(i) != pattern.charAt(j)) {
                    return false;
                }
                i++;
                j++;
            }
        }
        return i == input.length() && j == pattern.length();
        // because j only moves 1 step at a time so j at most will be pattern.length()
        // but i can be larger than input.length()
    }

    public static void main(String[] args) {
        String input = "apple";
        String pattern = "a2l2";
        StringAbbreviationMatching instance = new StringAbbreviationMatching();
        System.out.println(instance.match(input, pattern));
    }
}
