import java.util.LinkedList;
import java.util.Queue;

class BookingRequest {
    String guestName;
    String roomType;

    public BookingRequest(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

public class book_my_stay {
    public static void main(String[] args) {
        // 1. Initialize the Queue
        Queue<BookingRequest> bookingQueue = new LinkedList<>();

        // 2. Add requests (Intake - Use Case 5)
        bookingQueue.add(new BookingRequest("Abhi", "Single"));
        bookingQueue.add(new BookingRequest("Subha", "Double"));
        bookingQueue.add(new BookingRequest("Vanmathi", "Suite"));

        System.out.println("--- Booking Request Queue Initialized ---");

        // 3. Processing (FIFO Order)
        processBookings(bookingQueue);
    }

    public static void processBookings(Queue<BookingRequest> queue) {
        System.out.println("\nBooking Request Queue");

        // While there are people in line, process them one by one
        while (!queue.isEmpty()) {
            // .poll() removes the head of the queue (the first person who arrived)
            BookingRequest current = queue.poll();
            System.out.println("Processing booking for Guest: " + current.guestName +
                    ", Room Type: " + current.roomType);
        }

        System.out.println("\nAll pending requests have been processed.");
    }
}