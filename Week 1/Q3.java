/* 3. Design and implement a Java application to simulate a Hotel Room Booking System that demonstrates the object-oriented concepts of inheritance and runtime polymorphism.
i. Create a base class named Room that represents a general hotel room. The class should contain data members such as room number and base tariff, and a method calculateTariff() to compute the room cost.
ii. Create derived classes such as StandardRoom and LuxuryRoom that inherit from the Room class. Each derived class should override the calculateTariff() method to compute the tariff based on room-specific features such as air conditioning, additional amenities, or premium services.
iii. In the main class, create a base class reference of type Room and assign it to objects of different derived classes (StandardRoom, LuxuryRoom). Invoke the calculateTariff() method using the base class reference to demonstrate runtime polymorphism, where the method call is resolved at runtime based on the actual object type. */

class Room {
    int roomNumber;
    double baseTariff;

    Room(int roomNumber, double baseTariff) {
        this.roomNumber = roomNumber;
        this.baseTariff = baseTariff;
    }

    double calculateTariff() {
        return baseTariff;
    }

    void printDetails() {
        System.out.println("Room Number: " + this.roomNumber + "\nBase Tariff: " + this.baseTariff);
    }
}

class StandardRoom extends Room {
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
    void printDetails() {
        System.out.println("Room Number: " + this.roomNumber + "\nBase Tariff: " + this.baseTariff + "\nTotal Tariff: "
                + calculateTariff() + "\n");
    }
}

class LuxuryRoom extends Room {
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
    void printDetails() {
        System.out.println("Room Number: " + this.roomNumber + "\nBase Tariff: " + this.baseTariff + "\nTotal Tariff: "
                + calculateTariff() + "\n");
    }
}

class Q3 {
    public static void main(String[] args) {

        Room r1 = new StandardRoom(101, 4500.0, true, true);
        r1.calculateTariff();
        Room r2 = new StandardRoom(102, 3500.0, true, false);
        r2.calculateTariff();
        Room r3 = new LuxuryRoom(305, 5000.0, true, true);
        r3.calculateTariff();

        System.out.println("Standard Room");
        r1.printDetails();
        System.out.println("Standard Room");
        r2.printDetails();
        System.out.println("Luxury Room");
        r3.printDetails();

    }
}