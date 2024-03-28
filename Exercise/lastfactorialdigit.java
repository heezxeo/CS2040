import java.util.*;

public class lastfactorialdigit {
    public static void main (String[] args) {
        Scanner sc = new Scanner (System.in);

        int trials = sc.nextInt();
        for (int i = 1; i < trials + 1; i++) {
            int factor = 1;
            int num = sc.nextInt();
            for (int n = 1; n <= num ; n++) {
                factor *= n;
            }
            int finaldigit = factor % 10;
            System.out.println(finaldigit);
        } 
    }
}