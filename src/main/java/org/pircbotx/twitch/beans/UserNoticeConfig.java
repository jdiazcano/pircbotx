package org.pircbotx.twitch.beans;

import com.google.common.collect.ImmutableMap;
import lombok.ToString;

import java.util.List;

/**
 * Created by Javier on 25/08/2016.
 */
@ToString
public class UserNoticeConfig extends PrivMsgConfig {
    // https://github.com/justintv/Twitch-API/blob/master/IRC.md#usernotice-1

    private String msgId;
    private int months;
    private String userMessage;
    private String user;

    public UserNoticeConfig(String message, List<String> parsedLine, ImmutableMap<String, String> tags, String line) {
        super(message, parsedLine, tags, line);
        this.msgId = tags.get("msg-id");
        this.months = Integer.parseInt(tags.getOrDefault("msg-param-months", "-1"));
        this.userMessage = tags.get("system-msg");
        this.user = tags.get("login");
    }

    public String getMsgId() {
        return msgId;
    }

    public int getMonths() {
        return months;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public String getUser() {
        return user;
    }
}
