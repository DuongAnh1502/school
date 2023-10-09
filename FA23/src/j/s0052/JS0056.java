package j.s0052;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class JS0056 {
    static Scanner sc = new Scanner(System.in);
    static WorkerHash wk = new WorkerHash();
    public static void main(String[] args) {
        int n;
        do{
            System.out.println("1. Add a Worker");
            System.out.println("2. Up salary");
            System.out.println("3. Down salary");
            System.out.println("4. Display Information salary");
            System.out.println("5. Exit");
            System.out.print("Enter option: ");
            n = sc.nextInt();
            sc.nextLine();
            switch(n){
                case 1 -> wk.addWorker();
                case 2 -> wk.handleSalary("UP");
                case 3 -> wk.handleSalary("DOWN");
                case 4 -> wk.display();
                default -> {System.out.println("Invalid option");}
            }
        } while(n!=6);
    }
}
class Worker {
    private String code;
    private String name;
    private int age;
    private int salary;
    private String location;
    public Worker() {
    }
    public Worker(String code, String name, int age,int salary,String location) {
        this.code = code;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.location = location;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
}
class WorkerHash {
    private static HashMap<String,Worker> listWorker;
    private static ArrayList<Salary> salaryStatus;
    static Scanner sc = new Scanner(System.in);
    static String isValid(String text,String type) {
        System.out.print(text);
        String input = sc.nextLine();
        switch(type) {
            case "ID" -> {
                if(listWorker.get(input) != null) return isValid("ID is already exist, Enter again : ", type);
                else return input.matches("^W\\s[0-9]+$") ? input : isValid("Enter again : ", type);
            }
            case "oldID" -> {return input.matches("^W\\s[0-9]+$") ? input : isValid("Enter again : ", type);}
            case "WORD" -> {return input.matches("^[a-zA-Z]+$") ? input : isValid("Enter again : ",type);}
            case "AGE" -> {return input.matches("^[1-4][0-9]$") ? input : isValid("Enter age again : ", type);}
            case "NUM" -> {return input.matches("^[1-9][0-9]*$") ? input : isValid("Enter salary again : ", type);}
            default -> {return input;}
        }
    }
    public WorkerHash() {
        listWorker = new HashMap<>();
        salaryStatus = new ArrayList<>();
    }
    public void addWorker() {
        String code = isValid("Enter code : ", "ID");
        String name = isValid("Enter name : ", "WORD");
        int age = Integer.parseInt(isValid("Enter age : ", "AGE"));
        int salary = Integer.parseInt(isValid("Enter salary : ","NUM"));
        String location = isValid("Enter location : ", "default");
        listWorker.put(code, new Worker(code,name,age,salary,location));
    }
    public void handleSalary(String type) {
        String code = isValid("Enter code : ", "oldID");
        Worker worker = listWorker.get(code);
        if(worker != null) {
            int salary = Integer.parseInt(isValid("Enter salary : ", "NUM"));
            switch(type) {
                case "UP" -> worker.setSalary(salary+worker.getSalary());
                case "DOWN" -> worker.setSalary(worker.getSalary()-salary);
            }
            LocalDate date = LocalDate.now();
            salaryStatus.add(new Salary(code,worker.getName(),worker.getAge(),worker.getSalary() > 0 ? worker.getSalary() : 0,type,date));
        } else System.out.println("Worker not founded!!");
    }
    public void display() {
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s\n","Code","Name","Age","Salary","Status","Date");
        for(Salary s:salaryStatus) {
            System.out.println(s.toString());
        }
    }
}
class Salary {
    private String code;
    private String name;
    private int age;
    private int salary;
    private String status;
    private LocalDate date;
    public Salary() {
    }
    public Salary(String code, String name,int age,int salary, String status, LocalDate date) {
        this.code = code;
        this.age = age;
        this.name = name;
        this.salary = salary;
        this.status = status;
        this.date = date;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("%-20s %-20s %-20d %-20d %-20s %-20s",code,name,age,salary,status,date.format(formatter));
    }
}