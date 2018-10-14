package thumbtackOA;

import java.util.*;

public class ProProjectMatching {
    static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Pro {
        Point location;
        int minPay;
        List<Interval> timeRanges;

        public Pro(Point location, int minPay, List<Interval> timeRanges) {
            this.location = location;
            this.minPay = minPay;
            this.timeRanges = timeRanges;
        }
        @Override
        public String toString() {
            return minPay + "";
        }
    }

    static class Interval {
        int start;
        int end; // start and end both in minutes
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    static class Project {
        Point location;
        int maxBudget;
        int size; // 0, 1, or 2
        List<Interval> intervals;

        public Project(Point location, int maxBudget, int size, List<Interval> intervals) {
            this.location = location;
            this.maxBudget = maxBudget;
            this.size = size;
            this.intervals = intervals;
        }

        @Override
        public String toString() {
            return maxBudget + "";
        }
    }

    public int userProMatching(int[][] projectsInfo,
                               String[][] projectsTimeRanges,
                               int[][] prosInfo,
                               String[][] prosTimeRanges) {
        List<Project> projects = getProjects(projectsInfo, projectsTimeRanges);
        Set<Pro> pros = getPros(prosInfo, prosTimeRanges);

        Map<Project, List<Pro>> map = getAvailablePros(projects, pros);
        System.out.println(map);
        return getMaxMatching(map, projects, pros);
    }

    private int getMaxMatching(Map<Project, List<Pro>> map, List<Project> projects, Set<Pro> pros) {
        // find the maximum number of projects that can be done;
        // using dfs
        int[] max = new int[1];
        dfs(projects, 0, 0, map, pros, max);
        return max[0];
    }


    // map.size() levels in total
    // each level represents the choice for the i-th project
    // we can choose use the i-th project, or not to use it
    private void dfs(List<Project> projects, int index, int count,
                     Map<Project, List<Pro>> map, Set<Pro> availablePros, int[] max) {
        if (index == projects.size()) {
            max[0] = Math.max(max[0], count);
            return;
        }
        Project cur = projects.get(index);
        for (Pro pro: map.get(cur)) {
            if (availablePros.remove(pro)) {
                dfs(projects, index + 1, count + 1, map, availablePros, max);
                availablePros.add(pro);
            }
        }
        dfs(projects, index + 1, count, map, availablePros, max);
    }

    private Map<Project, List<Pro>> getAvailablePros(List<Project> projects, Set<Pro> pros) {
        Map<Project, List<Pro>> map = new HashMap<>();
        for (Project project : projects) {
            map.put(project, getAvailablePros(project, pros));
        }

        return map;
    }

    private List<Pro> getAvailablePros(Project project, Set<Pro> pros) {
        List<Pro> results = new ArrayList<>();
        for (Pro pro: pros) {
            if (isMatched(project, pro)) {
                results.add(pro);
            }
        }
        return results;
    }

    private boolean isMatched(Project project, Pro pro) {
        if (project.maxBudget < pro.minPay) {
            return false;
        }
        int travelTime = Math.abs(pro.location.x - project.location.x) + Math.abs(pro.location.y - project.location.y);

        int timeToWork = (project.size + 1) * 2 * 60; // in minutes

        List<Interval> proIntervals = new ArrayList<>();
        // find the time range for travel
        int index = 0; // index for the travel time range
        for (Interval proInterval : pro.timeRanges) {
            if (travelTime + proInterval.start < proInterval.end) {
                proIntervals.add(new Interval(proInterval.start + travelTime, proInterval.end));
                break;
            }
            index++;
        }
        if (proIntervals.isEmpty()) { // no time range for travel
            return false;
        }

        // then copy all the remaining time intervals
        for (int i = index + 1; i < pro.timeRanges.size(); i++) {
            proIntervals.add(pro.timeRanges.get(i));
        }

        // the time ranges for the project
        List<Interval> projectIntervals = project.intervals;
        List<Interval> overlaps = overlappingIntervals(proIntervals, projectIntervals);

        for (Interval interval: overlaps) {
            timeToWork -= (interval.end - interval.start);
        }
        return timeToWork <= 0;
    }


    private List<Interval> overlappingIntervals(List<Interval> list1, List<Interval> list2) {

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

    private List<Project> getProjects(int[][] projectsInfo, String[][] projectsTimeRanges) {

//        static class Project {
//            Point location;
//            int maxBudget;
//            int size; // 0, 1, or 2
//            Interval interval;
//        }
        List<Project> results = new ArrayList<>();
        // project info: [0, 0, 5, 0]
        for (int i = 0; i < projectsInfo.length; i++) {
            int[] projectInfo = projectsInfo[i];
            Project project = new Project(new Point(projectInfo[0], projectInfo[1]), projectInfo[2], projectInfo[3],
                    getTimeIntervals(projectsTimeRanges[i]));
            results.add(project);
        }
        return results;
    }

    private Set<Pro> getPros(int[][] prosInfo, String[][] prosTimeRanges) {
        Set<Pro> set = new HashSet<>();
        for (int i = 0; i < prosInfo.length; i++) {
            int[] proInfo = prosInfo[i];
            // [5, 0, 7]
            Pro pro = new Pro(new Point(proInfo[0], proInfo[1]), proInfo[2], getTimeIntervals(prosTimeRanges[i]));
            set.add(pro);
        }
        return set;
    }

    private List<Interval> getTimeIntervals(String[] times) {
        // ["10:00-16:06"]
        List<Interval> results = new ArrayList<>();
        for (String time: times) {
            results.add(getInterval(time));
        }
        return results;
    }

    private Interval getInterval(String time) {
        String[] startAndEnd = time.split("-");
        String[] startTime = startAndEnd[0].split(":");
        int start = Integer.parseInt(startTime[0]) * 60 + Integer.parseInt(startTime[1]);
        String[] endTime = startAndEnd[1].split(":");
        int end = Integer.parseInt(endTime[0]) * 60 + Integer.parseInt(endTime[1]);
        return new Interval(start, end);
    }

    public static void main(String[] args) {
        ProProjectMatching instance = new ProProjectMatching();
        int[][] projectsInfo = {{0, 0, 5, 0},
                {1, 2, 8, 1},
                {-2, 1, 10, 2}};
        String[][] projectsTimeRanges = {{"08:00-10:30", "11:59-14:00", "20:30-23:05"},
                {"16:30-21:00"},
                {"00:00-23:59"}};
        int[][] prosInfo = {{5, 0, 7},
                {0, 1, 5},
                {-1, -1, 8}};
        String[][] prosTimeRanges = {{"10:00-16:06"},
                {"11:50-14:00", "20:30-23:07"},
                {"17:00-23:30"}};

        System.out.println(instance.userProMatching(projectsInfo, projectsTimeRanges, prosInfo, prosTimeRanges));
    }

}
