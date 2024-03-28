//Ha HeeJu A0266301N

import java.io.*;
import java.util.*;


public class almostunionfind {
	public static void main (String[] args) throws IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer tokenizer;

		String input = sc.readLine();
		tokenizer = new StringTokenizer(input);

		while (tokenizer.hasMoreTokens()) {
			int n = Integer.parseInt(tokenizer.nextToken());
			int m = Integer.parseInt(tokenizer.nextToken());
			UFDS unionfind = new UFDS(n);

			for (int i = 0; i < m; i++) {
				String[] set = sc.readLine().split(" ");
				int operation = Integer.parseInt(set[0]);

				if (operation == 1) {
    				int p = Integer.parseInt(set[1]); 
    				int q = Integer.parseInt(set[2]);
   					unionfind.unionSet(p, q);
				} else if (operation == 2) {
    				int p = Integer.parseInt(set[1]);
    				int q = Integer.parseInt(set[2]);
    				unionfind.moveSet(p, q);
				} else {
    				int p = Integer.parseInt(set[1]);
    				pw.write(unionfind.count(p) + " " + unionfind.sum(p) + "\n");

					}
				}
			}
			pw.close();
		}
	}

class UFDS {
    public int[] parent;
    public int[] next;
    public int[] number;
    public long[] result;

    public UFDS (int n) {
        this.parent = new int[n + 1];
        this.next = new int[n + 1];
        this.number = new int[n + 1];
        this.result = new long[n + 1];

        for (int i = 1; i < n + 1; i++) {
            parent[i] = i;
            next[i] = i;
            number[i] = 1;
            result[i] = i;
        }
    }

    int searchSet(int p) {
        int nextNum = next[p];
        while (nextNum != parent[nextNum]) {
            nextNum = parent[nextNum];
        }
        next[p] = nextNum;
        return nextNum;
    }

    void unionSet(int p, int q) {
        if (searchSet(p) != searchSet(q)) {
            int x = searchSet(p);
            int y = searchSet(q);
            parent[x] = y;
            next[q] = x;
            number[y] += number[x];
            result[y] += result[x];
        }
    }

    void moveSet(int p, int q) {
        if (searchSet(p) != searchSet(q)) {
            int x = searchSet(p);
            int y = searchSet(q);
            next[p] = y;
            number[y] += 1;
            result[y] += p;
            number[x] -= 1;
            result[x] -= p;
        }
    }

    int count(int p) {
        return number[searchSet(p)];
    }

    long sum(int p) {
        return result[searchSet(p)];
    }
}
