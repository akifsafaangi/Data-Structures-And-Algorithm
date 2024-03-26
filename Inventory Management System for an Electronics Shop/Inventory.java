import java.util.LinkedList;
import java.util.ArrayList;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The Inventory class to represent an inventory.
 *
 * @file Inventory.java
 * @author Akif Safa Angi
 * @brief Function implementations related to inventory
 * @version 1.0
 * @date 2024-03-25
 */

public class Inventory {

    /** Linked list which hold Array List devices */
    LinkedList<ArrayList<Device>> devices;

    /**
     * Time Complexity: O(1).
     * It performs basic initializing and adding operations. Time complexity is constant.
     * 
     * Constructs a new Inventory object.
     * Adds 5 new Device ArrayList for all electronic devices
     * Adds some examples to these ArrayLists to test
     */
    public Inventory() {
        devices = new LinkedList<>();
        devices.add(new ArrayList<Device>());
        devices.add(new ArrayList<Device>());
        devices.add(new ArrayList<Device>());
        devices.add(new ArrayList<Device>());
        devices.add(new ArrayList<Device>());

        // Add elements for examples
        devices.get(0).add(new TV("LG OLED evo C3", 5000, 30));
        devices.get(0).add(new TV("Samsung T5300", 4500.50, 50));

        devices.get(1).add(new Laptop("Asus Zenbook 14 OLED", 8000, 40));

        devices.get(2).add(new Smartphone("Apple Iphone 13", 6000.30, 200));
        devices.get(2).add(new Smartphone("Samsung A21", 3000, 300));
        devices.get(2).add(new Smartphone("Sony Xperia Z23", 1000, 20));

        devices.get(3).add(new Headphones("Apple Airpods Pro", 3050.25, 150));
        devices.get(3).add(new Headphones("JBL Tune TW25", 1500, 80));
        devices.get(3).add(new Headphones("Sony MDR", 500, 60));

        devices.get(4).add(new Tablet("Huawei MatePad T 10S", 2000.80, 300));
        devices.get(4).add(new Tablet("Samsung Galaxy Tab A9", 2512.56, 15));
    }

    /**
     * Time Complexity: O(n)
     * It performs basic operations like assigments, printing etc. Time complexity is constant of these operations.
     * parseDouble and parseInt functions' time complexities are O(n).
     * Also checkName funtion's time complexity is O(n).
     * For the all function time complexity will be O(n).
     * 
     * Adds a new device to a specific category in the inventory.
     * @param category The category of the new device
     * @param name The name of the new device
     * @param price The price of the new device
     * @param quantity The quantity of the new device
     */
    public void addDevice(String category, String name, String price, String quantity) {
        double dPrice;
        int iQuantity;
        try {
            dPrice = Double.parseDouble(price);
            iQuantity = Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format");
            return;
        }
        if(category.equals("TV")) {
            if(checkName(name, 0)) {
                System.out.println("This device already exist!");
                return;
            }
            devices.get(0).add(new TV(name, dPrice, iQuantity));
        } else if(category.equals("Laptop")) {
            if(checkName(name, 1)) {
                System.out.println("This device already exist!");
                return;
            }
            devices.get(1).add(new Laptop(name, dPrice, iQuantity));
        } else if(category.equals("Smart Phone")) {
            if(checkName(name, 2)) {
                System.out.println("This device already exist!");
                return;
            }
            devices.get(2).add(new Smartphone(name, dPrice, iQuantity));
        } else if(category.equals("Headphones")) {
            if(checkName(name, 3)) {
                System.out.println("This device already exist!");
                return;
            }
            devices.get(3).add(new Headphones(name, dPrice, iQuantity));                
        } else if(category.equals("Tablet")) {
            if(checkName(name, 4)) {
                System.out.println("This device already exist!");
                return;
            }
            devices.get(4).add(new Tablet(name, dPrice, iQuantity));
        } else {
            System.out.println("Invalid category name");
            return;
        }
        System.out.println(category + ", " + name + ", " + price + "$, " + quantity + " amount added...");
    }

    /**
     * Time Complexity: O(n)
     * k is the size of devices in the ArrayList for the loop.
     * Also there is string equals method, which checks each char of the string. We can say x for the length of the string. Time complexity of this equal method wil be O(x).
     * Time complexity of this function will be(k*x). We can generalize as O(n).
     * 
     * Checks whether a device with that name already exists in the specified category.
     * @param name The name which will be checked
     * @param category The index of category which should be checked
     * @return boolean If the name is exist or not
     */
    public boolean checkName(String name, int category) {
        for(int i = 0;i < devices.get(category).size();i++) {
            if(name.equals(devices.get(category).get(i).getName())) return true;
        }
        return false;
    }

    /**
     * Time Complexity: O(n)
     * The outer loop iterates through each ArrayList<Device>, which contributes O(k) operations.
     * The inner loop iterates through each Device within each ArrayList<Device>, which contributes O(m) operations per outer loop iteration.
     * Also there is string equals method, which checks each char of the string. We can say x for the length of the string. Time complexity of this equal method wil be O(x).
     * So in total time complexity of the funtion will be O(k*m*x). We can say O(n) to generalize.
     * 
     * Removes the device with the given name from inventory, if any.
     * @param name The name of the device which will be deleted
     */
    public void removeDevice(String name) {
        // Loops for all devices
        for(ArrayList<Device> dv : devices) {
            for(Device d : dv) {
                if(name.equals(d.getName())) { // If there is device with this name
                    dv.remove(dv.indexOf(d)); // Get index of the device and remove it
                    System.out.println("Device removed");
                    return;
                }
            }
        }
        System.out.println("Device doesn't exist");
    }

    /**
     * Time Complexity: O(n)
     * parseDouble and parseInt functions' time complexities are O(n).
     * The outer loop iterates through each ArrayList<Device>, which contributes O(k) operations.
     * The inner loop iterates through each Device within each ArrayList<Device>, which contributes O(m) operations per outer loop iteration.
     * So in total time complexity of the funtion will be O(k*m). We can say O(n) to generalize.
     * 
     * Updates the information of the named device.
     * @param name The name of the device
     * @param newName The new name of the device
     * @param price The new price of the device
     * @param quantity The new quantity of the device
     */
    public void updateDevice(String name, String newName, String price, String quantity) {
        double dPrice = 0;
        int iQuantity = 0;
        boolean qEmpty = false;
        boolean pEmpty = false;
        if(newName.isEmpty()) {
            newName = name;
        }
        // Controls for price and quantity. If they are entered valid. Try to convert them to double and int
        try {
            if(!price.isEmpty()) {
                dPrice = Double.parseDouble(price);
            } else {
                pEmpty = true; // User leaved it blank
            }
            if(!quantity.isEmpty()) {
                iQuantity = Integer.parseInt(quantity);
            } else {
                qEmpty = true; // User leaved it blank
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format");
            return;
        }
        if(iQuantity < 0 || dPrice < 0) { // If quantity or price are entered less than 0. Print error and return
            System.out.println("Values can't be less than 0");
            return;
        }
        // Loop for all devices 
        for(ArrayList<Device> dv : devices) {
            for(Device d : dv) {
                if(name.equals(d.getName())) { // If there is a device with this name
                    if(pEmpty) { // If price is entered as empty
                        dPrice = d.getPrice(); // Price will be same
                    }
                    if(qEmpty) { // If quantity is entered as empty
                        iQuantity = d.getQuantity(); // Quantity will be same
                    }
                    d.updateDevice(newName, dPrice, iQuantity);
                    System.out.println(name + " details updated: New name - " + newName + " Price - " + price + "$, Quantity - " + quantity);
                    return;
                }
            }
        }
        System.out.println("Device doesn't exist!");
    }

    /**
     * Time Complexity: O(n)
     * The outer loop iterates through each ArrayList<Device>, which contributes O(k) operations.
     * The inner loop iterates through each Device within each ArrayList<Device>, which contributes O(m) operations per outer loop iteration.
     * So in total time complexity O(k*m). We can say O(n) to generalize.
     * 
     * Displays a list of all devices.
     */
    public void listDevices() {
        int i = 1;
        System.out.println("Device List:");
        for(ArrayList<Device> dv : devices) {
            for(Device d : dv) {
                // System.out.println(i + ". Category: " + d.getCategory() + ", Name: " + d.getName() + ", Price: " + d.getPrice() + "$, Quantity: " + d.getQuantity());
                System.out.printf("%d. Category: %s, Name: %s, Price: %.2f$, Quantity: %d\n", i, d.getCategory(), d.getName(), d.getPrice(), d.getQuantity());
                i++;
            }
        }
    }

    /**
     * Time Complexity: O(n)
     * There are some basic compare and assigments. Time complexities are O(1).
     * The outer loop iterates through each ArrayList<Device>, which contributes O(k) operations.
     * The inner loop iterates through each Device within each ArrayList<Device>, which contributes O(m) operations per outer loop iteration.
     * So in total time complexity O(k*m). We can say O(n) to generalize.
     * 
     * Finds the cheapest device in the inventory.
     */
    public void findCheapestDevice() {
        double min = devices.get(0).get(0).getPrice();
        Device temp = null;
        // Loops for all devices
        for(ArrayList<Device> dv : devices) {
            for(Device d : dv) {
                if(d.getPrice() <= min)  { // If price of the device is less equal than minimum. Sets min to this price
                    min = d.getPrice();
                    temp = d;
                }
            }
        }
        if(temp != null) { // If there is a device with minimum price.
            System.out.print("The cheapest device is:");
            System.out.println("Category: " + temp.getCategory() + ", Name: " + temp.getName() + ", Price: " + temp.getPrice() + "$, Quantity: " + temp.getQuantity());
        } else {
            System.out.println("Couldn't find any device!");
        }
    }

    /**
     * Time Complexity: O(n logn), where n is the total number of devices.
     * For adding all devices to new ArrayList, time complexity will be m(size of the all ArrayList) * k(size of the devices in the arraylist). So we can say O(m*k) = O(n).
     * The sort operation typically uses an algorithm like TimSort, which has a time complexity of O(n log n) in the average and worst cases. Since there are n devices in the allDevices list, sorting them will take O(n log n) time.
     * 
     * Prints all devices in the inventory sorted according to price.
     */
    public void sortDevice() {
        System.out.println("Devices sorted by price:");
        ArrayList<Device> allDevices = new ArrayList<>();
        // Add all devices to one array list
        for (ArrayList<Device> deviceList : devices) {
            allDevices.addAll(deviceList);
        }

        // Sort the list of all devices by price
        allDevices.sort((d1, d2) -> Double.compare(d1.getPrice(), d2.getPrice()));
        int i = 1;
        for(Device d : allDevices) {
            System.out.println(i + ". Category: " + d.getCategory() + ", Name: " + d.getName() + ", Price: " + d.getPrice() + "$, Quantity: " + d.getQuantity());
            i++;
        }
    }

    /**
     * Time complexity: O(n)
     * K is the number of ArrayList<Device> elements in devices, and m is the average number of Device objects in each ArrayList<Device>. m*k operations will be done. We can say n as general. 
     * The outer loop iterates through each ArrayList<Device>, which contributes O(k) operations.
     * The inner loop iterates through each Device within each ArrayList<Device>, which contributes O(m) operations per outer loop iteration.
     * 
     * Calculates the total value of all devices.
     * @return double The value of all devices
     */
    public double calculateInventoryValue() {
        double total = 0;
        for(ArrayList<Device> dv : devices) {
            for(Device d : dv) {
                total += d.getPrice();
            }
        }
        return total;
    }

    /**
     * Time Complexity: O(n)
     * There are some basic compare and assigments. Time complexities are O(1).
     * Dominant operation in this function is the nested loop. k as the number of elements in the devices list, m as the average number of Device objects in each ArrayList<Device>.
     * Also there is string equals method, which checks each char of the string. We can say x for the length of the string. Time complexity of this equal method wil be O(x).
     * So time complexity of this nested loop's will be O(m*k*x). We can say O(n) to generalize.
     * 
     * Restocks the quantity of the named device.
     * @param name The name of the device
     * @param type Type of restocking(Add/Remove)
     * @param quantity The quantity which will be added or removed
     */
    public void restockDevice(String name, String type, int quantity) {
        int t = -1;
        int newQ = -1;
        // If type is add
        if(type.equals("Add")) {
            t = 0;
        } else if (type.equals("Remove")) { // If type is remove
             t = 1;
        } else { // If type is invalid give an error and return
            System.out.println("Invalid stock type!");
            return;
        }
        for(ArrayList<Device> dv : devices) {
            for(Device d : dv) {
                if(name.equals(d.getName())) { // If there is a device with this name
                    if(t == 0) { // If the type is add. Adds to quantity
                        newQ = d.getQuantity() + quantity;
                        System.out.println(name + " restocked. New quantity: " + newQ);
                    } else if(t == 1) { // If the type is add. Removes from quantity
                        if(d.getQuantity() - quantity >= 0) { // If new quantity is greater than -1
                            newQ = d.getQuantity() - quantity; // Calculate new quantity
                            System.out.println(name + " stock reduced. New quantity: " + newQ); // Prints new quantity
                        } else { // If new quantity is less than 0. Set to 0 as minimum
                            newQ = 0;
                            System.out.println("You can't reduce stock less than 0. Quantity set to 0. " + name + " stock reduced. New quantity: " + newQ);
                        }
                    }
                    d.updateDevice(name, d.getPrice(), newQ);
                    return;
                }
            }
        }
        System.out.println("Device doesn't exist!");
    }

    /**
     * Time Complexity: O(n)
     * Opening, closing, initializing file etc. are basic file I/O operations. Time complexity is O(1).
     * Dominant operation in this function is the loop, which writes each device's information to file. k as the number of elements in the devices list, m as the average number of Device objects in each ArrayList<Device>.
     * So time complexity of this nested loop's will be O(m*k). We can say O(n) to generalize.
     * 
     * Exports the all devices' information to the file.
     */
    public void exportInventory() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("report.txt"));
            // Get current date
            LocalDate currentDate = LocalDate.now();
            String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("d MMMM yyyy"));    
            
            writer.println("Electronics Shop Inventory Report");
            writer.println("Generated on: " + formattedDate + "\n");
            writer.println("---------------------------------------");
            writer.println("| No. | Category | Name | Price | Quantity |");
            writer.println("---------------------------------------");

            int count = 1;
            // Loops for all devices in the LinkedList and writes to the file of each device
            for (ArrayList<Device> dv : devices) {
                for (Device d : dv) {
                    writer.printf("| %-2d | %-10s | %-25s | $%-8.2f | %-5d |\n", count, d.getCategory(), d.getName(), d.getPrice(), d.getQuantity()); // Write to the file
                    count++;
                }
            }

            writer.println("---------------------------------------\n");
            writer.println("Summary:");
            writer.println("- Total Number of Devices: " + (count - 1));
            writer.printf("- Total Inventory Value: $%.2f\n", calculateInventoryValue());
            writer.print("End of Report");

            writer.close();
            System.out.println("Inventory report has been written to report.txt.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
