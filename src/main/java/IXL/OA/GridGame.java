package IXL.OA;

public class GridGame {
    /*
Key: find the largest overlapped rectangle whose left-bottom corner is (1,1)

Data Structure:
int[] topRight: coordinate of right-top corner.

Algorithm:
Init:
topRight {MAX, MAX}

for each step (r,c):
	choose topRight[i] to be min { topRight[i], r (or c) }
end
*/

    public long getMaxCount(String[] steps) {
        Long[] topRight = {null, null};
        for (String step: steps) {
            long[] rc = getRC(step);
            if (topRight[0] == null) {
                topRight[0] = rc[0];
                topRight[1] = rc[1];
            } else {
                topRight[0] = Math.min(topRight[0], rc[0]);
                topRight[1] = Math.min(topRight[1], rc[1]);
            }
        }
        return topRight[0] * topRight[1];
    }

    private long[] getRC(String step) {
        String[] rc = step.split(" ");
        return new long[] {Long.parseLong(rc[0]), Long.parseLong(rc[1])};
    }

    public static void main(String[] args) {
        GridGame instance = new GridGame();
        System.out.println(instance.getMaxCount(new String[] {"10 10", "5 15", "3 8"}));
    }

}
