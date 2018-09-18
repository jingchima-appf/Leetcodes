package OAs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AddBold {

    static class Interval implements Comparable<Interval> {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Interval interval) {
            return this.start - interval.start;
        }
    }

    public String addBold(String target, String[] keywords) {
        if (target == null || target.length() == 0 || keywords == null || keywords.length == 0) {
            return "";
        }
        List<Interval> results = new ArrayList<>();

        // find all substrings of target in keywords, and record interval (start, end)
        for (String keyword : keywords) {
            int start = target.indexOf(keyword);
            if (start != -1) {
                results.add(new Interval(start, start + keyword.length()));
            }
        }

        // sort and merge
        Collections.sort(results);
        List<Interval> merged = new ArrayList<>();
        Interval prev = null;
        for (Interval interval : results) {
            if (prev != null && prev.end >= interval.start) {
                prev.end = Math.max(prev.end, interval.end);
            } else {
                merged.add(interval);
            }
            prev = interval;
        }

        // return addBolded String
        StringBuilder sb = new StringBuilder();
        if (merged.isEmpty()) {
            return sb.toString();
        } else {
            Iterator<Interval> iterator = merged.iterator();
            Interval cur = iterator.next();
            for (int i = 0; i <= target.length(); i++) {
                if (i == cur.start) {
                    sb.append("<b>");
                } else if (i == cur.end) {
                    sb.append("</b>");
                    if (iterator.hasNext()) {
                        cur = iterator.next();
                    }
                }
                if (i < target.length()) {
                    sb.append(target.charAt(i));
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AddBold addBold = new AddBold();
        System.out.println(addBold.addBold("abcdefg", new String[] {"ab", "bcdhhh", "fg"}));
    }
}
