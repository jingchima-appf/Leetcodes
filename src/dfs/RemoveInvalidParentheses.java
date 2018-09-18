package dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class RemoveInvalidParentheses {
    public List<String> removeInvalidParentheses(String s) {
        int validNum = getValidParenthesisNum(s);
        Set<String> resultSet = new HashSet<>();
        dfs(s, 0, 0, 0, validNum, new StringBuilder(), resultSet);
        List<String> results = new ArrayList<>();
        System.out.println(validNum);
        for (String result: resultSet) {
            results.add(result);
        }
        return results;
    }

    private void dfs(String s, int index, int left, int right, int validNum, StringBuilder path, Set<String> results) {
        if (index == s.length()) {
            if (left == right && right == validNum) {
                results.add(path.toString());
            }
            return;
        }

        char cur = s.charAt(index);
        if (cur != '(' && cur != ')') {
            path.append(cur);
            dfs(s, index + 1, left, right, validNum, path, results);
            path.deleteCharAt(path.length()-1);
        } else {
            dfs(s, index + 1, left, right, validNum, path, results); // not choose s.charAt(index)
            if (cur == '(' && left < validNum) {
                path.append(cur);
                dfs(s, index + 1, left + 1, right, validNum, path, results);
                path.deleteCharAt(path.length()-1);
            } else if (cur == ')' && right < left) {
                path.append(cur);
                dfs(s, index + 1, left, right + 1, validNum, path, results);
                path.deleteCharAt(path.length()-1);
            }
        }
    }

    private int getValidParenthesisNum(String s) {
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else if (right < left && s.charAt(i) == ')') {
                right++;
            }
        }
        return Math.min(left, right);
    }

    public static void main(String[] args) {
        RemoveInvalidParentheses removeInvalidParentheses = new RemoveInvalidParentheses();
        System.out.println(removeInvalidParentheses.removeInvalidParentheses("(a)())()"));
    }
}
