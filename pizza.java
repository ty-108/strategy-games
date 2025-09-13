import java.util.*;

public class pizza {
    public static void main(String[] args) {
        double totalSlices = 0D, available = 100D;
        ArrayList<person> entries = new ArrayList<>();

        // take in inputted names and pizza slices
        Scanner in = new Scanner(System.in);
        while(true) {
            String name = in.next();
            double slices = 0D;
            if(name.equals("done")) break;
            while(in.hasNext()) {
                String str = in.next();
                try {
                    slices = Double.parseDouble(str);
                    break;
                } catch (Exception e) {
                    name += " " + str;
                }
            }

            totalSlices += slices;
            entries.add(new person(name, slices));
        }

        // add to list of eliminated people while total slices more than available
        Collections.sort(entries, new comp());
        Stack<String> eliminated = new Stack<>();
        while(totalSlices > available) {
            totalSlices -= entries.get(0).slices;
            eliminated.add(entries.get(0).name);
            entries.remove(0);
        }

        // print out results, winners, and losers
        System.out.println("Slices taken: " + totalSlices);
        for(int i = 0; i < Math.min(3, entries.size()); i++) {
            System.out.println("Name: " + entries.get(i).name + ", Slices: " + entries.get(i).slices);
        }
        System.out.println("\nAnd the greedy people: ");
        while(!eliminated.isEmpty()) {
            System.out.print(eliminated.pop());
            if(!eliminated.isEmpty()) System.out.print(", ");
        }

        in.close();
    }

    static class person {
        String name;
        double slices;
        public person(String name, double slices) {
            this.name = name;
            this.slices = slices;
        }
    }

    static class comp implements Comparator<person> {
        public int compare(person a, person b) {
            if(b.slices < a.slices) return -1;
            return 1;
        }
    }
}
