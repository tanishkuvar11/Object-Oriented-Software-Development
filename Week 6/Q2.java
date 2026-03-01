import java.io.*;
import java.util.Scanner;

public class Q2 {

    static final String FILE = "rooms.ser";

    static class Room implements Serializable {
        int number;
        String type;
        double price;
        boolean bookingStatus;
        String guest;

        Room(int n, String t, double p, boolean b, String g) {
            number = n;
            type = t;
            price = p;
            bookingStatus = b;
            guest = b ? g : null;
        }

        public String toString() {
            return "\nRoom No: " + number +
                    "\nType: " + type +
                    "\nPrice: " + price +
                    "\nBooked: " + bookingStatus +
                    (bookingStatus ? "\nGuest: " + guest : "") +
                    "\n----------------";
        }
    }

    static void addRoom(Room r) throws Exception {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE, true));
        out.writeObject(r);
        out.close();
    }

    static void display() throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE));
        while (true) {
            Room r = (Room) in.readObject();
            System.out.println(r);
        }
    }

    static void search(int num) throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE));
        while (true) {
            Room r = (Room) in.readObject();
            if (r.number == num) {
                System.out.println(r);
                return;
            }
        }
    }

    static void update(int num, boolean status, String guest) throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE));
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("temp.ser"));

        while (true) {
            Room r = (Room) in.readObject();
            if (r.number == num) {
                r.bookingStatus = status;
                r.guest = status ? guest : null;
            }
            out.writeObject(r);
        }
    }

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1.Add  2.Display  3.Search  4.Update  5.Exit");
            int ch = sc.nextInt();

            if (ch == 1) {
                System.out.print("Number: ");
                int n = sc.nextInt();
                sc.nextLine();

                System.out.print("Type: ");
                String t = sc.nextLine();

                System.out.print("Price: ");
                double p = sc.nextDouble();
                sc.nextLine();

                System.out.print("Booked (true/false): ");
                boolean b = sc.nextBoolean();
                sc.nextLine();

                String g = null;
                if (b) {
                    System.out.print("Guest: ");
                    g = sc.nextLine();
                }

                addRoom(new Room(n, t, p, b, g));
            }

            else if (ch == 2) {
                try {
                    display();
                } catch (EOFException e) {
                }
            }

            else if (ch == 3) {
                System.out.print("Room number: ");
                int n = sc.nextInt();
                try {
                    search(n);
                } catch (EOFException e) {
                    System.out.println("Not found");
                }
            }

            else if (ch == 4) {
                System.out.print("Room number: ");
                int n = sc.nextInt();
                sc.nextLine();

                System.out.print("Booked (true/false): ");
                boolean b = sc.nextBoolean();
                sc.nextLine();

                String g = null;
                if (b) {
                    System.out.print("Guest: ");
                    g = sc.nextLine();
                }

                try {
                    update(n, b, g);
                } catch (EOFException e) {
                    new File(FILE).delete();
                    new File("temp.ser").renameTo(new File(FILE));
                }
            }

            else
                break;
        }
    }
}