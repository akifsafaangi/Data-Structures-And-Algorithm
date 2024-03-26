public class operator extends person {
    private int wage;
    private customer[] customers;
    private int customer_size;

    public operator(String name, String surname, String address, String phone, int ID, int wage) {
        super(name, surname, address, phone, ID);
        this.wage = wage;
        customer_size = 0;
    }

    public void print_operator() {
        super.print_information();
        System.out.println("Wage: " + wage);
        System.out.println("----------------------------");
    }

    public void print_customers() {
        if(customer_size == 0) {
            System.out.println("This operator doesn't have any customer.");
            System.out.println("----------------------------");
            return;
        }
        for (int i = 0; i < customer_size; i++) {
            System.out.println("Customer #" + (i + 1) + " (" + (customers[i].getClass().getName().equals("retail_customer") ? "a retail customer)" : "a corporate customer)"));
            customers[i].print_customer();
            customers[i].print_orders();
        }
    }

    public void define_customers(customer[] customers) {
        this.customers = new customer[100];
        int i = 0;
        while(i < customers.length && customers[i] != null) {
            if(customers[i].getOperatorID() == ID) {
                this.customers[customer_size] = customers[i];
                customer_size++;
            }
            i++;
        }
    }

    public customer[] getCustomers() {
        return customers;
    }
}