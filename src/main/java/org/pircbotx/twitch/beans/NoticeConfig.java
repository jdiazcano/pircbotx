package org.pircbotx.twitch.beans;

import com.google.common.collect.ImmutableMap;
import lombok.ToString;

import java.util.List;

/**
 * Created by Javier on 25/08/2016.
 */
@ToString
public class NoticeConfig extends PrivMsgConfig {
    // https://github.com/justintv/Twitch-API/blob/master/IRC.md#usernotice-1

    private String msgId;

    public NoticeConfig(String message, List<String> parsedLine, ImmutableMap<String, String> tags, String line) {
        super(message, parsedLine, tags, line);
        this.msgId = tags.get("msg-id");
    }
}
