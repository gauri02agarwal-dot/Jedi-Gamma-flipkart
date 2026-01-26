package com.flipfit.bean;

import java.util.List;

public class Customer {
    private String customerId; // Add this field
    private double walletBalance;
    private List<String> preferences;

    // Add this Getter
    public String getCustomerId() {
        return customerId;
    }

    // Add this Setter
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public double getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }

    public List<String> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<String> preferences) {
        this.preferences = preferences;
    }
}