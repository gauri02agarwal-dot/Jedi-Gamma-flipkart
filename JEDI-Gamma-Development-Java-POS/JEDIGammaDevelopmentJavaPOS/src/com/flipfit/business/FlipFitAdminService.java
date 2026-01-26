package com.flipfit.business;

import com.flipfit.bean.Admin;
import com.flipfit.bean.GymOwner;
import java.util.ArrayList;
import java.util.List;

public class FlipFitAdminService {
    // This list acts as our temporary database for gym owner requests
    private List<GymOwner> gymOwnerRequests = new ArrayList<>();
    
    // Existing Admin setup
    Admin admin = new Admin();

    /**
     * Helper method to add a request to the list (used during registration)
     */
    public void addGymOwnerRequest(GymOwner owner) {
        gymOwnerRequests.add(owner);
    }

    /**
     * Filters the list to show only owners with status 0 (Pending)
     */
    public List<GymOwner> viewPendingGymOwnerRequests() {
        List<GymOwner> pendingList = new ArrayList<>();
        for (GymOwner owner : gymOwnerRequests) {
            if (owner.getApprovalStatus() == 0) { // 0 = Pending
                pendingList.add(owner);
            }
        }
        return pendingList;
    }

    /**
     * Finds a gym owner by PAN and updates their status
     */
    public boolean approveGymOwnerRegistration(String panCard) {
        for (GymOwner owner : gymOwnerRequests) {
            if (owner.getPanCard().equalsIgnoreCase(panCard)) {
                owner.setApprovalStatus(1); // 1 = Approved
                System.out.println(">>> Success: Owner with PAN " + panCard + " approved.");
                return true;
            }
        }
        System.out.println(">>> Error: Owner with PAN " + panCard + " not found.");
        return false;
    }

    public void viewAllApprovedGymOwners() {
        System.out.println("\n--- List of Approved Gym Owners ---");
        boolean found = false;
        for (GymOwner owner : gymOwnerRequests) {
            if (owner.getApprovalStatus() == 1) {
                System.out.println("Approved PAN: " + owner.getPanCard());
                found = true;
            }
        }
        if (!found) System.out.println("No approved owners found.");
    }

    // --- MAIN METHOD FOR TESTING ---
    public static void main(String[] args) {
        FlipFitAdminService service = new FlipFitAdminService();

        // 1. Setup Dummy Data
        GymOwner ownerA = new GymOwner();
        ownerA.setPanCard("PAN123");
        ownerA.setApprovalStatus(0);

        GymOwner ownerB = new GymOwner();
        ownerB.setPanCard("PAN456");
        ownerB.setApprovalStatus(0);

        // 2. Add to service
        service.addGymOwnerRequest(ownerA);
        service.addGymOwnerRequest(ownerB);

        System.out.println("Initial pending requests: " + service.viewPendingGymOwnerRequests().size());

        // 3. Test Approval
        System.out.println("\nApproving PAN123...");
        service.approveGymOwnerRegistration("PAN123");

        // 4. Verify Results
        System.out.println("Pending requests remaining: " + service.viewPendingGymOwnerRequests().size());
        service.viewAllApprovedGymOwners();
        
        // 5. Test non-existent PAN
        System.out.println("\nTesting invalid PAN approval:");
        service.approveGymOwnerRegistration("FAKE999");
    }
}