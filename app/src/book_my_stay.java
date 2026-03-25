import java.util.*;

// Represents the completed record of a booking
class Reservation {
    String guestName;
    String roomType;
    String roomId;

    public Reservation(String guestName, String roomType, String roomId) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "Guest: " + guestName + ", Room Type: " + roomType + " (ID: " + roomId + ")";
    }
}

public class book_my_stay {
    // Use Case 8: The Historical Ledger (Ordered Storage)
    private static List<Reservation> bookingHistory = new ArrayList<>();

    public static void main(String[] args) {
        // 1. Simulate the Flow: Confirmation -> History
        confirmAndStore("Abhi", "Single", "Single-1");
        confirmAndStore("Subha", "Double", "Double-1");
        confirmAndStore("Vanmathi", "Suite", "Suite-1");

        // 2. Admin Request: Generate Report
        generateBookingReport();
    }

    // This method simulates the transition from Use Case 6 to Use Case 8
    public static void confirmAndStore(String name, String type, String id) {
        // Create the historical record
        Reservation record = new Reservation(name, type, id);

        // Add to history list (Preserves insertion/chronological order)
        bookingHistory.add(record);

        System.out.println("System: Reservation stored in history for " + name);
    }

    // Reporting Service logic
    public static void generateBookingReport() {
        System.out.println("\n--- Booking History and Reporting ---");
        System.out.println("Booking History Report");

        if (bookingHistory.isEmpty()) {
            System.out.println("No records found.");
        } else {
            // Iterating through the list provides the report in order of confirmation
            for (Reservation res : bookingHistory) {
                // Formatting matches your specific requirement
                System.out.println("Guest: " + res.guestName + ", Room Type: " + res.roomType);
            }
        }
    }
}