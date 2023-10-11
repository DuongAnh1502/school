package j.s0052;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class JS0059 {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Person> list = new ArrayList<>();
    static String isValid(String text,String type) {
        System.out.print(text);
        String input = sc.nextLine();
        switch(type) {
            case "PATH" -> {
                File file = new File(input);
                if(!file.exists()) isValid("File does not exits\nEnter again : ", type);
                return input;
            }
            case "MONEY" -> {return input.matches("^\\d+$") ? input : isValid("Enter money again : ", type);}
            case "PATHwrite" -> {
                File file = new File(input);
                if(!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException ex) {
                        System.out.println("Error!");
                    }
                }
                return input;
            }
            default -> {return input;}
        }
    }
    static void findPerson() {
        String path = isValid("Enter path : ", "PATH");
        list = readFile(path);
        float money = Float.parseFloat(isValid("Enter money : ", "MONEY"));
        System.out.printf("%-15s %-15s %-15s\n","Name","Adress","Money");
        for(Person p : list) {
            if(p.getMoney() > money) {
                System.out.println(p.toString());
            }
        }
    }
    static void copyText() {
        String pathRead = isValid("Enter path : ", "PATH");
        list = readFile(pathRead);
        String pathWrite = isValid("Enter new file name : ", "PATHwrite");
        writeFile(pathWrite);
    }
    public static ArrayList readFile(String path) {
        ArrayList<Person> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                String arr[] = line.split(",");
                list.add(new Person(arr[0],arr[1],Float.parseFloat(arr[2])));
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return list;
    }
    public static void writeFile(String path) {
        try {
            FileWriter fw = new FileWriter(path);
            String line = "";
            for (Person person : list) {
                line += person.getName()+ "," + person.getAddress()+","+person.getMoney()+"\n";
            }
            fw.write(line);
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        int n;
        do {
            System.out.println("1. Find person info");
            System.out.println("2. Copy Text to new file");
            System.out.println("3. Exit");
            System.out.print("Choose : ");
            n = sc.nextInt();
            sc.nextLine();
            switch(n) {
                case 1 -> findPerson();
                case 2 -> copyText();
                default -> {System.out.println("Invalid option!");}
            }
        } while(n!=3);
    }
}
class Person {
    private String name;
    private String address;
    private float money;
    public Person(String name, String address, float money) {
        this.name = name;
        this.address = address;
        this.money = money;
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
    public float getMoney() {
        return money;
    }
    public void setMoney(float money) {
        this.money = money;
    }
    @Override
    public String toString() {
        return String.format("%-15s %-15s %-15f", name,address,money);
    }
}
