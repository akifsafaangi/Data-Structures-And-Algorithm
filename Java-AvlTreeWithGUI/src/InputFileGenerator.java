import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class InputFileGenerator {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java InputFileGenerator <output_file> <num_operations>");
            return;
        }

        String outputFile = args[0];
        int numOperations = Integer.parseInt(args[1]);
        generateInputFile(outputFile, numOperations);
    }

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
