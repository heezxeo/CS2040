import java.util.*;

public class timeloop {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        for (int count = 1; count < num+1; count++) {
            System.out.print(count + " " + "Abracadabra" + "\n");
        }

        System.out.println();
    }
}
