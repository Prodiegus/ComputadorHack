import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DirManager {
    /**
     * this class is used to manage the directory information, like opening a file, closing a file, etc.
     * and also retuning a list with all the lines of a hack file
     */
    public DirManager() {
        // constructor
    }

    public ArrayList<String> getProgram(String path) {
        // open a file and then read it, line by line and return a list with all the lines
        ArrayList<String> lines = new ArrayList<>();
        try {
            // we are going to try to open the file of the path
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(line);
            }
            scanner.close();
            return lines;

        }catch (Exception e) {
            System.err.println("An error has occurred.");
            System.err.println("Error: " + e.getMessage());
            System.err.println("Unable to open file '" + path + "'");
            return null;
        }
    }
}