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

public class book_my_stay {

    public static void main(String[] args) {

        int singleRoomAvailable = 5;
        int doubleRoomAvailable = 3;
        int suiteRoomAvailable = 2;

        Room room1 = new SingleRoom();
        Room room2 = new DoubleRoom();
        Room room3 = new SuiteRoom();

        System.out.println("=== Hotel Room Information ===\n");

        room1.displayRoomDetails();
        System.out.println("Available: " + singleRoomAvailable);
        System.out.println();

        room2.displayRoomDetails();
        System.out.println("Available: " + doubleRoomAvailable);
        System.out.println();

        room3.displayRoomDetails();
        System.out.println("Available: " + suiteRoomAvailable);
    }
}
