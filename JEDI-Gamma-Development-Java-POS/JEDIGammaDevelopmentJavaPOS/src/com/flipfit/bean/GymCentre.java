package com.flipfit.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Gym Centre in the FlipFit system.
 * Slots are managed via a List, where index 0 is the first slot of the day.
 */
public class GymCentre {
    private int centreId;
    private String centreName;
    private String openTime;
    private List<Slot> slots; // 1 array slots contain both morning and evening, consecutive indexes
    private int price;

    // Default Constructor
    public GymCentre() {
        this.slots = new ArrayList<>();
    }

    public List<Slot> getFilledSlots() {
        List<Slot> filledSlots = new ArrayList<>();
        for (Slot s : this.slots) {
            if (s.isFull()) {
                filledSlots.add(s);
            }
        }
        return filledSlots;
    }

    public GymCentre(int centreId, String centreName, String openTime, int price) {
        this.centreId = centreId;
        this.centreName = centreName;
        this.openTime = openTime;
        this.price = price;
        this.slots = new ArrayList<>();
    }

    public void viewDetails() {
        System.out.println("Centre ID: " + centreId);
        System.out.println("Center Name: " + centreName);
        System.out.println("Open Time: " + openTime);
        System.out.println("Price per slot: " + price);
        return;
    }

    /**
     * Adds a new slot to the end of the schedule.
     * @param slot The Slot object to add
     * @return true if added successfully
     */
    public boolean addSlot(Slot slot) {
        System.out.println("Slot added...");
        return this.slots.add(slot);
    }

    /**
     * Removes a slot by its index (e.g., index 0 for the first slot).
     * @param index The position of the slot in the list
     * @return true if removed successfully
     */
    public boolean removeSlot(int index) {
        if (index >= 0 && index < slots.size()) {
            slots.remove(index);
            System.out.println("Slot at index " + index + " removed!");
            return true;
        }
        System.out.println("Invalid slot index.");
        return false;
    }

    /**
     * Updates the price for the entire centre.
     */
    public boolean changePricePerSlot(int newPrice) {
        this.price = newPrice;
        System.out.println("Price updated to: " + newPrice);
        return true;
    }

    // --- Getters and Setters ---

    public int getCentreId() { return centreId; }
    public void setCentreId(int centreId) { this.centreId = centreId; }

    public String getCentreName() { return centreName; }
    public void setCentreName(String centreName) { this.centreName = centreName; }

    public String getOpenTime() { return openTime; }
    public void setOpenTime(String openTime) { this.openTime = openTime; }

    public List<Slot> getSlots() { return slots; }
    public void setSlots(List<Slot> slots) { this.slots = slots; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
}