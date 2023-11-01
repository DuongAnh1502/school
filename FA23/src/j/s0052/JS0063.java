package j.s0052;
import java.util.ArrayList;
import java.util.Scanner;
public class JS0063 {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Programer> list = new ArrayList<>();
    static String validate(String text,String type) {
        System.out.print(text);
        String input = sc.nextLine();
        switch(type) {
            case "NAME" -> {return input.matches("[A-Za-z]+") ? input : validate("Enter only character\nPlease enter name : ", type);}
            case "NUM" -> {
                if(input.matches("^-?\\d+(\\.\\d+)?$")) {
                    return Integer.parseInt(input) > 0 ? input : validate("Salary is greater than zero\nPlease enter salary : ", type);
                }
                else return validate("Please enter only number\nPlease enter salary : ", type);
            }
            default -> {return input;}
        }
    }
    static void inputProgramer() {
        System.out.println("Input information Programer");
        String name = validate("Please enter name : ", "NAME");
        String address = validate("Please enter address : ", "NAME");
        float salary = Float.parseFloat(validate("Please enter salary : ", "NUM"));
        list.add(new Programer(name,address,salary));
    }
    public static void main(String[] args) {
        for(int i = 0;i < 3;i++) {
            inputProgramer();
        }
        for(Programer pro : list) {
            pro.display();
        }
    }
}
class Programer {
    private String name;
    private String address;
    private float salary;
    public Programer() {
    }
    public Programer(String name, String address, float salary) {
        this.name = name;
        this.address = address;
        this.salary = salary;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public float getSalary() {
        return salary;
    }
    public void setSalary(float salary) {
        this.salary = salary;
    }
    public void display() {
        System.out.println("Information of Person : "+getName());
        System.out.println("Name : "+getName());
        System.out.println("Address : "+getAddress());
        System.out.println("Salary : "+getSalary());
    }
}