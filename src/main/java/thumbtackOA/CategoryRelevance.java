package thumbtackOA;

import java.util.*;

public class CategoryRelevance {

    static class Rel {
        String category;
        double score;
        public Rel(String category, double score) {
            this.category = category;
            this.score = score;
        }
        public Rel(Rel rel) {
            this.category = rel.category;
            this.score = rel.score;
        }

        @Override
        public String toString() {
            return this.category;
        }
    }

    public String[][] categorySuggestions(String[] categories, String[] projects, int k) {

        List<List<String>> results = new ArrayList<>();
        Map<String, List<Rel>> graph = getGraphMap(categories, projects);

        Map<String, Rel> map = new HashMap<>();
        PriorityQueue<Rel> pq = new PriorityQueue<>(new Comparator<Rel>() {
            @Override
            public int compare(Rel rel1, Rel rel2) {
                if (rel1.score == rel2.score) {
                    return -rel1.category.compareTo(rel2.category);
                }
                if (rel1.score - rel2.score > 0) {
                    return 1;
                }
                return -1;
            }
        }); // min heap
        // pqEntries and pq should always store the same Rel object

        for (String project : projects) {
            List<Rel> relateds = graph.get(project);
            // if project is not in the categories
            if (relateds == null) {
                continue;
            }
            for (Rel neighbor: relateds) {
                Rel oldRel = map.get(neighbor.category);
                if (oldRel != null
                        && oldRel.score >= neighbor.score) {
                    continue;
                }
                Rel newRel = new Rel(neighbor);
                map.remove(newRel.category);
                pq.remove(oldRel);
                map.put(newRel.category, newRel);
                pq.offer(newRel);
                if (pq.size() > k) {
                    pq.poll();
                }
            }
            addToResults(results, pq, k); // 注意results 里面是倒叙的！
        }
        return asStringArray(results);
    }

    private void addToResults(List<List<String>> results,
                              PriorityQueue<Rel> pq, int k) {
        PriorityQueue<Rel> copy = new PriorityQueue<>(pq);
        List<String> categories = new ArrayList<>();
        while (!copy.isEmpty()) {
            categories.add(copy.poll().category);
        }
        results.add(categories);
    }

    private String[][] asStringArray(List<List<String>> list) {
        int m = list.size();
        String[][] results = new String[m][];
        for (int i = 0; i < m; i++) {
            results[i] = new String[list.get(i).size()];
            for (int j = 0; j < list.get(i).size(); j++) {
                results[i][list.get(i).size() - 1 - j] = list.get(i).get(j);
            }
        }
        return results;
    }

    // note some categories may not in the list !! so we also need to take in projects as a parameter
    private Map<String, List<Rel>> getGraphMap(String[] categories, String[] projects) {
        Map<String, List<Rel>> map = new HashMap<>();
        for (String project : projects) {
            List<Rel> list = map.get(project);
            if (list == null) {
                list = new ArrayList<>();
                map.put(project, list);
            }
            list.add(new Rel(project, 1)); // relevance with itself !! // forgot to put itself into the list!
        }
        for (String category: categories) {
            String[] info = category.split(",");
            if (!map.containsKey(info[0])) {
                map.put(info[0], new ArrayList<>());
            }
            if (!map.containsKey(info[1])) {
                map.put(info[1], new ArrayList<>());
            }
            List<Rel> list0 = map.get(info[0]);
            List<Rel> list1 = map.get(info[1]);
            list0.add(new Rel(info[1], Double.parseDouble(info[2])));
            list1.add(new Rel(info[0], Double.parseDouble(info[2])));
        }
        return map;
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
        CategoryRelevance instance = new CategoryRelevance();
//        String[] categories = {"House Painting,Interior Painting,0.9",
//                "Handyman,Massage Therapy,0.1",
//                "Handyman,House Painting,0.5",
//                "House Painting,House Cleaning,0.6",
//                "Furniture Assembly,Handyman,0.8",
//                "Furniture Assembly,Massage Therapy,0.1",
//                "Plumbing Drain Repair,Junk Removal,0.3"
//        };
//
//
//        String[] projects = {"House Painting", "Handyman"};
        String[] categories = {"1,2,1","2,4,0.3","4,3,0.2","3,1,0.5","1,4,0.1"};
        String[] projects = {"4", "4"};
        int k = 2;

        //[["House Painting", "Interior Painting", "House Cleaning"], ["Handyman", "House Painting", "Interior Painting"]].
        String[][] results = instance.categorySuggestions(categories, projects, k);
        display(results);
    }


}
