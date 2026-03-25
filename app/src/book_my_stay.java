import java.util.*;

public class book_my_stay {
    // Shared Mutable State
    private static Map<String, Integer> inventory = new HashMap<>();
    private static int roomCounter = 1;

    public static void main(String[] args) {
        // Initialize Inventory
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);

        System.out.println("Concurrent Booking Simulation");

        // Define guests and their requested room types
        String[][] requests = {
                {"Abhi", "Single"},
                {"Subha", "Single"},
                {"Vanmathi", "Double"},
                {"Kural", "Suite"}
        };

        // Create and start threads for each guest
        Thread[] threads = new Thread[requests.length];
        for (int i = 0; i < requests.length; i++) {
            final String name = requests[i][0];
            final String type = requests[i][1];

            threads[i] = new Thread(() -> processBooking(name, type));
            threads[i].start();
        }

        // Wait for all threads to finish (Join)
        for (Thread t : threads) {
            try { t.join(); } catch (InterruptedException e) { e.printStackTrace(); }
        }

        // Final Report
        System.out.println("\nRemaining Inventory:");
        inventory.forEach((type, count) -> System.out.println(type + ": " + count));
    }

    /**
     * The Critical Section:
     * The 'synchronized' keyword ensures only one thread enters this method
     * at a time, preventing Race Conditions.
     */
    public static synchronized void processBooking(String name, String type) {
        int available = inventory.getOrDefault(type, 0);

        if (available > 0) {
            // Simulate processing time
            try { Thread.sleep(10); } catch (InterruptedException e) {}

            // Update Inventory
            inventory.put(type, available - 1);

            // Assign Unique ID (Safe because of synchronization)
            String roomID = type + "-" + (roomCounter++);

            System.out.println("Booking confirmed for Guest: " + name + ", Room ID: " + roomID);
        } else {
            System.out.println("Booking failed for Guest: " + name + " (Sold Out)");
        }
    }
}