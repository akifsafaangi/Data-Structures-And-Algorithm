import java.util.Scanner;

/**
 * The Main class contains the main method to run the inventory management program.
 *
 * @file Main.java
 * @author Akif Safa Angi
 * @brief Main method to run the inventory management program
 * @version 1.0
 * @date 2024-03-25
 */
public class Main {
    /**
     * The main method is the entry point of the program.
     * It initializes the inventory, displays the menu, and handles user input.
     * It will be in loop until user choose to exit. 
     *
     * @param args The command-line arguments
     */
    public static void main(String[] args) {
        Inventory inv = new Inventory();
        int chosen = -1;
        Scanner sc = new Scanner(System.in);

        // Menu loop
        while(chosen != 0) {
            showMenu();
            chosen = sc.nextInt();
            sc.nextLine(); // Clear the buffer
            String category, name, price, quantity;

            // Handle user input based on the chosen option
            switch(chosen) {
                case 0:
                    System.out.println("Exitting program");
                    break;
                case 1:
                    // Gets category name
                    System.out.print("Enter category name: ");
                    category = sc.nextLine();
                    // If the user left it blank, get the input again
                    while(category.isEmpty()) {
                        System.out.print("Can't be empty. Enter category name: ");
                        category = sc.nextLine();
                    }

                    // Gets device name
                    System.out.print("Enter device name: ");
                    name = sc.nextLine();
                    // If the user left it blank, get the input again
                    while(name.isEmpty()) {
                        System.out.print("Can't be empty. Enter device name: ");
                        name = sc.nextLine();
                    }

                    // Gets price
                    System.out.print("Enter price(as dollar): ");
                    price = sc.nextLine();
                    // If the user left it blank, get the input again
                    while(price.isEmpty()) {
                        System.out.print("Can't be empty. Enter price(as dollar): ");
                        price = sc.nextLine();
                    }
                    
                    // Gets quantity
                    System.out.print("Enter quantity: ");
                    quantity = sc.nextLine();
                    // If the user left it blank, get the input again
                    while(quantity.isEmpty()) {
                        System.out.print("Can't be empty. Enter quantity: ");
                        quantity = sc.nextLine();
                    }
                    inv.addDevice(category, name, price, quantity);
                    break;
                case 2:
                    System.out.print("Enter device name: ");
                    name = sc.nextLine();
                    // If the user left it blank, get the input again
                    while(name.isEmpty()) {
                        System.out.print("Can't be empty. Enter device name: ");
                        name = sc.nextLine();
                    }
                    inv.removeDevice(name);
                    break;
                case 3:
                    System.out.print("Enter the name of the device to update: ");
                    name = sc.nextLine();
                    while(name.isEmpty()) {
                        System.out.print("Can't be empty. Enter the name of the device to update: ");
                        name = sc.nextLine();
                    }
                    String newName;
                    System.out.print("Enter new name(leave blank to keep current name): ");
                    newName = sc.nextLine();

                    System.out.print("Enter new price(as dollar) (leave blank to keep current price): ");
                    price = sc.nextLine();

                    System.out.print("Enter new quantity (leave blank to keep current quantity): ");
                    quantity = sc.nextLine();
                    // Checks if the all of them are empty. If they are empty no need to update. Give information and skip calling updateDevice
                    if(price.isEmpty() && quantity.isEmpty() && newName.isEmpty()) {
                        System.out.println("Nothing updated for the current device");
                        continue;
                    }
                    inv.updateDevice(name, newName, price, quantity);
                    break;
                case 4:
                    inv.listDevices();
                    break;
                case 5:
                    inv.findCheapestDevice();
                    break;
                case 6:
                    inv.sortDevice();
                    break;
                case 7:
                    System.out.printf("Total inventory value: %.2f$\n", inv.calculateInventoryValue());
                    break;
                case 8:
                    System.out.print("Enter the name of the device to restock: ");
                    name = sc.nextLine();
                    while(name.isEmpty()) {
                        System.out.print("Can't be empty. Enter the name of the device to restock: ");
                        name = sc.nextLine();
                    }
                    // Gets type of the restocking
                    String type;
                    System.out.print("Do you want to add or remove stock? (Add/Remove): ");
                    type = sc.nextLine();
                    while(type.isEmpty()) {
                        System.out.print("Can't be empty. Do you want to add or remove stock? (Add/Remove): ");
                        type = sc.nextLine();
                    }

                    System.out.print("Enter the quantity to add: ");
                    quantity = sc.nextLine();
                    while(quantity.isEmpty()) {
                        System.out.print("Can't be empty. Enter the quantity to add: ");
                        quantity = sc.nextLine();
                    }
                    // If quantity is not int. Throw exception
                    try {
                        inv.restockDevice(name, type, Integer.parseInt(quantity));
                    } catch (Exception e) {
                        System.out.println("Invalid number format.");
                    }
                    break;
                case 9:
                    inv.exportInventory();
                    break;
                default:
                    System.out.println("Invalid Command");
                    break;
            }
        }
    }

    /**
     * Time Complexity: O(1)
     * Makes basic println operations.
     * 
     * Displays the menu of the program.
     */
    public static void showMenu() {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Welcome to the Electronics Inventory Management System!");
        System.out.println("Please select an option:");
        System.out.println("1. Add a new device");
        System.out.println("2. Remove a device");
        System.out.println("3. Update device details");
        System.out.println("4. List all devices");
        System.out.println("5. Find the cheapest device");
        System.out.println("6. Sort devices by price");
        System.out.println("7. Calculate total inventory value");
        System.out.println("8. Restock a device");
        System.out.println("9. Export inventory report");
        System.out.println("0. Exit");
        System.out.println("------------------------------------------------------------------------");
    }
}
