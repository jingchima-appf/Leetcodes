package generics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Generic {

    public static <E extends Comparable<? super E>> void sort(List<E> list) {

    }

    public static <E extends Comparable<E>> void sort2(List<E> list) {

    }

    public static void main(String[] args) {
        List<Employee> eList = new ArrayList<>();
        List<Manager> mList = new ArrayList<>();
        sort(eList);
        sort(mList);

        sort2(eList);
        // sort2(mList);
    }
}

class Employee implements Comparable<Employee> {

    @Override
    public int compareTo(Employee e) {
        return 1;
    }
}

class Manager extends Employee {

}
