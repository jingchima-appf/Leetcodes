package OAs.visa;

import java.util.HashMap;
import java.util.Map;

public class MostFrequentCharacters {

    // assume input is not null or empty
    public static char mostFrequentCharacter(String input) {
        // Map<Character, # Occurrence>
        // maxOccur: the # occurrences for res
        // max: the most frequent character
        // update map
        // compare with maxOccur, if > update
        Map<Character, Integer> map = new HashMap<>();
        Character max = null;
        int maxOccur = 0;
        for (int i = 0; i < input.length(); i++) {
            int num = map.getOrDefault(input.charAt(i), 0);
            map.put(input.charAt(i), num  + 1);
            if (num + 1 > maxOccur) {
                max = input.charAt(i);
                maxOccur = num + 1;
            }
        }
        return max;
    }
}
