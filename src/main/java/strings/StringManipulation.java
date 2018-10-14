package strings;

public class StringManipulation {

    /*
    Problem 1: reverse string using iterative and recursion
    */
    public void reverse(char[] string) {
        int left = 0;
        int right = string.length - 1;
        while (left < right) {
            swap(string, left, right);
            left++;
            right--;
        }
    }


    public void reverse(char[] string, int start, int end) {
        if (start >= end) {
            return;
        }
        swap(string, start, end);
        reverse(string, start+1, end-1);
    }


    /*
    Problem 2: Reverse Sentence e.g. I love Yahoo! => Yahoo! love I
     */

    public void reverseSentence(char[] string) {
        // [start, end) is a word => so the final position for end is string.length instead of string.length - 1 !!!!!!
        // step 1: reverse each word
        int start = 0;
        int end = 0;
        while (end <= string.length) {
            if (end == string.length || string[end] == ' ') {
                reverse(string, start, end-1); // assume there’s no space at the beginning.
                // if reverse can handle start >= end, then it's ok to have empty spaces at the beginning and end
                end++;
                start = end;
            } else {
                end++;
            }
        }
        reverse(string, 0, string.length - 1);
    }

    /*
    Problem 3: Circulate shifting right
     */
    void circulateShiftToRight(char[] string, int k) {
        k = k % string.length;
        int re = string.length - 1;
        int rs = string.length - k; // [rs, re) can out of boundary, so reverse() must be able to handle that situation!
        int ls = 0;
        int le = string.length - k - 1;
        reverse(string, rs, re);
        reverse(string, ls, le);
        reverse(string, ls, re);
    }


    // [left, right] inclusive
    private void swap(char[] string, int left, int right) {
        char c = string[left];
        string[left] = string[right];
        string[right] = c;
    }

    public static void display(char[] string) {
        for (char c: string) {
            System.out.print(c);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        StringManipulation stringManipulation = new StringManipulation();
        char[] string = "APPLE".toCharArray();

        /*
        Test for Problem 1
         */
        stringManipulation.reverse(string);
        display(string);
        stringManipulation.reverse(string, 0, string.length-1);
        display(string);

        /*
        Test for problem 2
         */
        string = "I Love Yahoo!".toCharArray();
        stringManipulation.reverseSentence(string);
        display(string);

        string = "  I Love Yahoo!   ".toCharArray();
        stringManipulation.reverseSentence(string);
        display(string);

        /*
        Test for problem 3
         */
        string = "12345".toCharArray();
        stringManipulation.circulateShiftToRight(string, 4);
        display(string);


    }
}
