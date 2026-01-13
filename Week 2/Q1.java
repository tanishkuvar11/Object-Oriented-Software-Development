/* 1. The Hotel Billing system should calculate the total bill amount for hotel guests based on
room charges and additional service charges. Store numeric values such as room tariff,
number of days stayed, and service charges using wrapper class objects (Integer,
Double) instead of primitive data types.

Demonstrate autoboxing by automatically converting primitive values to wrapper class
objects when assigning values or storing them in collections. Demonstrate unboxing by
automatically converting wrapper class objects back to primitive types while performing
arithmetic operations for bill calculation.

Create a main class to:
i. Initialize room tariff and number of days using primitive data types and store them in
wrapper objects.
ii. Perform total bill calculation using unboxed primitive values.
iii. Display the final hotel bill. */

package Week2;

import java.util.*;

public class Q1 {

    Double tariffObj;
    Integer daysObj;
    Double serviceObj;

    void initialize(double roomTariff, int days, double serviceCharge) {
        this.tariffObj = roomTariff;
        this.daysObj = days;
        this.serviceObj = serviceCharge;
    }

    double bill() {
        double roomTariff = tariffObj;
        int days = daysObj;
        double serviceCharge = serviceObj;

        double total = roomTariff * days + serviceCharge;
        return total;
    }

    void displayBill() {
        System.out.println("Total bill is: " + bill());
    }

    public static void main(String[] args) {
        Q1 obj = new Q1();

        double roomTariff;
        int days;
        double serviceCharge;

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter tariff: ");
        roomTariff = sc.nextDouble();

        System.out.println("Enter no of days: ");
        days = sc.nextInt();

        System.out.println("Enter service charge: ");
        serviceCharge = sc.nextDouble();

        obj.initialize(roomTariff, days, serviceCharge);
        obj.displayBill();

        sc.close();
    }

}
