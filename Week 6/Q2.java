import java.io.*;
import java.util.*;

public class Q2 {

    private static final String FILE_NAME = "rooms.ser";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Room");
            System.out.println("2. Display All Rooms");
            System.out.println("3. Search Room by Number");
            System.out.println("4. Update Booking Status");
            System.out.println("5. Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addRoom(sc);
                    break;
                case 2:
                    displayAllRooms();
                    break;
                case 3:
                    searchRoom(sc);
                    break;
                case 4:
                    updateRoom(sc);
                    break;
                case 5:
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static List<Room> readRooms() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            return (List<Room>) ois.readObject();

        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private static void writeRooms(List<Room> rooms) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(rooms);

        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    private static void addRoom(Scanner sc) {
        List<Room> rooms = readRooms();

        System.out.print("Room Number: ");
        int number = sc.nextInt();
        sc.nextLine();

        System.out.print("Room Type: ");
        String type = sc.nextLine();

        System.out.print("Price Per Night: ");
        double price = sc.nextDouble();
        sc.nextLine();

        System.out.print("Is Booked (true/false): ");
        boolean booked = sc.nextBoolean();
        sc.nextLine();

        String guest = "";
        if (booked) {
            System.out.print("Guest Name: ");
            guest = sc.nextLine();
        }

        rooms.add(new Room(number, type, price, booked, guest));
        writeRooms(rooms);

        System.out.println("Room added successfully.");
    }

    private static void displayAllRooms() {
        List<Room> rooms = readRooms();

        if (rooms.isEmpty()) {
            System.out.println("No rooms available.");
            return;
        }

        for (Room r : rooms) {
            System.out.println(r);
        }
    }

    private static void searchRoom(Scanner sc) {
        List<Room> rooms = readRooms();

        System.out.print("Enter Room Number: ");
        int number = sc.nextInt();

        for (Room r : rooms) {
            if (r.getRoomNumber() == number) {
                System.out.println(r);
                return;
            }
        }

        System.out.println("Room not found.");
    }

    private static void updateRoom(Scanner sc) {
        List<Room> rooms = readRooms();

        System.out.print("Enter Room Number: ");
        int number = sc.nextInt();
        sc.nextLine();

        for (Room r : rooms) {
            if (r.getRoomNumber() == number) {

                System.out.print("New Booking Status (true/false): ");
                boolean status = sc.nextBoolean();
                sc.nextLine();

                r.setBooked(status);

                if (status) {
                    System.out.print("Guest Name: ");
                    r.setGuestName(sc.nextLine());
                } else {
                    r.setGuestName("");
                }

                writeRooms(rooms);
                System.out.println("Room updated successfully.");
                return;
            }
        }

        System.out.println("Room not found.");
    }

    static class Room implements Serializable {

        private static final long serialVersionUID = 1L;

        private int roomNumber;
        private String roomType;
        private double pricePerNight;
        private boolean isBooked;
        private String guestName;

        public Room(int roomNumber, String roomType, double pricePerNight,
                boolean isBooked, String guestName) {
            this.roomNumber = roomNumber;
            this.roomType = roomType;
            this.pricePerNight = pricePerNight;
            this.isBooked = isBooked;
            this.guestName = guestName;
        }

        public int getRoomNumber() {
            return roomNumber;
        }

        public void setBooked(boolean booked) {
            isBooked = booked;
        }

        public void setGuestName(String guestName) {
            this.guestName = guestName;
        }

        @Override
        public String toString() {
            return "Room Number: " + roomNumber +
                    "\nRoom Type: " + roomType +
                    "\nPrice Per Night: " + pricePerNight +
                    "\nBooked: " + isBooked +
                    "\nGuest Name: " + guestName + "\n";
        }
    }
}