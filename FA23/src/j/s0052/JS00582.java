package j.s0052;
import java.util.*;
public class JS00582 {
    static Scanner sc = new Scanner(System.in);
    static HashMap<String,String> dictionary = new HashMap<>();
    static String isValid(String text,String type) {
        System.out.print(text);
        String input = sc.nextLine();
        switch(type) {
            case "OLD" -> {
                if(dictionary.get(input.toUpperCase())!=null) {
                    return isValid("This word already exists\nEnter again : ", type);
                } else return input.matches("^[a-zA-Z\s]+$") ? input.toUpperCase() : isValid("At least 1 character!\nEnter again : ",type);
            }
        }
        return input.matches("^[a-zA-Z\s]+$") ? input.toUpperCase() : isValid("At least 1 character!\nEnter again : ",type);
    }
    static void addWord(){
        String eng = isValid("Enter English : ","OLD");
        String vi = isValid("Enter Vietnamese : ","DF");
        dictionary.put(eng,vi);
    }
    static void deleteWord() {
        String eng = isValid("Enter English : ","DF");
        if (dictionary.get(eng) != null) {
            dictionary.remove(eng);
            System.out.println("Remove Successful");
        } else System.out.println("Word not found!!");
    }
    static void translateWord() {
        String eng = isValid("Enter English : ","DF");
        System.out.println(dictionary.get(eng) != null ? "Vietnamese : "+dictionary.get(eng) : "Word not found!!");
    }
    public static void main(String[] args) {
        int n;
        do {
            System.out.println("1. Add Word");
            System.out.println("2. Delete Word");
            System.out.println("3. Translate");
            System.out.println("4. Exit");
            System.out.print("Choose : ");
            n = sc.nextInt();
            sc.nextLine();
            switch(n) {
                case 1 -> addWord();
                case 2 -> deleteWord();
                case 3 -> translateWord();
                default -> {System.out.println("Invalid option!!");}
            }
        } while(n!=4);
    }
}