package csd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class PE{
    static ArrayList<Book> listBook = new ArrayList<>();
    static void readFile() {
        try(BufferedReader br = new BufferedReader(new FileReader("./src/data/books.csv.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\";\"");
                String isbn = parts[0];
                String bookTitle = parts[1];
                String bookAuthor = parts[2];
                int yearOfPublication = Integer.parseInt(parts[3].replaceAll("\"",""));
                String publisher = parts[4];
                String imageS = parts[5];
                String imageM = parts[6];
                String imageL = parts[7];
                listBook.add(new Book(isbn, bookTitle, bookAuthor, publisher, imageS, imageM, imageL, yearOfPublication));
            }
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }
    static int partition(int l,int r) {
        int i = l-1;
        int j = r+1;
        double pivot = listBook.get(l).convertToASCII(); 
        while(i<j) {
            do {
                i++;
            } while(listBook.get(i).convertToASCII() < pivot);
            do {
                j--;
            } while(listBook.get(j).convertToASCII() > pivot);
            if (i<j) {
                Book temp = listBook.get(i);
                listBook.set(i, listBook.get(j));
                listBook.set(j,temp);
            }
        }
        return j;
    }
    static void quickSort(int l,int r) {
        if(l<r) {
            int pivot = partition(l,r);
            quickSort(l, pivot);
            quickSort(pivot+1, r);
        }
    }
    public static void main(String[] args) {
        readFile();
        quickSort(0, listBook.size()-1);
        display();
    }
    public static void display() {
        System.out.println("Top 5 highest books by ISBN:");
        for (int i = listBook.size()-1; i >= listBook.size()-5; i--) {
            System.out.println(listBook.get(i).toString());
        }
        System.out.println("Top 5 lowest books by ISBN:");
        for (int i = 0; i < 5; i++) {
            System.out.println(listBook.get(i).toString());
        }
    }
}
class Book implements Comparable<Book>{
    private String isbn, bookTitle, bookAuthor, publisher, imageS, imageM, imageL;
    private int yearOfPublishcation;
    public double convertToASCII() {
        char[] chars = this.isbn.toCharArray();
        double sum = 0;
        for (char aChar : chars) {
            sum += Character.getNumericValue(aChar);
        }
        return sum;
    }
    public Book(String isbn, String bookTitle, String bookAuthor, String publisher, String imageS, String imageM, String imageL, int yearOfPublisher) {
        this.isbn = isbn;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.publisher = publisher;
        this.imageS = imageS;
        this.imageM = imageM;
        this.imageL = imageL;
        this.yearOfPublishcation = yearOfPublisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getImageS() {
        return imageS;
    }

    public void setImageS(String imageS) {
        this.imageS = imageS;
    }

    public String getImageM() {
        return imageM;
    }

    public void setImageM(String imageM) {
        this.imageM = imageM;
    }

    public String getImageL() {
        return imageL;
    }

    public void setImageL(String imageL) {
        this.imageL = imageL;
    }

    public int getYearOfPublisher() {
        return yearOfPublishcation;
    }

    public void setYearOfPublisher(int yearOfPublisher) {
        this.yearOfPublishcation = yearOfPublisher;
    }

    @Override
    public String toString() {
        return "Book{" +"yearOfPublishcation=" + yearOfPublishcation+" , " + "isbn=" + isbn + ", bookTitle=" + bookTitle + ", bookAuthor=" + bookAuthor + ", publisher=" + publisher + ", imageS=" + imageS + ", imageM=" + imageM + ", imageL=" + imageL  + "}";
    }

    @Override
    public int compareTo(Book o) {
        return (int) (this.convertToASCII() - o.convertToASCII());
    }
}
