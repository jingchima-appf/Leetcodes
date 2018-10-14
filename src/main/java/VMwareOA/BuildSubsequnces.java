package VMwareOA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BuildSubsequnces {
    public List<String> allSubsequnces(String s) {
        List<String> results = new ArrayList<>();
        //dfs(s, 0, new StringBuilder(), results);
        dfs2(s, 0, new StringBuilder(), results);
        Collections.sort(results);
        return results;
    }

    private void dfs(String s, int index,
                     StringBuilder path, List<String> results) {
        if (index == s.length()) {
            if (path.length() != 0) { // don't forget to remove the empty subsequence
                results.add(path.toString());
            }
            return;
        }
        path.append(s.charAt(index));
        dfs(s, index + 1, path, results);
        path.deleteCharAt(path.length() - 1);
        index++;
        while (index < s.length() && s.charAt(index) == s.charAt(index-1)) {
            index++;
        }
        dfs(s, index, path, results);
    }

    private void dfs2(String s, int index, StringBuilder path, List<String> results) {
        for (int i = index; i < s.length(); i++) {
            if (i != index && s.charAt(i) == s.charAt(i-1)) {
                continue;
            }
            path.append(s.charAt(i));
            results.add(path.toString());
            dfs2(s, i + 1, path, results);
            path.deleteCharAt(path.length() - 1);
        }
    }


    public static void main(String[] args) {
        BuildSubsequnces instance = new BuildSubsequnces();
        System.out.println(instance.allSubsequnces("bba"));
    }

}
