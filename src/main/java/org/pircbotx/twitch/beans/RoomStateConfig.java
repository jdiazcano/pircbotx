package org.pircbotx.twitch.beans;

import com.google.common.collect.ImmutableMap;
import lombok.ToString;

/**
 * Created by Javier on 25/08/2016.
 */
@ToString
public class RoomStateConfig {
    private String line;
    private String language;
    private boolean r9k;
    private int slow;
    private boolean subsOnly;

    public RoomStateConfig(String line, ImmutableMap<String, String> tags) {
        this.line = line;
        this.language = tags.getOrDefault("broadcaster-lang", language);
        this.r9k = tags.getOrDefault("r9k", "0").equals("1");
        this.slow = Integer.parseInt(tags.getOrDefault("slow", "0"));
        this.subsOnly = tags.getOrDefault("subs-only", "0").equals("1");
    }

    public String getLanguage() {
        return language;
    }

    public boolean isR9k() {
        return r9k;
    }

    public int isSlow() {
        return slow;
    }

    public boolean isSubsOnly() {
        return subsOnly;
    }

    public String getLine() {
        return line;
    }
}