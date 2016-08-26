package org.pircbotx.twitch.beans;

import com.google.common.collect.ImmutableMap;
import lombok.ToString;

import java.util.List;

/**
 * Created by Javier on 25/08/2016.
 */
@ToString
public class ClearChatConfig extends BaseConfig {
    // https://github.com/justintv/Twitch-API/blob/master/IRC.md#clearchat-1
    // Line example -> @ban-duration=1;ban-reason=Follow\sthe\srules :tmi.twitch.tv CLEARCHAT #channel :target_username
    private String userName;
    private int banDuration;
    private String banReason;

    public ClearChatConfig(String message, List<String> parsedLine, ImmutableMap<String, String> tags, String line) {
        super(message, parsedLine, line);
        this.userName = line.substring(line.lastIndexOf(":") + 1);
        this.banDuration = Integer.parseInt(tags.getOrDefault("ban-duration", "-1"));
        this.banReason = tags.get("ban-reason");
    }

    public String getUserName() {
        return userName;
    }

    public boolean isChannel() {
        return userName == null;
    }

    public int getBanDuration() {
        return banDuration;
    }

    public String getBanReason() {
        return banReason;
    }

    public boolean isPermanentBan() {
        return banReason != null && banDuration == -1;
    }

    public boolean isBan() {
        return banReason != null;
    }
}
