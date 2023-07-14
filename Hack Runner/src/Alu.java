public class Alu {
    /* arithmetic logic unit
     this class will be used to execute the arithmetic and logic operations
    */
    public boolean verbose;
    public String D;
    public String MA;

    public Alu(boolean verbose) {
        // constructor
        this.verbose = verbose;
        this.D = "0000000000000000";
        this.MA = "0000000000000000";
        if (verbose) {
            System.out.println("ALU was successfully initialized.");
        }
    }

    /*
    * Now we are going to create the methods that will execute the operations
    * one method for each operation
    * */
    public String dplusma() {
        if (verbose) {
            System.out.println("\nAdding " + D + " and " + MA+"\n");

        }
        return parseBinary(parseInt(D) + parseInt(MA));
    }

    public String maplusd() {
        if (verbose) {
            System.out.println("\nAdding " + MA + " and " + D+"\n");
        }
        return parseBinary(parseInt(MA) + parseInt(D));
    }

    public String maplusone() {
        if (verbose) {
            System.out.println("\nAdding " + MA + " and 1\n");
        }
        return parseBinary(parseInt(MA) + 1);
    }

    public String dplusone() {
        if (verbose) {
            System.out.println("\nAdding " + D + " and 1\n");
        }
        return parseBinary(parseInt(D) + 1);
    }

    public String dminusone() {
        if (verbose) {
            System.out.println("\nSubtracting " + D + " and 1\n");
        }
        return parseBinary(parseInt(D) - 1);
    }

    public String maminusone() {
        if (verbose) {
            System.out.println("\nSubtracting " + MA + " and 1\n");
        }
        return parseBinary(parseInt(MA) - 1);
    }
    public String maminusd() {
        if (verbose) {
            System.out.println("\nSubtracting " + MA + " and " + D+"\n");
        }
        return parseBinary(parseInt(MA) - parseInt(D));
    }

    public String dminusma() {
        if (verbose) {
            System.out.println("\nSubtracting " + D + " and " + MA+"\n");
        }
        return parseBinary(parseInt(D) - parseInt(MA));
    }

    public String dandma() {
        if (verbose) {
            System.out.println("\nDoing the AND operation between " + D + " and " + MA+"\n");
        }
        return parseBinary(parseInt(D) & parseInt(MA));
    }

    public String dorma() {
        if (verbose) {
            System.out.println("\nDoing the OR operation between " + D + " and " + MA+"\n");
        }
        return parseBinary(parseInt(D) | parseInt(MA));
    }

    public String notd() {
        if (verbose) {
            System.out.println("\nDoing the NOT operation to " + D+"\n");
        }
        return parseBinary(~parseInt(D));
    }

    public String notma() {
        if (verbose) {
            System.out.println("\nDoing the NOT operation to " + MA+"\n");
        }
        return parseBinary(~parseInt(MA));
    }

    private String parseBinary(int n) {
        boolean negative = false;
        if (n < 0) {
            negative = true;
            n *= -1;
        }
        if (verbose) {
            System.out.println("Parsing " + n + " to binary.");
        }
        // this method will parse a number to binary
        String binary = "";

        while (n > 0) {
            binary += n % 2;
            n /= 2;
        }
        while (binary.length() < 16) {
            binary += "0";
        }

        if (negative) {
            binary += "1";
        } else {
            binary += "0";
        }
        binary = new StringBuilder(binary).reverse().toString();
        if (verbose) {
            System.out.println("Binary: " + binary);
        }
        return binary;
    }

    private int parseInt(String s) {
        int number = 0;
        if (verbose) {
            System.out.print("Parsing " + s + " to ");
        }

        String[] binary = s.split("");
        for (int i = 1; i < binary.length; i++) {
            if (binary[i].equals("1")) {
                number += Math.pow(2, binary.length - i - 1);
            }
        }
        if (binary[0].equals("1")) {
            number *= -1;
        }
        if (verbose) {
            System.out.println(number);
        }
        return number;
    }
    public static void main(String[] args) {
        Alu alu = new Alu(true);
        alu.D = "0000000000000011";
        alu.MA = "0000000000000001";
        System.out.println(alu.dplusma());
        System.out.println(alu.maplusd());
        System.out.println(alu.maminusd());
        System.out.println(alu.dminusma());
        System.out.println(alu.maplusone());
        System.out.println(alu.dplusone());
        System.out.println(alu.maminusone());
        System.out.println(alu.dminusone());
        System.out.println(alu.dandma());
        System.out.println(alu.dorma());
        System.out.println(alu.notd());
        System.out.println(alu.notma());

    }

}
