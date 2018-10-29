package forusallOA;

public class CorrectParentheses {
    public static String correctParentheses(String input) {
        int open = 0;
        int i = 0; // the index to visit
        int addToFront = 0;
        int addToEnd = 0;
        while (i < input.length()) {
            char cur = input.charAt(i);
            // open >= 0
            if (cur == '(') {
                open++;
                i++;
            } else {
                if (open == 0) {
                    while (i < input.length()
                            && input.charAt(i) == ')') {
                        i++;
                        open--;
                    }
                    addToFront -= open;
                    open = 0;
                } else {
                    open--;
                    i++;
                }
            }
        }
        addToEnd = open;
        StringBuilder start = new StringBuilder();
        while (addToFront > 0) {
            start.append('(');
            addToFront--;
        }
        StringBuilder end = new StringBuilder();
        while (addToEnd > 0) {
            end.append(')');
            addToEnd--;
        }
        return start + input + end;
    }

}
