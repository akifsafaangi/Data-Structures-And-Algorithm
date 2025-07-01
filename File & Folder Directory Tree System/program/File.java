/**
 * The File class which represents a file.
 *
 * @file File.java
 * @author Akif Safa Angi
 * @brief Inherits from FileSystemElement and represents a file.
 * @version 1.0
 * @date 2024-04-21
 */
public class File extends FileSystemElement {
    /**
     * Constructs a new File object.
     * @param name The name of the new file
     * @param parent The parent of the this file
     */
    public File(String name, FileSystemElement parent) {
        super(name, parent);
    }

    /**
     * Prints the file.
     * @param prefix Prefix
     */
    @Override
    public void print(String prefix) {
        System.out.println(prefix + getName());
    }
}