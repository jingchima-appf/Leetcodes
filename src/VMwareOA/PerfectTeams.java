package VMwareOA;

import java.util.HashMap;
import java.util.Map;

public class PerfectTeams {
    public int differentTeams(String students) {
        if (students.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < students.length(); i++) {
            int num = map.getOrDefault(students.charAt(i), 0);
            map.put(students.charAt(i), num + 1);
        }
        int min = students.length() / 5;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            min = Math.min(min, entry.getValue());
        }
        return min;
    }

    public static void main(String[] args) {
        PerfectTeams instance = new PerfectTeams();
        System.out.println(instance.differentTeams("pcmbzpcmbz"));
    }
}
