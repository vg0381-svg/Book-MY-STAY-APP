import java.io.*;
import java.util.*;

// To be persisted, classes MUST implement Serializable
class InventoryData implements Serializable {
    private static final long serialVersionUID = 1L; // Ensures version compatibility
    Map<String, Integer> rooms;

    public InventoryData(Map<String, Integer> rooms) {
        this.rooms = rooms;
    }
}

public class book_my_stay {
    private static final String FILE_NAME = "inventory_state.dat";
    private static Map<String, Integer> currentInventory = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("System Recovery");

        // 1. Startup: Attempt to Restore State
        loadState();

        // 2. Display State
        System.out.println("Current Inventory:");
        currentInventory.forEach((type, count) -> System.out.println(type + ": " + count));

        // 3. Shutdown: Save State
        saveState();
    }

    private static void saveState() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            InventoryData data = new InventoryData(currentInventory);
            oos.writeObject(data);
            System.out.println("Inventory saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving state: " + e.getMessage());
        }
    }

    private static void loadState() {
        File file = new File(FILE_NAME);

        // 4. Failure Tolerance: Handle missing file
        if (!file.exists()) {
            System.out.println("No valid inventory data found. Starting fresh.");
            initializeDefaultInventory();
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            InventoryData loadedData = (InventoryData) ois.readObject();
            currentInventory = loadedData.rooms;
            System.out.println("System state recovered from " + FILE_NAME);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Recovery failed (corrupted file). Starting fresh.");
            initializeDefaultInventory();
        }
    }

    private static void initializeDefaultInventory() {
        currentInventory.put("Single", 5);
        currentInventory.put("Double", 3);
        currentInventory.put("Suite", 2);
    }
}