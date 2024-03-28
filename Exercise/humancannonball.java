//Ha HeeJu A0266301N

import java.io.*;
import java.util.*;

public class humancannonball {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<location> locationArr = new ArrayList<location>();

		String[] current = br.readLine().split(" ");
		double currX = Double.parseDouble(current[0]);
		double currY = Double.parseDouble(current[1]);
		locationArr.add(new location(currX, currY));

		String[] destination = br.readLine().split(" ");
		double destX = Double.parseDouble(destination[0]);
		double destY = Double.parseDouble(destination[1]);
		locationArr.add(new location(destX, destY));

		String n = br.readLine();
		int numberOfCannon = Integer.parseInt(n);

		for (int i = 0; i < numberOfCannon; i++) {
			String[] cannonGrid = br.readLine().split(" ");
			double cannonX = Double.parseDouble(cannonGrid[0]);
			double cannonY = Double.parseDouble(cannonGrid[1]);
			locationArr.add(new location(cannonX, cannonY));
		}

		double[][] adjMat = new double[numberOfCannon + 2][numberOfCannon + 2];
		double INFINITY = Double.POSITIVE_INFINITY;

		for (int j = 0; j < numberOfCannon + 2; j++) {
			for (int m = 0; m < numberOfCannon + 2; m++) {
				double result = 0;
				double distance = 0;
				double duration1 = INFINITY;
				double duration2 = INFINITY;

				location loc1 = locationArr.get(j);
				location loc2 = locationArr.get(m);

				distance = Math.hypot((loc1.x - loc2.x), (loc1.y - loc2.y));
				duration1 = distance / 5;
				
				if (j > 1) {
					duration2 = 2 + (Math.abs(distance - 50) / 5);
				}

				result = Math.min(duration1, duration2);
				adjMat[j][m] = result;
			}
		}

		PriorityQueue<pair> queue = new PriorityQueue<pair>(new pairComparator());
		double[] dist = new double[numberOfCannon + 2];

		for (int k = 0; k < numberOfCannon + 2; k++) {
    		dist[k] = INFINITY;
		}	

		dist[0] = 0;
		queue.add(new pair(0,0));
		while (!queue.isEmpty()) {
    		pair value = queue.poll();
    		double valDistance = value.distance;
    		int vertex = value.source;

    		if (valDistance == dist[vertex]) {
        		for (int i = 0; i < numberOfCannon + 2; i++) {
            		if (dist[vertex] + adjMat[vertex][i] < dist[i]) {
                		dist[i] = dist[vertex] + adjMat[vertex][i];
                		queue.add(new pair(dist[i], i));  
            		}
        		}
    		}
		}
		System.out.println(dist[1]);
	}
}


class location {
	public double x;
	public double y;

	location (double x, double y) {
		this.x = x;
		this.y = y;
	}
}

class pair {
	public double distance;
	public int source;

	pair(double distance, int source) {
		this.distance = distance;
		this.source = source;
	}
}

class pairComparator implements Comparator<pair> {
	public int compare(pair p1, pair p2) {
		if (p1.distance < p2.distance) {
			return -1;
		}
		else if (p1.distance > p2.distance) {
			return 1;
		} else {
			return 0;
		}
	}
}