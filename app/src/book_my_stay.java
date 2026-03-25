import java.util.*;

public class book_my_stay{

    // ------------------- Domain Model -------------------
    static class Room {
        private String type;
        private int beds;
        private int size;
        private double price;

        public Room(String type, int beds, int size, double price) {
            this.type = type;
            this.beds = beds;
            this.size = size;
            this.price = price;
        }

        public String getType() { return type; }
        public int getBeds() { return beds; }
        public int getSize() { return size; }
        public double getPrice() { return price; }
    }

    // ------------------- Inventory -------------------
    static class Inventory {
        private Map<String, Integer> availability;

        public Inventory(Map<String, Integer> availability) {
            this.availability = availability;
        }

        public int getAvailableCount(String roomType) {
            return availability.getOrDefault(roomType, 0);
        }
    }

    // ------------------- Search Service -------------------
    static class SearchService {
        private Inventory inventory;
        private Map<String, Room> roomCatalog;

        public SearchService(Inventory inventory, Map<String, Room> roomCatalog) {
            this.inventory = inventory;
            this.roomCatalog = roomCatalog;
        }

        public void displayAvailableRooms() {
            System.out.println("Room Search\n");

            for (Map.Entry<String, Room> entry : roomCatalog.entrySet()) {
                Room room = entry.getValue();
                int available = inventory.getAvailableCount(room.getType());

                // Validation: only show available rooms
                if (available > 0) {
                    System.out.println(room.getType() + " Room:");
                    System.out.println("Beds: " + room.getBeds());
                    System.out.println("Size: " + room.getSize() + " sqft");
                    System.out.println("Price per night: " + room.getPrice());
                    System.out.println("Available: " + available);
                    System.out.println();
                }
            }
        }
    }

    // ------------------- Main -------------------
    public static void main(String[] args) {

        // Room Catalog
        Map<String, Room> roomCatalog = new LinkedHashMap<>();
        roomCatalog.put("Single", new Room("Single", 1, 250, 1500.0));
        roomCatalog.put("Double", new Room("Double", 2, 400, 2500.0));
        roomCatalog.put("Suite", new Room("Suite", 3, 750, 5000.0));

        // Inventory
        Map<String, Integer> availability = new HashMap<>();
        availability.put("Single", 5);
        availability.put("Double", 3);
        availability.put("Suite", 2);

        Inventory inventory = new Inventory(availability);

        // Search
        SearchService searchService = new SearchService(inventory, roomCatalog);
        searchService.displayAvailableRooms();
    }
}