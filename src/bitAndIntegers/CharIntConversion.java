package bitAndIntegers;

public class CharIntConversion {

    // Problem 1: All Unique Characters II

    public boolean isAllUnique(String s) {

        return false;
    }

    // Problem 2: Parse string into number
    // parse from the higher to lower bits!!!
    public int parse(String s) {


        return 0;
    }

    // Problem 3: convert hex to decimal

    public static int convert(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'a' && c <= 'f') {
            return c - 'a' + 10;
        }
        if (c >= 'A' && c <= 'f') {
            return c - 'A' + 10;
        }
        return -1;
    }

    // Problem 4: implement atoi to convert a string to an integer
    // Assumption: the string is a valid integral number

    public static int atoi(String s) {

        // overflow => use long

        // negative number

        // starts from 0

        // leading and trailing spaces

        // 0x / 0b / e / .




        return 0;
    }


    // Problem 5: validate a string is numeric

    public static boolean validate(String s) {

        // .
        // e
        // leading and trailing space

        // step 1: possible characters : digit, 'e', 'E', '.', '+', '-', leading and trailing spaces.

        // step 2: spaces can only be at leading and trailing location

        // step 3: what's requirement for '+', '-':
        //

        // step 4: requirement for '.': before 'e' and only once

        // step 5: only one, between numbers

        return false;
    }

    // solution II: Regular Expression


    public static void main(String[] args) {
        int i = -65;
        System.out.println((char) i);
        int j = (int) (Math.pow(2, 16)) + i;
        System.out.println((char) j);
        System.out.println(convert('F'));
        i = 0;
        char[] characters = {'1', '2', (char) i, '5'};
        System.out.println(characters);
    }
}
