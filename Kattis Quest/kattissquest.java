// Ha HeeJu A0266301N

import java.util.*;
import java.io.*;

public class kattissquest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeSet<quest> qSet = new TreeSet<>(new questComparator());

        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            String function = sc.next();

            if (function.equals("add")) {
                qSet.add(new quest(sc.nextLong(), sc.nextLong(), i));

            } else if (function.equals("query")) {
                long amountOfGold = 0;
                long amountOfEnergy = sc.nextLong();

                while (!qSet.isEmpty()) {
                    quest currQuest = qSet.floor(new quest(amountOfEnergy, 10000000, 0));
                    if (currQuest == null || currQuest.energy > amountOfEnergy) {
                        break; 
                    }

                    amountOfGold += currQuest.gold;
                    amountOfEnergy -= currQuest.energy;
                    qSet.remove(currQuest);
                }
                System.out.println(amountOfGold);
            }
        }
        sc.close();
    }
}

class quest {
    public long energy;
    public long gold;
    public int key;

    quest(long energy, long gold, int key) {
        this.energy = energy;
        this.gold = gold;
        this.key = key;
    }
}

class questComparator implements Comparator<quest> {
    public int compare(quest q1, quest q2) {
        if (q1.energy < q2.energy) return -1;
        else if (q1.energy > q2.energy) return 1;
        else if (q1.energy == q2.energy) {
            if (q1.gold < q2.gold) return -1;
            else if (q1.gold > q2.gold) return 1;
            else if (q1.gold == q2.gold) {
                if (q1.key < q2.key) return -1;
                else if (q1.key > q2.key) return 1;
            }
        }

        return 0;
    }
}
