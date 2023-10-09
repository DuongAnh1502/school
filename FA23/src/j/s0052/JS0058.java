package j.s0052;
import java.util.HashMap;
import java.util.Scanner;
public class JS0058 {
    static Scanner sc = new Scanner(System.in);
    static HashMap<String,String> dictionary = new HashMap<>();
    static String isValid(String text) {
        System.out.print(text);
        String input = sc.nextLine();
        return input.matches("^[a-zA-Z\s]+$") ? input : isValid("At leats 1 character!\nEnter again : ");
    }
    static void addWord() {
        String eng = isValid("Enter English : ");
        String vi = isValid("Enter Vietnamese : ");
        dictionary.put(eng,vi);
    }
    static void deleteWord() {
        String eng = isValid("Enter English : ");
        if (dictionary.get(eng) != null) {
            dictionary.remove(eng);
            System.out.println("Remove Successful");
        } else System.out.println("Word not found!!");
    }
    static void translateWord() {
        String eng = isValid("Enter English : ");
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