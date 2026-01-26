package com.flipfit.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a specific time slot within a Gym Centre.
 */
public class Slot {
    private int slotId;
    private int startTime; // Represented in 24-hour format (e.g., 6 for 6 AM, 18 for 6 PM)
    private int maximumSeats;
    private int availableSeats;
    private WaitingList waitingList;

    public Slot(int slotId, int startTime, int maximumSeats) {
        this.slotId = slotId;
        this.startTime = startTime;
        this.maximumSeats = maximumSeats;
        this.availableSeats = maximumSeats;
        this.waitingList = new WaitingList(slotId, slotId);
    }

    // Helper method to check availability
    public boolean isFull() {
        return availableSeats <= 0;
    }

    // Standard Getters and Setters
    public int getStartTime() { return startTime; }
    public void setStartTime(int startTime) { this.startTime = startTime; }

    public int getSlotId() { return slotId; }
    public void setSlotId(int slotId) { this.slotId = slotId; }

    public int getMaximumSeats() { return maximumSeats; }
    public void setMaximumSeats(int maximumSeats) { this.maximumSeats = maximumSeats; }

    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }

    public WaitingList getWaitingList() { return waitingList; }
    public void setWaitingList(WaitingList waitingList) { this.waitingList = waitingList; }

    @Override
    public String toString() {
        return "Slot " + slotId + " [" + startTime + ":00] - Available: " + availableSeats + "/" + maximumSeats;
    }
}