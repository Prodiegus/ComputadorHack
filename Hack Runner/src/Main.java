import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    static boolean verbose;
    static boolean stepByStep;

    public Main() {
        // constructor
        verbose = false;
        stepByStep = false;
    }
    public static void run() {
        DirManager dirManager = new DirManager();
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> program = new ArrayList<>();

        System.out.println("Welcome to the Hack Assembler!");
        System.out.print("Please enter the path of the file you want to run: ");
        try {
            program = dirManager.getProgram(scanner.nextLine());
            if (stepByStep){
                System.out.print("\n Press enter to continue...");
                scanner.nextLine();
            }
            while (program == null) {
                System.out.println("The path you entered is not valid, please try again.");
                System.out.print("Please enter the path of the file you want to run: ");
                program = dirManager.getProgram(scanner.nextLine());
            }
        } catch (Exception e) {
            System.err.println("An error has occurred.");
            System.err.println("Error: " + e.getMessage());
            System.err.println("We can't continue with the execution of the program.");
            System.exit(1);
        }

        Executer executer = new Executer(verbose, stepByStep, program);
        if (verbose) {
            // verbose mode
            System.out.println("this is the program you entered:");
            executer.showProgramLines();
            System.out.println("Lines: " + program.size());
            System.out.println("The program was successfully loaded.");
        }
        if (stepByStep) {
            // step by step mode
            System.out.print("\nPress enter to execute the program...");
            scanner.nextLine();
        }
        executer.run();
        scanner.close();
    }

    public static void main(String[] args) {
        // program configuration environment variables
        if (args.length == 0) {
            run();
        } else {
            switch (args[0]) {
                case "-v" -> {
                    // verbose mode
                    verbose = true;
                    System.out.println("Verbose mode enabled.");
                    run();
                }
                case "-s" -> {
                    // step by step mode
                    stepByStep = true;
                    run();
                }
                case "-vs", "-sv" -> {
                    // verbose and step by step mode
                    verbose = true;
                    stepByStep = true;
                    System.out.println("Verbose and step by step mode enabled.");
                    run();
                }
                default -> {
                    System.out.println("Invalid argument.");
                    System.out.println("Usage: java Main [-v] [-s] [-vs] [-sv]");
                    System.out.println("Options:");
                    System.out.println("    -v: verbose mode (avoid extra debug information in the console)");
                    System.out.println("    -s: step by step mode");
                    System.out.println("    -vs: verbose and step by step mode");
                    System.out.println("    -sv: verbose and step by step mode");
                    System.exit(1);
                }
            }
        }
    }
}