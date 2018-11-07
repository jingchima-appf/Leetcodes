package IXL.OA;

public class ReduceFractionNums {
    /*
    input is in the format “a/b+c/d”
    1 <= a,b,c,d <= 2000
    */
    public String reduceFractionSum(String input) {

        // a/b+c/d
        // lcm(b,d) / b * a + lcm(b,d) / d * c
        // lcm(b, d)
        long[] nums = getNumbers(input);

        // seems we can first reduce the input!
        long[] first = reduceFraction(nums[0], nums[1]);
        long[] second = reduceFraction(nums[2], nums[3]);
        long a = first[0];
        long b = first[1];
        long c = second[0];
        long d = second[1];

        long lcmOfBD = lcm(b, d);

        // {nominator, denominator}
        long[] reduced = reduceFraction(lcmOfBD / b * a + lcmOfBD / d * c, lcmOfBD);
        return reduced[0] + "/" + reduced[1];
    }

    // return the reduced {nominator, denominator}
    private long[] reduceFraction(long nominator, long denominator) {
        long gcd = gcd(denominator, nominator); // greatest common divisor of denominator and nominator
        denominator /= gcd;
        nominator /= gcd;
        return new long[] {nominator, denominator};
    }

    private long[] getNumbers(String input) {
        // a/b+c/d
        String[] twoNums = input.split("\\+");
        String[] first = twoNums[0].split("/");
        String[] second = twoNums[1].split("/");
        return new long[] {Long.parseLong(first[0]), Long.parseLong(first[1]), Long.parseLong(second[0]), Long.parseLong(second[1])};
    }

    // the lowest common multiple
    private long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    // calculate the greatest common divisor
    private long gcd(long a, long b) {
        // Euclidean Algorithm
        if (a < b) {
            return gcd(b, a);
        }
        // a >= b
        if (b == 0) {
            return a;
        }
        // a = q * b + r
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        ReduceFractionNums instance = new ReduceFractionNums();
        System.out.println(instance.reduceFractionSum("722/148+360/176"));
    }

}
