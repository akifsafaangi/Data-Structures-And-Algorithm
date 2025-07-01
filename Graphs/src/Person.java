import java.util.*;
import java.time.*;

/**
 * The Person class which represents a Person.
 *
 * @file Person.java
 * @author Akif Safa Angi
 * @brief Represents a Person that holds person informations.
 * @version 1.0
 * @date 2024-05-29
 */
public class Person {
    /**
     * The name of the person.
     */
    String name;
    /**
     * The age of the person.
     */
    int age;
    /**
     * The hobbies of the person.
     */
    List<String> hobbies;
    /**
     * The timestamp of when the person created.
     */
    LocalDateTime timestamp;

    /**
     * Constructs a new Person object.
     *
     * @param name The name of the person
     * @param age The age of the person
     * @param hobbies The hobbies of the person
     * @param timestamp The timestamp of when the person created
     */
    public Person(String name, int age, List<String> hobbies, LocalDateTime timestamp) {
        this.name = name;
        this.age = age;
        this.hobbies = hobbies;
        this.timestamp = timestamp;
    }

    /**
     * Gets the timestamp of the person.
     *
     * @return LocalDateTime The timestamp of the person
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Gets the name of the person.
     *
     * @return String The name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the age of the person.
     *
     * @return int The age of the person
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets the hobbies of the person.
     *
     * @return List<String> The hobbies of the person
     */
    public List<String> getHobbies() {
        return hobbies;
    }

    /**
     * Overrides the toString method to return the person's information.
     */
    @Override
    public String toString() {
        return name + " (Age: " + age + ", Hobbies: " + hobbies + ")";
    }

    /**
     * Overrides the equals method to compare two person objects.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return Objects.equals(name, person.name);
    }

    /**
     * Overrides the hashCode method to return the hash code of the person object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
