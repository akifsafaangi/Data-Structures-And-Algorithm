
/**
 * Represents an tablet.
 * <p>This class encapsulates the attributes of an tablet, including its category, name,
 * price, and quantity.
 * 
 * @file Tablet.java
 * @author Akif Safa Angi
 * @brief Function implementations related to tablet
 * @version 1.0
 * @date 2024-03-25
 */

public class Tablet implements Device {

    /** Category of the device */
    private static String category = "Tablet";

    /** Name of the device */
    private String name;

    /** Price of the device */
    private double price;

    /** Quantity of the device */
    private int quantity;
    
    /**
     * Constructs a new Tablet object.
     * @param name The name of the Tablet
     * @param price Price of the Tablet
     * @param quantity The quantity of the Tablet
     */
    public Tablet(String name, double price, int quantity) {
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
     * Returns the name of this Tablet.
     * @return name The name of this Tablet
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Time Complexity: O(1).
     * Returns a value. Time complexity is constant.
     * 
     * Returns the price of this Tablet.
     * @return price The Price of this Tablet
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Time Complexity: O(1).
     * Returns a value. Time complexity is constant.
     * 
     * Returns the quantity of Tablet.
     * @return quantity The quantity of Tablet
     */
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * Time Complexity: O(1).
     * Make an basic assigment. Time complexity is constant.
     * 
     * Change the name of the Tablet.
     * @param name The new name of the Tablet
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Time Complexity: O(1).
     * Make an basic assigment. Time complexity is constant.
     * 
     * Change the price of the Tablet.
     * @param price The new price of the Tablet
     */
    @Override
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Time Complexity: O(1).
     * Make an basic assigment. Time complexity is constant.
     * 
     * Change the quantity of the Tablet.
     * @param quantity The new quantity of the Tablet
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Time Complexity: O(1).
     * Make an basic assigments. Time complexity is constant.
     * 
     * Updates Tablet details.
     * @param name The new name of the Tablet
     * @param price The new price of the Tablet
     * @param quantity The new quantity of the Tablet
     */
    @Override
    public void updateDevice(String name, Double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}