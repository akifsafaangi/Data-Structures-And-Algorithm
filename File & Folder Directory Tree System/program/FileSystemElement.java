import java.sql.Timestamp;

/**
 * The FileSystemElement abstrac class which represents a FileSystemElement.
 *
 * @file FileSystemElement.java
 * @author Akif Safa Angi
 * @brief An abstract class with common attributes and methods for both files and directories. 
 * @version 1.0
 * @date 2024-04-21
 */
public abstract class FileSystemElement {

    /** The name of the element */
    protected String name;
    
    /** The timestamp date when element is created */
    protected Timestamp dateCreated;
    
    /** The parent element refence of the the object */
    protected FileSystemElement parent = null;

    /** 
     * Constructs a new FileSystemElement object.
     * 
     * @param name The name of the new element
     * @param parent The parent of the this element
     */
    public FileSystemElement(String name, FileSystemElement parent) {
        this.name = name;
        this.parent = parent;
        this.dateCreated = new Timestamp(System.currentTimeMillis());
    }

    /**
     * Returns the name of the element.
     * 
     * @return the name of the element
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the date when element is created.
     * 
     * @return the date when element is created
     */
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    /**
     * Returns the parent element refence of the the object.
     * 
     * @return the parent FileSystemElement 
     */
    public FileSystemElement getParent() {
        return parent;
    }

    /**
     * Changes parent of the object.
     * 
     * @param parent The new parent
     */
    public void setParent(FileSystemElement parent) {
        this.parent = parent;
    }

    /**
     * Abstract function.
     * Prints the information of object.
     * 
     * @param prefix Prefix
     */
    public abstract void print(String prefix);
}