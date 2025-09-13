import java.util.*;

public class fireball {
    public static void main(String[] args) {
        ArrayList<entry> entries = new ArrayList<>();
        char[] moveArr = {'C', 'S', 'F', 'c', 's', 'f'};
        ArrayList<Character> moveList = new ArrayList<>();
        for(char c : moveArr) {
            moveList.add(c);
        }

        // take in inputted names and strings of moves
        Scanner in = new Scanner(System.in);
        while(true) {
            String name = in.next();
            String input = "";
            if(name.equals("done")) break;

            while(in.hasNext()) {
                String str = in.next();

                // if next string contains at least 5 C's, S's, or F's,
                // use that as their string of moves
                if(str.length() < 5) {
                    name += " " + str;
                    continue;
                }
                boolean isNotName = true;
                for(int i = 0; i < 5; i++) {
                    if(!moveList.contains(str.charAt(i))) {
                        isNotName = false;
                        break;
                    }
                }

                if(isNotName) {
                    input = str;
                    break;
                } else {
                    name += " " + str;
                }
            }
            if(input.isEmpty()) continue;

            String plan = convert(input);
            entries.add(new entry(name, plan, input, 0));
        }

        // pair each player with another and simulate fireball
        // award points according to results
        for(int i = 0; i < entries.size(); i++) {
            for(int j = i + 1; j < entries.size(); j++) {
                int status = fight(entries.get(i).plan, entries.get(j).plan);
                if(status == -1) {
                    entries.get(i).wins += 2;
                } else if(status == 1) {
                    entries.get(j).wins += 2;
                } else {
                    entries.get(i).wins++;
                    entries.get(j).wins++;
                }
            }
        }

        // sort players by wins and print out the top players' name(s) and result(s)
        Collections.sort(entries, new comp());
        int winningScore = entries.get(0).wins;
        System.out.println("Winning score: " + (double) winningScore / 2 + "/" + (entries.size() - 1));
        for(int i = 0; i < entries.size(); i++) {
            if(entries.get(i).wins != winningScore) break;
            System.out.println("Name: " + entries.get(i).name + ", Input: " + entries.get(i).input);
        }

        in.close();
    }

    static class entry {
        String name, plan, input;
        int wins;
        public entry(String name, String plan, String input, int wins) {
            this.name = name;
            this.plan = plan;
            this.wins = wins;
            this.input = input;
        }
    }

    static class comp implements Comparator<entry> {
        public int compare(entry a, entry b) {
            return b.wins - a.wins;
        }
    }

    // convert C's, F's, and S's to numbers corresponding to the moves
    // pad, trim, and clean string of moves as needed
    public static String convert(String str) {
        if(str.length() > 10) {
            str = str.substring(0, 10);
        }
        if(str.length() < 10) {
            while(str.length() != 10) {
                str += 'C';
            }
        }

        String ret = "";
        for(int i = 0; i < 10; i++) {
            if(str.substring(i, i + 1).equalsIgnoreCase("S")) {
                ret += '2';
            } else if(str.substring(i, i + 1).equalsIgnoreCase("F")) {
                ret += '1';
            } else {
                ret += '0';
            }
        }
        return ret;
    }

    // simulate two players playing each other
    public static int fight(String a, String b) {
        int powA = 1, powB = 1;
        for(int i = 0; i < 10; i++) {
            int state = Integer.parseInt(a.substring(i, i + 1)) * 3 + Integer.parseInt(b.substring(i, i + 1));
            if(state < 3) { // Player A charge
                powA++;
                if(state == 0) { // Player B charge
                    powB++;
                } else if(state == 1) { // Player B fireball
                    if(powB > 0) return 1;
                }
            } else if(state < 6) { // Player A fireball
                if(state == 3) { // Player B charge
                    powB++;
                    if(powA > 0) return -1;
                } else if(state == 4) { // Player B fireball
                    if(powA > powB) {
                        return -1;
                    } else if(powA < powB) {
                        return 1;
                    } else {
                        powA = 0;
                        powB = 0;
                    }
                } else if(state == 5) { // Player B shield
                    if(powA > 2) {
                        return -1;
                    } else {
                        powA = 0;
                    }
                }
            } else if(state < 9) { // Player A shield
                if(state == 6) { // Player B charge
                    powB++;
                } else if(state == 7) { // Player B fireball
                    if(powB > 2) {
                        return 1;
                    } else {
                        powB = 0;
                    }
                }
            }
        }
        return 0;
    }
}
