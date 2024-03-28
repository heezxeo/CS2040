// Ha HeeJu A0266301N

import java.util.* ;
import java.io.*;
import java.util.Comparator;

class Researcher {
	public int arrive;
	public int stay;

	Researcher(int arrive, int stay) {
		this.arrive = arrive;
		this.stay = stay;
	}

	public int totalTime() {
		return this.arrive + this.stay;
	}

	public int getArrive() {
		return arrive;
	}
}

class ResearchComparator implements Comparator<Researcher> {
	public int compare(Researcher r1, Researcher r2) {
		if (r1.getArrive() < r2.getArrive()) return -1;
		else if (r1.getArrive() > r2.getArrive()) return 1;
		return 0;
	}
}

public class workstations {
	public static void main (String[] args) throws IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		ResearchComparator researcherSort = new ResearchComparator();
		int unlockings = 0;

		String[] admin = sc.readLine().split(" ");
		int n = Integer.parseInt(admin[0]);
		int m = Integer.parseInt(admin[1]);

		PriorityQueue<Integer> workstation = new PriorityQueue<Integer>(); //number of minutes spending
		ArrayList<Researcher> workerArr = new ArrayList<Researcher>(); //researchers

		for (int j = 0; j < n; j++) {
			String[] worker = sc.readLine().split(" ");
			int a = Integer.parseInt(worker[0]);
			int s = Integer.parseInt(worker[1]);
			workerArr.add(new Researcher(a,s));
		}

		Collections.sort(workerArr, researcherSort);

		workstation.add(workerArr.get(0).totalTime());
		for (int i = 1; i < n; i++) {
			workstation.add(workerArr.get(i).totalTime());

			while (workstation.peek() + m < workerArr.get(i).getArrive()) {
				workstation.poll();
			} 
			
			if (workstation.peek() + m >= workerArr.get(i).getArrive() && workstation.peek() <= workerArr.get(i).getArrive()) {
				workstation.poll();
				unlockings += 1;
			}
		}

		System.out.println(unlockings);
	}
}
