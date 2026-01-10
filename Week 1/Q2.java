/* 2. Create a base class named Room to represent general room details in a hotel. The class should contain data members such as room number, room type, and base price. Implement multiple constructors (constructor overloading) in the Room class to initialize room objects in different ways, such as:
i. Initializing only the room number and type
ii. Initializing room number, type, and base price
iii. Create a derived class named DeluxeRoom that inherits from the Room class using single inheritance. The derived class should include additional data members such as free Wi-Fi availability and complimentary breakfast. Implement appropriate constructors in the derived class that invoke the base class constructors using the super keyword.
iv. Create a main class to instantiate objects of both Room and DeluxeRoom using different constructors and display the room details. This application should clearly illustrate constructor overloading and inheritance. */

class Room {
    protected int roomNumber;
    protected String roomType;
    protected double basePrice;

    Room(int roomNumber, String roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
    }

    Room(int roomNumber, String roomType, double basePrice) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.basePrice = basePrice;
    }

    void displayDetails() {
        System.out.println("Room Number : " + roomNumber);
        System.out.println("Room Type : " + roomType);
        System.out.println("Base Price : " + basePrice);
    }

}

class DeluxeRoom extends Room {
    private boolean freeWifi;
    private boolean compBreakfast;

    DeluxeRoom(int roomNumber, String roomType, double basePrice, boolean freeWifi, boolean compBreakfast) {
        super(roomNumber, roomType, basePrice);
        this.freeWifi = freeWifi;
        this.compBreakfast = compBreakfast;
    }

    void displayDetails() {
        super.displayDetails();
        System.out.println("Free Wifi : " + freeWifi);
        System.out.println("Complimentary Breakfast : " + compBreakfast);
        System.out.println("");
    }

}

class Q2 {
    public static void main(String[] args) {
        DeluxeRoom r1 = new DeluxeRoom(101, "Suite", 18000, true, true);
        Room r2 = new Room(309, "Executive", 4000);
        Room r3 = new Room(217, "Executive");
        r3.basePrice = 3500;

        r1.displayDetails();
        r2.displayDetails();
        System.out.println("");
        r3.displayDetails();
        System.out.println("");
    }
}