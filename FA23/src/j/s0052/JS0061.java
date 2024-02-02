package j.s0052;
import java.util.Scanner;
public class JS0061 {
    static Scanner sc = new Scanner(System.in);
    static double validate(String text) {
        System.out.print(text);
        String input = sc.nextLine();
        return input.matches("^[+]?\\d*\\.?\\d+$") ? Double.parseDouble(input) : validate("Enter again : ");
    }
    public static void main(String[] args) {
        double a = validate("Enter width of Rectangle : ");
        double b = validate("Enter length of Rectangle : ");
        Rectangle rec = new Rectangle(a, b);
        System.out.println("Perimeter : "+rec.getPerimeter());
        System.out.println("Area : "+rec.getArea());
        a = validate("Enter sideA of Triangle : ");
        b = validate("Enter sideB of Triangle : ");
        double c = validate("Enter sideC of Triangle : ");
        Triangle triangle = new Triangle(a, b, c);
        System.out.println("Perimeter : "+triangle.getPerimeter());
        System.out.println("Area : "+triangle.getArea());
        a = validate("Enter radius of Circle : ");
        Circle cir = new Circle(a);
        System.out.println("Perimeter : "+cir.getPerimeter());
        System.out.println("Area : "+cir.getArea());
    }
}
abstract class Shape {
    public abstract double getPerimeter();
    public abstract double getArea();
    public abstract void printResult();
}
class Circle extends Shape{
    private double radius;
    public Circle(double radius) {
        this.radius = radius;
    }
    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }
    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }
    @Override
    public void printResult() {
        System.out.println("Circle :");
    }
}
class Triangle extends Shape{
    private double sideA;
    private double sideB;
    private double sideC;
    public Triangle(double sideA, double sideB, double sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }
    @Override
    public double getPerimeter() {
        return sideA + sideB + sideC;
    }
    @Override
    public double getArea() {
        double s = getPerimeter() / 2;
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
    }
    @Override
    public void printResult() {
        System.out.println("Triangle with sides " + sideA + ", " + sideB + ", " + sideC);
    }
}
class Rectangle extends Shape {
    private double width;
    private double length;
    public Rectangle(double width, double length) {
        this.width = width;
        this.length = length;
    }
    @Override
    public void printResult() {
        System.out.println("Rectangle with width " + width + " and length " + length);
    }
    @Override
    public double getPerimeter() {
        return 2 * (width + length);
    }
    @Override
    public double getArea() {
        return width * length;
    }
}