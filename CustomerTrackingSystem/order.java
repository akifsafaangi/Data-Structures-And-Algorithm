public class order {
    private String product_name;
    private int count;
    private int total_price;
    private int status;
    private int customer_ID;
    private enum Status {
        Initialized,
        Processing,
        Completed,
        Cancelled
    }
    public order(String product_name, int count, int total_price, int status, int customer_ID) {
        this.product_name = product_name;
        this.count = count;
        this.total_price = total_price;
        this.status = status;
        this.customer_ID = customer_ID;
    }

    public void print_order() {
        Status[] statuses = Status.values();
        System.out.print("Product name: " + product_name);
        System.out.print(" - Count: " + count);
        System.out.print(" - Total price: " + total_price);
        System.out.println(" - Status: " + statuses [status] + ".");
    }
    public int getCustomerID() {return customer_ID;}
}