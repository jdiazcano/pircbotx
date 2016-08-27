package org.pircbotx.twitch.beans;

import com.google.common.collect.ImmutableMap;
import lombok.ToString;
import org.pircbotx.twitch.enums.UserType;
import org.pircbotx.twitch.utils.TwitchUtils;

import java.util.List;

/**
 * Created by Javier on 25/08/2016.
 */
@ToString
public class PrivMsgConfig extends BaseConfig {
    // https://github.com/justintv/Twitch-API/blob/master/IRC.md#usernotice-1

    private UserBadge badge;
    private String color;
    private String displayName;
    private List<TwitchEmote> emotes;
    private String messageId;
    private boolean mod;
    private boolean subscriber;
    private boolean turbo;
    private String roomId;
    private String userId;
    private UserType userType;
    private int bits;

    public PrivMsgConfig(String message, List<String> parsedLine, ImmutableMap<String, String> tags, String line) {
        super(message, parsedLine, line);
        this.badge = new UserBadge(tags.getOrDefault("badge", "").toUpperCase());
        this.color = tags.get("color");
        this.displayName = tags.get("display-name");
        this.emotes = TwitchUtils.parseEmotes(tags.get("emotes"));
        this.messageId = tags.get("id");
        this.mod = "1".equals(tags.getOrDefault("mod", "0"));
        this.subscriber = "1".equals(tags.getOrDefault("subscriber", "0"));
        this.turbo = "1".equals(tags.getOrDefault("turbo", "0"));
        this.roomId = tags.get("room-id");
        this.userId = tags.get("user-id");

        if ("".equals(tags.getOrDefault("user-type", ""))) {
            userType = UserType.NORMAL;
        } else {
            this.userType = UserType.valueOf(tags.get("user-type").toUpperCase());
        }

        this.bits = Integer.parseInt(tags.getOrDefault("bits", "-1"));
    }

    public UserBadge getBadge() {
        return badge;
    }

    public String getColor() {
        return color;
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<TwitchEmote> getEmotes() {
        return emotes;
    }

    public String getMessageId() {
        return messageId;
    }

    public boolean isMod() {
        return mod;
    }

    public boolean isSubscriber() {
        return subscriber;
    }

    public boolean isTurbo() {
        return turbo;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getUserId() {
        return userId;
    }

    public UserType getUserType() {
        return userType;
    }

    public int getBits() {
        return bits;
    }
}
