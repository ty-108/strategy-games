import java.util.ArrayList;
import java.util.Scanner;

public class avg_2_3 {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Double> answers = new ArrayList<>();
        double sum = 0D;

        // store all inputted names and answers
        Scanner in = new Scanner(System.in);
        while(true) {
            String name = in.next();
            double val = 0D;
            if(name.equals("done")) break;
            while(in.hasNext()) {
                String str = in.next();
                try {
                    val = Double.parseDouble(str);
                    break;
                } catch (Exception e) {
                    name += " " + str;
                }
            }

            if(val < 1 || val > 1000) continue;
            names.add(name);
            answers.add(val);
            sum += val;
        }

        // calculate avg, 2/3 avg, and check which guess was closest
        double avg = sum / names.size();
        double target = 2 * avg / 3;
        String bestName = "";
        double bestGuess = 10000;
        for(int i = 0; i < names.size(); i++) {
            if(Math.abs(target - answers.get(i)) < Math.abs(target - bestGuess)) {
                bestName = names.get(i);
                bestGuess = answers.get(i);
            }
        }

        // round off decimals
        double avgRounded = (double) (Math.round(avg * 100)) / 100;
        double targetRounded = (double) (Math.round(target * 100)) / 100;

        // print out results
        System.out.println("Winner: " + bestName);
        System.out.println("Guess: " + bestGuess);
        System.out.println("Average: " + avgRounded);
        System.out.println("2/3 of Average: " + targetRounded);

        in.close();
    }
}
