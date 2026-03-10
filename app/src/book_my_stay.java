import java.util.HashMap;
import java.util.Map;

abstract class Room {

    private int beds;
    private int size;
    private double price;

    public Room(int beds, int size, double price) {
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public int getBeds() {
        return beds;
    }

    public int getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public abstract String getRoomType();

    public void displayRoomDetails() {
        System.out.println("Room Type: " + getRoomType());
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sq ft");
        System.out.println("Price per night: $" + price);
    }
}

class SingleRoom extends Room {

    public SingleRoom() {
        super(1, 200, 100);
    }

    @Override
    public String getRoomType() {
        return "Single Room";
    }
}

class DoubleRoom extends Room {

    public DoubleRoom() {
        super(2, 350, 180);
    }

    @Override
    public String getRoomType() {
        return "Double Room";
    }
}

class SuiteRoom extends Room {

    public SuiteRoom() {
        super(3, 500, 350);
    }

    @Override
    public String getRoomType() {
        return "Suite Room";
    }
}

/* Centralized Inventory Management */
class RoomInventory {

    private Map<String, Integer> inventory = new HashMap<>();

    public void registerRoom(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int change) {
        int current = inventory.getOrDefault(roomType, 0);
        inventory.put(roomType, current + change);
    }

    public void displayInventory() {
        System.out.println("\n=== Current Room Inventory ===");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " Available: " + entry.getValue());
        }
    }
}

public class book_my_stay {

    public static void main(String[] args) {

        // Create room objects
        Room single = new SingleRoom();
        Room dbl = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Register room types
        inventory.registerRoom(single.getRoomType(), 5);
        inventory.registerRoom(dbl.getRoomType(), 3);
        inventory.registerRoom(suite.getRoomType(), 2);

        // Display room details
        System.out.println("=== Room Information ===\n");
        single.displayRoomDetails();
        dbl.displayRoomDetails();
        suite.displayRoomDetails();

        // Display centralized inventory
        inventory.displayInventory();

        // Simulate booking (update availability)
        System.out.println("\nBooking one Single Room...");
        inventory.updateAvailability("Single Room", -1);

        inventory.displayInventory();
    }
}
