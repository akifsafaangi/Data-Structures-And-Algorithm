import java.util.Scanner;
import java.util.LinkedList;

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

    /** File System to manage files and directories */
    private static FileSystem fs = new FileSystem();

    /** Scanner */
    private static Scanner scanner = new Scanner(System.in);
    
    /** Current Directory */
    private static Directory currentDirectory;


    /**
     * The main method is the entry point of the program.
     *
     * @param args The command-line arguments
     */
    public static void main(String[] args) {
        currentDirectory = fs.getRoot();

        while (true) {
            printMenu();
            System.out.print("Please select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.println("Current directory: " + fs.getCurrentPath(currentDirectory));
                    changeDirectory();
                    break;
                case 2:
                    listContents();
                    break;
                case 3:
                    System.out.println("Current directory: " + fs.getCurrentPath(currentDirectory));
                    System.out.print("Create file or directory (f/d): ");
                    String type = scanner.nextLine();
                    if(type.equals("d")) {
                        createDirectory();
                    } else if(type.equals("f")) {
                        createFile();
                    } else {
                        System.out.println("Invalid command.");
                    }
                    break;
                case 4:
                    System.out.println("Current directory: " + fs.getCurrentPath(currentDirectory));
                    System.out.print("Create file or directory (f/d): ");
                    String dType = scanner.nextLine();
                    if(dType.equals("d")) {
                        deleteDirectory();
                    } else if(dType.equals("f")) {
                        deleteFile();
                    } else {
                        System.out.println("Invalid command.");
                    }
                    break;
                case 5:
                    System.out.println("Current directory: " + fs.getCurrentPath(currentDirectory));
                    moveElement();
                    break;
                case 6:
                    search();
                    break;
                case 7:
                    printDirectoryTree();
                    break;
                case 8:
                    sortDirectoryByDate();
                    break;
                case 9:
                    System.out.println("Exiting program!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please select a number from the menu.");
            }
        }
    }

    /**
     * Time Complexity: O(1)
     * Makes basic println operations.
     * 
     * Displays the menu of the program.
     */
    private static void printMenu() {
        System.out.println("===== File System Management Menu =====");
        System.out.println("1. Change directory");
        System.out.println("2. List directory contents");
        System.out.println("3. Create file/directory");
        System.out.println("4. Delete file/directory");
        System.out.println("5. Move file/directory");
        System.out.println("6. Search file/directory");
        System.out.println("7. Print directory tree");
        System.out.println("8. Sort contents by date created");
        System.out.println("9. Exit");
    }

    /**
     * Function which get new path and call fs changeDirectory function.
     */
    private static void changeDirectory() {
        System.out.print("Enter new directory path: ");
        String newPath = scanner.nextLine();
        if(newPath.isEmpty()) {
            System.out.println("Path can't be empty");
            return;
        }
        Directory check = fs.changeDirectory(newPath);
        if(check == null) {
            System.out.println("Path not found: ");
        } else {
            currentDirectory = check;
            System.out.println("Directory changed to: " + fs.getCurrentPath(currentDirectory));
        }
    }

    /**
     * Function which call fs listContents function.
     */
    private static void listContents() {
        fs.listContents(currentDirectory);
    }

    /**
     * Function which get name of the new file and call fs createFile function.
     */
    private static void createFile() {
        System.out.print("Enter name for new file: ");
        String name = scanner.nextLine();
        if(name.isEmpty()) {
            System.out.println("Name can't be empty");
            return;
        }
        fs.createFile(name, currentDirectory);
    }

    /**
     * Function which get name of the new directory and call fs createDirectory function.
     */
    private static void createDirectory() {
        System.out.print("Enter name for new directory: ");
        String name = scanner.nextLine();
        if(name.isEmpty()) {
            System.out.println("Name can't be empty");
            return;
        }
        fs.createDirectory(name, currentDirectory);
    }

    /**
     * Function which get name of the file which will be deleted and call fs deleteFile function.
     */
    private static void deleteFile() {
        System.out.print("Enter name of file to delete: ");
        String name = scanner.nextLine();
        if(name.isEmpty()) {
            System.out.println("Name can't be empty");
            return;
        }
        fs.deleteFile(name, currentDirectory);
    }

    /**
     * Function which get name of the directory which will be deleted and call fs deleteDirectory function.
     */
    private static void deleteDirectory() {
        System.out.print("Enter name of directory to delete: ");
        String name = scanner.nextLine();
        if(name.isEmpty()) {
            System.out.println("Name can't be empty");
            return;
        }
        fs.deleteDirectory(name, currentDirectory);
    }

    /**
     * Function which call fs deleteDirectory function.
     */
    private static void moveElement() {
        // Gets name of file
        System.out.print("Enter name of file/directory to move: ");
        String name = scanner.nextLine();
        if(name.isEmpty()) {
            System.out.println("Name can't be empty");
            return;
        }
        // Enter new path
        System.out.print("Enter new directory path: ");
        String newPath = scanner.nextLine();
        if(newPath.isEmpty()) {
            System.out.println("Path can't be empty");
            return;
        }

        Directory newParent = fs.getRoot();
        newPath = newPath.substring(1); // Remove leading "/"
        // Split path to string parts
        String[] directories = newPath.split("/");
        boolean found;
        for(String dir : directories) {
            found = false;
            if(!dir.isEmpty()) {
                // Get current directory's children
                LinkedList<FileSystemElement> children = newParent.getChildren();
                for(FileSystemElement child : children) {
                    // If path name is found and it's a directory
                    if(child.getClass().getName().equals("Directory") && child.getName().equals(dir)) {
                        // Go to below level directory
                        newParent = (Directory) child;
                        found = true;
                        break;
                    }
                }
                if(!found) {
                    System.out.println("Path not found: " + newPath);
                    return;
                }
            } else {
                newParent = fs.getRoot();
            }
        }
        fs.moveElement(name, currentDirectory, newParent);
    }

    /**
     * Function which will get query and calls fs search function.
     */
    private static void search() {
        System.out.print("Search query: ");
        String query = scanner.nextLine();
        System.out.println("Searching from root...");
        boolean found = fs.search(query);
        if(!found) {
            System.out.println("Not found!");
        }
    }

    /**
     * Function which call fs printDirectoryTree function.
     */
    private static void printDirectoryTree() {
        System.out.println("Path to current directory from root:");
        fs.printDirectoryTree(currentDirectory);
    }

    /**
     * Function which call fs sortDirectoryByDate function.
     */
    private static void sortDirectoryByDate() {
        fs.sortDirectoryByDate(currentDirectory);
    }
}