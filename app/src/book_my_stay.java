import java.util.*;

// 1. Custom Exception for domain-specific errors
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

public class book_my_stay {
    // Valid room types defined by the business
    private static final Set<String> VALID_ROOM_TYPES = Set.of("Single", "Double", "Suite");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Use Case 9: Booking Validation ---");

        System.out.print("Enter guest name: ");
        String name = scanner.nextLine();

        System.out.print("Enter room type (Single/Double/Suite): ");
        String type = scanner.nextLine();

        try {
            // 2. Fail-Fast Validation
            validateRequest(name, type);

            // If validation passes, proceed to allocation (simulated)
            System.out.println("Booking confirmed for Guest: " + name + ", Room Type: " + type);

        } catch (InvalidBookingException e) {
            // 3. Graceful Failure Handling
            System.err.println("Booking failed: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("\nSystem remains stable and ready for the next request.");
        }
    }

    /**
     * Guarding System State: This method ensures input is valid
     * BEFORE any inventory or allocation logic is triggered.
     */
    public static void validateRequest(String name, String type) throws InvalidBookingException {
        // Check for empty name
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        // Check for invalid room type (Case-Sensitive check)
        if (!VALID_ROOM_TYPES.contains(type)) {
            throw new InvalidBookingException("Invalid room type selected.");
        }
    }
}