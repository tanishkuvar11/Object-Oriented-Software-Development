/* Design and implement a Java-based hotel room management application that
simulates concurrent room booking and room release operations using
multiple threads. The system must ensure data consistency when multiple
customers attempt to book or release rooms simultaneously. A hotel has a limited
number of rooms. Multiple customer threads attempt to book rooms at the same
time. If no rooms are available, the booking thread must wait.
When a room is released by another thread, the waiting booking thread must be
notified and allowed to proceed. */

import java.util.Scanner;

class Hotel {

    private int availableRooms;

    public Hotel(int totalRooms) {
        this.availableRooms = totalRooms;
    }

    // Book a room
    public synchronized void bookRoom(String customerName) {
        while (availableRooms == 0) {
            System.out.println(customerName + " is waiting for a room.");
            try {
                wait(); // wait until room is released
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        availableRooms--;
        System.out.println(customerName + " booked a room. Rooms left: " + availableRooms);
    }

    // Release a room
    public synchronized void releaseRoom(String customerName) {
        availableRooms++;
        System.out.println(customerName + " released a room. Rooms available: " + availableRooms);
        notify(); // notify waiting booking threads
    }
}

class Customer extends Thread {

    private final Hotel hotel;
    private final String customerName;

    public Customer(Hotel hotel, String customerName) {
        this.hotel = hotel;
        this.customerName = customerName;
    }

    @Override
    public void run() {
        hotel.bookRoom(customerName);

        try {
            // Simulate stay
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        hotel.releaseRoom(customerName);
    }
}

public class Q1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter total number of rooms: ");
        int rooms = sc.nextInt();

        System.out.print("Enter number of customers: ");
        int customers = sc.nextInt();

        Hotel hotel = new Hotel(rooms);

        for (int i = 1; i <= customers; i++) {
            Customer t = new Customer(hotel, "Customer " + i);
            t.start();
        }

        sc.close();
    }
}