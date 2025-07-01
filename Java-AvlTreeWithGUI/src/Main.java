import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Main <input_file>");
            return;
        }

        String inputFile = args[0];
        StockDataManager manager = new StockDataManager();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                processCommand(line, manager);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Perform a simple performance analysis
        performPerformanceAnalysis(manager, new int[]{1000, 2000, 3000, 4000, 5000, 6000 , 7000, 8000, 9000, 10000});
    }

    private static void processCommand(String line, StockDataManager manager) {
        String[] tokens = line.split(" ");
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

    private static void performPerformanceAnalysis(StockDataManager manager, int[] sizes) {
        for(int size : sizes) {
            long startTime, endTime;

            // Measure time for ADD operation
            startTime = System.nanoTime();
            for (int i = 0; i < size; i++) {
                System.out.println(i);
                manager.addOrUpdateStock("SYM" + i, Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000));
            }
            endTime = System.nanoTime();
            System.out.println("Average ADD time for " + size + " operations: " + (endTime - startTime) / size + " ns");

            // Measure time for SEARCH operation
            startTime = System.nanoTime();
            for (int i = 0; i < size; i++) {
                manager.searchStock("SYM" + i);
            }
            endTime = System.nanoTime();
            System.out.println("Average SEARCH time for " + size + " operations: " + (endTime - startTime) / size + " ns");

            startTime = System.nanoTime();
            for (int i = 0; i < size; i++) {
                manager.updateStock("SYM" + i, "SYM" + (i + size), Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000));
            }
            endTime = System.nanoTime();
            System.out.println("Average UPDATE time for " + size + " operations: " + (endTime - startTime) / size + " ns");
            
            // Measure time for REMOVE operation
            startTime = System.nanoTime();
            for (int i = 0; i < size; i++) {
                manager.removeStock("SYM" + i);
            }
            endTime = System.nanoTime();
            System.out.println("Average REMOVE time for " + size + " operations: " + (endTime - startTime) / size + " ns");
        }
    }
}
