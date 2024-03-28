import java.util.* ;

public class numberfun {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        int testcase = sc.nextInt();
        int a, b, c;

        for (int i = 0; i < testcase; i++){
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();

        if (a + b == c) {
            System.out.println("Possible");
        }
        else if (Math.abs(a - b) == c){
            System.out.println("Possible");
        }
        else if (a * b == c) {
            System.out.println("Possible");
        }
        else if (a/b == c && a % b == 0) {
            System.out.println("Possible");
        }
        else if (b/a == c && b % a == 0) {
            System.out.println("Possible");
        }
        else {
            System.out.println("Impossible");
        }
        }
    }
}