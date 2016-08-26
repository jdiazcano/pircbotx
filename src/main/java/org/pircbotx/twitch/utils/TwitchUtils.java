package org.pircbotx.twitch.utils;

import com.google.common.base.Strings;
import org.pircbotx.twitch.beans.TwitchEmote;
import org.pircbotx.twitch.beans.TwitchEmoteLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Javier on 25/08/2016.
 */
public class TwitchUtils {

    /**
     * Parses and convertes to POJOs an emotes tag of twitch
     *
     * Example: emote_id:first_index-last_index,another_first-another_last/another_emote_id:first_index-last_index
     * Emote url: http://static-cdn.jtvnw.net/emoticons/v1/:emote_id/:size
     *
     * @param rawEmotes
     * @return
     */
    public static List<TwitchEmote> parseEmotes(String rawEmotes) {
        List<TwitchEmote> emotes = new ArrayList<>();

        if (Strings.isNullOrEmpty(rawEmotes)) {
            return emotes;
        }

        String[] perEmote = rawEmotes.split("/");
        for(String emoteStr : perEmote) {
            long id = Long.parseLong(emoteStr.substring(0, emoteStr.indexOf(":")));
            String withoutId = emoteStr.substring(emoteStr.indexOf(":") + 1);
            TwitchEmote emote = new TwitchEmote(id);
            String[] locations = withoutId.split(",");
            for (String loc : locations) {
                String[] numbers = loc.split("-");
                int start = Integer.parseInt(numbers[0]);
                int end = Integer.parseInt(numbers[1]);
                emote.addLocation(new TwitchEmoteLocation(start, end));
            }
            emotes.add(emote);
        }
        return emotes;
    }
}
