/* 2. Design and implement a Java application to manage room tariff details in a Hotel
Management System using Java enumerations (enum). The application should
demonstrate the use of enum constants, enum constructors, and enum methods.

i. Define an enum named RoomType to represent different types of hotel rooms such as
STANDARD, DELUXE, and SUITE. Each enum constant should be associated with a base
tariff value using an enum constructor. The enum should also include methods to return
the base tariff and to calculate the total room cost based on the number of days stayed.
ii. Create a main class to select a room type, specify the number of days of stay, and
compute the total room tariff by invoking the enum methods. The application should
clearly illustrate how enum constructors are used to initialize constant-specific data and
how enum methods operate on that data. */

package Week2;

import java.util.*;

enum RoomType {
    STANDARD(5000),
    DELUXE(8000),
    SUITE(16000);

    private double baseTariff;

    RoomType(double baseTariff) {
        this.baseTariff = baseTariff;
    }

    double getTariff() {
        return baseTariff;
    }

    double calcCost(int days) {
        return baseTariff * days;
    }
}

public class Q2 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("Enter room type: ");
        String type = in.next().toUpperCase();

        RoomType room = RoomType.valueOf(type);

        System.out.println("Enter no. of days: ");
        int days = in.nextInt();

        double totalCost = room.calcCost(days);

        System.out.println("\nRoom Type        : " + room);
        System.out.println("Base Tariff/day  : " + room.getTariff());
        System.out.println("Days Stayed      : " + days);
        System.out.println("Total Room Cost  : " + totalCost);

        in.close();
    }
}