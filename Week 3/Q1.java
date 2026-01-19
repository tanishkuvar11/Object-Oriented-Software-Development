/* 1. Design and implement a Java application to simulate a Hotel Room Service
Management System where multiple service requests are handled concurrently
using multithreading.

i. In a hotel, different room service tasks such as room cleaning, food delivery, and
maintenance may occur at the same time. To efficiently manage these tasks, the
application should create separate threads for each service request so that they can
execute concurrently rather than sequentially.
ii. Create individual threads for different service operations using Java thread creation
techniques (Thread class or Runnable interface). Each thread should simulate a service
task by displaying status messages and pausing execution using the sleep() method to
represent processing time.
iii. The main program should start multiple threads simultaneously and demonstrate
concurrent execution of hotel service tasks. */

package Week3;

import java.util.Scanner;

class HotelService implements Runnable {
    private String serviceName;
    private int roomNumber;
    private int serviceTime;

    public HotelService(String serviceName, int roomNumber, int serviceTime) {
        this.serviceName = serviceName;
        this.roomNumber = roomNumber;
        this.serviceTime = serviceTime;
    }

    public void run() {
        try {
            System.out.println(serviceName + " started for Room " + roomNumber);
            Thread.sleep(serviceTime);
            System.out.println(serviceName + " completed for Room " + roomNumber);
        } catch (InterruptedException e) {
            System.out.println(serviceName + " interrupted for Room " + roomNumber);
        }
    }
}

public class Q1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter details for Service 1:");
        System.out.print("Service Name: ");
        String s1 = sc.nextLine();
        System.out.print("Room Number: ");
        int r1 = sc.nextInt();
        System.out.print("Service Time (ms): ");
        int t1Time = sc.nextInt();
        sc.nextLine();

        System.out.println("\nEnter details for Service 2:");
        System.out.print("Service Name: ");
        String s2 = sc.nextLine();
        System.out.print("Room Number: ");
        int r2 = sc.nextInt();
        System.out.print("Service Time (ms): ");
        int t2Time = sc.nextInt();
        sc.nextLine();

        System.out.println("\nEnter details for Service 3:");
        System.out.print("Service Name: ");
        String s3 = sc.nextLine();
        System.out.print("Room Number: ");
        int r3 = sc.nextInt();
        System.out.print("Service Time (ms): ");
        int t3Time = sc.nextInt();

        HotelService service1 = new HotelService(s1, r1, t1Time);
        HotelService service2 = new HotelService(s2, r2, t2Time);
        HotelService service3 = new HotelService(s3, r3, t3Time);

        Thread t1 = new Thread(service1);
        Thread t2 = new Thread(service2);
        Thread t3 = new Thread(service3);

        System.out.println("\nAll service requests have been initiated.");

        t1.start();
        t2.start();
        t3.start();

        sc.close();
    }
}