// Ha HeeJu A0266301N

import java.io.*;
import java.util.*;

class dominos {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());
        for (int i = 0; i < testcase; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int tiles = Integer.parseInt(st.nextToken());
            int lines = Integer.parseInt(st.nextToken());

            //adjlist
            ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
            for (int j = 0; j < tiles; j++) {
                adjList.add(new ArrayList<>());
            }

            for (int k = 0; k < lines; k++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                adjList.get(x - 1).add(y - 1);
            }

            // topological ordering 
            int[] visited = new int[tiles];
            Stack<Integer> stack = new Stack<>();
            int[] predecessor = new int[tiles];
            Arrays.fill(predecessor, -1);

            for (int n = 0; n < tiles; n++) {
                if (visited[n] == 0) {
                    DFS(n, visited, stack, adjList);
                }
            }

            ArrayList<ArrayList<Integer>> transposed = new ArrayList<>();
            for (int j = 0; j < tiles; j++) {
                transposed.add(new ArrayList<>());
            }

            for (int c = 0; c < tiles; c++) {
                ArrayList<Integer> list = adjList.get(c);
                for (int d = 0; d < list.size(); d++) {
                    int startEdge = list.get(d);
                    transposed.get(startEdge).add(c);
                }
            }

            // count scc
            int[] check = new int[tiles + 1];
            int[] checkvisited = new int[tiles];
            int count = 0;

            while (!stack.empty()) {
                int pop = stack.pop();
                if (checkvisited[pop] == 0) {
                    count ++;
                    DFStranspose(pop, checkvisited, predecessor, transposed, count);
                }
            }

            for (int f = 0; f < tiles; f++) {
                ArrayList<Integer> arraylist = adjList.get(f);
                for (int g = 0; g < arraylist.size(); g++) {
                    int neighbour = arraylist.get(g);
                    if (predecessor[f] != predecessor[neighbour]) {
                        if (check[predecessor[neighbour]] == 0) {
                            check[predecessor[neighbour]] = 1;
                            count --;
                        }
                    }
                }
            }

            System.out.println(count);
        }
    }

    public static void DFS(int node, int[] visited, Stack<Integer> stack, ArrayList<ArrayList<Integer>> adjList) {
        visited[node] = 1;
        int size = adjList.get(node).size();
        for (int i = 0; i < size; i++) {
            int neighbour = adjList.get(node).get(i);
            if (visited[neighbour] == 0) {
                DFS(neighbour, visited, stack, adjList);
            }
        }
        stack.push(node);
    }

    public static void DFStranspose(int node, int[] visited, int[] predecessor, ArrayList<ArrayList<Integer>> transposed, int count) {
        visited[node] = 1;
        predecessor[node] = count;
        int size = transposed.get(node).size();
        for (int i = 0; i < size; i++) {
            int neighbour = transposed.get(node).get(i);
            if (visited[neighbour] == 0) {
                DFStranspose(neighbour, visited, predecessor, transposed, count);
            }
        }
    }
}

