package com.flipfit.business;

import com.flipfit.bean.GymOwner;
import java.util.List;

/**
 * Interface defining the business operations an Admin can perform.
 */
public interface FlipFitAdminInterface {

    // Admin Account Management
    void createAdmin(int id, String name, String emailAddress, int phone, String password);

    // Gym Owner Management
    void addGymOwnerRequest(GymOwner owner);
    
    List<GymOwner> viewPendingGymOwnerRequests();
    
    boolean approveGymOwnerRegistration(String panCard);
    
    void viewAllApprovedGymOwners();

    // Gym Centre Management
    void viewAllApprovedGyms();
    
    String viewPendingGymRequests();
}