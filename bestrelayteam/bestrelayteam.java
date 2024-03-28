// Ha HeeJu A0266301N

import java.util.* ;
import java.io.*;
import java.util.Comparator;

class Runner {
    public String runnerName;
    public double firstLeg;
    public double secondLeg;

    public Runner (String runnerName, double firstLeg, double secondLeg) {
        this.runnerName = runnerName;
        this.firstLeg = firstLeg;
        this.secondLeg = secondLeg;
    }

    public double getSecondLeg(){
        return secondLeg;
    }
}

class RunnerComparator implements Comparator<Runner> {
    public int compare(Runner r1, Runner r2) {
        if (r1.getSecondLeg() < r2.getSecondLeg()) return -1;
        else if (r1.getSecondLeg() > r2.getSecondLeg()) return 1;
        return 0;
    }
}

public class bestrelayteam {
    public static void main (String[] args) {

        Scanner sc = new Scanner(System.in);
        int runnerNum = sc.nextInt();
        sc.nextLine();

        RunnerComparator runnerSort = new RunnerComparator();
        double testTime = 0;
        double finalTime = 10000;

        ArrayList<Runner> team = new ArrayList<Runner>();
        ArrayList<Integer> finalTeam = new ArrayList<Integer>();

        for (int i = 0 ; i < runnerNum; i++) {
            team.add(new Runner(sc.next(),sc.nextDouble(), sc.nextDouble()));
        }

        Collections.sort(team, runnerSort);

        for (int i = 0; i < runnerNum; i++) {
            ArrayList<Integer> testTeam = new ArrayList<Integer>();
            testTime = team.get(i).firstLeg;
            testTeam.add(i);

            for (int j = 0; testTeam.size() < 4; j++) {
                if (j == i) continue;
                testTeam.add(j);
                testTime += team.get(j).secondLeg;
            }

            if (testTime < finalTime) {
                finalTime = testTime;
                finalTeam = testTeam;
            }
        }

        System.out.println(finalTime);
        for (int i = 0 ; i < 4; i++) {
            System.out.println(team.get(finalTeam.get(i)).runnerName);
        }
    }
}


