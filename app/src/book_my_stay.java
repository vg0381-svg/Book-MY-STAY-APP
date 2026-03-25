import java.util.*;

// Represents an individual Add-On Service
class Service {
    String name;
    double cost;

    public Service(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }
}

public class book_my_stay {
    // Use Case 7: Mapping Reservation ID (e.g., "Single-1") to a LIST of Services
    private static Map<String, List<Service>> reservationAddOns = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("--- Use Case 7: Add-On Service Selection ---");

        // 1. Simulate an existing Reservation ID from Use Case 6
        String resId = "Single-1";

        // 2. Guest selects multiple services
        addServiceToReservation(resId, new Service("Breakfast Buffet", 500.0));
        addServiceToReservation(resId, new Service("Airport Pickup", 1000.0));

        // 3. Calculate and Display
        displayAddOnSummary(resId);
    }

    public static void addServiceToReservation(String resId, Service service) {
        // Map and List Combination: computeIfAbsent creates the list if it doesn't exist
        reservationAddOns.computeIfAbsent(resId, k -> new ArrayList<>()).add(service);
        System.out.println("Service Added: " + service.name + " to " + resId);
    }

    public static void displayAddOnSummary(String resId) {
        List<Service> services = reservationAddOns.get(resId);
        double totalCost = 0;

        System.out.println("\n--- Add-On Service Selection ---");
        System.out.println("Reservation ID: " + resId);

        if (services != null) {
            for (Service s : services) {
                totalCost += s.cost;
            }
        }

        System.out.println("Total Add-On Cost: " + totalCost);
    }
}