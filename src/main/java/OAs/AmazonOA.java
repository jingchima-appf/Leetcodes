package OAs;

import java.util.List;

public class AmazonOA {

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    List<List<Integer>> nearestXsteakHouses(int totalSteakhouses, List<List<Integer>> allLocations,  int numSteakhouses) {
        if (totalSteakhouses < numSteakhouses) {
            return allLocations;
        }

        return null;
    }



    public static void main(String[] args) {
        AmazonOA amazonOA = new AmazonOA();

    }
}
