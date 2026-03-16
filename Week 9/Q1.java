import javafx.application.Application;
import javafx.collections.*;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Q1 extends Application {

    // List to store all rooms
    ObservableList<Room> rooms = FXCollections.observableArrayList();

    // List to store all customers
    ObservableList<Customer> customers = FXCollections.observableArrayList();

    // Filtered list used to show only available / all rooms
    FilteredList<Room> filteredRooms = new FilteredList<>(rooms, p -> true);

    // Tables to display room and customer data
    TableView<Room> roomTable = new TableView<>();
    TableView<Customer> customerTable = new TableView<>();

    public static void main(String[] args) {
        launch(args); // Launch JavaFX application
    }

    @Override
    public void start(Stage stage) {

        // Text fields and combo box for room input
        TextField roomNoField = new TextField();
        ComboBox<String> typeBox = new ComboBox<>();
        typeBox.getItems().addAll("Single", "Double", "Deluxe");
        TextField priceField = new TextField();

        // Buttons for room operations
        Button addRoomBtn = new Button("Add Room");
        Button showAvailBtn = new Button("Show Available");
        Button showAllBtn = new Button("Show All");

        // Layout for room form
        GridPane roomForm = new GridPane();
        roomForm.setVgap(10);
        roomForm.setHgap(10);
        roomForm.addRow(0, new Label("Room No"), roomNoField);
        roomForm.addRow(1, new Label("Type"), typeBox);
        roomForm.addRow(2, new Label("Price"), priceField);
        roomForm.add(addRoomBtn, 1, 3);

        // Room table column: room number
        TableColumn<Room, Integer> c1 = new TableColumn<>("Room No");
        c1.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(
                data.getValue().getRoomNumber()).asObject());

        // Room table column: type
        TableColumn<Room, String> c2 = new TableColumn<>("Type");
        c2.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
                data.getValue().getType()));

        // Room table column: price
        TableColumn<Room, Double> c3 = new TableColumn<>("Price");
        c3.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(
                data.getValue().getPrice()).asObject());

        // Room table column: availability
        TableColumn<Room, Boolean> c4 = new TableColumn<>("Available");
        c4.setCellValueFactory(data -> new javafx.beans.property.SimpleBooleanProperty(
                data.getValue().isAvailable()));

        // Add columns to table and set data source
        roomTable.getColumns().addAll(c1, c2, c3, c4);
        roomTable.setItems(filteredRooms);

        // Text fields for customer input
        TextField nameField = new TextField();
        TextField contactField = new TextField();
        TextField bookRoomField = new TextField();

        // Buttons for booking and checkout
        Button bookBtn = new Button("Book Room");
        Button checkoutBtn = new Button("Checkout");

        // Layout for customer form
        GridPane custForm = new GridPane();
        custForm.setVgap(10);
        custForm.setHgap(10);
        custForm.addRow(0, new Label("Name"), nameField);
        custForm.addRow(1, new Label("Contact"), contactField);
        custForm.addRow(2, new Label("Room No"), bookRoomField);
        custForm.add(bookBtn, 1, 3);

        // Customer table column: name
        TableColumn<Customer, String> cc1 = new TableColumn<>("Name");
        cc1.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
                data.getValue().getName()));

        // Customer table column: contact
        TableColumn<Customer, String> cc2 = new TableColumn<>("Contact");
        cc2.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
                data.getValue().getContact()));

        // Customer table column: room number
        TableColumn<Customer, Integer> cc3 = new TableColumn<>("Room");
        cc3.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(
                data.getValue().getRoomNumber()).asObject());

        // Add columns and set data source
        customerTable.getColumns().addAll(cc1, cc2, cc3);
        customerTable.setItems(customers);

        // Add new room button action
        addRoomBtn.setOnAction(e -> {
            try {
                int no = Integer.parseInt(roomNoField.getText());
                String type = typeBox.getValue();
                double price = Double.parseDouble(priceField.getText());

                rooms.add(new Room(no, type, price)); // Add room to list
                roomTable.refresh();
                showAlert("Room added");

                // Clear inputs
                roomNoField.clear();
                priceField.clear();
                typeBox.setValue(null);

            } catch (Exception ex) {
                showAlert("Invalid room input");
            }
        });

        // Show only available rooms
        showAvailBtn.setOnAction(e -> {
            filteredRooms.setPredicate(room -> room.isAvailable());
            roomTable.refresh();
        });

        // Show all rooms
        showAllBtn.setOnAction(e -> {
            filteredRooms.setPredicate(room -> true);
            roomTable.refresh();
        });

        // Book room button action
        bookBtn.setOnAction(e -> {
            try {
                int rno = Integer.parseInt(bookRoomField.getText());

                // Find room from list
                Room r = rooms.stream()
                        .filter(x -> x.getRoomNumber() == rno)
                        .findFirst().orElse(null);

                if (r == null) {
                    showAlert("Room does not exist");
                    return;
                }

                if (!r.isAvailable()) {
                    showAlert("Room already occupied");
                    return;
                }

                r.setAvailable(false); // Mark room as occupied

                customers.add(new Customer(
                        nameField.getText(),
                        contactField.getText(),
                        rno)); // Add customer

                roomTable.refresh();
                showAlert("Booking successful");

                // Clear inputs
                nameField.clear();
                contactField.clear();
                bookRoomField.clear();

            } catch (Exception ex) {
                showAlert("Invalid booking input");
            }
        });

        // Checkout button action
        checkoutBtn.setOnAction(e -> {
            Customer sel = customerTable.getSelectionModel().getSelectedItem();

            if (sel == null) {
                showAlert("Select customer to checkout");
                return;
            }

            // Make room available again
            rooms.stream()
                    .filter(x -> x.getRoomNumber() == sel.getRoomNumber())
                    .findFirst()
                    .ifPresent(x -> x.setAvailable(true));

            customers.remove(sel); // Remove customer
            roomTable.refresh();
            showAlert("Checkout done");
        });

        // Left side layout (room management)
        VBox left = new VBox(15,
                roomForm,
                new HBox(10, showAvailBtn, showAllBtn),
                roomTable);

        // Right side layout (customer management)
        VBox right = new VBox(15,
                custForm,
                checkoutBtn,
                customerTable);

        // Root layout
        HBox root = new HBox(20, left, right);
        root.setPadding(new Insets(20));

        // Scene setup
        stage.setScene(new Scene(root, 900, 500));
        stage.setTitle("Hotel Management System");
        stage.show();
    }

    // Method to show alert dialog
    void showAlert(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();
    }

    // Room class
    class Room {
        private int roomNumber;
        private String type;
        private double price;
        private boolean available;

        public Room(int roomNumber, String type, double price) {
            this.roomNumber = roomNumber;
            this.type = type;
            this.price = price;
            this.available = true; // Default room is available
        }

        public int getRoomNumber() {
            return roomNumber;
        }

        public String getType() {
            return type;
        }

        public double getPrice() {
            return price;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }
    }

    // Customer class
    class Customer {
        private String name;
        private String contact;
        private int roomNumber;

        public Customer(String name, String contact, int roomNumber) {
            this.name = name;
            this.contact = contact;
            this.roomNumber = roomNumber;
        }

        public String getName() {
            return name;
        }

        public String getContact() {
            return contact;
        }

        public int getRoomNumber() {
            return roomNumber;
        }
    }
}