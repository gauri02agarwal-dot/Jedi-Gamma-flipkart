package com.flipfit.bean;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Bean class representing the Waiting List entity as per the class diagram.
 */
public class WaitingList {
    private int waitListId;
    private int centreId;
    private int slotId;
    private Notification notification; // Reference to the Notification bean
    private Queue<Integer> waitingQueue; // Queue of User/Customer IDs

    // Default Constructor initializing the queue
    public WaitingList() {
        this.waitingQueue = new LinkedList<>();
    }

    // Parameterized Constructor
    public WaitingList(int waitListId, int centreId, int slotId) {
        this.waitListId = waitListId;
        this.centreId = centreId;
        this.slotId = slotId;
        this.waitingQueue = new LinkedList<>();
    }

    // Getters and Setters
    public int getWaitListId() {
        return waitListId;
    }

    public void setWaitListId(int waitListId) {
        this.waitListId = waitListId;
    }

    public int getCentreId() {
        return centreId;
    }

    public void setCentreId(int centreId) {
        this.centreId = centreId;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public Queue<Integer> getWaitingQueue() {
        return waitingQueue;
    }

    public void setWaitingQueue(Queue<Integer> waitingQueue) {
        this.waitingQueue = waitingQueue;
    }

    // Methods defined in the class diagram
    
    /**
     * Adds a user to the end of the waiting queue.
     * @param userId The ID of the customer to add
     */
    public void addToQueue(int userId) {
        this.waitingQueue.add(userId);
    }

    /**
     * Removes and returns the next user from the queue (FIFO).
     * @return The userId of the next person in line
     */
    public Integer removeFromQueue() {
        return this.waitingQueue.poll();
    }
}