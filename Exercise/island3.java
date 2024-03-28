//Ha HeeJu A0266301N

import java.util.*;
import java.io.*;

class rowcolumn {
	int row;
	int column;

	rowcolumn(int row, int column) {
		this.row = row;
		this.column = column;
	}
}

public class island3 {
	public static void main (String[] args) throws IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		String input [] = sc.readLine().split(" ");
		int r = Integer.parseInt(input[0]);
		int c = Integer.parseInt(input[1]);

		char[][] grid = new char[r][c];
		int[][] visited = new int[r][c];
		Queue<rowcolumn> gridinput = new ArrayDeque<rowcolumn>();
		int result = 0;

		for (int i = 0; i < r; i++) {
			String str = sc.readLine();
			for (int j = 0; j < c; j++) {
				char character = str.charAt(j);
				grid[i][j] = character;
				visited[i][j] = 0;
			}
		}

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if ((grid[i][j] == 'L') && (visited[i][j] == 0)){
					result++;
					rowcolumn curr = new rowcolumn(i,j);
                    gridinput.add(curr);
                    visited[i][j] = 1;
					BFS(gridinput, r, c, grid, visited);
				}

			} 
		}
		System.out.println(result);
	}

public static void BFS(Queue<rowcolumn> queue, int maxrow, int maxcolumn, char[][] island, int[][] visit) {
    int[] drow = {0, 0, -1, 1}; 
    int[] dcolumn = {-1, 1, 0, 0};

    while (!queue.isEmpty()) {
        rowcolumn rc = queue.poll();
        int i = rc.row;
        int j = rc.column;

        for (int k = 0; k < 4; k++) {
            int nextrow = i + drow[k];
            int nextcolumn = j + dcolumn[k];

            if (nextrow >= 0 && nextrow < maxrow && nextcolumn >= 0 && nextcolumn < maxcolumn && island[nextrow][nextcolumn] != 'W' && visit[nextrow][nextcolumn] == 0) {
                rowcolumn next = new rowcolumn(nextrow, nextcolumn);
                queue.offer(next);
                visit[nextrow][nextcolumn] = 1;
            	}
        	}
    	}
	}
}