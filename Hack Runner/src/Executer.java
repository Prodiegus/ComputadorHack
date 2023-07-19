import java.util.ArrayList;
import java.util.Scanner;

public class Executer {
    boolean verbose;
    boolean stepByStep;
    ArrayList<String> program;
    Memory memory;

    Alu alu;

    public Executer(boolean verbose, boolean stepByStep, ArrayList<String> program) {
        this.verbose = verbose;
        this.stepByStep = stepByStep;
        this.program = program;
        this.memory = new Memory(verbose);
        this.alu = new Alu(verbose);
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
        //we will use a for loop to parse the program
        // On that way we could emulate the program counter
        int pc = 0;
        String A = "0000000000000000";
        String D = "0000000000000000";
        String M = "0000000000000000";
        for(int i = 0; i < program.size(); i++) {
            String line = program.get(i);
            if (verbose){
                System.out.println("Parsing line: " + line);
            }
            if (stepByStep){
                System.out.print("Press enter to continue...");
                pause.nextLine();
            }
            // now we need to check if the line is a (A) instruction or a C instruction

            if (isAInstruction(line)){
                // if the line is a (A) instruction we will set the value of the A register to the value of the instruction
                if (verbose) {
                    System.out.println("The line is a (A) instruction.");
                }
                A = line;
            }
            if (isCInstruction(line)) {
                // if the line is a C instruction we will do the operation
                if (verbose) {
                    System.out.println("The line is a C instruction.");
                }
                String a = getA(line);
                String comp = getComp(line);
                String dest = getDest(line);
                String jump = getJump(line);
                String out = "0000000000000000";

                // we will check the comp operation and temporally save the result on the local M register
                if (a.equals("0")) {
                    if (verbose) {
                        System.out.println("The a is 0.");
                    }
                    switch (comp){
                        case "101010" -> {
                            // 0
                            if (verbose) {
                                System.out.println("The comp is 101010.");
                            }
                            M = alu.parseBinary(0);

                        }
                        case "1111111" -> {
                            // 1
                            if (verbose) {
                                System.out.println("The comp is 1111111.");
                            }
                            M = alu.parseBinary(1);
                        }
                        case "111010" -> {
                            // -1
                            if (verbose) {
                                System.out.println("The comp is 111010.");
                            }
                            M = alu.parseBinary(-1);
                        }
                        case "001100" -> {
                            // D
                            if (verbose) {
                                System.out.println("The comp is 001100.");
                            }
                            M = D;
                        }
                        case "110000" -> {
                            // A
                            if (verbose) {
                                System.out.println("The comp is 110000.");
                            }
                            M = A;
                        }
                        case "001101" -> {
                            // !D
                            if (verbose) {
                                System.out.println("The comp is 001101.");
                            }
                            alu.D = D;
                            M = alu.notd();
                        }
                        case "110001" -> {
                            // !A
                            if (verbose) {
                                System.out.println("The comp is 110001.");
                            }
                            alu.MA = A;
                            M = alu.notma();
                        }
                        case "001111" -> {
                            // -D
                            if (verbose) {
                                System.out.println("The comp is 001111.");
                            }
                            alu.D = D;
                            M = alu.minusX(D);
                        }
                        case "110011" -> {
                            // -A
                            if (verbose) {
                                System.out.println("The comp is 110011.");
                            }
                            alu.MA = A;
                            M = alu.minusX(A);
                        }
                        case "011111" -> {
                            // D+1
                            if (verbose) {
                                System.out.println("The comp is 011111.");
                            }
                            alu.D = D;
                            M = alu.dplusone();
                        }
                        case "110111" -> {
                            // A+1
                            if (verbose) {
                                System.out.println("The comp is 110111.");
                            }
                            alu.MA = A;
                            M = alu.maplusone();
                        }
                        case "001110" -> {
                            // D-1
                            if (verbose) {
                                System.out.println("The comp is 001110.");
                            }
                            alu.D = D;
                            M = alu.dminusone();
                        }
                        case "110010" -> {
                            // A-1
                            if (verbose) {
                                System.out.println("The comp is 110010.");
                            }
                            alu.MA = A;
                            M = alu.maminusone();
                        }
                        case "000010" -> {
                            // D+A
                            if (verbose) {
                                System.out.println("The comp is 000010.");
                            }
                            alu.D = D;
                            alu.MA = A;
                            M = alu.dplusma();
                        }
                        case "010011" -> {
                            // D-A
                            if (verbose) {
                                System.out.println("The comp is 010011.");
                            }
                            alu.D = D;
                            alu.MA = A;
                            M = alu.dminusma();
                        }
                        case "000111" -> {
                            // A-D
                            if (verbose) {
                                System.out.println("The comp is 000111.");
                            }
                            alu.D = D;
                            alu.MA = A;
                            M = alu.maplusd();
                        }
                        case "000000" -> {
                            // D&A
                            if (verbose) {
                                System.out.println("The comp is 000000.");
                            }
                            alu.D = D;
                            alu.MA = A;
                            M = alu.dandma();
                        }
                        case "010101" -> {
                            // D|A
                            if (verbose) {
                                System.out.println("The comp is 010101.");
                            }
                            alu.D = D;
                            alu.MA = A;
                            M = alu.dorma();
                        }
                        default -> {
                            System.err.println("An error has occurred.");
                            System.err.println("Error: Invalid comp.");
                            System.err.println("We can't continue with the execution of the program.");
                            System.exit(1);
                        }
                    }
                }
                if (a.equals("1")) {
                    if (verbose) {
                        System.out.println("The a is 1.");
                    }
                    switch (comp){
                        case "110000" -> {
                            // M
                            if (verbose) {
                                System.out.println("The comp is 110000.");
                            }
                            M = memory.getSlot(alu.parseInt(A));

                        }
                        case "110001" -> {
                            // !M
                            if (verbose) {
                                System.out.println("The comp is 110001.");
                            }
                            alu.MA = memory.getSlot(alu.parseInt(A));
                            M = alu.notma();
                        }
                        case "110011" -> {
                            // -M
                            if (verbose) {
                                System.out.println("The comp is 110011.");
                            }
                            alu.MA = memory.getSlot(alu.parseInt(A));
                            M = alu.minusX(memory.getSlot(alu.parseInt(A)));
                        }
                        case "110111" -> {
                            // M+1
                            if (verbose) {
                                System.out.println("The comp is 110111.");
                            }
                            alu.MA = memory.getSlot(alu.parseInt(A));
                            M = alu.maplusone();
                        }
                        case "110010" -> {
                            // M-1
                            if (verbose) {
                                System.out.println("The comp is 110010.");
                            }
                            alu.MA = memory.getSlot(alu.parseInt(A));
                            M = alu.maminusone();
                        }
                        case "000010" -> {
                            // D+M
                            if (verbose) {
                                System.out.println("The comp is 000010.");
                            }
                            alu.D = D;
                            alu.MA = memory.getSlot(alu.parseInt(A));
                            M = alu.dplusma();
                        }
                        case "010011" -> {
                            // D-M
                            if (verbose) {
                                System.out.println("The comp is 010011.");
                            }
                            alu.D = D;
                            alu.MA = memory.getSlot(alu.parseInt(A));
                            M = alu.dminusma();
                        }
                        case "000111" -> {
                            // M-D
                            if (verbose) {
                                System.out.println("The comp is 000111.");
                            }
                            alu.D = D;
                            alu.MA = memory.getSlot(alu.parseInt(A));
                            M = alu.maplusd();
                        }
                        case "000000" -> {
                            // D&M
                            if (verbose) {
                                System.out.println("The comp is 000000.");
                            }
                            alu.D = D;
                            alu.MA = memory.getSlot(alu.parseInt(A));
                            M = alu.dandma();
                        }
                        case "010101" -> {
                            // D|M
                            if (verbose) {
                                System.out.println("The comp is 010101.");
                            }
                            alu.D = D;
                            alu.MA = memory.getSlot(alu.parseInt(A));
                            M = alu.dorma();
                        }
                    }
                }
                // now we will check the dest
                out = M;
                if (verbose) {
                    System.out.println("The dest is " + dest);
                }
                switch (dest){
                    case "000" -> {
                        // null
                        if (verbose) {
                            System.out.println("The dest is null.");
                        }
                    }
                    case "001" -> {
                        // M
                        if (verbose) {
                            System.out.println("The dest is M.");
                        }
                        memory.setM(M);
                        memory.setSlot(alu.parseInt(A), M);
                    }
                    case "010" -> {
                        // D
                        if (verbose) {
                            System.out.println("The dest is D.");
                        }
                        alu.D = M;
                        D = M;
                    }
                    case "011" -> {
                        // MD
                        if (verbose) {
                            System.out.println("The dest is MD.");
                        }
                        memory.setM(M);
                        memory.setSlot(alu.parseInt(A), M);
                        alu.D = M;
                        alu.MA = M;
                        D = M;
                    }
                    case "100" -> {
                        // A
                        if (verbose) {
                            System.out.println("The dest is A.");
                        }
                        alu.MA = M;
                        A = M;
                    }
                    case "101" -> {
                        // AM
                        if (verbose) {
                            System.out.println("The dest is AM.");
                        }
                        alu.MA = M;
                        A = M;
                        memory.setM(M);
                    }
                    case "110" -> {
                        // AD
                        if (verbose) {
                            System.out.println("The dest is AD.");
                        }
                        alu.D = M;
                        alu.MA = M;
                        D = M;
                        memory.setM(M);
                        memory.setSlot(alu.parseInt(A), M);
                    }
                    case "111" -> {
                        // AMD
                        if (verbose) {
                            System.out.println("The dest is AMD.");
                        }
                        alu.D = M;
                        alu.MA = M;
                        D = M;
                        memory.setM(M);
                        memory.setSlot(alu.parseInt(A), M);
                        A = M;
                    }
                    default -> {
                        System.err.println("An error has occurred.");
                        System.err.println("Error: Invalid dest.");
                        System.err.println("We can't continue with the execution of the program.");
                        System.exit(1);
                    }
                }
				// now we will check the jump
                if (verbose) {
                    System.out.println("The jump is " + jump);
                }
                switch (jump){
                    case "000" -> {
                        // null
                        if (verbose) {
                            System.out.println("The jump is null.");
                        }
                    }
                    case "001" -> {
                        // JGT
                        if (verbose) {
                            System.out.println("The jump is JGT.");
                        }
                        if (alu.parseInt(out) > 0) {
                            pc = alu.parseInt(A);
                        }
                    }
                    case "010" -> {
                        // JEQ
                        if (verbose) {
                            System.out.println("The jump is JEQ.");
                        }
                        if (alu.parseInt(out) == 0) {
                            pc = alu.parseInt(A);
                        }
                    }
                    case "011" -> {
                        // JGE
                        if (verbose) {
                            System.out.println("The jump is JGE.");
                        }
                        if (alu.parseInt(out) >= 0) {
                            pc = alu.parseInt(A);
                        }
                    }
                    case "100" -> {
                        // JLT
                        if (verbose) {
                            System.out.println("The jump is JLT.");
                        }
                        if (alu.parseInt(out) < 0) {
                            pc = alu.parseInt(A);
                        }
                    }
                    case "101" -> {
                        // JNE
                        if (verbose) {
                            System.out.println("The jump is JNE.");
                        }
                        if (alu.parseInt(out) != 0) {
                            pc = alu.parseInt(A);
                        }
                    }
                    case "110" -> {
                        // JLE
                        if (verbose) {
                            System.out.println("The jump is JLE.");
                        }
                        if (alu.parseInt(out) <= 0) {
                            pc = alu.parseInt(A);
                        }
                    }
                    case "111" -> {
                        // JMP
                        if (verbose) {
                            System.out.println("The jump is JMP.");
                        }
                        pc = alu.parseInt(A);
                    }
                    default -> {
                        System.err.println("An error has occurred.");
                        System.err.println("Error: Invalid jump.");
                        System.err.println("We can't continue with the execution of the program.");
                        System.exit(1);
                    }
                }
            }
            if (verbose) {
                System.out.println(("We have finished parsing the line."));
                System.out.println("line: " +pc+"/"+program.size());
            }
            if (stepByStep) {
                System.out.print("Press enter to continue with the next line...");
                pause.nextLine();
            }
        }
        System.out.println("The program was successfully executed.");
        System.out.println("here is the memory:");
        memory.checkMemory();
        pause.close();
    }

    /*
     * On a hack program we have 2 types of lines:
     *  - A instruction
     *     - @value
     *       this instruction will set the value of the A register to the value of the value
     *       that means that if we have @2, the value of the A register will be 2
     *       in other words, we are setting the value of the A register to 2.
     * - C instruction
     *       this instruction will do an operation with the A register and the M register,
     *       and then it will set the value of the M register to the result of the operation
     *       the operations are:
     *       - 0: M = 0
     *       - 1: M = 1
     *       - -1: M = -1
     *       - D: M = D
     *       - A: M = A
     *       - !D: M = !D
     *       - !A: M = !A
     *       - -D: M = -D
     *       - -A: M = -A
     *       - D+1: M = D+1
     *       - A+1: M = A+1
     *       - D-1: M = D-1
     *       - A-1: M = A-1
     *       - D+A: M = D+A
     *       - D-A: M = D-A
     *       - A-D: M = A-D
     *       - D&A: M = D&A
     *       - D|A: M = D|A
     *       - M: M = M
     *       - !M: M = !M
     *       - -M: M = -M
     *       - M+1: M = M+1
     *       - M-1: M = M-1
     *       - D+M: M = D+M
     *       - D-M: M = D-M
     *       - M-D: M = M-D
     *       - D&M: M = D&M
     *       - D|M: M = D|M
     *       Which the ALU will do the operation and from the executer we will manage the result
     * */

// now with all that in mind we will first create an instruction detector

    private boolean isAInstruction(String line) {
        // this method will return true if the line is a (A) instruction
        return line.startsWith("0");
    }
    private boolean isCInstruction(String line) {
        // this method will return true if the line is a C instruction
        return line.startsWith("111");
    }
    // actually we made 2 methods, but we can make only one


    // for the c instruction we will make a method that will return the (a), comp, dest and jump

    private String getA(String line) {
        // this method will return the (a) of the C instruction
        return line.split("")[3];
    }
    private String getComp(String line) {
        // this method will return the comp of the C instruction
        return line.substring(4, 9);
    }
    private String getDest(String line) {
        // this method will return the dest of the C instruction
        return line.substring(10, 12);
    }
    private String getJump(String line) {
        // this method will return the jump of the C instruction
        return line.substring(13, 15);
    }
}


