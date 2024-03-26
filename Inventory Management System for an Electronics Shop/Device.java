
/**
 * Represents interface of a device.
 * 
 * <p>This interface defines the basic attributes and behaviors of an electronic device,
 * including its category, name, price, and quantity. Classes implementing this interface
 * should provide concrete implementations of these methods to represent specific types
 * of electronic devices.
 * 
 * @file Device.java
 * @author Akif Safa Angi
 * @brief Function implementations related to device
 * @version 1.0
 * @date 2024-03-25
 */

interface Device {

    /**
     * Returns the category of the device.
     * @return the category of the device
     */
    String getCategory();

    /**
     * Returns the name of the device.
     * @return the name of the device
     */
    String getName();
    
    /**
     * Returns the price of the device.
     * @return the price of the device
     */
    double getPrice();
    
    /**
     * Returns the quantity of the device.
     * @return the quantity of the device
     */
    int getQuantity();

    /**
     * Sets the new name of the device.
     * @param name The new name of the device
     */
    void setName(String name);

    /**
     * Sets the new price of the device.
     * @param price The new price of the device
     */
    void setPrice(Double price);

    /**
     * Sets the new quantity of the device.
     * @param quantity The new quantity of the device
     */
    void setQuantity(int quantity);

    /**
     * Updates device details.
     * @param name The new name of the device
     * @param price The new price of the device
     * @param quantity The new quantity of the device
     */
    void updateDevice(String name, Double price, int quantity);
}