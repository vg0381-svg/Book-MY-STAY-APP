import java.util.*;

public class book_my_stay {
    // Inventory state
    private static Map<String, Integer> inventory = new HashMap<>();
    // Use Case 10: Stack to track Room IDs for LIFO rollback
    private static Stack<String> allocationStack = new Stack<>();

    public static void main(String[] args) {
        // Initializing state for the example
        inventory.put("Single", 5);

        // Simulating a confirmed booking (from Use Case 6)
        String roomToCancel = "Single-1";
        allocationStack.push(roomToCancel); // Tracked for rollback
        inventory.put("Single", 4); // Decremented during booking

        System.out.println("Booking Cancellation");

        // Perform Cancellation
        cancelBooking("Single");
    }

    public static void cancelBooking(String roomType) {
        // 1. Validate: Ensure there is something to cancel
        if (allocationStack.isEmpty()) {
            System.out.println("Error: No active bookings found to cancel.");
            return;
        }

        // 2. LIFO Rollback: Pop the most recent Room ID
        String releasedID = allocationStack.pop();

        // 3. Inventory Restoration: Increment immediately
        if (inventory.containsKey(roomType)) {
            int currentStock = inventory.get(roomType);
            inventory.put(roomType, currentStock + 1);

            // 4. Confirmation Message
            System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);
            System.out.println("\nRollback History (Most Recent First):");
            System.out.println("Released Reservation ID: " + releasedID);
            System.out.println("Updated " + roomType + " Room Availability: " + inventory.get(roomType));
        }
    }
}