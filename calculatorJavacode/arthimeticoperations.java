import java.util.Scanner;


public class arthimeticoperations {
    public double n1, n2;

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        
        return a / b;
    }

    public double modulus(double a, double b) {
        
        return a % b;
    }

    public static void main(String[] args) {
        arthimeticoperations op = new arthimeticoperations();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the first number");
        op.n1 = scan.nextDouble();
        System.out.println("Enter the second numer");
        op.n2 = scan.nextDouble();
        double sum = op.add(op.n1, op.n2);
        double diff = op.subtract(op.n1, op.n2);
        double prod = op.multiply(op.n1, op.n2);
        double div = op.divide(op.n1, op.n2);
        double remainder = op.modulus(op.n1, op.n2);
        System.out.println("Sum : " + sum + " diff : " + diff + " prod : " + prod + " div : " + div + " remainder : " + remainder);


    }
}
    