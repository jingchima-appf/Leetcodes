package thumbtackOA;

import java.util.*;

public class OA {
    static class Rel {
        String name;
        double score;
        public Rel(String name, double score) {
            this.name = name;
            this.score = score;
        }
    }

    String[][] categorySuggestions(String[] categories, String[] projects, int k) {

        // step 1: build the graph
        Map<String, List<Rel>> graph = getGraph(categories);

        // step 2: maintain a priority queue which stores the Rel related to at least one of the
        // already created projects. No duplicates
        Map<String, Rel> map = new HashMap<>(); // name of categories so far  => (name + score) object
        PriorityQueue<Rel> pq = new PriorityQueue<>(11, new Comparator<Rel>() { // minHeap
            @Override
            public int compare(Rel rel1, Rel rel2) {
                if (rel1.score == rel2.score) {
                    return rel2.name.compareTo(rel1.name);
                }
                return rel1.score < rel2.score ? -1 : 1;
            }
        });

        String[][] results = new String[projects.length][];
        for (int i = 0; i < projects.length; i++) {
            String project = projects[i];
            List<Rel> relateds = graph.get(project);
            // for each project, see all the related categories.
            if (relateds == null) {
                continue;
            }
            for (Rel cur : relateds) {
                Rel old = map.get(cur.name); // old and cur share the same category name
                if (old == null) {
                    pq.offer(cur);
                    map.put(cur.name, cur);
                } else if (old.score < cur.score) {
                    pq.remove(old);
                    map.put(old.name, cur);
                    pq.offer(cur);
                }

                if (pq.size() > k) {
                    pq.poll();
                }
            }
            results[i] = getResult(pq);
        }
        return results;
    }

    Map<String, List<Rel>> getGraph(String[] categories) {
        Map<String, List<Rel>> map = new HashMap<>();
        for (String category: categories) {
            String[] pair = category.split(",");
            String cate1 = pair[0];
            String cate2 = pair[1];
            double score = Double.parseDouble(pair[2]);

            List<Rel> neighbor1s = map.get(cate1);
            if (neighbor1s == null) {
                neighbor1s = new ArrayList<>();
                neighbor1s.add(new Rel(cate1, 1)); // add itself
                map.put(cate1, neighbor1s);
            }
            List<Rel> neighbor2s = map.get(cate2);
            if (neighbor2s == null) {
                neighbor2s = new ArrayList<>();
                neighbor2s.add(new Rel(cate2, 1)); // add itself
                map.put(cate2, neighbor2s);
            }

            neighbor1s.add(new Rel(cate2, score));
            neighbor2s.add(new Rel(cate1, score));
        }

        return map;
    }

    String[] getResult(PriorityQueue<Rel> pq) {
        PriorityQueue<Rel> copy = new PriorityQueue<>(pq);
        String[] results = new String[copy.size()];
        int n = copy.size();
        while (!copy.isEmpty()) {
            n--;
            results[n] = copy.poll().name;
            System.out.print(results[n] + " ");
        }
        System.out.println();
        return results;
    }

    public static void display(String[][] res) {
        for (String[] strings : res) {
            System.out.print("[ ");
            for (String s: strings) {
                System.out.print(s);
                System.out.print(" ,");
            }
            System.out.println(" ]");
        }
    }

    public static void main(String[] args) {
        OA instance  = new OA();
                String[] categories = {"Electric and Wiring Repair,House Repair,0.8",
        "Electric and Wiring Repair,House Painting,0.2",
                "Electric and Wiring Repair,House Cleaning,0.25",
                "Junk Removal,Electric and Wiring Repair,0.1",
                "House Painting,Interior Painting,0.9",
                "Handyman,Massage Therapy,0.1",
                "Handyman,House Painting,0.5",
                "House Painting,House Cleaning,0.7",
                "Furniture Assembly,Handyman,0.8",
                "Furniture Assembly,Massage Therapy,0.1",
                "Plumbing Drain Repair,Junk Removal,0.3",
                "Dog Training,House Cleaning,0.0001"
                };


        String[] projects = {"Personal Training",
                "Electric and Wiring Repair",
                "Junk Removal",
                "House Cleaning"};

        int k = 10;

        display(instance.categorySuggestions(categories, projects, k));
    }

}
