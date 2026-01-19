/* 2. Design and implement a Java application to simulate an Online Order
Processing System where multiple customer orders are processed simultaneously
using multithreading.

i. In an e-commerce platform, several operations such as order validation, payment
processing, and order shipment must be handled concurrently for different customers.
To improve system performance and responsiveness, each order processing task should
be executed in a separate thread.
ii. Create individual threads for handling different customer orders or different stages of
order processing. Each thread should simulate processing by displaying status messages
and using the sleep() method to represent time-consuming operations.
iii. The main program should start multiple threads at the same time and demonstrate
concurrent execution of order-related tasks. */

package Week3;

import java.util.Scanner;

class OrderProcessor implements Runnable {
    private String taskName;
    private String orderId;
    private int processingTime;

    public OrderProcessor(String taskName, String orderId, int processingTime) {
        this.taskName = taskName;
        this.orderId = orderId;
        this.processingTime = processingTime;
    }

    public void run() {
        try {
            System.out.println("Started " + taskName + " for Order: " + orderId);
            Thread.sleep(processingTime);
            System.out.println("Successful " + taskName + " completed for Order: " + orderId);
        } catch (InterruptedException e) {
            System.out.println("Error " + taskName + " failed for Order: " + orderId);
        }
    }
}

public class Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter details for Order Validation:");
        System.out.print("Order ID: ");
        String id1 = sc.nextLine();
        System.out.print("Processing Time (ms): ");
        int time1 = sc.nextInt();
        sc.nextLine();

        System.out.println("\nEnter details for Payment Processing:");
        System.out.print("Order ID: ");
        String id2 = sc.nextLine();
        System.out.print("Processing Time (ms): ");
        int time2 = sc.nextInt();
        sc.nextLine();

        System.out.println("\nEnter details for Order Shipment:");
        System.out.print("Order ID: ");
        String id3 = sc.nextLine();
        System.out.print("Processing Time (ms): ");
        int time3 = sc.nextInt();

        OrderProcessor validation = new OrderProcessor("Order Validation", id1, time1);
        OrderProcessor payment = new OrderProcessor("Payment Processing", id2, time2);
        OrderProcessor shipment = new OrderProcessor("Order Shipment", id3, time3);

        Thread t1 = new Thread(validation);
        Thread t2 = new Thread(payment);
        Thread t3 = new Thread(shipment);

        t1.start();
        t2.start();
        t3.start();

        System.out.println("\nAll threads dispatched.");
        sc.close();
    }
}