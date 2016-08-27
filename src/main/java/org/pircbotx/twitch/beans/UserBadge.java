package org.pircbotx.twitch.beans;

public class UserBadge {
    private String badge;
    private int quantity;

    public UserBadge(String rawLine) {
        if (rawLine.indexOf('/') > -1) {
            this.badge = rawLine.substring(0, rawLine.indexOf('/'));
            this.quantity = Integer.parseInt(rawLine.substring(rawLine.lastIndexOf('/') + 1));
        } else {
            this.badge = "none";
        }
    }

    public UserBadge(String badge, int quantity) {
        this.badge = badge;
        this.quantity = quantity;
    }

    public String getBadge() {
        return badge;
    }

    public int getQuantity() {
        return quantity;
    }
}
