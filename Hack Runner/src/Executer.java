import java.util.ArrayList;
import java.util.Scanner;

public class Executer {
    boolean verbose;
    boolean stepByStep;
    ArrayList<String> program;

    public Executer(boolean verbose, boolean stepByStep, ArrayList<String> program) {
        this.verbose = verbose;
        this.stepByStep = stepByStep;
        this.program = program;
    }

    public void showProgramLines() {
        // this method will show all the lines of the program
        for (String line : program) {
            System.out.println(line);
        }
    }

    public void run() {
        // this method will run the program
        // first we need to parse the program
        Scanner pause = new Scanner(System.in);
        for (String line: program){
            if (verbose){
                System.out.println("Parsing line: " + line);
            }
            if (stepByStep){
                System.out.print("Press enter to continue...");
                pause.nextLine();
            }
        }
        System.out.println("The program was successfully executed.");
        pause.close();
    }
}
