package j.s0052;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
public class JS0055 {
    static Scanner sc = new Scanner(System.in);
    static DoctorHash dc = new DoctorHash();
    public static void main(String[] args) {
        int n;
        do{
            System.out.println("1. Add Doctor");
            System.out.println("2. Update Doctor");
            System.out.println("3. Delete Doctor");
            System.out.println("4. Search Doctor");
            System.out.println("5. Print Doctor");
            System.out.println("6. Exit");
            System.out.print("Enter option: ");
            n = sc.nextInt();
            sc.nextLine();
            switch(n){
                case 1 -> dc.addDoctor();
                case 2 -> dc.updateDoctor();
                case 3 -> dc.deleteDoctor();
                case 4 -> dc.searchDoctor();
                case 5 -> dc.printDoctor();
                default -> {System.out.println("Invalid option");}
            }
        } while(n!=6);
    }
}
class Doctor {
    private String code;
    private String name;
    private String specialization;
    private int availability;
    public Doctor() {
    }
    public Doctor(String code, String name, String specialization, int availability) {
        this.code = code;
        this.name = name;
        this.specialization = specialization;
        this.availability = availability;  
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
    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    public int getAvailability() {
        return availability;
    }
    public void setAvailability(int availability) {
        this.availability = availability;
    }
    @Override
    public String toString() {
        return String.format("%-20s %-20s %-20s %-20d",code,name,specialization,availability);
    }
}
class DoctorHash {
    private static HashMap<String,Doctor> listDoctor;
    static Scanner sc = new Scanner(System.in);
    static String isValid(String text,String type) {
        System.out.print(text);
        String input = sc.nextLine();
        switch(type) {
            case "ID" -> {
                if(listDoctor.get(input) != null) return isValid("ID is already exist, Enter again : ", type);
                else return input.matches("^DOC\\s[0-9]+$") ? input : isValid("Enter again : ", type);
            }
            case "oldID" -> {return input.matches("^DOC\\s[0-9]+$") ? input : isValid("Enter again : ", type);}
            case "WORD" -> {return input.matches("^[a-zA-Z]+$") ? input : isValid("Enter again : ",type);}
            case "NUM" -> {return input.matches("[1-9]+$") ? input : isValid("Enter availability again : ", type);}
            default -> {return "-1";}
        }
    }
    public DoctorHash() {
        listDoctor = new HashMap<>();
    }
    public void addDoctor() {
        String id = isValid("Enter ID : ","ID");
        String name = isValid("Enter name : ", "WORD");
        String specialization = isValid("Enter specialization : ", "WORD");
        int availability = Integer.parseInt(isValid("Enter availability : ", "NUM"));
        listDoctor.put(id, new Doctor(id,name,specialization,availability));
    }
    public void updateDoctor() {
        String id = isValid("Enter ID : ", "oldID");
        Doctor doctor = listDoctor.get(id);
        if(doctor != null) {
            doctor.setName(isValid("Enter name : ", "WORD"));
            doctor.setSpecialization(isValid("Enter specialization : ", "WORD"));
            doctor.setAvailability(Integer.parseInt(isValid("Enter availability : ", "NUM")));
        } else System.out.println("Doctor is not founded");
    }
    public void deleteDoctor() {
        String id = isValid("Enter ID : ", "oldID");
        if(listDoctor.get(id) != null) listDoctor.remove(id);
        else System.out.println("Doctor is not founded");
    }
    public void searchDoctor() {
        String id = isValid("Enter ID : ", "oldID");
        if(listDoctor.get(id) != null) System.out.println(listDoctor.get(id));
        else System.out.println("Doctor is not founded");
    }
    public void printDoctor() {
        System.out.printf("%-20s %-20s %-20s %-20s\n","Code","Name","Specialization","Availability");
        Collection<Doctor> doctor = listDoctor.values();
        for (Doctor dc : doctor) {
            System.out.println(dc.toString());
        }
    }
}