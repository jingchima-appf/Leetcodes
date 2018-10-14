import java.util.*;

public class Test<T> {
    static class Container {
        int num;
        public Container(int num) {
            this.num = num;
        }
    }

    public static <T> T getFirst(List<T> list) {
        List<Integer> l = new ArrayList<>();
        Object g = l.get(0);
        return list.get(0);
    }

    public static <T> boolean isGood(T t) {
        T good = (T) new Object();
        if (good instanceof Integer) {
            System.out.println("Integer");
        } else {
            System.out.println(good.getClass());
        }


        return true;
    }

    public static void main(String[] args) {
//        List<Integer> list1 = new ArrayList<>();
//        List<Integer> list2 = new ArrayList<>();
//        List<Integer> res = new ArrayList<>();
//
//        Iterator<Integer> iterator1 = list1.iterator();
//        Iterator<Integer> iterator2 = list2.iterator();
//
//        Integer i = 9;
//        isGood(i);

        Set<List<Integer>> set = new HashSet<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        System.out.println(list1.hashCode());
        System.out.println(list2.hashCode());
        set.add(list1);
        System.out.println(set.add(list2));

        PriorityQueue<Container> pq = new PriorityQueue<>(new Comparator<Container>() {
            @Override
            public int compare(Container o1, Container o2) {
                return o1.num < o2.num ? -1 : 1;
            }
        });

        pq.offer(new Container(1));
        pq.offer(new Container(1));
        System.out.println(pq);

    }

}
