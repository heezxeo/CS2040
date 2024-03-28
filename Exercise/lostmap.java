//Ha HeeJu A0266301N

import java.io.*;
import java.util.*;

class Edge {
    int row;
    int column;
    int weight;

    Edge(int row, int column, int weight) {
        this.row = row;
        this.column = column;
        this.weight = weight;
    }
}

class EdgeComparator implements Comparator<Edge> {
    public int compare(Edge i, Edge j) {
        if (i.weight != j.weight) {
            return Integer.compare(i.weight, j.weight);
        } else {
            return Integer.compare(i.row, j.row);
        }
    }
}

class UFDS {
    int[] p;

    UFDS(int n) {
        this.p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }
    }

    int findSet(int i) {
        if (p[i] == i) {
            return i;
        } else {
            p[i] = findSet(p[i]);
            return p[i];
        }
    }

    boolean isSameSet(int i, int j) {
        return findSet(i) == findSet(j);
    }

    void unionSet(int i, int j) {
        if (!isSameSet(i, j)) {
            int x = findSet(i), y = findSet(j);
            p[y] = x;
        }
    }
}

public class lostmap {
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int n = Integer.parseInt(sc.readLine());
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] line = sc.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(line[j]);
            }
        }

        PriorityQueue<Edge> queue = new PriorityQueue<>(new EdgeComparator());
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                queue.offer(new Edge(i, j, matrix[i][j]));
            }
        }

        UFDS ufds = new UFDS(n);
        while (!queue.isEmpty()) {
            Edge e = queue.poll();
            int i = e.row;
            int j = e.column;

            if (!ufds.isSameSet(i, j)) {
                ufds.unionSet(i, j);
                pw.write((i + 1) + " " + (j + 1) + "\n");
            }
        }

        pw.close();
    }
}

