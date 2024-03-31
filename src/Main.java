import java.util.Scanner;

public class Main {

    static Scanner scan = new Scanner(System.in);
    static double[] dataForTest = new double[] { 1, 2, 3, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10 };
    static EnrtopyCalculator p = new EnrtopyCalculator();

    public static void main(String[] args) {

        int choice;
        do {
            System.out.println("1.set data");
            System.out.println("2.exit");
            choice = scan.nextInt();
            scan.nextLine();
            switch (choice) {
                case 1:
                    setData();
                    break;
                case 2:
                    System.out.println("exiting program");
                    return;
            }
        } while (true);

    }

    // ask the user to specify data he want to calculate
    public static void setData() {
        int choice;
        do {
            System.out.println("---------------------------");
            System.out.println("choose from list :\n1.Enter new data\n2.Use existed one\n3.Back");
            System.out.println("---------------------------");
            choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1:
                    // ask user to enter a data to save it in an array
                    System.out.println("enter size of data : ");
                    int size = scan.nextInt();
                    double[] userData = new double[size];
                    for (int i = 0; i < size; i++) {
                        System.out.print("enter data for index " + i + " : ");
                        userData[i] = scan.nextDouble();
                    }
                    p.setSourceData(userData);
                    list();
                    break;
                case 2:
                    p.setSourceData(dataForTest);
                    list();
                    break;

                case 3:
                    return;

            }
        } while (true);

    }




    // ask user to calculate or display table

    public static void list() {
        int choice;
        do {
            System.out.println("---------------------------");
            System.out.println("choose from list");
            System.out.println("1.calculate entropy");
            System.out.println("2.calculate frequencies");
            System.out.println("3.calculate probability");
            System.out.println("4.calculate self-information");
            System.out.println("5.display data");
            System.out.println("6.back");
            System.out.println("---------------------------");
            choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1:
                    p.calculateEntropy();
                    break;
                case 2 :
                    p.calculateFrequencies();
                    break;
                case 3 :
                    p.calculateProbability();
                    break;
                case 4 :
                    p.calculateSelfInformation();
                    break;
                case 5:
                    p.displayTable();
                    break;
                case 6:
                    System.out.println("See you later!");
                    return;

            }
        } while (true);

    }


}
