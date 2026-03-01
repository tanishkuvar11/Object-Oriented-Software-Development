import java.util.Scanner;

class RoomCharges<T extends Number> {

    private T price;
    private T discount; // percentage

    RoomCharges(T price, T discount) {
        this.price = price;
        this.discount = discount;
    }

    public void calculate() {

        double p = price.doubleValue();
        double d = discount.doubleValue();

        double totalPrice = p;
        double discountedPrice = p - (p * d / 100);

        System.out.println("Total Price: " + totalPrice);
        System.out.println("Discounted Price: " + discountedPrice);
    }
}

public class Q3 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Room Price: ");
        Double price = sc.nextDouble();

        System.out.print("Enter Discount Percentage: ");
        Double discount = sc.nextDouble();

        RoomCharges<Double> room = new RoomCharges<>(price, discount);
        room.calculate();

        sc.close();
    }
}