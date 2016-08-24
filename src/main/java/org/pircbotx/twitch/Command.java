package org.pircbotx.twitch;

import com.google.common.collect.ImmutableMap;
import org.pircbotx.PircBotX;

/**
 * Created by Javier on 24/08/2016.
 */
public interface Command<T> {
    boolean handle(PircBotX bot, String command, T config);

    T build(String line, ImmutableMap<String, String> tags);
}
