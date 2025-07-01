import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.SwingUtilities;

/**
 * The Main class contains the main method.
 *
 * @file Main.java
 * @author Akif Safa Angi
 * @brief Main method
 * @version 1.0
 * @date 2024-04-21
 */
public class Main {
    /**
     * The main method is the entry point of the program.
     *
     * @param args The command-line arguments
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Main <input_file>");
            return;
        }

        String inputFile = args[0];
        StockDataManager manager = new StockDataManager();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) { // Read input file
            String line;
            while ((line = br.readLine()) != null) { // Process each line
                processCommand(line, manager);
            }
            manager.printTree(); // Print the stocks
            manager.printInOrder();
            manager.printPreOrder();
            manager.printPostOrder();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            String plotType = "line"; // Change to "scatter" for scatter plot
            GUIVisualization frame = new GUIVisualization(plotType); // Create a new instance of GUIVisualization
            frame.setVisible(true); // Make the frame visible

            int size = 1000; // Example sizes
            performPerformanceAnalysis(new int[]{10000, 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000}, frame); // Perform analysis and update GUI
        });
    }

    /**
     * Processes a command from the input file.
     *
     * @param line The command line
     * @param manager The StockDataManager
     */
    private static void processCommand(String line, StockDataManager manager) {
        String[] tokens = line.replace('\u00A0', ' ').split(" "); // Split the line by spaces
        String command = tokens[0];

        switch (command) {
            case "ADD":
                manager.addOrUpdateStock(tokens[1], Double.parseDouble(tokens[2]), Long.parseLong(tokens[3]), Long.parseLong(tokens[4]));
                break;
            case "REMOVE":
                manager.removeStock(tokens[1]);
                break;
            case "SEARCH":
                Stock stock = manager.searchStock(tokens[1]);
                if (stock != null) {
                    System.out.println(stock);
                } else {
                    System.out.println("Stock not found: " + tokens[1]);
                }
                break;
            case "UPDATE":
                manager.updateStock(tokens[1], tokens[2], Double.parseDouble(tokens[3]), Long.parseLong(tokens[4]), Long.parseLong(tokens[5]));
                break;
            default:
                System.out.println("Unknown command: " + command);
                break;
        }
    }

    /**
     * Performs performance analysis on the StockDataManager.
     *
     * @param manager The StockDataManager
     * @param sizes The array of sizes to test
     * @param gui The GUIVisualization
     */
    private static void performPerformanceAnalysis(int[] sizes, GUIVisualization gui) {
            int iterations = 100;

            for(int size : sizes) {
                StockDataManager manager = new StockDataManager(); // Initialize your StockDataManager
                long totalAddTime = 0, totalSearchTime = 0, totalUpdateTime = 0, totalRemoveTime = 0;

                // Pre-add 'size' elements
                for (int i = 0; i < size; i++) {
                    manager.addOrUpdateStock("SYM" + i, Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000));
                }

                for (int j = 0; j < iterations; j++) {
                    long startTime, endTime;

                    // Measure time for ADD operation
                    startTime = System.nanoTime();
                    for(int x = 0; x < 1000; x++) {
                        manager.addOrUpdateStock("SYM" + (size + x), Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000));
                    }
                    endTime = System.nanoTime();
                    totalAddTime += (endTime - startTime);

                    // Measure time for SEARCH operation
                    startTime = System.nanoTime();
                    for(int x = 0; x < 1000; x++) {
                        manager.searchStock("SYMD" + x);
                    }
                    endTime = System.nanoTime();
                    totalSearchTime += (endTime - startTime);

                    // Measure time for UPDATE operation
                    startTime = System.nanoTime();
                    for(int x = 0; x < 1000; x++) {
                        manager.addOrUpdateStock("SYMD" + x, Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000));
                    }
                    endTime = System.nanoTime();
                    totalUpdateTime += (endTime - startTime);

                    for(int x = 0; x < 1000; x++) {
                        manager.removeStock("SYMD" + x);
                    }

                    // Measure time for REMOVE operation
                    startTime = System.nanoTime();
                    for(int x = 0; x < 500; x++) {
                        manager.removeStock("SYM" + (size - x - 1));
                    }
                    endTime = System.nanoTime();
                    totalRemoveTime += (endTime - startTime);
                }

                long averageAddTime = totalAddTime / iterations;
                long averageSearchTime = totalSearchTime / iterations;
                long averageUpdateTime = totalUpdateTime / iterations;
                long averageRemoveTime = totalRemoveTime / iterations;

                System.out.println("Average ADD time for " + size + " operations: " + averageAddTime + " ns");
                System.out.println("Average SEARCH time for " + size + " operations: " + averageSearchTime + " ns");
                System.out.println("Average UPDATE time for " + size + " operations: " + averageUpdateTime + " ns");
                System.out.println("Average REMOVE time for " + size + " operations: " + averageRemoveTime + " ns");

                // Add data point to GUI
                gui.addDataPoint(size, averageAddTime, averageSearchTime, averageUpdateTime, averageRemoveTime);
            }
    }
}