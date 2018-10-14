package intervals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Interval {
    private int start;
    private int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[" + start + " ," + end + "]";
    }

    public static List<Interval> overlappingIntervals(List<Interval> list1, List<Interval> list2) {

        // assumption: the intervals have been sorted by the start time
        // and in each list the time intervals don't overlap,
        // and each list contains at least one interval

        // [1,2]  [4, 5]  [7, 9]
        // [3, 5]  [6, 8]
        // [4, 5]
        List<Interval> overlappings = new ArrayList<>();
        Iterator<Interval> iterator1 = list1.iterator();
        Interval interval1 = iterator1.next(); // the previous interval in list 1
        Iterator<Interval> iterator2 = list2.iterator();
        Interval interval2 = iterator2.next();
        while (iterator1.hasNext() || iterator2.hasNext()) {
            int start = Math.max(interval1.start, interval2.start);
            int end = Math.min(interval1.end, interval2.end);
            if (start < end) {
                overlappings.add(new Interval(start, end));
            }
            if (!iterator2.hasNext() || interval1.end <= interval2.end) {
                interval1 = iterator1.next();
            } else if (!iterator1.hasNext() || interval2.end <= interval1.end ){
                interval2 = iterator2.next();
            }
        }
        int start = Math.max(interval1.start, interval2.start);
        int end = Math.min(interval1.end, interval2.end);
        if (start < end) {
            overlappings.add(new Interval(start, end));
        }
        return overlappings;
    }

    public static List<Interval> generateIntervals(int[] starts, int[] ends) {
        if (starts.length != ends.length) {
            return new ArrayList<>();
        }
        List<Interval> results = new ArrayList<>();
        for (int i = 0; i < starts.length; i++) {
            results.add(new Interval(starts[i], ends[i]));
        }
        return results;
    }

    public static void main(String[] args) {
        // [1, 2]  [5, 8]  [10, 14]
        // [3, 4]  [4,7] [9, 15]
        int[] starts = {1, 5, 10};
        int[] ends = {2, 8, 14};
        List<Interval> list1 = generateIntervals(starts, ends);
        starts = new int[] {3, 4, 9};
        ends = new int[] {4, 7, 15};

        List<Interval> list2 = generateIntervals(starts, ends);
        System.out.println(list1);
        System.out.println(list2);
        System.out.println(overlappingIntervals(list1, list2));
    }

}
