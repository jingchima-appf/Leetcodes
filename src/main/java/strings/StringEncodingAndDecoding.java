package strings;

public class StringEncodingAndDecoding {
    public String decompress(String input) {
        // return decompressI(input);
        return decompressII(input);
    }

    // Method I: using StringBuilder
    private String decompressI(String input) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < input.length()) {
            char cur = input.charAt(i); // assumption: original characters in 'a' to 'z'
            i++;
            int num = 0;
            while ( i < input.length() && Character.isDigit(input.charAt(i)) ) {
                num = num * 10 + (input.charAt(i) - '0');
                i++;
            }
            addToStringBuilder(sb, cur, num);
        }
        return sb.toString();
    }

    private void addToStringBuilder(StringBuilder sb, char cur, int num) {
        for (int i = 0; i < num; i++) {
            sb.append(cur);
        }
    }

    // Method II: using char array to do in-place operation
    private String decompressII(String input) {
        char[] string = getCharArray(input);
        int slow = 0; // [0, slow) processed
        int fast = string.length - input.length(); // fast: the index of character being processed
        while (fast < string.length) {
            char cur = string[fast];
            fast++; // forgot to increase fast
            int num = 0;
            while ( fast < string.length && Character.isDigit(string[fast]) ) {
                num = num * 10 + string[fast] - '0';
                fast++; // forgot to increase fast
            }
            // fill the characters
            for (int i = 0; i < num; i++) {
                string[slow] = cur;
                slow++;
            }
        }
        return new String(string, 0, slow);  // can contain spaces after slow
    }

    // return char array with enough space, and empty spaces are at the beginning.
    // note the returned array can contain more spaces
    private char[] getCharArray(String input) {
        int length = input.length();
        int i = 0;
        while (i < input.length()) {
            i++; // skip the char
            int num = 0;
            while ( i < input.length() && Character.isDigit(input.charAt(i)) ) {
                num = num * 10 + input.charAt(i) - '0';
                i++;
            }
            length += Math.max(0, num - 2); // at least store all characters in input
        }
        char[] string = new char[length];
        int newIdx = length - 1;
        for (i = input.length() - 1; i >= 0; i--) {
            string[newIdx] = input.charAt(i);
            newIdx--;
        }
        return string;
    }

    public static void main(String[] args) {
        StringEncodingAndDecoding instance = new StringEncodingAndDecoding();
        System.out.println(instance.decompress("h29"));
    }
}
