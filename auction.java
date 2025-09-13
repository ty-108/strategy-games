import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@SuppressWarnings("unchecked")
public class auction {
    public static void main(String[] args) throws IOException {
        int items = 10, moneyPerPerson = 100;
        ArrayList<person> entrants = new ArrayList<>();

        // store all entries (name and all bids) in entrants
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            if(name.equals("done")) break;
            int val = 0;
            while(st.hasMoreTokens()) {
                String str = st.nextToken();
                try {
                    val = Integer.parseInt(str);
                    break;
                } catch (Exception e) {
                    name += " " + str;
                }
            }

            entrants.add(new person(name, new int[items]));
            entrants.get(entrants.size() - 1).bids[0] = val;
            int idx = 1;
            int totalBids = val;
            while(st.hasMoreTokens() && idx < items) {
                val = Integer.parseInt(st.nextToken());
                entrants.get(entrants.size() - 1).bids[idx] = val;
                totalBids += val;
                idx++;
            }
            if(totalBids > moneyPerPerson) entrants.remove(entrants.size() - 1);
        }

        // collect all bids together by item
        ArrayList<bid>[] allBids = new ArrayList[items];
        for(int i = 0; i < items; i++) {
            allBids[i] = new ArrayList<>();
        }
        for(int i = 0; i < entrants.size(); i++) {
            for(int j = 0; j < items; j++) {
                allBids[j].add(new bid(i, entrants.get(i).bids[j]));
            }
        }

        // sort all bids for all items by decreasing bid amount
        for(int i = 0; i < items; i++) {
            Collections.sort(allBids[i], new comp());
        }

        // find the winnings for each item and add pts to winners
        double[] points = new double[entrants.size()];
        for(int i = 0; i < items; i++) {
            int winningBid = allBids[i].get(0).amount;
            if(winningBid == 0) continue;
            int idx = 0;
            ArrayList<Integer> winners = new ArrayList<>();
            while(idx < entrants.size() && allBids[i].get(idx).amount == winningBid) {
                winners.add(allBids[i].get(idx).idx);
                idx++;
            }
            for(int j : winners) {
                points[j] += (double) (i + 1) * 10 / winners.size();
            }
        }

        // add points from remaining unbid money
        for(int i = 0; i < entrants.size(); i++) {
            int totalMoneyBid = 0;
            for(int j = 0; j < items; j++) {
                totalMoneyBid += entrants.get(i).bids[j];
            }
            points[i] += (double) (moneyPerPerson - totalMoneyBid) / 10;
        }

        // sort people's points and find winners
        double winningPoints = -1;
        ArrayList<Integer> auctionWinners = new ArrayList<>();
        for(int i = 0; i < entrants.size(); i++) {
            if(points[i] > winningPoints) {
                winningPoints = points[i];
                auctionWinners.clear();
                auctionWinners.add(i);
            } else if(points[i] == winningPoints) {
                auctionWinners.add(i);
            }
        }

        // print out winners and their bids
        for(int i : auctionWinners) {
            System.out.print(entrants.get(i).name + ", Bids:");
            for(int j : entrants.get(i).bids) {
                System.out.print(" " + j);
            }
            System.out.println();
        }
        System.out.println("\nThey each scored a total of " + winningPoints + " points!");

        System.out.println("\nList of players:");
        for(int i = 0; i < entrants.size(); i++) {
            System.out.println(entrants.get(i).name + ": " + points[i]);
        }

        br.close();
    }
    static class person {
        String name;
        int[] bids;
        public person(String name, int[] bids) {
            this.name = name;
            this.bids = bids;
        }
    }
    static class bid {
        int idx, amount;
        public bid(int idx, int amount) {
            this.idx = idx;
            this.amount = amount;
        }
    }
    static class comp implements Comparator<bid> {
        public int compare(bid a, bid b) {
            return b.amount - a.amount;
        }
    }
}
