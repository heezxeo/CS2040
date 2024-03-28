import java.io.*;
import java.util.*;

public class CardTrading {
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(
            new InputStreamReader(System.in));

        String[] first = sc.readLine().split(" ");
        int n = Integer.parseInt(first[0]);
        int t = Integer.parseInt(first[1]);
        int k = Integer.parseInt(first[2]);

        int[] deck = new int[100000];
        String[] second = sc.readLine().split(" ");
        for (String card : second) {
            int cardNo = Integer.parseInt(card);
            deck[cardNo]++;
        }

        ArrayList<Profit> profitlist = new ArrayList<Profit>();

        for (int c = 0; c < t; c++) {
            String[] buysell = sc.readLine().split(" ");
            int cardName = c + 1;
            int buyP = Integer.parseInt(buysell[0]);
            int sellP = Integer.parseInt(buysell[1]);
            int buy = (2 - deck[cardName]) * buyP;
            int sell = deck[cardName] * sellP;
            profitlist.add(new Profit(buy, sell));
        }

        profitComparator priceSort = new profitComparator();
        Collections.sort(profitlist, priceSort);

        long coin = 0;
        for (int i = 0; i < k; i++) {
            Profit nCard = profitlist.get(i);
            int buying = nCard.getBuy();
            coin -= buying;
        }

        for (int j = k; j < t; j++) {
            Profit nCard = profitlist.get(j);
            int selling = nCard.getSell();
            coin += selling;
        }
        System.out.println(coin);
    }
}

class Profit {
    public int buy;
    public int sell;

    public Profit(int buy, int sell) {
        this.buy = buy;
        this.sell = sell;
    }

    public int getBuy() {
        return buy;
    }

    public int getSell() {
        return sell;
    }
}

class profitComparator implements Comparator<Profit> {
    public int compare(Profit p1, Profit p2) {
        if ((p1.getBuy() + p1.getSell()) < (p2.getBuy() + p2.getSell())) return -1;
        else if ((p1.getBuy() + p1.getSell()) > (p2.getBuy() + p2.getSell())) return 1;
        else {
            if (p1.getBuy() < p2.getBuy()) return -1;
            else if (p1.getBuy() > p2.getBuy()) return 1;
            return 0;
        }
    }
}
