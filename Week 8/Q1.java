/* Design and implement a Hotel Management System in Java using the Collection Framework to manage hotel operations efficiently. 
The system should store, retrieve, update, and process hotel-related data dynamically using appropriate collection classes. */

import java.util.*;

class Room implements Comparable<Room> {
    int roomNumber;
    String roomType;
    double pricePerDay;
    boolean isAvailable;

    Room(int roomNumber, String roomType, double pricePerDay) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerDay = pricePerDay;
        this.isAvailable = true;
    }

    @Override
    public int compareTo(Room r) {
        return this.roomNumber - r.roomNumber;
    }

    public String toString() {
        return "Room No: " + roomNumber +
                ", Type: " + roomType +
                ", Price: " + pricePerDay +
                ", Available: " + isAvailable;
    }
}

class Customer {
    int customerId;
    String name;
    String contactNumber;
    int roomNumber;

    Customer(int customerId, String name, String contactNumber) {
        this.customerId = customerId;
        this.name = name;
        this.contactNumber = contactNumber;
        this.roomNumber = -1;
    }

    public String toString() {
        return "Customer ID: " + customerId +
                ", Name: " + name +
                ", Contact: " + contactNumber +
                ", Room No: " + roomNumber;
    }
}

public class Q1 {

    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Customer> customers = new ArrayList<>();
    static HashMap<Integer, Customer> roomMap = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    static void addRoom() {
        System.out.print("Enter Room Number: ");
        int number = sc.nextInt();
        sc.nextLine();

        for (Room r : rooms) {
            if (r.roomNumber == number) {
                System.out.println("Room already exists.");
                return;
            }
        }

        System.out.print("Enter Room Type: ");
        String type = sc.nextLine();

        System.out.print("Enter Price: ");
        double price = sc.nextDouble();

        rooms.add(new Room(number, type, price));
        System.out.println("Room added.");
    }

    static void displayAvailableRooms() {
        Collections.sort(rooms);

        Iterator<Room> it = rooms.iterator();
        while (it.hasNext()) {
            Room r = it.next();
            if (r.isAvailable) {
                System.out.println(r);
            }
        }
    }

    static void addCustomer() {
        System.out.print("Enter Customer ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Customer c : customers) {
            if (c.customerId == id)
                System.out.println("Customer already exists.");
            return;
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Contact: ");
        String contact = sc.nextLine();

        customers.add(new Customer(id, name, contact));
        System.out.println("Customer added.");
    }

    static void bookRoom() {
        System.out.print("Enter Customer ID: ");
        int id = sc.nextInt();

        Customer customer = null;
        for (Customer c : customers) {
            if (c.customerId == id) {
                customer = c;
                break;
            }
        }

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.print("Enter Room Number: ");
        int roomNo = sc.nextInt();

        for (Room r : rooms) {
            if (r.roomNumber == roomNo) {
                if (!r.isAvailable) {
                    System.out.println("Room already booked.");
                    return;
                }

                r.isAvailable = false;
                customer.roomNumber = roomNo;
                roomMap.put(roomNo, customer);
                System.out.println("Room booked.");
                return;
            }
        }

        System.out.println("Room not found.");
    }

    static void checkoutCustomer() {
        System.out.print("Enter Room Number: ");
        int roomNo = sc.nextInt();

        if (!roomMap.containsKey(roomNo)) {
            System.out.println("Room not occupied.");
            return;
        }

        Customer customer = roomMap.get(roomNo);
        customer.roomNumber = -1;
        roomMap.remove(roomNo);

        for (Room r : rooms) {
            if (r.roomNumber == roomNo) {
                r.isAvailable = true;
                break;
            }
        }

        System.out.println("Checkout done.");
    }

    static void displayAllCustomers() {
        for (Customer c : roomMap.values())
            System.out.println(c);
    }

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n1. Add Room");
            System.out.println("2. Display Available Rooms");
            System.out.println("3. Add Customer");
            System.out.println("4. Book Room");
            System.out.println("5. Checkout Customer");
            System.out.println("6. Display All Customers");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addRoom();
                    break;
                case 2:
                    displayAvailableRooms();
                    break;
                case 3:
                    addCustomer();
                    break;
                case 4:
                    bookRoom();
                    break;
                case 5:
                    checkoutCustomer();
                    break;
                case 6:
                    displayAllCustomers();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }

        } while (true);
    }
}