
/**
 * Represents a headphones.
 * <p>This class encapsulates the attributes of an headphones, including its category, name,
 * price, and quantity.
 * 
 * @file Headphones.java
 * @author Akif Safa Angi
 * @brief Function implementations related to headphones
 * @version 1.0
 * @date 2024-03-25
 */

public class Headphones implements Device {

    /** Category of the device */
    private static String category = "Headphones";

    /** Name of the device */
    private String name;

    /** Price of the device */
    private double price;

    /** Quantity of the device */
    private int quantity;
    
    /**
     * Constructs a new Headphones object.
     * @param name The name of the Headphones
     * @param price Price of the Headphones
     * @param quantity The quantity of the Headphones
     */
    public Headphones(String name, double price, int quantity) {
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
     * Returns the name of this Headphones.
     * @return name The name of this Headphones
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Time Complexity: O(1).
     * Returns a value. Time complexity is constant.
     * 
     * Returns the price of this Headphones.
     * @return price The Price of this Headphones
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Time Complexity: O(1).
     * Returns a value. Time complexity is constant.
     * 
     * Returns the quantity of Headphones.
     * @return quantity The quantity of Headphones
     */
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * Time Complexity: O(1).
     * Make an basic assigment. Time complexity is constant.
     * 
     * Change the name of the Headphones.
     * @param name The new name of the Headphones
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Time Complexity: O(1).
     * Make an basic assigment. Time complexity is constant.
     * 
     * Change the price of the Headphones.
     * @param price The new price of the Headphones
     */
    @Override
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Time Complexity: O(1).
     * Make an basic assigment. Time complexity is constant.
     * 
     * Change the quantity of the Headphones.
     * @param quantity The new quantity of the Headphones
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Time Complexity: O(1).
     * Make an basic assigments. Time complexity is constant.
     * 
     * Updates Headphones details.
     * @param name The new name of the Headphones
     * @param price The new price of the Headphones
     * @param quantity The new quantity of the Headphones
     */
    @Override
    public void updateDevice(String name, Double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}