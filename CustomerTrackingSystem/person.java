public class person {
    private String name;
    private String surname;
    private String address;
    private String phone;
    protected int ID;

    public person(String name, String surname, String address, String phone, int ID) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.ID = ID;
    }

    public void print_information() {
        System.out.println("Name & Surname: " + name + " " + surname);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phone);
        System.out.println("ID: " + ID);
    }

    public int getID() {
        return ID;
    }
}