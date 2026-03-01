import java.util.Scanner;

public class Q2 {

    // Generic Method
    public static <T> void display(T data) {
        System.out.println(data);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Room Number: ");
        Integer roomNumber = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Room Type: ");
        String roomType = sc.nextLine();

        System.out.print("Enter Price per Night: ");
        Double price = sc.nextDouble();

        System.out.print("Enter Booking Status (true/false): ");
        Boolean bookingStatus = sc.nextBoolean();

        System.out.println("\nRoom Details:");
        display(roomNumber);
        display(roomType);
        display(price);
        display(bookingStatus);

        sc.close();
    }
}