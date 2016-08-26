package org.pircbotx.twitch.enums;

/**
 * Created by Javier on 25/08/2016.
 */
public enum UserType {
    NORMAL(""),
    STAFF("staff"),
    ADMIN("admin"),
    GLOBAL_MOD("global_mod"),
    MOD("mod");

    private String type;

    UserType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
