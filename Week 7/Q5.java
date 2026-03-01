import java.util.Scanner;

class Pair<T, U> {

    private T first;
    private U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public void display() {
        System.out.println("Room Number: " + first);
        System.out.println("Guest Name: " + second);
        System.out.println("---------------------");
    }
}

public class Q5 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of bookings: ");
        int n = sc.nextInt();
        sc.nextLine();

        Pair<Integer, String>[] bookings = new Pair[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nBooking " + (i + 1));

            System.out.print("Room Number: ");
            Integer roomNo = sc.nextInt();
            sc.nextLine();

            System.out.print("Guest Name: ");
            String guest = sc.nextLine();

            bookings[i] = new Pair<>(roomNo, guest);
        }

        System.out.println("\nBooking Records:");
        for (int i = 0; i < n; i++) {
            bookings[i].display();
        }

        sc.close();
    }
}