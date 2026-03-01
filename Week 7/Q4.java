import java.util.Scanner;

public class Q4 {

    // Generic method to print any type of array
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.println(element);
        }
        System.out.println("---------------------");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of rooms: ");
        int n = sc.nextInt();
        sc.nextLine();

        Integer[] roomNumbers = new Integer[n];
        String[] roomTypes = new String[n];
        Double[] roomPrices = new Double[n];

        for (int i = 0; i < n; i++) {

            System.out.println("\nRoom " + (i + 1));

            System.out.print("Room Number: ");
            roomNumbers[i] = sc.nextInt();
            sc.nextLine();

            System.out.print("Room Type: ");
            roomTypes[i] = sc.nextLine();

            System.out.print("Room Price: ");
            roomPrices[i] = sc.nextDouble();
        }

        System.out.println("\nRoom Numbers:");
        printArray(roomNumbers);

        System.out.println("Room Types:");
        printArray(roomTypes);

        System.out.println("Room Prices:");
        printArray(roomPrices);

        sc.close();
    }
}