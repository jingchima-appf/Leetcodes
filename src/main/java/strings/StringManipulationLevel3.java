package strings;

public class StringManipulationLevel3 {
    /*
    String Shuffling
     */

    /*
    @Assumption:
    length of string is even
    Left part only contains letter and right part only contains digits
    left and right parts are sorted ascendingly
    */
    public void stringShuffle(char[] string) {
        shuffleString(string, 0, string.length - 1);
    }

//    private void stringShuffle(char[] string, int left, int right) {
//        if (left >= right - 1) { // two numbers, should be the base case!!!!!!
//            return;
//        }
//        int size = right - left + 1;
//        int ls = left;
//        int re = right;
//        int mid = (left + right) / 2;
//        int lm = (left + mid) / 2;
//        int rm = (mid + 1 + right) / 2;
//
//        // [ls, lm] [lm + 1, mid] [mid + 1, rm] [rm + 1, re]
//        // size/4  size/2 - size/4   size/4    size/2 - size/4
//        switchParts(string, lm + 1, mid, rm);
//        // A  B  C  1  2  3
//        // ls lm mi    rm re
//        // A  B  1  2  C  3
//        stringShuffle(string, ls, ls + size / 2 - 1);
//        stringShuffle(string, ls + size / 2, re);
//    }

    private void shuffleString(char[] string, int start, int end) {
        if (end - start <= 1) {
            return;
        }
        // A     B      C      1     2     3
        // start lm     mid          rm         end
        int mid = (start + end) / 2;
        // left: [start, mid]
        // right: [mid + 1, end];
        int leftMid = (start + mid) / 2;
        int rightMid = (mid + 1 + end) / 2;
        swapTwoChunks(string, leftMid + 1, mid, rightMid);

        // A   B   1   2  C   3
        // st
        int firstQuarterSize = leftMid - start + 1;
        shuffleString(string, start, start + firstQuarterSize * 2 - 1);
        shuffleString(string, start + firstQuarterSize * 2, end);
    }

    // swap [left, mid] and [mid + 1, right]
    private void swapTwoChunks(char[] string, int left,
                               int mid, int right) {
        if (left >= right) {
            return;
        }
        reverse(string, left, mid);
        reverse(string, mid+1, right);
        reverse(string, left, right);
    }


    private void reverse(char[] string, int left, int right) {
        if (left >= right) {
            return;
        }
        while (left < right) {
            swap(string, left, right);
            left++;
            right--;
        }
    }


    /*
 @Assumption:
 string array always have enough space at the end
 len <= string.length
 @input: len is the length of actual String
 */
    public void encode(char[] string, int len) {

        // move backward the characters
        moveBackward(string, len);

        // use two pointers to copy result array

        // a b b c c c => a1b2c3
        // [0, slow) the processed locations
        // fast: the index of the character being processed

        int slow = 0;
        int fast = string.length - len;
        int fastStart = string.length - len;
        while (fast < string.length) {
            // fast = fastStart; // fastStart points to the start of a range
            while (fast < string.length
                    && string[fast] == string[fastStart]) {
                fast++;
            }
            string[slow] = string[fastStart];
            slow++;
            slow += fillNumber(string, slow, fast - fastStart); // fast - fastStart can be multi-digits!!
            fastStart = fast;
        }
        if (slow < string.length) {
            string[slow] = '\0';
        }
    }

    // len is the actual length of the String inside
    private void moveBackward(char[] string, int len) {
        int back = string.length - 1;
        int prev = len - 1;
        while (prev >= 0) {
            string[back] = string[prev];
            prev--;
            back--;
        }
    }

    /*
    @Assumption: string array always has enough space
    */
    private int fillNumber(char[] string, int start, int number) {
        String numberString = number + "";
        for (int i = 0; i < numberString.length(); i++) {
            string[i + start] = numberString.charAt(i);
        }
        return numberString.length();
    }

    /*
@Assumption:
input string is a series of (character + number)
input string always has enough space
*/
    public void decode(char[] string, int length) {

        // a1  b3  c2
        moveBackward(string, length);

        // [0, slow) processed
        // fast: processing
        int slow = 0;
        int fast = string.length - length; // fast starts from a letter
        while (fast < string.length) {
            int num = string[fast + 1] - '0'; // we assume input string is a series of (character + number) so fast + 1 is valid
            for (int i = 0; i < num; i++) {
                string[slow] = string[fast];
                slow++;
            }
            fast += 2;
        }
        if (slow < string.length) {
            string[slow] = '\0'; // marked as the end (exclusive);
        }
    }


    /*
@Assumption:
input string is a series of [character + (number)] if number = 1 then omits it.
input string always has enough space
*/
    public void decodeII(char[] string, int length) {

        // a1  b3  c2
        moveBackward(string, length);

        // [0, slow) processed
        // fast: processing
        int slow = 0;
        int fast = string.length - length; // fast starts from a letter
        while (fast < string.length) {
            int num;
            if (fast + 1 < string.length
                    && Character.isDigit(string[fast + 1])) {
                num = string[fast + 1] - '0';
            } else {
                num = 1;
            }
            for (int i = 0; i < num; i++) {
                string[slow] = string[fast];
                slow++;
            }
            fast++;
            if (num > 1) {
                fast++;
            }
        }
        if (slow < string.length) {
            string[slow] = '\0'; // marked as the end (exclusive);
        }
    }




    private void swap(char[] string, int i, int j) {
        char tmp = string[i];
        string[i] = string[j];
        string[j] = tmp;
    }

    public static void display(char[] string) {
        for (char c: string) {
            if (c == '\0') {
                System.out.println();
                return;
            }
            System.out.print(c);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        char[] string = "ABCD1234".toCharArray();
        StringManipulationLevel3 stringManipulationLevel3 = new StringManipulationLevel3();
        stringManipulationLevel3.stringShuffle(string);
        display(string);

        string = "ABC123".toCharArray();
        stringManipulationLevel3.stringShuffle(string);
        display(string);

        string = "abbbccc ".toCharArray(); // need a space to make sure that slow is always <= fast, so no useful contents will be overridden.
        stringManipulationLevel3.encode(string, 7);
        display(string); //

        stringManipulationLevel3.decode(string, 6);
        display(string);

        string = "ab2cd3            ".toCharArray();
        stringManipulationLevel3.decodeII(string, 6);
        display(string);
    }
}
