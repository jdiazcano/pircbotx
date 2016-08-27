package org.pircbotx.twitch;

import com.google.common.collect.ImmutableMap;
import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.UserHostmask;

import java.util.List;

/**
 * Created by Javier on 24/08/2016.
 */
public interface Command<T> {
    boolean handle(PircBotX bot, String command, UserHostmask sourceUser, Channel sourceChannel, String target, T config);

    T build(String message, List<String> parsedLine, ImmutableMap<String, String> tags, String line);
}
