package VMwareOA;

public class ShiftStrings {
    public String getShiftedString(String s,
                                   int leftShift,
                                   int rightShift) {
        leftShift %= s.length();
        rightShift %= s.length();
        char[] string = s.toCharArray();
        rightShift(string, rightShift - leftShift);
        return new String(string);
    }

    private void rightShift(char[] string, int shift) {
        if (shift < 0) {
            shift += string.length;
        }
        reverse(string, 0, string.length - shift - 1); // both inclusive
        reverse(string, string.length - shift, string.length - 1);
        reverse(string, 0, string.length - 1);
    }

    private void reverse(char[] string, int left, int right) {
        while (left < right) {
            swap(string, left, right);
            left++;
            right--;
        }
    }

    private void swap(char[] string, int i, int j) {
        char tmp = string[i];
        string[i] = string[j];
        string[j] = tmp;
    }

    public static void main(String[] args) {
        ShiftStrings instance = new ShiftStrings();
        System.out.println(instance.getShiftedString("12345", 4, 1));
        // 45123
    }
}
