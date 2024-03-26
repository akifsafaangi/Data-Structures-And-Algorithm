
/**
 * Represents laptop.
 * <p>This class encapsulates the attributes of an laptop, including its category, name,
 * price, and quantity.
 * 
 * @file Laptop.java
 * @author Akif Safa Angi
 * @brief Function implementations related to Laptop
 * @version 1.0
 * @date 2024-03-25
 */

public class Laptop implements Device {

    /** Category of the device */
    private static String category = "Laptop";

    /** Name of the device */
    private String name;

    /** Price of the device */
    private double price;

    /** Quantity of the device */
    private int quantity;
    
    /**
     * Constructs a new Laptop object.
     * @param name The name of the Laptop
     * @param price Price of the Laptop
     * @param quantity The quantity of the Laptop
     */
    public Laptop(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Time Complexity: O(1).
     * Returns a value. Time complexity is constant.
     * 
     * Returns the category of the device.
     * @return category The category of the device
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Time Complexity: O(1).
     * Returns a value. Time complexity is constant.
     * 
     * Returns the name of this Laptop.
     * @return name The name of this Laptop
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Time Complexity: O(1).
     * Returns a value. Time complexity is constant.
     * 
     * Returns the price of this Laptop.
     * @return price The Price of this Laptop
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Time Complexity: O(1).
     * Returns a value. Time complexity is constant.
     * 
     * Returns the quantity of Laptop.
     * @return quantity The quantity of Laptop
     */
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * Time Complexity: O(1).
     * Make an basic assigment. Time complexity is constant.
     * 
     * Change the name of the Laptop.
     * @param name The new name of the Laptop
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Time Complexity: O(1).
     * Make an basic assigment. Time complexity is constant.
     * 
     * Change the price of the Laptop.
     * @param price The new price of the Laptop
     */
    @Override
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Time Complexity: O(1).
     * Make an basic assigment. Time complexity is constant.
     * 
     * Change the quantity of the Laptop.
     * @param quantity The new quantity of the Laptop
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Time Complexity: O(1).
     * Make an basic assigments. Time complexity is constant.
     * 
     * Updates Laptop details.
     * @param name The new name of the Laptop
     * @param price The new price of the Laptop
     * @param quantity The new quantity of the Laptop
     */
    @Override
    public void updateDevice(String name, Double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}