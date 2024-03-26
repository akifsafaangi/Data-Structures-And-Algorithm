public class customer extends person {
    private order[] orders;
    private int operator_ID;
    private int order_size;

    public customer(String name, String surname, String address, String phone, int ID, int operator_ID) {
        super(name, surname, address, phone, ID);
        this.operator_ID = operator_ID;
    }

    public void print_customer() {
        super.print_information();
        System.out.println("Operator ID: " + operator_ID);
    }

    public void print_orders() {
        for (int i = 0; i < order_size; i++) {
            System.out.print("Order #" + (i + 1) + " => ");
            orders[i].print_order();
        }
        System.out.println("----------------------------");
    }

    public void define_orders(order[] orders) {
        this.orders = new order[100];
        int i = 0;
        order_size = 0;
        while(i < orders.length && orders[i] != null) {
            if(orders[i].getCustomerID() == ID) {
                this.orders[order_size] = orders[i];
                order_size++;
            }
            i++;
        }
    }

    public order[] getOrders() {
        return orders;
    }

    public int getOperatorID() {return operator_ID;}
}