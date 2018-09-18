package dfs;

import java.util.ArrayList;
import java.util.List;

class Parentheses {
    private final char removed = ' ';

    public List<String> removeInvalidParentheses(String s) {
        List<String> results = new ArrayList<>();
        dfs(s.toCharArray(), 0, new char[] {'(', ')'}, -1, results);
        return results;
    }

    private void dfs(char[] string, int idx, char[] parentheses, int lastRemoved, List<String> results) {
        int open = 0;
        while (idx < string.length && open >= 0) {
            if (string[idx] == parentheses[0]) {
                open++;
            } else if (string[idx] == parentheses[1]) {
                open--;
            }
            idx++;
        }
        if (open < 0) {
            idx--;
        }
        if (idx < string.length) {
            for (int i = lastRemoved + 1; i <= idx; i++) {
                if (string[i] != parentheses[1]) {
                    continue;
                }
                char tmp = string[i];
                string[i] = removed;
                dfs(string, idx+1, parentheses, i, results);
                string[i] = tmp;
                while (i + 1 <= idx && string[i+1] == string[i]) {
                    i++;
                }
            }
        } else {
            results.add(getString(string));
        }
    }

    private String getString(char[] string) {
        StringBuilder sb = new StringBuilder();
        for (char c: string) {
            if (c != removed) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Parentheses parentheses = new Parentheses();
        System.out.println(parentheses.removeInvalidParentheses("()))"));
    }
}