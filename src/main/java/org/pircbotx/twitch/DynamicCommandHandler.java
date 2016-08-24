package org.pircbotx.twitch;

import com.google.common.collect.ImmutableMap;
import org.pircbotx.PircBotX;

import java.util.HashMap;
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

    public boolean handle(PircBotX bot, String command, String line, ImmutableMap<String, String> tags) {
        Command comm = commands.get(command);
        if (comm != null) {
            return comm.handle(bot, command, comm.build(line, tags));
        } else {
            return false;
        }
    }
}
