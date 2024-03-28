import java.util.Scanner;

public class autori {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split("-");

        for (int i = 0; i < input.length; i++) {
            System.out.print(input[i][0]);
        }
        System.out.println();
    }
}
