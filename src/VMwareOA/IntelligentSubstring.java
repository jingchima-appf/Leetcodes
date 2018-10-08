package VMwareOA;

public class IntelligentSubstring {
    public int getSpecialSubstring(String s, int k, String charValue) {
        // sliding window: [left, right)
        // int count: the number of normal characters for the substring
        // int maxLen;
        int left = 0;
        int right = 0;
        int count = 0;
        int maxLen = 0;
        while (right < s.length()) {
            if (isNormal(s.charAt(right), charValue)) {
                count++;
            }
            right++;
            if (count > k) {
                while (!isNormal(s.charAt(left), charValue)) {
                    left++;
                }
                left++;
                count--; // forgot to decrease count!
            }
            maxLen = Math.max(maxLen, right - left);
        }
        return maxLen;
    }

    private boolean isNormal(char c, String charValue) {
        return charValue.charAt(c - 'a') == '0';
    }

    public static void main(String[] args) {
        IntelligentSubstring instance = new IntelligentSubstring();
        System.out.println(instance.getSpecialSubstring("acdefgh", 1, "1100111011111111111111111"));
    }

}
