import java.util.Scanner;
import java.io.File;

public class hw1 {
    public static void main(String[] args) {
        order[] orders = new order[100];
        customer[] customers = new customer[100];
        operator[] operators = new operator[100];
        int length;
        length = set_lines(orders, customers, operators);
        ID_Input(customers, operators, length);
    }
    public static int set_lines(order[] orders, customer[] customers, operator[] operators) {
        file_info[] inputs = new file_info[100];
        int order_count = 0;
        int customer_count = 0;
        int operator_count = 0;
        try {
            File file = new File("content.txt"); // Create a File object 
            Scanner sc = new Scanner(file); // Create a Scanner object to read from the file
            int index = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(";"); // Split to parts
                if(parts.length > 0 && !line.endsWith(";")) { // If it's not empty and it doesn't end with ";"
                    inputs[index] = new file_info(parts);
                    index++;
                }
            }
            sc.close();
        }  catch (Exception ex) {
            System.out.println("Error occured: " + ex);
        }
        for(int i = 0;i < file_info.getLength();i++) {
                String[] tempContent = inputs[i].getContent();
                if(check_order_information(tempContent, inputs[i].getSize()) == 0) { // If line is order
                    orders[order_count] = new order(tempContent[1], Integer.parseInt(tempContent[2]), Integer.parseInt(tempContent[3]), Integer.parseInt(tempContent[4]), Integer.parseInt(tempContent[5]));
                    order_count++;
                } else {
                    int flag = check_information(tempContent, inputs[i].getSize());
                    if(flag == 0 && control_ID(customers, Integer.parseInt(tempContent[5])) == 0) {  // If line is retail customer and it's ID is unique
                        customers[customer_count] = new retail_customer(tempContent[1], tempContent[2], tempContent[3], tempContent[4], Integer.parseInt(tempContent[5]), Integer.parseInt(tempContent[6]));
                        customers[customer_count].define_orders(orders);
                        customer_count++;
                    } else if (flag == 1 && control_ID(customers, Integer.parseInt(tempContent[5])) == 0) {  // If line is corporate customer and it's ID is unique
                        customers[customer_count] = new corporate_customer(tempContent[1], tempContent[2], tempContent[3], tempContent[4], Integer.parseInt(tempContent[5]), Integer.parseInt(tempContent[6]), tempContent[7]);
                        customers[customer_count].define_orders(orders);
                        customer_count++;
                    } else if (flag == 2 && control_ID(customers, operators, Integer.parseInt(tempContent[5])) == 0) {  // If line is operator and it's ID is unique
                        operators[operator_count] = new operator(tempContent[1], tempContent[2], tempContent[3], tempContent[4], Integer.parseInt(tempContent[5]), Integer.parseInt(tempContent[6]));
                        operators[operator_count].define_customers(customers);
                        operator_count++;
                    }
                }
            }
        return operator_count + customer_count;
    }
    public static void ID_Input (final customer[] customers, final operator[] operators, final int length) {
        int found = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your ID...");
        if (sc.hasNextInt()) {
            int id = sc.nextInt(); // Enter ID
            
            // Check if input is within the specified range
            if (int_check(id) == 0) {
                for(int i = 0;i < length;i++) {
                    if(operators[i] != null && id == operators[i].getID()) { // If the operator exist and it's ID matched with input
                        System.out.println("*** Operator Screen ***");
                        System.out.println("----------------------------");;
                        operators[i].print_operator();
                        operators[i].print_customers();
                        found = 1;
                        break;
                    } else if(customers[i] != null && id == customers[i].getID()) { // If the customer exist and it's ID matched with input
                        System.out.println("*** Customer Screen ***");
                        customers[i].print_customer();
                        customers[i].print_orders();
                        found = 1;
                        break;
                    }
                }
                if(found == 0) {
                    System.out.println("No operator/customer was found with ID " + id + ". Please try again.");
                }
            } else {
                System.out.println("Input must be between 0 and " + Integer.MAX_VALUE + ".");
            }
        } else {
            System.out.println("Invalid input. Please enter a valid integer.");
        }
    }
    public static int check_order_information (final String[] tempContent, final int length) {
        if(length != 6) {
            return -1;
        }
        if(!tempContent[0].equals("order")) {
            return -1;
        }
        if(string_check(tempContent[1]) == -1) {
            return -1;
        }
        for(int i = 2;i < 6;i++) {
            try {
                int flag = int_check(Integer.parseInt(tempContent[i])); // Convert to int, if it's not int throws an exception
                
                // Check if the integer is valid
                if (flag == -1) {
                    return -1;
                } else if (flag == 1 && i != 4) { // If the integer is zero and it's not status 
                    return -1;
                }
            } catch (Exception e) {
                // Handle the case where the string cannot be parsed to an integer
                return -1;
            }
        }
        return Integer.parseInt(tempContent[4]) < 4 ? 0 : -1;
    }
    public static int check_information (final String[] tempContent, final int length) {
        if(tempContent[0].equals("retail_customer")  || tempContent[0].equals("operator")) {
            if(length != 7) {
                return -1;
            }
        } else if (tempContent[0].equals("corporate_customer")) {
            if(length != 8) {
                return -1;
            }
        } else {
            return -1;
        }
        for(int i = 1;i < 5;i++) {
            if(string_check(tempContent[i]) == -1) {
                return -1;
            }
        }
        for(int i = 5;i < 7;i++) {
            try {
                if(int_check(Integer.parseInt(tempContent[i])) != 0) {
                    return -1;
                }
            } catch (Exception e) {
                return -1;
            }
        }
        if(tempContent[0].equals("retail_customer")) {
            return 0;
        } else if (tempContent[0].equals("corporate_customer")) {
            return 1;
        } else {
            return 2;
        }
    }
    public static int string_check (final String str) {
        return (str.length() > 0) ? 0 : -1;
    }
    public static int int_check (final int num) {
        if (num == 0) {
            return 1;
        }
        if (num < 0) {
            return -1;
        }

        // Check if the integer is small enough to be stored in an integer variable
        if (num > Integer.MAX_VALUE) {
            return -1;
        }
        return 0;
    }
    public static int control_ID (final customer[] customers, final int ID) {
        int i = 0;
        while(i < customers.length && customers[i] != null) {
            if(customers[i].getID() == ID) {
                return -1;
            }
            i++;
        }
        return 0;
    }
    public static int control_ID (final customer[] customers, final operator[] operators, final int ID) {
        int i = 0;
        while(i < customers.length && customers[i] != null) {
            if(customers[i].getID() == ID) {
                return -1;
            }
            i++;
        }
        i = 0;
        while(i < operators.length && operators[i] != null) {
            if(operators[i].getID() == ID) {
                return -1;
            }
            i++;
        }
        return 0;
    }
}