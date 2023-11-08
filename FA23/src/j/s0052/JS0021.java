package j.s0052;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
public class JS0021 {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> studentList = new ArrayList<>();
    static String validate(String text,String type) {
        System.out.print(text);
        String input = sc.nextLine();
        switch(type) {
            case "SID" -> {return input.matches("S\\d+") ? input : validate("Enter again : ", type);}
            case "WORD" -> {return input.matches("^[A-Za-z\\s]+$") ? input : validate("Enter again : ", type);}
            case "SEM" -> {return input.matches("^SE\\d+$") ? input : validate("Enter again : ", type);}
            default -> {return input;}
        }
    }
    static void addStudent() {
        String id = validate("Enter student ID : ","SID");
        String name = validate("Enter student name : ", "WORD");
        String semester = validate("Enter semester : ", "SEM");
        String courseName = validate("Enter course name : ", "WORD");
        studentList.add(new Student(id,name,semester,courseName));
    }
    static Student searchName(String name) {
        for(Student s : studentList) {
            if(s.getStudentName().equalsIgnoreCase(name)) return s;
        }
        return null;
    }
    static void findNSort() {
        Comparator<Student> nameComparator = (Student student1, Student student2) -> student1.compareTo(student2);
        studentList.sort(nameComparator);
        String name = validate("Enter name", "WORD");
        System.out.println(searchName(name) == null ? "Student not found!!" : searchName(name).toString());
    }
    static void handleStudent(int n) {
        String name = validate("Enter student ID : ", "SID");
        Student student = searchName(name);
        if (student != null) {
            switch(n) {
                case 1 -> {
                    student.setSemester(validate("Update semester : ", "SEM"));
                    student.setCourseName(validate("Update course name : ", "WORD"));
                }
                case 2 -> {studentList.remove(student);}
                default -> {System.out.println("Back to menu");}
            }
        }
    }
    static void report() {
        HashMap<Student,Integer> studentMap = new HashMap<>();
        for(Student s : studentList) studentMap.put(s, 0);
        for(int i = 0;i<studentList.size();i++) {
            int count = 0;
            for(int j = i;j<studentList.size();j++) {
                Student st1 = studentList.get(i);
                Student st2 = studentList.get(j);
                if (st1.getStudentName().equalsIgnoreCase(st2.getStudentName()) && st1.getCourseName().equalsIgnoreCase(st2.getCourseName()))
                    count++;
            }
            studentMap.put(studentList.get(i), count);
        }
        Collection<Student> key = studentMap.keySet();
        for(Student s : key) {
            System.out.println(s.getStudentName() + "|" + s.getCourseName() + "|" + studentMap.get(s));
        }
    }
    static void updateDelete() {
        int n;
        do {
            System.out.println("1. Update student");
            System.out.println("2. Delete student");
            System.out.println("3. Back");
            System.out.println("Choose : ");
            n = sc.nextInt();
            sc.nextLine();
            handleStudent(n);
        } while(n!=3);
    }
    public static void main(String[] args) {
        int n;
        do {
            System.out.println("1. Create student");
            System.out.println("2. Find and Sort");
            System.out.println("3. Update/Delete");
            System.out.println("4. Report");
            System.out.println("5. Exit");
            System.out.print("Choose : ");
            n = sc.nextInt();
            sc.nextLine();
            switch(n) {
                case 1 -> addStudent();
                case 2 -> findNSort();
                case 3 -> updateDelete();
                case 4 -> report();
            }
        } while(n!=5);
    }
}
class Student implements Comparable<Student>{
    private String id;
    private String studentName;
    private String semester;
    private String courseName;
    public Student() {
    }
    public Student(String id, String studentName, String semester, String courseName) {
        this.id = id;
        this.studentName = studentName;
        this.semester = semester;
        this.courseName = courseName;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public String getSemester() {
        return semester;
    }
    public void setSemester(String semester) {
        this.semester = semester;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", studentName=" + studentName + ", semester=" + semester + ", courseName=" + courseName + '}';
    }
    @Override
    public int compareTo(Student o) {
        return this.studentName.substring(0, 1).compareTo(o.getStudentName().substring(0, 1));
    }
}