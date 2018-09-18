package dfs;

import java.util.ArrayList;
import java.util.List;

public class FindSubsets {

    public List<String> subSets(String set) {
        if (set == null) {
            return new ArrayList<>();
        }
        List<String> results = new ArrayList<>();
        dfs(set, 0, new StringBuilder(), results);
        return results;
    }

    private void dfs(String set, int index, StringBuilder path, List<String> results) {
        if (index == set.length()) {
            results.add(path.toString());
            return;
        }
        path.append(set.charAt(index));
        dfs(set, index+1, path, results);
        path.deleteCharAt(path.length()-1);
        dfs(set, index+1, path, results);
    }

    // subproblems
    // recursion rule
    // base case

    public List<String> subSetsHelper(String set, int end) {
        if (end < 0) {
            List<String> results = new ArrayList<>();
            results.add("");
            return results;
        }
        List<String> sub_results = subSetsHelper(set, end-1);
        List<String> results = new ArrayList<>();
        for (String subRes : sub_results) {
            results.add(subRes);
            results.add(subRes + set.charAt(end));
        }
        return results;
    }

    public static void main(String[] args) {
        FindSubsets findSubsets = new FindSubsets();
        System.out.println(findSubsets.subSets("123"));
        System.out.println(findSubsets.subSetsHelper("123", 2));
    }
}
