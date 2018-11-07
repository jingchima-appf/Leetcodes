package IXL.OA;

public class AnagramDifference {
    public int[] numOfBits(String[] array1, String[] array2) {
        final int characterNum = 26;
        int[] counts = new int[characterNum];
        int[] results = new int[array1.length];
        String string1;
        String string2;

        for (int i = 0; i < array1.length; i++) {
            string1 = array1[i];
            string2 = array2[i];
            if (string1.length() != string2.length()) {
                results[i] = -1;
                continue;
            }
            fillCounts(string1, counts);
            results[i] = getBits(string2, counts);
        }
        return results;

    }

    // fill the counts array with # occur for each character
    private void fillCounts(String s, int[] counts) {
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
        }
    }


    // return the minimum number of bits to change
// s.length() == sum of (counts)
    private int getBits(String s, int[] counts) {
        int bitsToMatch = s.length();
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            if (counts[idx] > 0) {
                counts[idx]--;
                bitsToMatch--;
            }
        }
        return bitsToMatch;
    }

    private static void display(int[] array) {
        for (int s: array) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        AnagramDifference instance = new AnagramDifference();
        String[] array1 = {"good"};
        String[] array2 = {"dbsa"};
        display(instance.numOfBits(array1, array2));
    }

}
