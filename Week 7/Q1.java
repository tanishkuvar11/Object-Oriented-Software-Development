import java.util.Scanner;

class Room<T, U> {

    private T roomId;
    private U roomAttribute;

    public Room(T roomId, U roomAttribute) {
        this.roomId = roomId;
        this.roomAttribute = roomAttribute;
    }

    public void display() {
        System.out.println("Room ID: " + roomId);
        System.out.println("Room Attribute: " + roomAttribute);
        System.out.println("---------------------");
    }
}

public class Q1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Integer + String
        System.out.println("Enter Integer Room Number:");
        int num = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter Room Type:");
        String type = sc.nextLine();

        Room<Integer, String> room1 = new Room<>(num, type);

        // String + Double
        System.out.println("Enter String Room ID:");
        String id = sc.nextLine();

        System.out.println("Enter Room Price:");
        double price = sc.nextDouble();

        Room<String, Double> room2 = new Room<>(id, price);

        System.out.println("\nStored Room Details:");
        room1.display();
        room2.display();

        sc.close();
    }
}