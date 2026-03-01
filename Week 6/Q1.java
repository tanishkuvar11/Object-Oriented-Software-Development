import java.io.*;
import java.util.Scanner;

class Q1 {

    private static final String FILE_NAME = "rooms.txt";
    private static final int ROOM_TYPE_LENGTH = 20;
    private static final int RECORD_SIZE = 53;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Room");
            System.out.println("2. View Room");
            System.out.println("3. Update Booking Status");
            System.out.println("4. Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addRoom(sc);
                    break;
                case 2:
                    viewRoom(sc);
                    break;
                case 3:
                    updateBooking(sc);
                    break;
                case 4:
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void addRoom(Scanner sc) {
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rw")) {

            System.out.print("Room Number: ");
            int roomNumber = sc.nextInt();
            sc.nextLine();

            long position = (long) (roomNumber - 1) * RECORD_SIZE;
            file.seek(position);

            System.out.print("Room Type: ");
            String roomType = sc.nextLine();

            System.out.print("Price per Night: ");
            double price = sc.nextDouble();

            System.out.print("Booking Status (true/false): ");
            boolean isBooked = sc.nextBoolean();

            file.writeInt(roomNumber);

            StringBuilder sb = new StringBuilder(roomType);
            sb.setLength(ROOM_TYPE_LENGTH);
            file.writeChars(sb.toString());

            file.writeDouble(price);
            file.writeBoolean(isBooked);

            System.out.println("Room added successfully.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewRoom(Scanner sc) {
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "r")) {

            System.out.print("Enter Room Number: ");
            int roomNumber = sc.nextInt();

            long position = (long) (roomNumber - 1) * RECORD_SIZE;
            file.seek(position);

            int rNo = file.readInt();

            char[] type = new char[ROOM_TYPE_LENGTH];
            for (int i = 0; i < ROOM_TYPE_LENGTH; i++) {
                type[i] = file.readChar();
            }

            double price = file.readDouble();
            boolean status = file.readBoolean();

            System.out.println("Room Number: " + rNo);
            System.out.println("Room Type: " + new String(type).trim());
            System.out.println("Price: " + price);
            System.out.println("Booked: " + status);

        } catch (EOFException e) {
            System.out.println("Room does not exist.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void updateBooking(Scanner sc) {
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rw")) {

            System.out.print("Enter Room Number: ");
            int roomNumber = sc.nextInt();

            long position = (long) (roomNumber - 1) * RECORD_SIZE;
            file.seek(position);

            file.readInt();

            for (int i = 0; i < ROOM_TYPE_LENGTH; i++) {
                file.readChar();
            }

            file.readDouble();

            System.out.print("New Booking Status (true = book / false = vacate): ");
            boolean newStatus = sc.nextBoolean();

            file.writeBoolean(newStatus);

            System.out.println("Booking status updated.");

        } catch (EOFException e) {
            System.out.println("Room does not exist.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}