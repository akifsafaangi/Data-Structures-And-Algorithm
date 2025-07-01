import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * The Main class contains the main method.
 *
 * @file Main.java
 * @author Akif Safa Angi
 * @brief Main method
 * @version 1.0
 * @date 2024-05-29
 */
public class Main {
    /** 
     * The SocialNetworkGraph object which represents a social network graph.
     */
    private static SocialNetworkGraph network = new SocialNetworkGraph();
    /**
     * The scanner object for reading input.
     */
    private static Scanner scanner = new Scanner(System.in);
    /**
     * The DateTimeFormatter object for parsing date and time.
     */
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * The main method is the entry point of the program.
     *
     * @param args The command-line arguments
     */
    public static void main(String[] args) {
        // Adding some people for demonstration
        network.addPerson("Akif Angi", 25, Arrays.asList("reading", "hiking", "cooking", "watching", "basketball", "gaming"));
        network.addPerson("Safa Angi", 22, Arrays.asList("swimming", "cooking", "watching", "reading"));
        network.addPerson("Ece Tek", 27, Arrays.asList("hiking", "painting"));
        network.addPerson("Burak Kal", 30, Arrays.asList("reading", "swimming", "hiking"));
        network.addPerson("Ahmet Ali", 28, Arrays.asList("running", "swimming", "watching", "painting"));
        network.addPerson("Zeybep Bir", 26, Arrays.asList("reading", "hiking", "cooking", "running"));

        while (true) {
            System.out.println("===== Social Network Analysis Menu =====");
            System.out.println("1. Add person");
            System.out.println("2. Remove person");
            System.out.println("3. Add friendship");
            System.out.println("4. Remove friendship");
            System.out.println("5. Find shortest path");
            System.out.println("6. Suggest friends");
            System.out.println("7. Count clusters");
            System.out.println("8. Exit");
            System.out.print("Please select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addPerson();
                    break;
                case 2:
                    removePerson();
                    break;
                case 3:
                    addFriendship();
                    break;
                case 4:
                    removeFriendship();
                    break;
                case 5:
                    findShortestPath();
                    break;
                case 6:
                    suggestFriends();
                    break;
                case 7:
                    countClusters();
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        }
    }

    private static void addPerson() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter hobbies (comma-separated): ");
        List<String> hobbies = Arrays.asList(scanner.nextLine().split(","));
        network.addPerson(name, age, hobbies);
    }

    private static void removePerson() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter timestamp (yyyy-MM-dd HH:mm:ss): ");
        String timestampStr = scanner.nextLine();
        LocalDateTime timestamp = LocalDateTime.parse(timestampStr, formatter);
        network.removePerson(name, timestamp);
    }

    private static void addFriendship() {
        System.out.print("Enter first person's name: ");
        String name1 = scanner.nextLine();
        System.out.print("Enter first person's timestamp (yyyy-MM-dd HH:mm:ss): ");
        String timestampStr1 = scanner.nextLine();
        LocalDateTime timestamp1 = LocalDateTime.parse(timestampStr1, formatter);
        System.out.print("Enter second person's name: ");
        String name2 = scanner.nextLine();
        System.out.print("Enter second person's timestamp (yyyy-MM-dd HH:mm:ss): ");
        String timestampStr2 = scanner.nextLine();
        LocalDateTime timestamp2 = LocalDateTime.parse(timestampStr2, formatter);

        network.addFriendship(name1, timestamp1, name2, timestamp2);
    }

    private static void removeFriendship() {
        System.out.print("Enter first person's name: ");
        String name1 = scanner.nextLine();
        System.out.print("Enter first person's timestamp (yyyy-MM-dd HH:mm:ss): ");
        String timestampStr1 = scanner.nextLine();
        LocalDateTime timestamp1 = LocalDateTime.parse(timestampStr1, formatter);

        System.out.print("Enter second person's name: ");
        String name2 = scanner.nextLine();
        System.out.print("Enter second person's timestamp (yyyy-MM-dd HH:mm:ss): ");
        String timestampStr2 = scanner.nextLine();
        LocalDateTime timestamp2 = LocalDateTime.parse(timestampStr2, formatter);

        network.removeFriendship(name1, timestamp1, name2, timestamp2);
    }

    private static void findShortestPath() {
        System.out.print("Enter start person's name: ");
        String startName = scanner.nextLine();
        System.out.print("Enter start person's timestamp (yyyy-MM-dd HH:mm:ss): ");
        String startTimestampStr = scanner.nextLine();
        LocalDateTime startTimestamp = LocalDateTime.parse(startTimestampStr, formatter);

        System.out.print("Enter end person's name: ");
        String endName = scanner.nextLine();
        System.out.print("Enter end person's timestamp (yyyy-MM-dd HH:mm:ss): ");
        String endTimestampStr = scanner.nextLine();
        LocalDateTime endTimestamp = LocalDateTime.parse(endTimestampStr, formatter);

        network.findShortestPath(startName, startTimestamp, endName, endTimestamp);
    }

    private static void suggestFriends() {
        System.out.print("Enter person's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter person's timestamp (yyyy-MM-dd HH:mm:ss): ");
        String timestampStr = scanner.nextLine();
        LocalDateTime timestamp = LocalDateTime.parse(timestampStr, formatter);

        System.out.print("Enter maximum number of friends to suggest: ");
        int maxSuggestions = scanner.nextInt();
        scanner.nextLine();

        network.suggestFriends(name, timestamp, maxSuggestions);
    }

    private static void countClusters() {
        network.countClusters();
    }
}