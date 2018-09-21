package bitAndIntegers;

public class BitOperations {
    // Question 1: Given a number num, test if num’s k-th bit is one

    public boolean kthBitIsOne(int num, int k) { // k from right to left
        if (k > 31 || k < 0) {
            return false;
        }
        return ( (num >> k) & 0x01 ) == 1;
    }

    // Question 2: set the kth bit (from right to left) to 0

    /*
    kth bit, from left to right
     */
    public int setBit(int num, int k) {
        // 0010 -> 1010
        return num | (1 << k);
    }

    public int getBit(int num, int index) {
        return (num >> index) & 0x01;
    }


    // Question 3: Determine if a number is a power of 2

    // Method I: num > 0 && only 1 '1' in binary <=> power of 2
    // time complexity: assume input num has m bits, then O(m)
    public boolean isPowerOfTwoI(int num) {
        if (num <= 0) {
            return false;
        }
        int count = 0;
        for (int i = 0; i < 32; i++) {
            count += ( (num >> i) & 0x01 );
        }
        return count == 1;
    }

    // Method II: bit operation
    // 0 1 0 0 0
    // 0 0 1 1 1
    public boolean isPowerOfTwoII(int num) {
        return num > 0 && (num & (num - 1)) == 0;
    }

    // Question 4: How to determine the number of different bits
    public int numberOfDifferentBits(int num1, int num2) {
        int xor = num1 ^ num2;
        int count = 0;
        for (int i = 0; i < 32; i++) {
            count += ( (xor >> i) & 0x01);
        }
        return count;
    }

    // Follow-up 2: count the number of bits in a number

    public int bitCount(int num) {
        if (num == 0) {
            return 0;
        }
        if (num == 1) { // base case
            return 1;
        }
        return 1 + bitCount(num >>> 1);
    }


    // Question 5: Determine if a string only contains unique characters

    /*
    @Assumption: only ascii
    */
    // 256 bit, i-th bit represents i-th character in ASCII appears if it’s 1, doesn’t otherwise.
    // time complexity: O(256)
    // space complexity: O(256 / 32 * 4 bytes)
    public boolean onlyUnqiueCharacter(String s) {
        int[] containers = new int[256 / 32];
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            int n = ( (int) cur ) / 32; // n-th bucket. Should divide bucket size instead of containers.length!
            int k = ( (int) cur ) % 32; // k-th bit in the bucket. Note k starts from right to left
            if ( ((containers[n] >> k) & 0x01) == 1) {
                return false;
            } else {
                containers[n] |= (1 << k);
            }
        }
        return true;
    }

    // Question 6: reverse all bits in the number

    public int reverseBits(int num) { // must have return value because input is of primitive type
        // 0 1 1 0 0
        // l       r
        int left = 31; // when talking about k-th bit in a number, start from right to left
        int right = 0;
        while (left > right) {
            num = swapBits(num, left, right);
            left--;
            right++;
        }
        return num;
    }

    private int swapBits(int num, int i, int j) {
        if (getBit(num, i) == getBit(num, j)) {
            return num;
        }
        // 0 0 1 1
        // i     j
        // 1 0 0 1 xor
        // 1 0 1 0

        return num ^ ( (1 << i) | (1 << j) ); //相当于只对i, j两位取反，其他位不影响，所以用这个异或操作
    }

    // Question 7:
    public String toHex(int num) {
        // 23 -> 16 + 7 -> 0x17
        StringBuilder result = new StringBuilder();
        while (num > 0) {
            int cur = num % 16;
            if (cur < 10) {
                result.append(cur);
            } else {
                result.append( (char) (cur - 10 + 'A') );
            }
            num = num / 16;
        }
        result.append('x').append('0');
        return result.reverse().toString();
    }

    public String toHexII(int num) {
        // 0000 1010 -> 0A
        StringBuilder sb = new StringBuilder().append("0x");
        for (int i = 28; i >= 0; i -= 4) {
            int cur = (num >> i) & 0x0F;
            if (cur < 10) {
                if (i == 0 || cur > 0) {
                    sb.append(cur);
                }
            } else {
                sb.append((char) (cur - 10 + 'A'));
            }
        }
        return sb.toString();
    }




    public static void main(String[] args) {
        BitOperations bitOperations = new BitOperations();
        // Question 1
        // 10 -> 1 0 1 0
        assert !bitOperations.kthBitIsOne(10, 0);

        // Question 2
        // test setBit
        assert bitOperations.setBit(10, 0) == 11;

        // Question 3
        assert !bitOperations.isPowerOfTwoI(10);
        assert bitOperations.isPowerOfTwoI(128);
        assert !bitOperations.isPowerOfTwoI(Integer.MIN_VALUE);

        assert !bitOperations.isPowerOfTwoII(10);
        assert bitOperations.isPowerOfTwoII(128);
        assert !bitOperations.isPowerOfTwoII(Integer.MIN_VALUE);

        // Question 4
        assert bitOperations.numberOfDifferentBits(10, 10) == 0;
        assert bitOperations.numberOfDifferentBits(11, 10) == 1;
        // 11: 001011
        // 20: 100000
        assert bitOperations.numberOfDifferentBits(11, 32) == 4;
        assert bitOperations.numberOfDifferentBits(-1, Integer.MAX_VALUE) == 1;

        // Follow-up 2:
        assert bitOperations.bitCount(65) == 7;
        assert bitOperations.bitCount(183) == 8;
        assert bitOperations.bitCount(-1) == 32;

        // Question 5:
        assert !bitOperations.onlyUnqiueCharacter("goood");
        assert bitOperations.onlyUnqiueCharacter("abcdefg");

        // Question 6:
        assert bitOperations.reverseBits(0b00001) == Integer.MIN_VALUE;
        assert bitOperations.reverseBits(0b00010) == 0x40000000;

        // Question 7:
        assert bitOperations.toHex(15).equals("0xF");
        assert bitOperations.toHex(33).equals("0x21");

        assert bitOperations.toHexII(15).equals("0xF");
        assert bitOperations.toHexII(33).equals("0x21");

    }
}
