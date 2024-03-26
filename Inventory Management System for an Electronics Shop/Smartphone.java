
/**
 * Represents a smart phone.
 * <p>This class encapsulates the attributes of an smart phone, including its category, name,
 * price, and quantity.
 * 
 * @file Smartphone.java
 * @author Akif Safa Angi
 * @brief Function implementations related to smart phone
 * @version 1.0
 * @date 2024-03-25
 */

public class Smartphone implements Device {

    /** Category of the device */
    private static String category = "Smartphone";

    /** Name of the device */
    private String name;

    /** Price of the device */
    private double price;

    /** Quantity of the device */
    private int quantity;
    
    /**
     * Constructs a new Smartphone object.
     * @param name The name of the Smartphone
     * @param price Price of the Smartphone
     * @param quantity The quantity of the Smartphone
     */
    public Smartphone(String name, double price, int quantity) {
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
     * Returns the name of this Smartphone.
     * @return name The name of this Smartphone
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Time Complexity: O(1).
     * Returns a value. Time complexity is constant.
     * 
     * Returns the price of this Smartphone.
     * @return price The Price of this Smartphone
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Time Complexity: O(1).
     * Returns a value. Time complexity is constant.
     * 
     * Returns the quantity of Smartphone.
     * @return quantity The quantity of Smartphone
     */
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * Time Complexity: O(1).
     * Make an basic assigment. Time complexity is constant.
     * 
     * Change the name of the Smartphone.
     * @param name The new name of the Smartphone
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Time Complexity: O(1).
     * Make an basic assigment. Time complexity is constant.
     * 
     * Change the price of the Smartphone.
     * @param price The new price of the Smartphone
     */
    @Override
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Time Complexity: O(1).
     * Make an basic assigment. Time complexity is constant.
     * 
     * Change the quantity of the Smartphone.
     * @param quantity The new quantity of the Smartphone
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Time Complexity: O(1).
     * Make an basic assigments. Time complexity is constant.
     * 
     * Updates Smartphone details.
     * @param name The new name of the Smartphone
     * @param price The new price of the Smartphone
     * @param quantity The new quantity of the Smartphone
     */
    @Override
    public void updateDevice(String name, Double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}