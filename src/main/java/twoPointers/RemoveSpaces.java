package twoPointers;

public class RemoveSpaces {
    public String removeSpaces(String input) {
        if (input == null) {
            return "";
        }
        int slow = 0;
        int fast = 0;
        char[] string = input.toCharArray();
        // step 1: remove all leading spaces
        while (fast < input.length() && string[fast] == ' ') {
            fast++;
        }

        // step 2: keep only one space for consecutive spaces
        while (fast < input.length()) {
            string[slow] = string[fast];
            slow++;
            fast++;
            if (string[fast - 1] == ' ') {
                while (fast < input.length() && string[fast] == ' ') {
                    fast++;
                }
            }
        }

        // step 3: remove the trailing spaces
        if (slow < input.length()) {
            while (slow > 0 && string[slow-1] == ' ') {
                slow--;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < slow; i++) {
            sb.append(string[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        RemoveSpaces removeSpaces = new RemoveSpaces();
        System.out.println(removeSpaces.removeSpaces("I   Love  Yahoo  "));
    }
}
