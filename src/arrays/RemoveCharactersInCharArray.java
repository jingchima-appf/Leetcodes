package arrays;

import java.util.HashSet;
import java.util.Set;

public class RemoveCharactersInCharArray {
    public int removeCharacter(char[] string, char[] targets) { // inclusive
        Set<Character> toRemove = new HashSet<>();
        for (char c: targets) {
            toRemove.add(c);
        }
        int fast = 0;
        int slow = 0;
        // [0, slow) => processed
        // [0, fast) => visited
        while (fast < string.length) {
            if (!toRemove.contains(string[fast])) {
                //swap(string, slow, fast); // no need to swap, we can directly assign the value
                string[slow] = string[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }


    public static void main(String[] args) {
        char[] string = "student".toCharArray();
        char[] targets = {'u', 'n'};
        RemoveCharactersInCharArray removeCharactersInCharArray = new RemoveCharactersInCharArray();
        int last = removeCharactersInCharArray.removeCharacter(string, targets);
        for (int i = 0; i < last; i++) {
            System.out.print(string[i] + " ");
        }
    }

}
