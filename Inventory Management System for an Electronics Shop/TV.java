
/**
 * Represents a tv.
 * <p>This class encapsulates the attributes of an tv, including its category, name,
 * price, and quantity.
 * 
 * @file TV.java
 * @author Akif Safa Angi
 * @brief Function implementations related to TV
 * @version 1.0
 * @date 2024-03-25
 */

public class TV implements Device {

    /** Category of the device */
    private static String category = "TV";

    /** Name of the device */
    private String name;

    /** Price of the device */
    private double price;

    /** Quantity of the device */
    private int quantity;
    
    /**
     * Time Complexity: O(1).
     * It doesn't get any input size. It just does basic assignments. Time complexity is constant.
     * 
     * Constructs a new TV object.
     * @param name The name of the TV
     * @param price The Price of the TV
     * @param quantity The quantity of the TV
     */
    public TV(String name, double price, int quantity) {
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
     * Returns the name of this TV.
     * @return name The name of this TV
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Time Complexity: O(1).
     * Returns a value. Time complexity is constant.
     * 
     * Returns the price of this TV.
     * @return price The Price of this TV
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Time Complexity: O(1).
     * Returns a value. Time complexity is constant.
     * 
     * Returns the quantity of TV.
     * @return quantity The quantity of TV
     */
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * Time Complexity: O(1).
     * Make an basic assigment. Time complexity is constant.
     * 
     * Change the name of the TV.
     * @param name The new name of the TV
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Time Complexity: O(1).
     * Make an basic assigment. Time complexity is constant.
     * 
     * Change the price of the TV.
     * @param price The new price of the TV
     */
    @Override
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Time Complexity: O(1).
     * Make an basic assigment. Time complexity is constant.
     * 
     * Change the quantity of the TV.
     * @param quantity The new quantity of the TV
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Time Complexity: O(1).
     * Make an basic assigments. Time complexity is constant.
     * 
     * Updates TV details.
     * @param name The new name of the TV
     * @param price The new price of the TV
     * @param quantity The new quantity of the TV
     */
    @Override
    public void updateDevice(String name, Double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}