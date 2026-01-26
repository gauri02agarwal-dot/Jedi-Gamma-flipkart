package com.flipfit.client;

import com.flipfit.business.WaitingListBusiness;
import com.flipfit.business.WaitingListBusinessImpl;
import com.flipfit.bean.WaitingList;
import java.util.Scanner;

public class GymCustomerMenu {
    // Reference to the business logic layer
    private WaitingListBusiness waitingListService = new WaitingListBusinessImpl();
    private Scanner scanner = new Scanner(System.in);

    public void displayCustomerMenu(int userId) {
        int choice = 0;

        while (choice != 4) {
            System.out.println("\n--- Gym Customer Menu ---");
            System.out.println("1. View Available Slots");
            System.out.println("2. Book a Slot");
            System.out.println("3. View My Waitlist Status");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Displaying hardcoded slots: Slot ID 101 (Full), Slot ID 102 (Available)");
                    break;
                case 2:
                    bookSlot(userId);
                    break;
                case 3:
                    viewWaitlistStatus();
                    break;
                case 4:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void bookSlot(int userId) {
        // Hardcoded example for demonstration as requested
        int selectedSlotId = 101; 
        int selectedCentreId = 10;

        // Simulating logic: If slot is full (Hardcoded for your presentation)
        System.out.println("\nChecking availability for Slot 101...");
        System.out.println("Status: This slot is currently full.");
        
        System.out.print("Would you like to join the Waiting List for this slot? (yes/no): ");
        String joinChoice = scanner.next();

        if (joinChoice.equalsIgnoreCase("yes")) {
            // Calls the business layer to update the Map/Queue
            waitingListService.addCustomerToWaitlist(userId, selectedSlotId, selectedCentreId);
            System.out.println("Success: You have been added to the queue!");
        }
    }

    private void viewWaitlistStatuhesare s() {
        System.out.print("Enter Slot ID to check waitlist: ");
        int slotId = scanner.nextInt();
        
        // Fetching the hardcoded bean from the business layer
        WaitingList wl = waitingListService.getWaitingListStatus(slotId);
        
        if (wl != null && !wl.getWaitingQueue().isEmpty()) {
            System.out.println("Waitlist for Slot " + slotId + ": " + wl.getWaitingQueue());
            System.out.println("Your position: " + wl.getWaitingQueue().size());
        } else {
            System.out.println("No one is currently on the waitlist for this slot.");
        }
    }
}