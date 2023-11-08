package j.s0052;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
public class JS006465 {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> list = new ArrayList<>();
    public static boolean isValidDate(String dateStr, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        try {
            LocalDate date = LocalDate.parse(dateStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    static String validate(String text,String type) {
        System.out.print(text);
        String input = sc.nextLine();
        switch(type) {
            case "NAME" -> {return input.matches("[A-Za-z]+") ? input : validate("Enter only character\nPlease enter name : ",type);}
            case "PHONE" -> {return input.matches("^\\d{10}$") ? input : validate("Enter only number\nPhone : ", type);}
            case "EMAIL" -> {return input.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$") ? input : validate("Enter again\nEmail : ", type);}
            case "DATE" -> {return isValidDate(input, "dd/MM/yyyy") ? input : validate("Enter format dd/MM/yyyy\nDate : ", type);}
            case "CLASS" -> {return input.matches("FU\\d+") ? input : validate("Enter with form FU and number\nPlease enter class : ",type);}
            case "SCORE" -> {
                String[] arr = text.split(" ");
                String subject = arr[0];
                if(input.matches("^-?\\d+(\\.\\d+)?$")) {
                    float value = Float.parseFloat(input);
                    if(value >= 0 && value <= 10) return input;
                    if (value < 0) {
                        return validate(subject+" is greater than equal 0\n"+subject+" : ", type);
                    } else if (value > 10) return validate(subject+" is less than equal 10\n"+subject+" : ", type);
                }
                return validate(subject + " must be digit\n"+subject+" : ", type);
            }
            default -> {return input;}
        }
    }
    static void inputStudent() {
        boolean loop = true;
        do{
            String name = validate("Name : ", "NAME");
            String classes = validate("Classes : ", "CLASS");
            String phone = validate("Phone : ","PHONE" );
            String email = validate("Email : ", "EMAIL");
            String date = validate("Date : ", "DATE");
            float maths = Float.parseFloat(validate("Maths : ", "SCORE"));
            float chemestry = Float.parseFloat(validate("Chemestry : ", "SCORE"));
            float physics = Float.parseFloat(validate("Physics : ", "SCORE"));
            list.add(new Student(name,classes,maths,chemestry,physics));
            System.out.println("Do you want to enter more student information?(Y/N)");
            String isLoop = sc.nextLine();
            loop = isLoop.equalsIgnoreCase("Y");
        } while(loop);
    }
    public static void main(String[] args) {
        inputStudent();
        for(Student st : list) {
            st.display();
        }
    }
}
class Student {
    private String name;
    private String classes;
    private float maths;
    private float chemistry;
    private float physics;
    private float avg;
    private String type;
    public Student() {
    }
    public Student(String name, String classes, float maths, float chemistry, float physics) {
        this.name = name;
        this.classes = classes;
        this.maths = maths;
        this.chemistry = chemistry;
        this.physics = physics;
        this.avg = (maths+chemistry+physics)/3;
        if(this.avg > 7.5f) type = "A";
        else if (this.avg >= 6 && this.avg <= 7.5) type = "B";
        else if (this.avg >= 4 && this.avg < 6) type = "C";
        else type = "D";
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getClasses() {
        return classes;
    }
    public void setClasses(String classes) {
        this.classes = classes;
    }
    public float getMaths() {
        return maths;
    }
    public void setMaths(float maths) {
        this.maths = maths;
    }
    public float getChemistry() {
        return chemistry;
    }
    public void setChemistry(float chemistry) {
        this.chemistry = chemistry;
    }
    public float getPhysics() {
        return physics;
    }
    public void setPhysics(float physics) {
        this.physics = physics;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public float getAvg() {
        return avg;
    }
    public void setAvg(float avg) {
        this.avg = avg;
    }
    public void display() {
        System.out.println("Student info");
        System.out.println("Name : "+getName());
        System.out.println("AVG : "+getAvg());
        System.out.println("Type : "+getType());
    }
}