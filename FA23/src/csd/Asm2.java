package csd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Array;
import java.util.ArrayList;
class Asm2 {
    static ArrayList<Book> listBook = new ArrayList<>();
    public static void readFile() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("FA23/src/data/books.csv.txt"));
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
        } catch (Exception e) {
            System.out.println("Error");
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                System.out.println("Error");
            }
        }
    }
    public static double convertToASCII(String string) {
        char[] chars = string.toCharArray();
        double sum = 0;
        for (char aChar : chars) {
            sum += Character.getNumericValue(aChar);
        }
        return sum;
    }
    static void merge(ArrayList<Book> arr,int l,int m,int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        ArrayList<Book> L = new ArrayList<>(n1);
        ArrayList<Book> R = new ArrayList<>(n2);
        for (int i = 0; i < n1; ++i)
            L.add(arr.get(l + i));
        for (int j = 0; j < n2; ++j)
            R.add(arr.get(m + 1 + j));
        int i = 0, j = 0;
        while (i < n1 && j < n2) {
            if (convertToASCII(L.get(i).getIsbn()) <= convertToASCII(R.get(j).getIsbn())) {
                arr.set(l, L.get(i));
                i++;
            }
            else {
                arr.set(l, R.get(j));
                j++;
            }
            l++;
        }
        while (i < n1) {
            arr.set(l, L.get(i));
            i++;
            l++;
        }
        while (j < n2) {
            arr.set(l, R.get(j));
            j++;
            l++;
        }
    }
    static void sort(ArrayList<Book> arr,int l,int r) {
        if (l < r) {
            int m = (l + r) / 2;
            sort(arr, l, m);
            sort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
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
    public static void main(String[] args) {
        readFile();
        sort(listBook,0,listBook.size()-1);
        display();
    }
}
class Book {
    private String isbn, bookTitle, bookAuthor, publisher, imageS, imageM, imageL;
    private int yearOfPublishcation;

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

}