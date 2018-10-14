package twoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CommonNumbersOfTwoSortedArrays {
    public List<Integer> common(List<Integer> A, List<Integer> B) {
        List<Integer> results = new ArrayList<>();
        if (A.size() == 0 || B.size() == 0) {
            return results;
        }
        Iterator<Integer> iteratorA = A.iterator();
        Iterator<Integer> iteratorB = B.iterator();
        Integer a = iteratorA.next();
        Integer b = iteratorB.next();
        while (a != null && b != null) {
            if (a.equals(b)) {
                results.add(a);
                if (iteratorA.hasNext()) {
                    a = iteratorA.next();
                } else {
                    a = null;
                }
                if (iteratorB.hasNext()) {
                    b = iteratorB.next();
                } else {
                    b = null;
                }

            } else if (a < b) {
                if (iteratorA.hasNext()) {
                    a = iteratorA.next();
                } else {
                    a = null;
                }
            } else {
                if (iteratorB.hasNext()) {
                    b = iteratorB.next();
                } else {
                    b = null;
                }
            }
        }
        return results;
    }

    public static void main(String[] args) {
        CommonNumbersOfTwoSortedArrays commonNumbersOfTwoSortedArrays = new CommonNumbersOfTwoSortedArrays();
        List<Integer> A = Arrays.asList(1,1,1,1,1);
        List<Integer> B = Arrays.asList(1,1,1,1);
        System.out.println(commonNumbersOfTwoSortedArrays.common(A, B));
    }
}
