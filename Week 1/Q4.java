/* 4. Create an abstract class named Room that represents a generic hotel room. The abstract class should contain common data members such as room number and base price, and include an abstract method calculateTariff() that must be implemented by all subclasses. It may also include concrete methods such as displayRoomDetails().
i. Create derived classes such as StandardRoom and LuxuryRoom that extend the abstract Room class and provide concrete implementations for the calculateTariff() method based on room-specific features.
ii. Create an interface named Amenities that declares methods such as provideWifi() and provideBreakfast(). The derived room classes should implement this interface to define the amenities offered for each room type.
iii. Create a main class to instantiate different room objects using a base class reference and invoke the implemented methods to demonstrate abstraction and interface-based design. */

abstract class Room {
    int roomNumber;
    double baseTariff;

    Room(int roomNumber, double baseTariff) {
        this.roomNumber = roomNumber;
        this.baseTariff = baseTariff;
    }

    abstract double calculateTariff();

    void displayRoomDetails() {
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Base Price: " + baseTariff);
    }
}

interface Amenities {
    void provideWifi();

    void provideBreakfast();
}

class StandardRoom extends Room implements Amenities {

    boolean AC;
    boolean premium;

    StandardRoom(int roomNumber, double baseTariff, boolean AC, boolean premium) {
        super(roomNumber, baseTariff);
        this.AC = AC;
        this.premium = premium;
    }

    @Override
    double calculateTariff() {
        if (AC == true && premium == true)
            return baseTariff + 5000.0;

        if (AC == true && premium == false)
            return baseTariff + 2000.0;

        return baseTariff;
    }

    @Override
    public void provideWifi() {
        System.out.println("Free WiFi available");
    }

    @Override
    public void provideBreakfast() {
        System.out.println("Breakfast not included");
    }
}

class LuxuryRoom extends Room implements Amenities {

    boolean AC;
    boolean premium;

    LuxuryRoom(int roomNumber, double baseTariff, boolean AC, boolean premium) {
        super(roomNumber, baseTariff);
        this.AC = AC;
        this.premium = premium;
    }

    @Override
    double calculateTariff() {
        if (AC == true && premium == true)
            return baseTariff + 8000.0;

        return baseTariff + 5000.0;
    }

    @Override
    public void provideWifi() {
        System.out.println("High-speed WiFi available");
    }

    @Override
    public void provideBreakfast() {
        System.out.println("Complimentary breakfast included");
    }
}

public class Q4 {
    public static void main(String[] args) {

        Room r1 = new StandardRoom(101, 2000, true, false);
        Room r2 = new LuxuryRoom(201, 5000, true, true);

        System.out.println("Standard Room");
        r1.displayRoomDetails();
        System.out.println("Tariff: " + r1.calculateTariff());
        ((Amenities) r1).provideWifi();
        ((Amenities) r1).provideBreakfast();

        System.out.println();

        System.out.println("Luxury Room");
        r2.displayRoomDetails();
        System.out.println("Tariff: " + r2.calculateTariff());
        ((Amenities) r2).provideWifi();
        ((Amenities) r2).provideBreakfast();
    }
}
