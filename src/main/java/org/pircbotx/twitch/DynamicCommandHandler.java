package org.pircbotx.twitch;

import com.google.common.collect.ImmutableMap;
import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Javier on 24/08/2016.
 */
public class DynamicCommandHandler {
    private Map<String, Command> commands = new HashMap<>();

    public DynamicCommandHandler addCommand(String command, Command handler) {
        commands.put(command, handler);
        return this;
    }

    public DynamicCommandHandler removeCommand(String command) {
        commands.remove(command);
        return this;
    }

    public boolean handle(PircBotX bot, String command, User sourceUser, Channel sourceChannel, String target, String message, List<String> parsedLine, ImmutableMap<String, String> tags, String line) {
        Command comm = commands.get(command);
        if (comm != null) {
            return comm.handle(bot, command, sourceUser, sourceChannel, target, comm.build(message, parsedLine, tags, line));
        } else {
            return false;
        }
    }
}
