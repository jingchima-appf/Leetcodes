package strings;

public class StringReplace {

    public String replace(String input, String source, String target) {
        char[] string = getCharArray(input, source, target); // stirng always has enough space

        int slow = 0; // [0, slow) subarray processed
        int fast = string.length - input.length(); // fast: index being processed

        while (fast < string.length) {
            if (isStartOfSource(string, fast, source)) {
                fillArray(string, slow, target); // forgot should write from slow instead of fast
                fast += source.length();
                slow += target.length();
            } else {
                string[slow] = string[fast]; // forgot to copy the character !!!!!!
                slow++;
                fast++;
            }
        }
        return new String(string, 0, slow); // slow is exclusive
    }

    // Extra spaces are at the beginning.
    // time complexity: input.length() = n, source.length() = s, target.length() = t
    // O ( n * (s + t) )
    private char[] getCharArray(String input, String source, String target) {
        if (source.length() >= target.length()) {
            return input.toCharArray();
        }
        int count = 0;
        int idx = 0;
        while (idx < input.length()) {
            if (isStartOfSource(input.toCharArray(), idx, source)) {
                count++;
                idx += source.length();
            } else {
                idx++;
            }
        }
        char[] string = new char[ input.length() + ( target.length() - source.length() ) * count ];
        int newIdx = string.length - 1;
        for (int i = input.length() - 1; i >= 0; i--) {
            string[newIdx] = input.charAt(i);
            newIdx--;
        }
        return string;
    }

    private boolean isStartOfSource(char[] string, int start, String source) {
        if (start + source.length() > string.length) { // forgot to check length
            return false;
        }
        for (int i = 0; i < source.length(); i++) {
            if (string[i + start] != source.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private void fillArray(char[] string, int start, String target) {
        for (int i = 0; i < target.length(); i++) {
            string[start + i] = target.charAt(i);
        }
    }

    public static void main(String[] args) {
        StringReplace stringReplace = new StringReplace();
        System.out.println(stringReplace.replace("aaa", "aa", "bbb"));
    }
}

