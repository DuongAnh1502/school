package j.s0052;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class JS0053 {
    static Scanner sc = new Scanner(System.in);
    static int isValidNumber(String text) {
        System.out.print(text);
        String input = sc.nextLine();
        return input.matches("[0-9]+") ? Integer.parseInt(input) : isValidNumber("Please input numberand number is greater than zero : ");
    }
    static void sortAscending(ArrayList arr) {
        if(arr.isEmpty()) {
            System.out.println("Array is empty please input value");
        } else {
            Collections.sort(arr);
            System.out.println(arr.toString());
        }
    }
    static void sortDescending(ArrayList arr) {
        if(arr.isEmpty()) {
            System.out.println("Array is empty please input value");
        } else {
            Collections.sort(arr, Collections.reverseOrder());
            System.out.println(arr.toString());
        }
    }
    static ArrayList inputElement() {
        System.out.println("Input lenght of an array");
        int n = isValidNumber("Enter number : ");
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 0;i<n;i++) {
            arr.add(isValidNumber("Enter number "+(i+1)+": ")); 
        }
        return arr;
    }
    public static void main(String[] args) {
        int n;
        ArrayList<Integer> arr = new ArrayList<>();
        do {
            System.out.println("1. Input Element");
            System.out.println("2. Sort Ascending");
            System.out.println("3. Sort Descending");
            System.out.println("4. Exit");
            System.out.print("Enter selection : ");
            n = sc.nextInt();
            sc.nextLine();
            switch(n) {
                case 1 -> arr = inputElement();
                case 2 -> sortAscending(arr);
                case 3 -> sortDescending(arr);
                default -> {System.out.println("Invalid option!!");}
            }
        } while(n!=4);
    }
}