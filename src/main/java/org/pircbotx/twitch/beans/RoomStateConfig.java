package org.pircbotx.twitch.beans;

import com.google.common.collect.ImmutableMap;
import lombok.ToString;

import java.util.List;

/**
 * Created by Javier on 25/08/2016.
 */
@ToString
public class RoomStateConfig extends BaseConfig {
    // https://github.com/justintv/Twitch-API/blob/master/IRC.md#roomstate-1

    private String language;
    private boolean r9k;
    private int slow;
    private boolean subsOnly;

    public RoomStateConfig(String message, List<String> parsedLine, ImmutableMap<String, String> tags, String line) {
        super(message, parsedLine, line);
        this.language = tags.getOrDefault("broadcaster-lang", language);
        this.r9k = "1".equals(tags.getOrDefault("r9k", "0"));
        this.slow = Integer.parseInt(tags.getOrDefault("slow", "0"));
        this.subsOnly = "1".equals(tags.getOrDefault("subs-only", "0"));
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

}
