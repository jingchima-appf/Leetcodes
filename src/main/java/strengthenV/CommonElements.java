package strengthenV;

import java.util.*;

public class CommonElements {

    // Question 1
    // find the common elements in two arrays
    public List<Integer> commonElements(int[] array1, int[] array2) {
        return case1(array1, array2);
    }

    // case 1: two arrays are sorted in ascending order, and have similar length
    List<Integer> case1(int[] array1, int[] array2) {
        // i: array1
        // j: array2
        /*
        compare array1[i] and array2[j]
            if array1[i] < array2[j]
                then i++
            else if array[i] == array2[j]
                add array[i] into results
                skip all the same elements in array1 and array2
            else
                then j++

         */
        // time: O(m + n)
        // space: O(1)

        int i = 0;
        int j = 0;
        List<Integer> results = new ArrayList<>();
        while (i < array1.length && j < array2.length) {
            if (array1[i] < array2[j]) {
                i++;
            } else if (array1[i] == array2[j]) {
                results.add(array1[i]);
                i++;
                j++;
                while (i < array1.length && array1[i] == array1[i-1]) {
                    i++;
                }
                while (j < array2.length && array2[j] == array2[j-1]) {
                    j++;
                }
            } else {
                j++;
            }
        }
        return results;
    }



    // case 2: if the size of two arrays are very different
    /*
    for each element in short array,
        use binary search to find if there's that element in the long array
    endfor
     */

    List<Integer> case2(int[] array1, int[] array2) {
        if (array1.length > array2.length) {
            return case2(array2, array1);
        }
        List<Integer> results = new ArrayList<>();
        for (int num : array1) {
            if (binarySearch(array2, num)) {
                results.add(num);
            }
        }
        return results;
    }

    private boolean binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] == target) {
                return true;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }


    // case 3: if the input array is unsorted, and we want to optimize time

    List<Integer> case3(int[] array1, int[] array2) {
        Set<Integer> set = new HashSet<>();
        for (int num : array1) {
            set.add(num);
        }
        List<Integer> results = new ArrayList<>();
        for (int num : array2) {
            if (set.remove(num)) {
                results.add(num);
            }
        }
        return results;
    }


    // Question 2: find common elements in k sorted arrays with similar lengths

    // arrays.length >= 2
    // each array contains at least one element
    public List<Integer> commonElements(int[][] arrays) {
        List<Integer> res = new ArrayList<>();
        for (int num : arrays[0]) {
            res.add(num);
        }
        for (int i = 1; i < arrays.length; i++) {
            commonElementsInTwoArray(res, arrays[i]);
        }
        return res;
    }

    void commonElementsInTwoArray(List<Integer> results, int[] nums) {
        Iterator<Integer> iterator = results.iterator();
        int num1 = iterator.next();
        int j = 0; // index for nums
        while (iterator.hasNext()) {
            if (j >= nums.length || num1 < nums[j]) {
                iterator.remove();
                num1 = iterator.next();
            } else if (num1 == nums[j]) {
                while (iterator.hasNext()) {
                    int tmp = iterator.next();
                    if (tmp == num1) {
                        iterator.remove();
                    } else {
                        num1 = tmp;
                        break;
                    }
                }
                j++;
                while (j < nums.length && nums[j] == nums[j-1]) {
                    j++;
                }
            } else {
                j++;
            }
        }
        while (j < nums.length) {
            if (num1 == nums[j]) {
                return;
            }
            j++;
        }
        iterator.remove();
    }

    /*

     */

    List<Integer> iterative(int[][] arrays) {
        return null;
    }

}
