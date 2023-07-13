import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    boolean verbose;
    boolean stepByStep;

    public Main() {
        // constructor
        this.verbose = false;
        this.stepByStep = false;
    }
    public static void showProgramLines(ArrayList<String> program) {
        // this method will show all the lines of the program
        for (String line : program) {
            System.out.println(line);
        }
    }

    public static void run() {
        DirManager dirManager = new DirManager();
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> program = new ArrayList<String>();

        System.out.println("Welcome to the Hack Assembler!");
        System.out.print("Please enter the path of the file you want to run: ");
        try {
            program = dirManager.getProgram(scanner.nextLine());
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
        showProgramLines(program);
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
                    System.out.println("Verbose mode enabled.");
                    run();
                }
                case "-s" -> {
                    // step by step mode
                    System.out.println("Step by step mode enabled.");
                    run();
                }
                case "-vs", "-sv" -> {
                    // verbose and step by step mode
                    System.out.println("Verbose and step by step mode enabled.");
                    run();
                }
                default -> {
                    System.out.println("Invalid argument.");
                    System.out.println("Usage: java Main [-v] [-s] [-vs] [-sv]");
                    System.out.println("Options:");
                    System.out.println("    -v: verbose mode");
                    System.out.println("    -s: step by step mode");
                    System.out.println("    -vs: verbose and step by step mode");
                    System.out.println("    -sv: verbose and step by step mode");
                    System.exit(1);
                }
            }
        }
    }
}