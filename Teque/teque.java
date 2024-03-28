//Ha HeeJu A0266301N

import java.io.*;
import java.util.*;

class input {
    public String operate;
    public int number;

    public input(String operate, int number) {
        this.number = number;
        this.operate = operate;
    }
}

public class teque {
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(sc.readLine().trim());
        Hashtable<Integer, Integer> first = new Hashtable<>();
        Hashtable<Integer, Integer> second = new Hashtable<>();
        int firstStart = -1;
        int firstEnd = 0;
        int secondStart = -1;
        int secondEnd = 0;

        for (int i = 0; i < n; i++) {
            String[] line = sc.readLine().trim().split(" ");
            input current = new input(line[0], Integer.parseInt(line[1].trim()));

            if (current.operate.equals("get")) {
                Integer result = current.number < first.size()
                        ? first.get(current.number + firstStart + 1)
                        : second.get(current.number - first.size() + secondStart + 1);
                pw.println(result);
                continue;
            }

            if (current.operate.equals("push_back")) {
                second.put(secondEnd++, current.number);
            } else if (current.operate.equals("push_front")) {
                first.put(firstStart--, current.number);
            } else if (current.operate.equals("push_middle")) {
                first.put(firstEnd++, current.number);
            }

            if (second.size() > first.size()) {
                first.put(firstEnd++, second.get(secondStart + 1));
                secondStart++;
                second.remove(secondStart);
            } else if (first.size() - 1 > second.size()) {
                second.put(secondStart--, first.get(firstEnd - 1));
                firstEnd--;
                first.remove(firstEnd);
            }
        }
        pw.close();
    }
}
