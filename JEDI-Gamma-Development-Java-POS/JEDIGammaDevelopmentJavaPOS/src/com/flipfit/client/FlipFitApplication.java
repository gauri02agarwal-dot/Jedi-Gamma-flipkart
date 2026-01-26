package com.flipfit.client;

import com.flipfit.bean.*;
import com.flipfit.business.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlipFitApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookingBusinessService bookingBusiness = new BookingBusinessService();

        GymCentre gc1 = new GymCentre(1, "Mahadevapura", "06:00 - 21:00", 500);
        List<Slot> masterSlot = new ArrayList<>();
        masterSlot.add(new Slot(1, 6, 2)); // Capacity 2 for testing waitlist
        masterSlot.add(new Slot(2, 7, 2)); // Capacity 2 for testing waitlist
        masterSlot.add(new Slot(3, 8, 2)); // Capacity 2 for testing waitlist
        masterSlot.add(new Slot(4, 18, 10)); // Evening Slot
        masterSlot.add(new Slot(5, 19, 10)); // Evening Slot
        masterSlot.add(new Slot(6, 20, 10)); // Evening Slot
        for(Slot s : masterSlot) gc1.addSlot(s);

        System.out.println("==========================================");
        System.out.println("      WELCOME TO FLIPFIT SYSTEMS");
        System.out.println("==========================================");

        boolean running = true;
        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. View Available Slots");
            System.out.println("2. Book a Slot");
            System.out.println("3. Cancel a Booking");
            System.out.println("4. View System Snapshot (Admin)");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayMasterSlots(gc1.getSlots());
                    break;

                case 2:
                    System.out.print("Enter User ID: ");
                    int userId = scanner.nextInt();
                    System.out.print("Enter Slot ID to book: ");
                    int slotId = scanner.nextInt();

                    Slot selectedSlot = findSlotById(gc1.getSlots(), slotId);
                    if (selectedSlot != null) {
                        bookingBusiness.createBooking(userId, gc1.getCentreId(), selectedSlot, 1, gc1.getPrice());
                    } else {
                        System.out.println("Error: Invalid Slot ID.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Booking ID to cancel: ");
                    int bId = scanner.nextInt();
                    boolean result = bookingBusiness.cancelBooking(bId, gc1.getSlots());
                    if(!result) System.out.println("Cancellation failed.");
                    break;

                case 4:
                    displaySystemState(gc1.getSlots(), bookingBusiness.getAllBookings());
                    break;

                case 5:
                    running = false;
                    System.out.println("Exiting FlipFit. Stay Healthy!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static Slot findSlotById(List<Slot> slots, int id) {
        for (Slot s : slots) {
            if (s.getSlotId() == id) return s;
        }
        return null;
    }

    private static void displaySystemState(List<Slot> slots, List<Booking> bookings) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                CURRENT SYSTEM SNAPSHOT");
        System.out.println("=".repeat(60));
        displayMasterSlots(slots);
        displayWaitingLists(slots);
        displayBookings(bookings);
        System.out.println("=".repeat(60) + "\n");
    }

    private static void displayMasterSlots(List<Slot> slots) {
        System.out.println("\n[SLOT STATUS]");
        System.out.printf("%-10s %-10s %-12s %-15s\n", "Slot ID", "Time", "Category", "Availability");
        for (Slot s : slots) {
            String cat = (s.getStartTime() < 12) ? "Morning" : "Evening";
            System.out.printf("%-10d %-10d %-12s %-1d/%-1d Seats\n",
                    s.getSlotId(), s.getStartTime(), cat, s.getAvailableSeats(), s.getMaximumSeats());
        }
    }

    private static void displayWaitingLists(List<Slot> slots) {
        System.out.println("\n[WAITING LISTS]");
        System.out.printf("%-10s %-20s %-10s\n", "Slot ID", "Queue (User IDs)", "Count");
        for (Slot s : slots) {
            WaitingList wl = s.getWaitingList();
            System.out.printf("%-10d %-20s %-10d\n",
                    s.getSlotId(), wl.getWaitingQueue().toString(), wl.getQueueSize());
        }
    }

    private static void displayBookings(List<Booking> bookings) {
        System.out.println("\n[ACTIVE BOOKINGS]");
        if (bookings.isEmpty()) {
            System.out.println("No active bookings found.");
            return;
        }
        System.out.printf("%-12s %-10s %-10s %-10s\n", "Booking ID", "User ID", "Slot ID", "Amount");
        for (Booking b : bookings) {
            System.out.printf("%-12d %-10d %-10d ₹%-10.2f\n",
                    b.getBookingId(), b.getUserId(), b.getSlotId(), b.getTotalCost());
        }
    }
}