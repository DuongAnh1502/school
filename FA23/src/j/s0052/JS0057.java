package j.s0052;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class JS0057 {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        UserInfo user = new UserInfo();
        int n;
        do {
            System.out.println("1. Create new account");
            System.out.println("2. Login system");
            System.out.println("3. Exit");
            System.out.print("> Choose : ");
            n = sc.nextInt();
            sc.nextLine();
            switch(n) {
                case 1 -> user.handleAccount("create");
                case 2 -> user.handleAccount("login");
                default -> {System.out.println("Invalid option!!!");}
            }
        } while(n!=3);   
    }
}
class User {
    private String username;
    private String password;
    public User() {
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
class UserInfo {
    static ArrayList<User> list = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    public void getAllUser() {
        if (list.isEmpty()){
            readFile();
        }
    }
    static String isValid(String text,String type) {
        System.out.print(text);
        String input = sc.nextLine();
        switch(type) {
            case "createUSERNAME" -> {
                for(User user:list) {
                    if(user.getUsername().equals(input))
                        return isValid("Username already exists!!\nEnter again : ", type);
                }
                return input.matches("^[a-zA-Z0-9_.]{5,32}$") ? input : isValid("You must enter least at 5 character, and no space!\nEnter username : ",type);
            }
            case "loginUSERNAME" -> {return input.matches("^[a-zA-Z0-9_.]{5,32}$") ? input : isValid("You must enter least at 5 character, and no space!\nEnter username : ",type);}
            case "PASSWORD" -> {return input.matches("^.{6,}$") ? input : isValid("You must enter least at 6 character, and no space!\n Enter password : ",type);}
            default -> {return input;}
        }
    }
    public void handleAccount(String type) {
        getAllUser();
        String username = isValid("Enter username : ", type+"USERNAME");
        String password = isValid("Enter password : ", "PASSWORD");
        switch(type) {
            case "create" -> {
                list.add(new User(username,password));
                writeFile();
            }
            case "login" -> {
                boolean isLogin = false;
                for(User user : list) {
                    if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                        System.out.println("Login successful!");
                        isLogin = true;
                        break;
                    }
                }
                if (isLogin) {
                    System.out.println("1. Logout");
                    System.out.print(">Choose : ");
                    int m = sc.nextInt();
                    sc.nextLine();
                    if(m==1) break;
                }
            }
        }
    }
    public void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("./src/data/user.txt"))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                String arr[] = line.split(",");
                list.add(new User(arr[0],arr[1]));
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public void writeFile() {
        try {
            FileWriter fw = new FileWriter("./src/data/user.txt");
            String line = "";
            for (User user : list) {
                line += user.getUsername()+ "," + user.getPassword()+"\n";
            }
            fw.write(line);
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}