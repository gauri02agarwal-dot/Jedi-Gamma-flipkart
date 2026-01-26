package com.flipfit.business;

import com.flipfit.bean.WaitingList;
import java.util.HashMap;
import java.util.Map;

public class WaitingListBusinessImpl implements WaitingListBusiness {
    
    // Hardcoded Collection to act as your "Database"
    // Key: slotId (Integer), Value: WaitingList object
    private static Map<Integer, WaitingList> waitlistDb = new HashMap<>();

    @Override
    public void addCustomerToWaitlist(int userId, int slotId, int centreId) {
        // Retrieve the waitlist for this slot, or create one if it doesn't exist
        WaitingList waitlist = waitlistDb.get(slotId);
        
        if (waitlist == null) {
            waitlist = new WaitingList(waitlistDb.size() + 1, centreId, slotId);
            waitlistDb.put(slotId, waitlist);
        }
        
        // Add user to the queue (Logic from your Bean)
        waitlist.addToQueue(userId);
        System.out.println("User " + userId + " successfully added to hardcoded Waitlist for Slot " + slotId);
    }

    @Override
    public void promoteCustomerFromWaitlist(int slotId) {
        WaitingList waitlist = waitlistDb.get(slotId);
        
        if (waitlist != null && !waitlist.getWaitingQueue().isEmpty()) {
            Integer nextUser = waitlist.removeFromQueue();
            System.out.println("Hardcoded Logic: Promoting User " + nextUser + " from Waitlist to Booking.");
        } else {
            System.out.println("No users in the waiting list for this slot.");
        }
    }
}