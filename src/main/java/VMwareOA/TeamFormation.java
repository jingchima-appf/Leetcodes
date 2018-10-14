package VMwareOA;

import java.util.ArrayList;
import java.util.List;

public class TeamFormation {
    public List<List<Integer>> findAllTeams(List<Integer> skills, int k, int min, int max) {
        // requirement 1: size of team >= k
        // requirement 2: each element in range [min, max]
        List<List<Integer>> results = new ArrayList<>();
        dfs(skills, 0, k, min, max, new ArrayList<>(), results);
        return results;
    }

    private void dfs(List<Integer> skills, int index,
                     int k, int min, int max,
                     List<Integer> path, List<List<Integer>> results) {

        // index => the index of players
        // k => the number of players we still have to select

        if (index == skills.size()) {
            if (k <= 0) {
                results.add(new ArrayList<>(path));
            }
            return;
        }
        if (k > skills.size() - index) {
            return;
        }
        if (skills.get(index) >= min && skills.get(index) <= max) {
            path.add(skills.get(index));
            dfs(skills, index + 1, k - 1, min, max, path, results);
            path.remove(path.size() - 1);
        }
        dfs(skills, index + 1, k, min, max, path, results);
    }

    public static void main(String[] args) {
        TeamFormation instance = new TeamFormation();
        List<Integer> skills = new ArrayList<>();
        skills.add(12);
        skills.add(4);
        skills.add(6);
        skills.add(13);
        skills.add(5);
        skills.add(10);
        System.out.println(instance.findAllTeams(skills, 3, 4, 10));
    }

}
