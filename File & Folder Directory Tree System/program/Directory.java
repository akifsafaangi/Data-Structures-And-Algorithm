import java.util.LinkedList;

/**
 * The Directory class which represents a directory.
 *
 * @file Directory.java
 * @author Akif Safa Angi
 * @brief Represents a directory that can contain other files or directories.
 * @version 1.0
 * @date 2024-04-21
 */
public class Directory extends FileSystemElement {

    /** A Linked List containing child elements */
    private LinkedList<FileSystemElement> children;

    /**
     * Constructs a new Directory object.
     * 
     * @param name The name of the new directory
     * @param parent The parent of the this directory
     */
    public Directory(String name, FileSystemElement parent) {
        super(name, parent);
        children = new LinkedList<>();
    }

    /**
     * Returns the children of the directory.
     * 
     * @return LinkedList<FileSystemElement> LinkedList which holds children 
     */
    public LinkedList<FileSystemElement> getChildren() {
        return children;
    }

    /**
     * Adds new directory to children.
     * 
     * @param element The new directory
     */
    public void addElement(FileSystemElement element) {
        children.add(element);
    }

    /**
     * Removes the element from children.
     * 
     * @param element The directory which will be removed
     */
    public void removeElement(FileSystemElement element) {
        children.remove(element);
    }

    /**
     * Prints the directory name and its children according to tree structure.
     * 
     * @param prefix Prefix
     */
    @Override
    public void print(String prefix) {
        System.out.println(prefix + "* " + getName() + "/");
        for(FileSystemElement elem : children) {
            elem.print(prefix + "   ");
        }
    }
}