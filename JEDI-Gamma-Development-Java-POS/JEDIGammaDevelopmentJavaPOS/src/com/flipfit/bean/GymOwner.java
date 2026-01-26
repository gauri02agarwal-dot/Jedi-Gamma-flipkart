package com.flipfit.bean;

import java.util.List;

/**
 * Bean class representing a Gym Owner in the FlipFit system.
 */
public class GymOwner {
    private String panCard;
    private List<GymCentre> gymCentres;
    
    // Status: 0 = Pending, 1 = Approved, 2 = Rejected
    private int approvalStatus = 0; 

    // Constructor
    public GymOwner() {}

    // Getters and Setters
    public String getPanCard() {
        return panCard;
    }

    public void setPanCard(String panCard) {
        this.panCard = panCard;
    }

    public List<GymCentre> getGymCentres() {
        return gymCentres;
    }

    public void setGymCentres(List<GymCentre> gymCentres) {
        this.gymCentres = gymCentres;
    }

    public int getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(int approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
}