import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String args[]) throws Exception {
        // read the string filename
        String filename;
        filename = scan.nextLine();
        Map<String, Integer> map = new HashMap<>();
        while (scan.hasNext()) {
            String cur = getHostName(scan.nextLine());
            int num = map.getOrDefault(cur, 0);
            map.put(cur, num + 1);
            System.out.println(cur + " " + num);
        }

        FileWriter fileWriter = new FileWriter("records_" + filename);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            printWriter.print(entry.getKey() + " ");

            printWriter.println(entry.getValue());
        }
        printWriter.close();
    }

    private static String getHostName(String line) {
        int i = 0;
        while (i < line.length() && line.charAt(i) != ' ') {
            i++;
        }
        return line.substring(i);
    }
}