//Ha HeeJu A0266301N
import java.util.*;
import java.io.*;

public class weakvertices {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int n = sc.nextInt();
            if (n == -1) {
                break;
            }

            int[][] adjMatrix = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    adjMatrix[i][j] = sc.nextInt();
                }
            }

            ArrayList<Integer> weakVertices = WeakVerticesArr(adjMatrix);

            for (int vertices : weakVertices) {
                System.out.print(vertices + " ");
            }
            System.out.println();
        }
    }

    public static ArrayList<Integer> WeakVerticesArr(int[][] adjMatrix) {
        int num = adjMatrix.length;
        int check[] = new int[num];

        ArrayList<Integer> weak = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                for (int k = 0; k < num; k++) {
                    if ((adjMatrix[i][j] == 1) && (adjMatrix[i][k] == 1) && (adjMatrix[j][k] == 1)) {
                        check[i] = 1;
                        check[j] = 1;
                        check[k] = 1;
                    }
                }
            }
        }

        for (int l = 0; l < num; l++) {
            if (check[l] == 0) {
                weak.add(l);
            }
        }
        return weak;
    }
}
