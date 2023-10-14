package j.s0052;
import java.io.*;
import java.util.*;
public class JS0058 {
    static Scanner sc = new Scanner(System.in);
    static HashMap<String,String> dictionary = new HashMap<>();
    static String isValid(String text,String type) {
        System.out.print(text);
        String input = sc.nextLine();
        switch(type) {
            case "OLD" -> {
                if(dictionary.get(input.toUpperCase())!=null) {
                    return isValid("This word already exists,\nEnter again : ", type);
                } else return input.matches("^[a-zA-Z\s]+$") ? input.toUpperCase() : isValid("At least 1 character!\nEnter again : ",type);
            }
        }
        return input.matches("^[a-zA-Z\s]+$") ? input.toUpperCase() : isValid("At least 1 character!\nEnter again : ",type);
    }
    static void addWord(){
        readFile();
        String eng = isValid("Enter English : ","OLD");
        String vi = isValid("Enter Vietnamese : ","DF");
        dictionary.put(eng,vi);
        writeFile();
    }
    static void deleteWord() {
        readFile();
        String eng = isValid("Enter English : ","DF");
        if (dictionary.get(eng) != null) {
            dictionary.remove(eng);
            System.out.println("Remove Successful");
        } else System.out.println("Word not found!!");
        writeFile();
    }
    static void translateWord() {
        readFile();
        String eng = isValid("Enter English : ","DF");
        System.out.println(dictionary.get(eng) != null ? "Vietnamese : "+dictionary.get(eng) : "Word not found!!");
    }
    static void readFile() {
        String line = "";
        try(BufferedReader br = new BufferedReader(new FileReader("./src/data/dictionary.txt"))) {
            while((line = br.readLine()) != null) {
                String[] inp = line.split(":");
                dictionary.put(inp[0].toUpperCase(),inp[1]);
            }
        } catch (IOException ex) {
            System.out.println("Find not found!");
        }
    }
    static void writeFile() {
        try {
            FileWriter fw = new FileWriter("./src/data/dictionary.txt");
            String line = "";
            for(String key : dictionary.keySet()) line+= key+":"+dictionary.get(key)+"\n";
            fw.write(line);
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
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