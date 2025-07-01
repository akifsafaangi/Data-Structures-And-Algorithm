import java.util.LinkedList;
import java.util.Comparator;
import java.util.Collections;

/**
 * The FileSystem class which manages file systems.
 *
 * @file FileSystem.java
 * @author Akif Safa Angi
 * @brief Manages the root directory and contains methods for user interactions such as navigating the directory tree, and managing files and directories.
 * @version 1.0
 * @date 2024-04-21
 */
public class FileSystem {
    
    /** Root directory of File System */
    private Directory root;

    /**
     * Constructor of File System.
     * Initializes the root.
     */
    public FileSystem() {
        this.root = new Directory("root", null);
    }

    /**
     * Sorts files within a directory by creation date.
     * 
     * @param dir Directory which will be sorted
     */
    public void sortDirectoryByDate(Directory dir) {
        // Create a copy of the directory's children to avoid modifying the original list
        LinkedList<FileSystemElement> sortedChildren = new LinkedList<>(dir.getChildren());

        // Sort the copied list by date created
        Collections.sort(sortedChildren, new Comparator<FileSystemElement>() {
            @Override
            public int compare(FileSystemElement o1, FileSystemElement o2) {
                return o1.getDateCreated().compareTo(o2.getDateCreated());
            }
        });

        // Print sorted directory contents
        System.out.println("Sorted contents of " + getCurrentPath(dir) + " by date created:");
        for (FileSystemElement child : sortedChildren) {
            if(child instanceof Directory) {
                System.out.println("* " + child.getName() + "/ (" + child.getDateCreated() + ")");
            } else {
                System.out.println(child.getName() + " (" + child.getDateCreated() + ")");
            }
        }
    }

    /**
     * Prints the directory tree showing its structure.
     */
    public void printDirectoryTree(Directory currentDirectory) {
        LinkedList<Directory> path = new LinkedList<>();
        Directory dir = currentDirectory;

        // Traverse from the current directory to the root
        while (dir != null) {
            path.add(dir);
            dir = (Directory) dir.getParent();
        }

        // Reverse the list to start from root
        Collections.reverse(path);

        // Print the directory tree
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            for (int j = 0; j < i; j++) {
                builder.append("   "); // Indentation for subdirectories
            }
            builder.append("* ").append(path.get(i).getName()).append("/");
            if(i == path.size() - 1) {
                builder.append(" (Current Directory)");
            }
            builder.append("\n");
        }

        LinkedList<FileSystemElement> children = currentDirectory.getChildren();
        // Look for all childs and find directories
        for(FileSystemElement child : children) {
            if(child.getClass().getName().equals("Directory")) {
                // Prints directory with directory mark
                for (int j = 0; j < path.size(); j++) {
                    builder.append("   "); // Indentation for subdirectories
                }
                builder.append("* ").append(child.getName()).append("/\n");
            }
        }
        // Look for all childs and find files
        for(FileSystemElement child : children) {
            if(child.getClass().getName().equals("File")) {
                // Prints file
                for (int j = 0; j < path.size(); j++) {
                    builder.append("   "); // Indentation for subdirectories
                }
                builder.append(child.getName()).append("\n");
            }
        }
        System.out.println(builder.toString());
    }

    /**
     * Searches for the name coming from the parameter in the file or directory.
     * 
     * @param name Name of the element which will be searched
     * @return boolean Returns true if file or folder exist
     */
    public boolean search(String name) {
        Directory curr = root;
        if(searchRecursively(name, curr)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Recursive helper for search function.
     * 
     * @param name  Name of the element which will be searched
     * @param directory Current directory which will be searched
     * @return boolean Returns true if file or folder exist
     */
    private boolean searchRecursively(String name, Directory directory) {
        LinkedList<FileSystemElement> children = directory.getChildren();
        // Look all elements of the current directory's children
        for (FileSystemElement child : children) {
            // If there is element with this name
            if (child.getName().equals(name)) {
                if (child instanceof File) {
                    System.out.println("Found: " + getCurrentPath(directory) + "/" + name);
                } else {
                    System.out.println("Found: " + getCurrentPath(directory) + "/" + name + "/");
                }
                return true;
            } 
            // If there is no element, check for children directories to search.
            else if (child instanceof Directory) {
                if (searchRecursively(name, (Directory) child)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Moves files and directories.
     * 
     * @param name  Name of the element which will be moved
     * @param currentDirectory The current directory
     * @param newParent The directory to move the element to
     */
    public void moveElement(String name, Directory currentDirectory, Directory newParent) {        
        LinkedList<FileSystemElement> children = currentDirectory.getChildren();
        for(FileSystemElement child : children) {
            // If moveable file exist
            if(name.equals(child.getName())) {
                FileSystemElement moveable = child;
                currentDirectory.removeElement(moveable); // Delete object from old parent
                newParent.addElement(moveable); // Add object to new parent
                moveable.setParent(newParent); // Sets object's parent reference to new parent
                System.out.println("File moved: " + name + " to " + getCurrentPath(newParent));
                return;
            }
        }
        // If moveable file doesn't exist
        System.out.println("File doesn't exist.");
        return;
    }

    /**
     * Deletes the file if exist.
     * 
     * @param name  Name of the element which will be deleted
     * @param parent The current directory
     */
    public void deleteFile(String name, Directory parent) {
        LinkedList<FileSystemElement> children = parent.getChildren();
        // Look all elements of the current directory's children
        for(FileSystemElement child : children) {
            // If file is exist call remove element and delete it from parent
            if(name.equals(child.getName()) && child instanceof File) {
                child.setParent(null);
                parent.removeElement(child);
                System.out.println("File deleted: " + name);
                return;
            }
        }
        System.out.println("File doesn't exist.");
    }

    /**
     * Deletes the directory if exist.
     * 
     * @param name  Name of the element which will be deleted
     * @param parent The current directory
     */
    public void deleteDirectory(String name, Directory parent) {
        LinkedList<FileSystemElement> children = parent.getChildren();
        // Look all elements of the current directory's children
        for (FileSystemElement dir : children) {
            // If directory is exist calls recursive function to delete directory's contents. Also call remove element and delete it from parent.
            if (dir instanceof Directory && dir.getName().equals(name)) {
                deleteDirectoryRecursively((Directory) dir);
                parent.removeElement(dir);
                dir.setParent(null);
                System.out.println("Directory deleted: " + name + "/");
                return;
            }
        }
        System.out.println("Directory doesn't exist.");
    }

    /**
     * Recursive helper function to Recursively delete directories and their contents.
     * 
     * @param directory The current directory
     */
    private void deleteDirectoryRecursively(Directory directory) {
        LinkedList<FileSystemElement> children = directory.getChildren();
        // Look all elements of the current directory's children
        for (FileSystemElement child : children) {
            // If directory go to inside of directory
            if (child instanceof Directory) {
                deleteDirectoryRecursively((Directory) child);
            }
            // Delete element from current directory
            directory.removeElement(child);
            child.setParent(null);
        }
    }

    /**
     * Creates file in current directory.
     * 
     * @param name The name of the file which will be created
     * @param parent The current directory
     */
    public void createFile(String name, Directory parent) {
        LinkedList<FileSystemElement> children = parent.getChildren();
        // Look all elements of the current directory's children
        for (FileSystemElement child : children) {
            // If there is a directory or file with same name
            if (name.equals(child.getName())) {
                System.out.println("There is a file or directory with same name.");
                return;
            }
        }
        File file = new File(name, parent);
        parent.addElement(file);
        System.out.println("File created: " + name);
    }

    /**
     * Creates directory in current directory.
     * 
     * @param name The name of the directory which will be created
     * @param parent The current directory
     */
    public void createDirectory(String name, Directory parent) {
        LinkedList<FileSystemElement> children = parent.getChildren();
        // Look all elements of the current directory's children
        for (FileSystemElement child : children) {
            // If there is a directory or file with same name
            if (name.equals(child.getName())) {
                System.out.println("There is a file or directory with same name.");
                return;
            }
        }
        Directory directory = new Directory(name, parent);
        parent.addElement(directory);
        System.out.println("Directory created: " + name + "/");
    }

    /**
     * Shows the current path.
     * 
     * @param dir The current directory
     * @return String current path
     */
    public String getCurrentPath(Directory dir) {
        StringBuilder sb = new StringBuilder();
        FileSystemElement current = dir;
        if(dir == root) {
            sb.append("/");
        }
        // Begins from current directory until the root
        while (current != root) {
            // Appends / and current directory's name
            sb.insert(0, "/" + current.getName());
            current = current.getParent();
        }
        return sb.toString();
    }

    /**
     * Lists contents of the current directory, with directories marked.
     * 
     * @param dir The current directory
     */
    public void listContents(Directory dir) {
        System.out.println("Listing contents of " + getCurrentPath(dir) + ":");
        LinkedList<FileSystemElement> children = dir.getChildren();
        // Look for all childs and find directories
        for(FileSystemElement child : children) {
            if(child.getClass().getName().equals("Directory")) {
                // Prints directory with directory mark
                System.out.println("* " + child.getName() + "/");
            }
        }
        // Look for all childs and find files
        for(FileSystemElement child : children) {
            if(child.getClass().getName().equals("File")) {
                // Prints file
                System.out.println(child.getName());
            }
        }
    }
    /** 
     * Finds the directory given the path and returns it to update the current directory.
     * 
     * @param path New directory's path
     * @return Directory New directory
     */
    public Directory changeDirectory(String path) {
        Directory current = root;
        path = path.substring(1); // Remove leading "/"
        String[] directories = path.split("/");
        boolean found;
        // Iterate through each directory name in the list
        for (String dir : directories) {
            found = false; // Initialize found flag for each directory

            // Check if the directory name is not empty
            if (!dir.isEmpty()) {
                LinkedList<FileSystemElement> children = current.getChildren(); // Get children of the current directory
                
                // Iterate through each child element of the current directory
                for (FileSystemElement child : children) {
                    // If the child is and its name matches the current directory name
                    if (child instanceof Directory && child.getName().equals(dir)) {
                        current = (Directory) child; // Update current directory to the found directory
                        found = true;
                        break;
                    }
                }

                // If the directory is not found
                if (!found) {
                    return null; // Return null to indicate directory not found
                }
            }
        }

        // Return the last found directory, which corresponds to the last directory in the list
        return current;
    }

    /**
     * Returns the root of file system.
     * 
     * @return Directory Root directory
     */
    public Directory getRoot() {
        return root;
    }
}