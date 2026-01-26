package com.flipfit.business;

import com.flipfit.bean.WaitingList;

public interface WaitingListBusiness {
    // Adds a user to the waiting list for a specific slot
    public void addCustomerToWaitlist(int userId, int slotId, int centreId);
    
    // Logic to move the next person from waitlist to a booking
    public void promoteCustomerFromWaitlist(int slotId);
    
    // Returns the current status of the queue for a slot
    public WaitingList getWaitingListStatus(int slotId);
}