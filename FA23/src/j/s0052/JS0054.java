package j.s0052;
import java.util.ArrayList;
import java.util.Scanner;
public class JS0054 {
    static int i = 0;
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Contact> contactList = new ArrayList<>();
    static String isValidPhoneNumber(String text) {
        System.out.print(text);
        String phone = sc.nextLine();
        return phone.matches("^(\\d{10}|\\d{3}-\\d{3}-\\d{4}|\\d{3}-\\d{3}-\\d{4}(x|ext)\\d{4}|\\d{3}\\.\\d{3}\\.\\d{4}|\\d{3} \\d{3} \\d{4})$") ? phone : isValidPhoneNumber("Please input Phone flow\r\n" + //
                                "• 1234567890\r\n" + //
                                "• 123-456-7890 \r\n" + //
                                "• 123-456-7890 x1234\r\n" + //
                                "• 123-456-7890 ext1234\r\n" + //
                                "• 123.456.7890\r\n" + //
                                "• 123 456 7890\nEnter again: ");
    }
    static String isValidName(String text) {
        System.out.print(text);
        String input = sc.nextLine();
        return input.matches("^[a-zA-Z]+$") ? input : isValidName("Enter again :");
    }
    static int isValidNumber(String text) {
        System.out.print(text);
        String input = sc.nextLine();
        return input.matches("[1-9]+") ? Integer.parseInt(input) : isValidNumber("ID is digit, Enter again : ");
    }
    static void addContact() {
        String firstname = isValidName("Enter First Name : ");
        String lastname = isValidName("Enter Last Name :");
        String group = isValidName("Enter group :");
        String adress = isValidName("Enter adress :");
        String phone = isValidPhoneNumber("Enter phone :");
        contactList.add(new Contact(++i,group,adress,phone,lastname,firstname));
    }
    static void displayContact() {
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s","ID","Fullname","Fistname","Lastname","Group","Address","Phone");
        System.out.println();
        for (Contact contact : contactList) {
            System.out.println(contact.toString());
        }
    }
    static void deleteContact() {
        int id = isValidNumber("Enter ID : ");
        for(int i = 0;i<contactList.size();i++) {
            if(contactList.get(i).getId() == id) contactList.remove(i);
        }
    }
    public static void main(String[] args) {
        int n;
        do {
            System.out.println("========= Contact program =========");
            System.out.println("1. Add a Contact");
            System.out.println("2. Display all Contact");
            System.out.println("3. Delete a Contact");
            System.out.println("4. Exit");
            System.out.print("Enter selection : ");
            n = sc.nextInt();
            sc.nextLine();
            switch(n) {
                case 1 -> addContact();
                case 2 -> displayContact();
                case 3 -> deleteContact();
                default -> {System.out.println("Invalid option!!");}
            }
        } while(n!=4);
    }
}
class Contact {
    private int id;
    private String fullname;
    private String group;
    private String address;
    private String phone;
    private String lastname;
    private String firstname;
    public Contact(int id, String group, String address, String phone, String lastname,String firstname) {
        this.id = id;
        this.fullname = firstname+" "+lastname;
        this.group = group;
        this.address = address;
        this.phone = phone;
        this.lastname = lastname;
        this.firstname = firstname;
    }
    public Contact() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public String getGroup() {
        return group;
    }
    public void setGroup(String group) {
        this.group = group;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    @Override
    public String toString() {
        return String.format("%-20d %-20s %-20s %-20s %-20s %-20s %-20s",id,fullname,firstname,lastname,group,address,phone);
    } 
}