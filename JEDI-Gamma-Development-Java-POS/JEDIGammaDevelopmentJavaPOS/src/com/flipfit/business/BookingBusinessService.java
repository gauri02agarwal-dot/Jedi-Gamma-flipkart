package com.flipfit.business;

import com.flipfit.bean.*;
import java.util.ArrayList;
import java.util.List;

public class BookingBusinessService {
    private List<Booking> bookingDb = new ArrayList<>();
    private int bookingCounter = 1;

    public void createBooking(int userId, int centreId, Slot slot, int numberOfMembers, int price) {
        if (slot.getAvailableSeats() >= numberOfMembers) {
            double totalCost = numberOfMembers * price;
            Booking newBooking = new Booking(bookingCounter++, userId, slot.getSlotId(), centreId, numberOfMembers, totalCost);
            slot.setAvailableSeats(slot.getAvailableSeats() - numberOfMembers);
            bookingDb.add(newBooking);
            System.out.println("[SUCCESS] Booking confirmed for User " + userId);
        } else {
            System.out.println("[WAITLIST] Slot full. User " + userId + " added to queue.");
            slot.getWaitingList().addToQueue(userId);
        }
    }

    public boolean cancelBooking(int bookingId, List<Slot> masterSlots) {
        Booking bookingToCancel = bookingDb.stream()
                .filter(b -> b.getBookingId() == bookingId)
                .findFirst().orElse(null);

        if (bookingToCancel == null) return false;

        bookingDb.remove(bookingToCancel);

        Slot slot = masterSlots.stream()
                .filter(s -> s.getSlotId() == bookingToCancel.getSlotId())
                .findFirst().orElse(null);

        if (slot != null) {
            slot.setAvailableSeats(slot.getAvailableSeats() + 1);
            System.out.println("[CANCELLED] Booking " + bookingId + " removed. Slot " + slot.getSlotId() + " now has 1 free seat.");

            // Notify the next person in line
            WaitingList wl = slot.getWaitingList();
            if (wl.getQueueSize() > 0) {
                Integer nextUserId = wl.removeFromQueue();
                Notification notify = new Notification(
                        (int)(Math.random()*1000),
                        nextUserId,
                        "A slot is now free in Slot ID: " + slot.getSlotId() + ". Book now!"
                );
                notify.send();
            }
        }
        return true;
    }

    public List<Booking> getAllBookings() { return bookingDb; }
}