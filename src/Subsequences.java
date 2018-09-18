import java.util.ArrayList;
import java.util.List;

public class Subsequences {

    List<String> findAllSubsequences(String s) {
        List<String> results = new ArrayList<>();
        dfs(s, 0, new StringBuilder(), results);
        return results;
    }

    private void dfs(String s, int start, StringBuilder path, List<String> results) {
        for (int i = start; i < s.length(); i++) {
            path.append(s.charAt(i));
            results.add(path.toString());
            dfs(s, i+1, path, results);
            path.deleteCharAt(path.length()-1);
        }
    }

    public static void main(String[] args) {

    }
}
