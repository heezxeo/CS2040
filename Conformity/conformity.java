import java.io.*;
import java.util.*;

public class conformity {
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(sc.readLine());
        int max = 0; //dummy

        Map<String, Integer> hm = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] classes = sc.readLine().split(" ");
            Arrays.sort(classes); 

            StringBuilder frosh = new StringBuilder();
            for (String course : classes) {
                frosh.append(course).append(" ");
            }

            String froshString = frosh.toString().trim();

            if (hm.containsKey(froshString)) {
                hm.put(froshString, hm.get(froshString) + 1);
            } else {
                hm.put(froshString, 1);
            }

            if (hm.get(froshString) > max) {
                max = hm.get(froshString);
            }
        }

        int total = 0;

        for (Map.Entry<String, Integer> entry : hm.entrySet()) {
            if (entry.getValue() == max) {
            total += entry.getValue();
            }
        }
        System.out.println(total);
    }
}

