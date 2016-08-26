package org.pircbotx.twitch.beans;

import java.util.List;

/**
 * Created by Javier on 26/08/2016.
 */
public class BaseConfig {

    private String rawLine;
    private String message;
    private List<String> parsedLine;

    public BaseConfig(String message, List<String> parsedLine, String line) {
        this.rawLine = line;
        this.message = message;
        this.parsedLine = parsedLine;
    }

    public String getRawLine() {
        return rawLine;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getParsedLine() {
        return parsedLine;
    }
}
