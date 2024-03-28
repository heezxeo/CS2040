//Ha HeeJu A0266301N

import java.io.*;
import java.util.*;

class Coin {
	int row;
	int column;
	int height;
	int diff;

	Coin(int row, int column, int height, int diff) {
		this.row = row;
		this.column = column;
		this.height = height;
		this.diff = diff;
	}
}

class CoinComparator implements Comparator<Coin> {
	public int compare(Coin c1, Coin c2) {
		int diffHeight = c1.diff - c2.diff;
		if (diffHeight > 0) {
			return 1;
		} else if (diffHeight < 0) {
			return -1;
		} else {
			return 0;
		}
	}
}

public class millionairemadness {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int m = Integer.parseInt(input[0]);
        int n = Integer.parseInt(input[1]);

        int[][] grid = new int[m][n];
        int[][] visited = new int[m][n];

        for (int i = 0; i < m; i++) {
            String[] next = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
            	int value = Integer.parseInt(next[j]);
                grid[i][j] = value;
            }
        }

        int[] drow = {0, 1, 0, -1};
        int[] dcolumn = {-1, 0, 1, 0};

        PriorityQueue<Coin> queue = new PriorityQueue<>(new CoinComparator());
        queue.offer(new Coin(0, 0, grid[0][0], 0));
        visited[0][0] = 1;
        int result = 0;

        while (!queue.isEmpty() && visited[m - 1][n - 1] == 0) {
            Coin curr = queue.poll();
            int x = curr.row;
            int y = curr.column;
            int difference = curr.diff;
            visited[x][y] = 1;
            if (difference > result) { result = difference; }

            /*if (curr.row == m - 1 && curr.column == n - 1) {
                break;
            }*/

            for (int k = 0; k < 4; k++) {
                int nextrow = x + drow[k];
                int nextcolumn = y + dcolumn[k];

                if (nextrow >= 0 && nextcolumn >= 0 && nextrow < m && nextcolumn < n && visited[nextrow][nextcolumn] == 0) {
                    int newheight = grid[nextrow][nextcolumn];
                    int newdiff = newheight - curr.height;
                    Coin nextCoin = new Coin(nextrow, nextcolumn, newheight, newdiff);
                    queue.offer(nextCoin);
                }
            }
        }

        System.out.println(result);
    }
}
