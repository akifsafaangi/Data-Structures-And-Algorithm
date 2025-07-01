import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * The InputFileGenerator class which represents a InputFileGenerator.
 *
 * @file InputFileGenerator.java
 * @author Akif Safa Angi
 * @brief Represents a InputFileGenerator to create random input file.
 * @version 1.0
 * @date 2024-05-23
 */
public class InputFileGenerator {

    /**
     * Main method for file generator
     * 
     * @param args The arguments
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java InputFileGenerator <output_file> <num_operations>");
            return;
        }

        String outputFile = args[0]; // Output file name
        int numOperations = Integer.parseInt(args[1]); // Number of operations
        generateInputFile(outputFile, numOperations); // Generate input file
    }

    /**
     * Generates a random input file with the given number of operations.
     * 
     * @param outputFile The output file name
     * @param numOperations The number of operations
     */
    private static void generateInputFile(String outputFile, int numOperations) {
        String[] commands = {"ADD", "REMOVE", "SEARCH", "UPDATE"};
        Random rand = new Random();

        try (FileWriter writer = new FileWriter(outputFile)) {
            for (int i = 0; i < numOperations; i++) {
                String command = commands[rand.nextInt(commands.length)];
                switch (command) {
                    case "ADD":
                        writer.write(String.format("ADD SYM%d %.2f %d %d\n", 
                            rand.nextInt(numOperations), Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000)));
                        break;
                    case "REMOVE":
                        writer.write(String.format("REMOVE SYM%d\n", rand.nextInt(numOperations)));
                        break;
                    case "SEARCH":
                        writer.write(String.format("SEARCH SYM%d\n", rand.nextInt(numOperations)));
                        break;
                    case "UPDATE":
                        writer.write(String.format("UPDATE SYM%d SYM%d %.2f %d %d\n", 
                            rand.nextInt(numOperations), 
                            rand.nextInt(numOperations), Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000)));
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
