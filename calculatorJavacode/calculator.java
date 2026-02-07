 import java.util.Scanner;
 public class calculator
 {
    public int ch;
    public static void main(String[] args) {
        while(true){
            System.out.println("Select one of the operations : 1.add, 2. diff, 3.prod, 4.div, 5.remainder");
            Scanner scan = new Scanner(System.in);
            int ch = scan.nextInt();
            if (ch==0){
                System.out.println("Exiting the caluculator");
                System.exit(0);
            }
            arthimeticoperations op = new arthimeticoperations();
            System.out.println("Enter the firsst number: ");
            op.n1=scan.nextDouble();
            System.out.println("Enter the second number: ");
            op.n2=scan.nextDouble();
            switch(ch)
            {
                case 1:
                    System.out.println("sum" + op.add(op.n1, op.n2));
                    break;
                
                case 2:
                    System.out.println("diff" + op.subtract(op.n1, op.n2));
                    break;

                case 3:
                    System.out.println("prod" + op.multiply(op.n1, op.n2));
                    break;

                case 4:
                    System.out.println("div" + op.divide(op.n1, op.n2));
                    break;

                case 5:
                    System.out.println("remainder" + op.modulus(op.n1, op.n2));
                    break;

                default:
                    System.out.println("invaalid opertions");
            }
        }


    }

 }