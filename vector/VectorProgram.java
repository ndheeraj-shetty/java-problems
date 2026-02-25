import java.util.Scanner;

class Vector {
    private double[] components;
    private int dimension;

    // Constructor
    public Vector(double[] components) throws InvalidDimensionException {
        if (components.length != 2 && components.length != 3) {
            throw new InvalidDimensionException("Vector must be 2D or 3D only.");
        }

        this.dimension = components.length;
        this.components = components;
    }

    // Addition
    public Vector add(Vector v)
            throws DimensionMismatchException, InvalidDimensionException {

        if (this.dimension != v.dimension) {
            throw new DimensionMismatchException("Vectors must have same dimension.");
        }

        double[] result = new double[dimension];
        for (int i = 0; i < dimension; i++) {
            result[i] = this.components[i] + v.components[i];
        }

        return new Vector(result);
    }

    // Subtraction
    public Vector subtract(Vector v)
            throws DimensionMismatchException, InvalidDimensionException {

        if (this.dimension != v.dimension) {
            throw new DimensionMismatchException("Vectors must have same dimension.");
        }

        double[] result = new double[dimension];
        for (int i = 0; i < dimension; i++) {
            result[i] = this.components[i] - v.components[i];
        }

        return new Vector(result);
    }

    // Dot Product
    public double dotProduct(Vector v) throws DimensionMismatchException {

        if (this.dimension != v.dimension) {
            throw new DimensionMismatchException("Vectors must have same dimension.");
        }

        double sum = 0;
        for (int i = 0; i < dimension; i++) {
            sum += this.components[i] * v.components[i];
        }

        return sum;
    }

    // Display
    public void display() {
        System.out.print("(");
        for (int i = 0; i < dimension; i++) {
            System.out.print(components[i]);
            if (i < dimension - 1)
                System.out.print(", ");
        }
        System.out.println(")");
    }
}

public class VectorProgram {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            Vector v1 = new Vector(new double[]{3, 4});
            Vector v2 = new Vector(new double[]{1, 2});

            Vector sum = v1.add(v2);
            Vector diff = v1.subtract(v2);
            double dot = v1.dotProduct(v2);

            System.out.println("Vector 1:");
            v1.display();

            System.out.println("Vector 2:");
            v2.display();

            System.out.println("Addition:");
            sum.display();

            System.out.println("Subtraction:");
            diff.display();

            System.out.println("Dot Product: " + dot);

            // Dimension mismatch example
            Vector v3 = new Vector(new double[]{1, 2, 3});
            v1.add(v3);

            // Invalid dimension example
            Vector v4 = new Vector(new double[]{1, 2, 3, 4});

        } catch (InvalidDimensionException e) {
            System.out.println("Invalid Dimension Error: " + e.getMessage());
        } catch (DimensionMismatchException e) {
            System.out.println("Dimension Mismatch Error: " + e.getMessage());
        }
    }
}
