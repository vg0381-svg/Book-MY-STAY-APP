import java.util.*;

class BookingRequest {
    String guestName;
    String roomType;

    public BookingRequest(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

public class book_my_stay {
    // Inventory: Simple counter for available rooms
    private static Map<String, Integer> inventory = new HashMap<>();

    // Use Case 6: Map of Room Types to a SET of allocated Room IDs
    // The Set ensures uniqueness (prevents double-booking)
    private static Map<String, Set<String>> allocatedRooms = new HashMap<>();

    public static void main(String[] args) {
        // Setup initial inventory
        inventory.put("Single", 10);
        inventory.put("Double", 5);
        inventory.put("Suite", 2);

        // Initialize the Request Queue (Use Case 5)
        Queue<BookingRequest> bookingQueue = new LinkedList<>();
        bookingQueue.add(new BookingRequest("Abhi", "Single"));
        bookingQueue.add(new BookingRequest("Subha", "Single"));
        bookingQueue.add(new BookingRequest("Vanmathi", "Suite"));

        System.out.println("--- Room Allocation Processing ---");
        processAndAllocate(bookingQueue);
    }

    public static void processAndAllocate(Queue<BookingRequest> queue) {
        while (!queue.isEmpty()) {
            BookingRequest request = queue.poll(); // FIFO retrieval
            String type = request.roomType;

            // 1. Check Availability
            if (inventory.containsKey(type) && inventory.get(type) > 0) {

                // 2. Generate Unique Room ID
                // Logic: Type name + current count allocated + 1
                int currentAllocatedCount = allocatedRooms.getOrDefault(type, new HashSet<>()).size();
                String roomID = type + "-" + (currentAllocatedCount + 1);

                // 3. Record in Set to prevent reuse (Atomic Logic)
                allocatedRooms.computeIfAbsent(type, k -> new HashSet<>()).add(roomID);

                // 4. Immediate Inventory Synchronization
                inventory.put(type, inventory.get(type) - 1);

                // 5. Confirm Reservation
                System.out.println("Booking confirmed for Guest: " + request.guestName + ", Room ID: " + roomID);
            } else {
                System.out.println("Booking FAILED for Guest: " + request.guestName + " (No " + type + " rooms left)");
            }
        }
    }
}