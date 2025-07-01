import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * The SocialNetworkGraph class which represents a SocialNetworkGraph.
 *
 * @file SocialNetworkGraph.java
 * @author Akif Safa Angi
 * @brief Represents a SocialNetworkGraph that handles social network management.
 * @version 1.0
 * @date 2024-05-29
 */
public class SocialNetworkGraph {
    /**
     * The people map which holds people by their names.
     */
    private Map<String, Person> people = new HashMap<>();
    /**
     * The friendships map which holds friendships between people.
     */
    private Map<Person, List<Person>> friendships = new HashMap<>();

    /**
     * Adds a person to the network.
     *
     * @param name The name of the person
     * @param age The age of the person
     * @param hobbies The hobbies of the person
     */
    public void addPerson(String name, int age, List<String> hobbies) {
        LocalDateTime timestamp = LocalDateTime.now(); // Current timestamp
        Person person = new Person(name, age, hobbies, timestamp); // Create new person
        people.put(name, person); // Add person to people
        friendships.put(person, new ArrayList<>()); // Add person to friendships
        System.out.println("Person added: " + name + " (Timestamp: " + timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ")");
    }

    /**
     * Removes a person from the network.
     *
     * @param name The name of the person
     * @param timestamp The timestamp of when the person removed
     */
    public void removePerson(String name, LocalDateTime timestamp) {
        Person person = getPerson(name);
        if (person != null) { // Check if person exists
            people.remove(name); // Remove person from people
            friendships.remove(person); // Remove person from friendships
            for (List<Person> friends : friendships.values()) { // Remove person from all friendships
                friends.remove(person); // Remove person from friends
            }
            System.out.println("Person removed: " + name);
        } else {
            System.out.println("Person not found or invalid timestamp");
        }
    }

    /**
     * Adds a friendship between two people.
     *
     * @param name1 The name of the first person
     * @param timestamp1 The timestamp of when the friendship added
     * @param name2 The name of the second person
     * @param timestamp2 The timestamp of when the friendship added
     */
    public void addFriendship(String name1, LocalDateTime timestamp1, String name2, LocalDateTime timestamp2) {
        Person person1 = getPerson(name1);
        Person person2 = getPerson(name2);
        if (person1 != null && person2 != null) { // Check if both persons exist
            friendships.get(person1).add(person2); // Add friendship
            friendships.get(person2).add(person1); // Add friendship
            System.out.println("Friendship added between " + name1 + " and " + name2);
        } else {
            System.out.println("One or both persons not found in the network.");
        }
    }

    /**
     * Removes a friendship between two people.
     *
     * @param name1 The name of the first person
     * @param timestamp1 The timestamp of when the friendship removed
     * @param name2 The name of the second person
     * @param timestamp2 The timestamp of when the friendship removed
     */
    public void removeFriendship(String name1, LocalDateTime timestamp1, String name2, LocalDateTime timestamp2) {
        Person person1 = getPerson(name1);
        Person person2 = getPerson(name2);
        if (person1 != null && person2 != null) { // Check if both persons exist
            friendships.get(person1).remove(person2); // Remove friendship
            friendships.get(person2).remove(person1); // Remove friendship
            System.out.println("Friendship removed between " + name1 + " and " + name2);
        } else {
            System.out.println("One or both persons not found in the network.");
        }
    }

    /**
     * Finds the shortest path between two people.
     *
     * @param name1 The name of the first person
     * @param timestamp1 The timestamp of when the search started
     * @param name2 The name of the second person
     * @param timestamp2 The timestamp of when the search started
     */
    public void findShortestPath(String name1, LocalDateTime timestamp1, String name2, LocalDateTime timestamp2) {
        Person start = getPerson(name1);
        Person end = getPerson(name2);
        if (start == null || end == null) { // Check if both persons exist
            System.out.println("One or both persons not found in the network.");
            return;
        }

        Map<Person, Person> prev = new HashMap<>(); // Previous person map
        Queue<Person> queue = new LinkedList<>(); // Queue for BFS
        Set<Person> visited = new HashSet<>(); // Visited set

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Person current = queue.poll(); // Dequeue
            if (current.equals(end)) break; // Check if reached end

            for (Person neighbor : friendships.get(current)) { // Visit neighbors
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    prev.put(neighbor, current);
                }
            }
        }

        printPath(start, end, prev); // Print shortest path
    }

    /**
     * Prints the shortest path between two people.
     *
     * @param start The start person
     * @param end The end person
     * @param prev The previous person map
     */
    private void printPath(Person start, Person end, Map<Person, Person> prev) {
        List<Person> path = new ArrayList<>();
        for (Person at = end; at != null; at = prev.get(at)) { // Reconstruct path
            path.add(at);
        }
        Collections.reverse(path); // Reverse path

        if (path.get(0).equals(start)) { // Check if path exists
            System.out.println("Shortest path: " + path.stream().map(Person::getName).reduce((a, b) -> a + " -> " + b).orElse(""));
        } else {
            System.out.println("No path found between " + start.getName() + " and " + end.getName());
        }
    }

    /**
     * Counts the number of clusters in the network.
     */
    public void countClusters() {
        Set<Person> visited = new HashSet<>(); // Visited set
        int clusterCount = 0;
        List<List<Person>> clusters = new ArrayList<>(); // Clusters list

        for (Person person : friendships.keySet()) { // Find clusters
            if (!visited.contains(person)) {
                List<Person> cluster = new ArrayList<>();
                bfs(person, visited, cluster); // BFS
                clusters.add(cluster); // Add cluster
                clusterCount++;
            }
        }

        System.out.println("Number of clusters: " + clusterCount);
        for (int i = 0; i < clusters.size(); i++) { // Print clusters
            System.out.println("Cluster " + (i + 1) + ":");
            for (Person person : clusters.get(i)) {
                System.out.println(person.getName());
            }
        }
    }

    /**
     * Breadth-first search to find clusters.
     *
     * @param start The start person
     * @param visited The visited set
     * @param cluster The cluster list
     */
    private void bfs(Person start, Set<Person> visited, List<Person> cluster) {
        Queue<Person> queue = new LinkedList<>(); // Queue for BFS
        queue.add(start); // Enqueue
        visited.add(start); // Mark as visited

        while (!queue.isEmpty()) {
            Person current = queue.poll();
            cluster.add(current);
            for (Person neighbor : friendships.get(current)) {
                if (!visited.contains(neighbor)) { // Visit neighbors
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    /**
     * Suggests friends for a person based on mutual friends and common hobbies.
     *
     * @param name The name of the person
     * @param timestamp The timestamp of when the suggestion started
     * @param maxSuggestions The maximum number of suggestions
     */
    public void suggestFriends(String name, LocalDateTime timestamp, int maxSuggestions) {
        Person person = getPerson(name);
        if (person == null) {
            System.out.println("Person not found or invalid timestamp");
            return;
        }

        Map<Person, Double> scores = new HashMap<>(); // Scores map
        Map<Person, Integer> mutualFriendsCount = new HashMap<>(); // Mutual friends count map
        Map<Person, Integer> commonHobbiesCount = new HashMap<>(); // Common hobbies count map

        for (Person friend : friendships.get(person)) { // Calculate scores
            for (Person friendOfFriend : friendships.get(friend)) { // Visit friends of friends
                if (!friendOfFriend.equals(person) && !friendships.get(person).contains(friendOfFriend)) { // Check if not friend
                    double score = scores.getOrDefault(friendOfFriend, 0.0); // Get score
                    score += 1;  // mutual friend
                    score += 0.5 * commonHobbies(person, friendOfFriend).size();  // common hobbies
                    scores.put(friendOfFriend, score); // Update score

                    mutualFriendsCount.put(friendOfFriend, mutualFriendsCount.getOrDefault(friendOfFriend, 0) + 1); // Update mutual friends count
                    commonHobbiesCount.put(friendOfFriend, commonHobbiesCount.getOrDefault(friendOfFriend, 0) + commonHobbies(person, friendOfFriend).size()); // Update common hobbies count
                }
            }
        }

        List<Map.Entry<Person, Double>> sortedScores = new ArrayList<>(scores.entrySet());
        sortedScores.sort((e1, e2) -> Double.compare(e2.getValue(), e1.getValue())); // Sort scores

        List<Person> suggestions = new ArrayList<>(); // Suggestions list
        for (int i = 0; i < Math.min(maxSuggestions, sortedScores.size()); i++) {
            suggestions.add(sortedScores.get(i).getKey()); // Add suggestion
        }

        System.out.println("Suggested friends for " + name + ":");
        for (Person suggestedFriend : suggestions) {
            double score = scores.get(suggestedFriend);
            int mutualFriends = mutualFriendsCount.get(suggestedFriend);
            int commonHobbies = commonHobbiesCount.get(suggestedFriend);
            System.out.printf("%s (Score: %.1f, %d mutual friends, %d common hobbies)%n",
                    suggestedFriend.getName(), score, mutualFriends, commonHobbies);
        }
    }

    /**
     * Gets a person by name.
     *
     * @param name The name of the person
     * @return Person The person object
     */
    private Person getPerson(String name) {
        Person person = people.get(name);
        return person != null ? person : null;
    }

    /**
     * Finds common hobbies between two people.
     *
     * @param p1 The first person
     * @param p2 The second person
     * @return List<String> The list of common hobbies
     */
    private List<String> commonHobbies(Person p1, Person p2) {
        List<String> common = new ArrayList<>(p1.getHobbies());
        common.retainAll(p2.getHobbies());
        return common;
    }
}
