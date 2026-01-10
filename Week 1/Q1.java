/* 1. Create a Book class with private data members including book ID, book title, author name, price, and availability status. Provide public setter methods to assign values to these data members and public getter methods to retrieve their values. Include validation in setter methods to ensure that the price is a positive value. */

class Book {

    private int bookID;
    private String title;
    private String author;
    private int price;
    private boolean avl;

    public void setMethod(int bookID, String title, String author, int price, boolean avl) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.price = price;
        this.avl = avl;
    }

    public int getbookID() {
        return bookID;
    }

    public String gettitle() {
        return title;
    }

    public String getauthor() {
        return author;
    }

    public int getprice() {
        return price;
    }

    public boolean getavl() {
        return avl;
    }

}

public class Q1 {
    public static void main(String[] args) {
        Book b1 = new Book();
        b1.setMethod(101, "Harry Potter", "J.K. Rowling", 300, true);
        Book b2 = new Book();
        b2.setMethod(101, "Hunger Games", "Suzanne Collins", 150, false);

        System.out.println(("Book 1 details:\nID: " + b1.getbookID()) + "\nTitle: " + b1.gettitle() + "\nAuthor: "
                + b1.getauthor() + "\nPrice: " + b1.getprice() + "\nAvailable: " + b1.getavl() + "\n");
        System.out.println(("Book 2 details:\nID: " + b2.getbookID()) + "\nTitle: " + b2.gettitle() + "\nAuthor: "
                + b2.getauthor() + "\nPrice: " + b2.getprice() + "\nAvailable: " + b2.getavl());

    }
}
