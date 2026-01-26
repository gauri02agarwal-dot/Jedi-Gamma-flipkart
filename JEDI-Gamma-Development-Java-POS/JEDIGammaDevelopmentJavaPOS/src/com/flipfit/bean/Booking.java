package com.flipfit.bean;

public class Booking {
    private int bookingId;
    private int userId;
    private int slotId;
    private int centreId;
    private int numberOfMembers;
    private double totalCost;

    public Booking(int bookingId, int userId, int slotId, int centreId, int numberOfMembers, double totalCost) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.slotId = slotId;
        this.centreId = centreId;
        this.numberOfMembers = numberOfMembers;
        this.totalCost = totalCost;
    }

    public int getBookingId() { return bookingId; }
    public int getUserId() { return userId; }
    public int getSlotId() { return slotId; }
    public int getCentreId() { return centreId; }
    public int getNumberOfMembers() { return numberOfMembers; }
    public double getTotalCost() { return totalCost; }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId + " | Slot: " + slotId + " | Members: " + numberOfMembers + " | Total: ₹" + totalCost;
    }
}