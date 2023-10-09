package j.s0052;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.function.Predicate;
public class ManageEastAsiaCountries {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<EastAsiaCountries> list = new ArrayList<>();
    static void addCountryInformation() {
        System.out.print(" ");
        String code = isValidName("Enter code of country:");
        String name = isValidName("Enter name of country: ");
        float total = isValidNumber("Enter total of country: ");
        String terrain = isValidName("Enter terrain of country: ");
        list.add(new EastAsiaCountries(code,name,total,terrain));
    }
    static float isValidNumber(String text) {
        System.out.print(text);
        String input = sc.nextLine();
        return input.matches("[0-9]+") ? Float.parseFloat(input) : isValidNumber("Enter again: ");
    }
    static String isValidName(String text) {
        System.out.print(text);
        String input = sc.nextLine();
        return input.matches("^[a-zA-Z]+$") ? input : isValidName("Enter again : ");
    }
    static void getRecentlyEnteredInformation(ArrayList<EastAsiaCountries> ls) {
        System.out.println("Country code\t\tCountry name\t\tTotal area\t\tCountry terrain");
        for(EastAsiaCountries ct:ls) {
            ct.display();
        }
    }
    static ArrayList<EastAsiaCountries> searchInformationByName(Predicate<EastAsiaCountries> p) {
        ArrayList<EastAsiaCountries> rs = new ArrayList<>();
        for(EastAsiaCountries c: list)
            if(p.test(c)) {
                rs.add(c);
            }
        return rs;
    }
    static void sortInformationByAscendingOrder() {
        Collections.sort(list, Comparator.comparing(EastAsiaCountries::getCountryName));
        System.out.println("Sorted Information by Country Name:");
        for (EastAsiaCountries country : list) {
            country.display();
        }
    }
    public static void main(String[] args) {
        int n;
        do {
            System.out.println("1. Enter the information for 11 countries in Southeast Asia.");
            System.out.println("2. Display already information.");
            System.out.println("3. Search the country according to the entered country's name.");
            System.out.println("4. Display the information increasing with the country name.");
            System.out.println("5. Exit.");
            System.out.print("Enter option : ");
            n = sc.nextInt();
            sc.nextLine();
            switch(n) {
                case 1 -> {addCountryInformation();}
                case 2 -> {getRecentlyEnteredInformation(list);} 
                case 3 -> {
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    getRecentlyEnteredInformation(searchInformationByName(p -> p.getCountryName().equalsIgnoreCase(name)));
                }
                case 4 -> {sortInformationByAscendingOrder();}
                default -> {System.out.println("Invalid option!!");}
            }
        } while(n!=5);
    }
}