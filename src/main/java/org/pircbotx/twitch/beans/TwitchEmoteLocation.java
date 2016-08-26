package org.pircbotx.twitch.beans;

import lombok.ToString;

/**
 * Created by Javier on 25/08/2016.
 */
@ToString
public class TwitchEmoteLocation {
    private int start;
    private int end;

    public TwitchEmoteLocation(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
