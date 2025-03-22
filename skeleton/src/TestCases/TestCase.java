package TestCases;
import CallTracer.*;
import java.util.List;
import java.util.Scanner;

public abstract class TestCase {
    CallTracer callTracer;

    public TestCase(CallTracer callTracer) {
        this.callTracer = callTracer;
    }

    public void intiInfo() {

    }

    public void testInfo() {

    }

    public int makeDecision(String dontes, List<String> actions) {
        System.out.println("Decision: " + dontes);
        int i = 1;
        for (String act : actions) {
            System.out.println("\t" + i + "; " + act);
            i+=1;
        }

        int choice = 0;
        boolean done = false;
        Scanner scanner = new Scanner(System.in);
        while (!done) {
            System.out.print(">> ");
            String input = scanner.nextLine().trim();
            try {
                choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= actions.size()) {
                    done = true;
                } else {
                    System.out.println("<< Error: Please choose a valid decision");
                }
            } catch (NumberFormatException e) {
                System.out.println("<< Error: Invalid input format");
            }
        }
        return choice;
    }
}
