package strings;

import java.util.ArrayList;

public class StringManipulationLevel2 {

    /*
    @Assumption: The input char array has enough space, and the content is at the beginning.
     */

    public void replaceString(char[] string, int length, String target, String newString) {
        int slow = string.length - 1; // (slow, string.length)
        int end = length - 1; // (start, end]
        while (end >= 0) {
            if (isEndOfTargetString(string, end, target)) {
                for (int i = newString.length() - 1; i >= 0; i--) {
                    string[slow] = newString.charAt(i);
                    slow--;
                }
                end -= target.length(); // notice end should only move target.length() instead of newString.length()
            } else {
                string[slow] = string[end];
                slow--;
                end--;
            }
        }
        int newLength = string.length - 1 - slow;
        // move forward
        for (int i = 0; i < newLength; i++) {
            string[i] = string[slow + 1 + i];
        }
        if (newLength < string.length) {
            string[newLength] = '\0';
        }
    }

    private void replaceAll(char[] string, int length, String target, String newString) {

        // move backward the string to the end
        int idx = length - 1;
        int newIdx = string.length - 1;
        while (idx >= 0) {
            string[newIdx] = string[idx];
            newIdx--;
            idx--;
        }

        int slow = 0; // [0, slow)
        int fast = newIdx + 1; // the index of character being processed [0, fast) processed

        // student
        // stxxxnt
        while (fast < string.length) { // must process until reaching end! CAN'T use fast < string.length - target.length() because some characters won't be copied!!
            if (isStartOfTargetString(string, fast, target)) {
                // [fast, end);
                for (int i = 0; i < newString.length(); i++) {
                    string[slow] = newString.charAt(i);
                    slow++;
                }
                fast += target.length();
            } else {
                string[slow] = string[fast];
                slow++;
                fast++;
            }
        }

        // set the end of the string
        if (slow < string.length) {
            string[slow] = '\0';
        }
    }

    private boolean isStartOfTargetString(char[] string, int start, String target) {
        if (start + target.length() > string.length) {
            return false;
        }
        for (int i = 0; i < target.length(); i++) {
            if (string[start] != target.charAt(i)) {
                return false;
            }
            start++;
        }
        return true;
    }

    private boolean isEndOfTargetString(char[] string, int end, String target) {
        if (end + 1 < target.length()) {
            return false;
        }
        for (int i = target.length() - 1; i >= 0; i--) {
            if (string[end] != target.charAt(i)) {
                return false;
            }
            end--;
        }
        return true;
    }

    public static void display(char[] string) {
        int i = 0;
        while (i < string.length && string[i] != '\0') {
            System.out.print(string[i]);
            i++;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        StringManipulationLevel2 stringManipulationLevel2 = new StringManipulationLevel2();
        char[] string = "student       ".toCharArray();
        int length  = "student".length();
        stringManipulationLevel2.replaceString(string, length, "ude", "xxxxx");
        display(string);

        string = "dendend".toCharArray();
        length = "dendend".length();
        stringManipulationLevel2.replaceString(string, length, "dend", "good");
        display(string);
        // dendend dend => good

        string = "dendend          ".toCharArray();
        stringManipulationLevel2.replaceAll(string, length, "dend", "good!!!!!!!!");
        display(string);
    }

}
