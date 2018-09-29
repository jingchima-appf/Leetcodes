package probabilities;

import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class Probability {

    // Question 1: Random Shuffling
    public void randomShuffling(int[] cards) {
        for (int i = 0; i < cards.length; i++) {

            // random number in [a, b): random(b-a) + a;
            int idx = ThreadLocalRandom.current().nextInt(i, cards.length);
            swap(cards, i, idx);
        }
    }

    private void swap(int[] numbers, int i, int j) {
        int tmp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = tmp;
    }

    // Question 2: Sampling
    private int counter = 0;
    private Integer sample = null;
    public void randomSampling(Iterator<Integer> nums) {
        counter++;
        int rand = ThreadLocalRandom.current().nextInt(counter);
        if (rand == 0) {
            sample = nums.next();
        }
    }

    // Follow-up 1: Random K Sampling
    private int k = 10;
    private Integer[] array = new Integer[k];
    public void randomKSampling(Iterator<Integer> nums) {
        int num = nums.next();
        counter++;
        if (counter <= k) {
            array[counter - 1] = num;
        } else {
            int idx = ThreadLocalRandom.current().nextInt(counter);
            if (idx < k) { // the new number is selected
                array[idx] = num; // probability = 1 / k
            }
        }
    }

    // Follow-up 2: Random Largest number
    public void randomLargest(Iterator<Integer> nums) {
        int num = nums.next();
        if (sample < num) {
            sample = num;
            counter = 1;
        } else if (sample == num) {
            counter++;
            int rand = ThreadLocalRandom.current().nextInt(counter);
            if (rand == 0) {
                sample = num;
            }
        }
    }

    // Follow-up 3: Random K Largest numbers
    // int counter: the number of largest numbers so far
    // Integer[k] array: the largest numbers selected
    public void randomKLargest(Iterator<Integer> nums) {
        int num = nums.next();
        if (counter == 0) {
            array[0] = num;
            counter++;
            return;
        }
        if (array[0] < num) {
            counter = 1;
            array[0] = num;
        } else if (array[0] == num) {
            counter++;
            if (counter <= k) {
                array[counter - 1] = num;
            } else {
                int idx = ThreadLocalRandom.current().nextInt(counter);
                if (idx < k) { // the new max number is selected
                    array[idx] = num;
                }
            }
        }
    }

    // Question 3: Random Number Generator
    // Problem 1: Large to small
    // Random m to random n where m >= n
    public int randomGenerator(int m, int n) {

        int max = m - (m % n);
        int rand = ThreadLocalRandom.current().nextInt(m);
        while (rand >= max) {
            rand = ThreadLocalRandom.current().nextInt(m);
        }
        return rand % n;
    }

    // Problem 2: small to large
    // Random small to random large where small < large
    // assumption, small and large are both > 1
    public int randomGeneratorSmallToLarge(int small, int large) {
        int numberOfBits = 1; // current number of bits for ceiling
        int ceiling = small; // power of small which must be greater or equal to large
        while (ceiling < large) { // not using division!!!
            ceiling *= small;
            numberOfBits++;
        }
        ceiling = ceiling - ceiling % large; // exclusive
        int rand;
        do {
            rand = 0; // don't forget to reset it
            for (int i = numberOfBits; i > 0; i--) {
                rand = rand * small + ThreadLocalRandom.current().nextInt(small);
            }
        } while (rand >= ceiling);
        return rand % large; // forgot to use modular
    }

    // Question 4: Given a stream, how to keep track of the median


    public static void main(String[] args) {
        Probability p = new Probability();
        int num = 300000;
        int m = 7;
        int n = 20;
        double[] occs = new double[n];
        for (int i = 0; i < num; i++) {
            occs[p.randomGeneratorSmallToLarge(m, n)]++;
        }
        for (int i = 0; i < n; i++) {
            occs[i] /= num;
            System.out.println(occs[i] + " ");
        }
        System.out.println();
    }

}
