//Ha HeeJu A0266301N

import java.io.*;
import java.util.*;

public class Coconut {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        ArrayList<Player> list = new ArrayList<Player>();

        // Input s and n
        String[] input = sc.nextLine().split(" ");
        int s = Integer.parseInt(input[0]);
        int n = Integer.parseInt(input[1]);

        //add players in queue to number of players
        for (int i = 1; i < n + 1; i++) {
            list.add(new Player(i, "fold"));
        }

        int currentID = 0;
        while (list.size() > 1) {
        	currentID = (currentID + s - 1) % list.size();
            Player current = list.get(currentID);

            if (current.action.equals("fold")) {
                current.diffAction("fist");
                list.add(currentID, new Player(current.idNumber, "fist"));


            } else if (current.action.equals("fist")) {
                current.diffAction("palmdown");
                currentID ++;



            } else if (current.action.equals("palmdown"))
            	list.remove(currentID);
            }

            System.out.println(list.get(0).idNumber);
        }
    }

class Player {
    public int idNumber;
    public String action;

    public Player(int idNumber, String action) {
        this.idNumber = idNumber;
        this.action = action;
    }

    public void diffAction(String action) {
        this.action = action;
    }
}


