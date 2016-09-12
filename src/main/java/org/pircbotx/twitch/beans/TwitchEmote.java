package org.pircbotx.twitch.beans;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Javier on 25/08/2016.
 */
@ToString
public class TwitchEmote {
    private long id;
    private List<TwitchEmoteLocation> locations = new ArrayList<>();

    public TwitchEmote(long id) {
        this.id = id;
    }

    public TwitchEmote addLocation(TwitchEmoteLocation location) {
        locations.add(location);
        return this;
    }

    public long getId() {
        return id;
    }

    public List<TwitchEmoteLocation> getLocations() {
        return locations;
    }
}
