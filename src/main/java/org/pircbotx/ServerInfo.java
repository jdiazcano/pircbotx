/**
 * Copyright (C) 2010-2014 Leon Blakey <lord.quackstar at gmail.com>
 * <p>
 * This file is part of PircBotX.
 * <p>
 * PircBotX is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * <p>
 * PircBotX is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License along with
 * PircBotX. If not, see <http://www.gnu.org/licenses/>.
 */
package org.pircbotx;

import com.google.common.collect.ImmutableMap;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.StringTokenizer;

/**
 * This is a giant info bean of various things about the server. This is
 * separate from the {@link PircBotX} class due to its length
 * <p/>
 * Most info thanks to <a href="www.irc.org/tech_docs/005.html">this great
 * website
 * </a> on what each one does
 * <p>
 * Note: On get methods that return int, -1 means the value exists but is 
 * unparsable natively
 *
 * @author Leon Blakey
 */
@Data
@Setter(AccessLevel.NONE)
public class ServerInfo {
    private static final Logger log = LoggerFactory.getLogger(ServerInfo.class);
    protected final PircBotX bot;
    //004 information
    protected String serverName;
    protected String serverVersion;
    protected String userModes;
    //005 information
    protected LinkedHashMap<String, String> isupportRaw = new LinkedHashMap<>();
    protected String prefixes;
    protected String channelTypes;
    protected String channelModes;
    protected int maxModes;
    protected int maxChannels;
    protected String chanlimit;
    protected int maxNickLength;
    protected int maxBans;
    protected ImmutableMap<String, Integer> maxList;
    protected String network;
    protected String exceptBans;
    protected String exceptInvites;
    protected String invites;
    protected boolean wallOps;
    protected boolean wallVoices;
    protected String statusMessage;
    protected String caseMapping;
    protected String eList;
    protected int topicLength;
    protected int kickLength;
    protected int channelLength;
    protected String channelIDLength;
    protected String standard;
    protected int silence;
    protected boolean RFC2812;
    protected boolean penalty;
    protected boolean forcedNickChanges;
    protected boolean safeList;
    protected int awayLength;
    protected boolean noQuit;
    protected boolean userIPExists;
    protected boolean cPrivMsgExists;
    protected boolean cNoticeExists;
    protected int maxTargets;
    protected boolean knockExists;
    protected boolean vChannels;
    protected int watchMax;
    protected boolean whoX;
    protected boolean callerID;
    protected boolean accept;
    protected String language;
    protected String extBanPrefix;
    protected String extBanList;
    //Other information
    @Setter(AccessLevel.PROTECTED)
    protected String motd;
    protected int highestConnections;
    protected int highestClients;
    protected int totalUsers;
    protected int totalInvisibleUsers;
    protected int totalServers;
    protected int totalOperatorsOnline;
    protected int totalUnknownConnections;
    protected int totalChannelsFormed;
    protected int serverUsers;
    protected int connectedServers;

    public void parse(int code, List<String> parsedLine) {
        //Pass off to speicific methods
        if (code == 004) {
            parse004(parsedLine);
        } else if (code == 005) {
            parse005(parsedLine);
        }
    }

    protected void parse004(List<String> parsedLine) {
        //004 PircBotX pratchett.freenode.net ircd-seven-1.1.3 DOQRSZaghilopswz CFILMPQbcefgijklmnopqrstvz bkloveqjfI
        serverName = Utils.tryGetIndex(parsedLine, 1, null);
        serverVersion = Utils.tryGetIndex(parsedLine, 2, null);
        userModes = Utils.tryGetIndex(parsedLine, 3, null);
        channelModes = Utils.tryGetIndex(parsedLine, 4, null);
    }

    protected void parse005(List<String> parsedLine) {
        //REFERENCE: http://www.irc.org/tech_docs/005.html
        for (String curItem : parsedLine) {
            String[] itemParts = curItem.split("=", 2);
            String key = itemParts[0];
            String value = (itemParts.length == 2) ? itemParts[1] : "";
            isupportRaw.put(key, value);
            if ("PREFIX".equalsIgnoreCase(key)) {
                prefixes = value;
            } else if ("CHANTYPES".equalsIgnoreCase(key)) {
                channelTypes = value;
            } else if ("CHANMODES".equalsIgnoreCase(key)) {
                channelModes = value;
            } else if ("MODES".equalsIgnoreCase(key)) {
                maxModes = tryParseInt("MODES", value);
            } else if ("MAXCHANNELS".equalsIgnoreCase(key)) {
                maxChannels = tryParseInt("MAXCHANNELS", value);
            } else if ("CHANLIMIT".equalsIgnoreCase(key)) {
                chanlimit = value;
            } else if ("NICKLEN".equalsIgnoreCase(key)) {
                maxNickLength = tryParseInt("NICKLEN", value);
            } else if ("MAXBANS".equalsIgnoreCase(key)) {
                maxBans = tryParseInt("MAXBANS", value);
            } else if ("MAXLIST".equalsIgnoreCase(key)) {
                StringTokenizer maxListTokens = new StringTokenizer(value, ":,");
                ImmutableMap.Builder<String, Integer> maxListBuilder = ImmutableMap.builder();
                while (maxListTokens.hasMoreTokens()) {
                    String next = maxListTokens.nextToken();
                    maxListBuilder.put(next, tryParseInt("MAXLIST>" + next, maxListTokens.nextToken()));
                }
                maxList = maxListBuilder.build();
            } else if ("NETWORK".equalsIgnoreCase(key)) {
                network = value;
            } else if ("EXCEPTS".equalsIgnoreCase(key)) {
                exceptBans = value;
            } else if ("INVEX".equalsIgnoreCase(key)) {
                exceptInvites = value;
            } else if ("WALLCHOPS".equalsIgnoreCase(key)) {
                wallOps = true;
            } else if ("WALLVOICES".equalsIgnoreCase(key)) {
                wallVoices = true;
            } else if ("STATUSMSG".equalsIgnoreCase(key)) {
                statusMessage = value;
            } else if ("CASEMAPPING".equalsIgnoreCase(key)) {
                caseMapping = value;
            } else if ("ELIST".equalsIgnoreCase(key)) {
                eList = value;
            } else if ("TOPICLEN".equalsIgnoreCase(key)) {
                topicLength = tryParseInt("TOPICLEN", value);
            } else if ("KICKLEN".equalsIgnoreCase(key)) {
                kickLength = tryParseInt("KICKLEN", value);
            } else if ("CHANNELLEN".equalsIgnoreCase(key)) {
                channelLength = tryParseInt("CHANNELLEN", value);
            } else if ("CHIDLEN".equalsIgnoreCase(key)) {
                channelIDLength = "!:" + tryParseInt("CHIDLEN", value);
            } else if ("IDCHAN".equalsIgnoreCase(key)) {
                channelIDLength = value;
            } else if ("STD".equalsIgnoreCase(key)) {
                standard = value;
            } else if ("SILENCE".equalsIgnoreCase(key)) {
                silence = tryParseInt("SILENCE", value);
            } else if ("RFC2812".equalsIgnoreCase(key)) {
                RFC2812 = true;
            } else if ("PENALTY".equalsIgnoreCase(key)) {
                penalty = true;
            } else if ("CPRIVMSG".equalsIgnoreCase(key)) {
                cPrivMsgExists = true;
            } else if ("CNOTICE".equalsIgnoreCase(key)) {
                cNoticeExists = true;
            } else if ("SAFELIST".equalsIgnoreCase(key)) {
                safeList = true;
            } else if ("KNOCK".equalsIgnoreCase(key)) {
                knockExists = true;
            } else if ("WHOX".equalsIgnoreCase(key)) {
                whoX = true;
            } else if ("CALLERID".equalsIgnoreCase(key) || "ACCEPT".equalsIgnoreCase(key)) {
                callerID = true;
            } else if ("USERIP".equalsIgnoreCase(key)) {
                userIPExists = true;
            } else if ("CNOTICE".equalsIgnoreCase(key)) {
                cNoticeExists = true;
            } else if ("EXTBAN".equalsIgnoreCase(key)) {
                if (value.contains(",")) {
                    String[] valueSplit = StringUtils.split(value, ",", 2);
                    if (valueSplit.length == 2) {
                        extBanPrefix = valueSplit[0];
                        extBanList = valueSplit[1];
                    } else {
                        extBanPrefix = null;
                        extBanList = valueSplit[0];
                    }
                } else {
                    extBanList = value;
                }
            }
        }
        //Freenode
        //005 PircBotX CHANTYPES=# EXCEPTS INVEX CHANMODES=eIbq,k,flj,CFLMPQcgimnprstz CHANLIMIT=#:120 PREFIX=(ov)@+ MAXLIST=bqeI:100 MODES=4 NETWORK=freenode KNOCK STATUSMSG=@+ CALLERID=g :are supported by this server
        //005 PircBotX CASEMAPPING=rfc1459 CHARSET=ascii NICKLEN=16 CHANNELLEN=50 TOPICLEN=390 ETRACE CPRIVMSG CNOTICE DEAF=D MONITOR=100 FNC TARGMAX=NAMES:1,LIST:1,KICK:1,WHOIS:1,PRIVMSG:4,NOTICE:4,ACCEPT:,MONITOR: :are supported by this server
        //005 PircBotX EXTBAN=$,arx WHOX CLIENTVER=3.0 SAFELIST ELIST=CTU :are supported by this server
        //Rizon
        //005 PircBotX CALLERID CASEMAPPING=rfc1459 DEAF=D KICKLEN=160 MODES=4 NICKLEN=30 TOPICLEN=390 PREFIX=(qaohv)~&@%+ STATUSMSG=~&@%+ NETWORK=Rizon MAXLIST=beI:100 TARGMAX=ACCEPT:,KICK:1,LIST:1,NAMES:1,NOTICE:4,PRIVMSG:4,WHOIS:1 CHANTYPES=# :are supported by this server
        //005 PircBotX CHANLIMIT=#:75 CHANNELLEN=50 CHANMODES=beI,k,l,BCMNORScimnpstz AWAYLEN=160 ELIST=CMNTU SAFELIST KNOCK NAMESX UHNAMES FNC EXCEPTS=e INVEX=I :are supported by this server
        //Mozilla
        //005 QTest AWAYLEN=200 CASEMAPPING=rfc1459 CHANMODES=Zbeg,k,FLfjl,ABCDKMNOQRSTcimnprstuz CHANNELLEN=64 CHANTYPES=# CHARSET=ascii ELIST=MU ESILENCE EXCEPTS=e EXTBAN=,ABCNOQRSTUcmprz FNC KICKLEN=255 MAP :are supported by this server
        //005 QTest MAXBANS=60 MAXCHANNELS=100 MAXPARA=32 MAXTARGETS=20 MODES=20 NAMESX NETWORK=Mozilla NICKLEN=31 OPERLOG OVERRIDE PREFIX=(Yqaohv)!~&@%+ SECURELIST SILENCE=32 :are supported by this server
        //005 QTest SSL=[::]:6697 STARTTLS STATUSMSG=!~&@%+ TOPICLEN=307 UHNAMES USERIP VBANLIST WALLCHOPS WALLVOICES WATCH=32 :are supported by this server
    }

    private static int tryParseInt(String name, String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            log.warn("Unparsable server info key '{}' value '{}' {}", name, value, ExceptionUtils.getMessage(e));
            return -1;
        }
    }

    /**
     * Get all supported server options as a map. Be careful about calling this
     * very early in the connection phase as we might not of received all the
     * 005 lines yet
     *
     * @return An <i>immutable copy</i> of the current supported options
     */
    public ImmutableMap<String, String> getIsupportRaw() {
        return ImmutableMap.copyOf(isupportRaw);
    }

    public String getISupportValue(String key) {
        return isupportRaw.get(key);
    }

    @SuppressWarnings("unchecked")
    public <T extends PircBotX> T getBot() {
        return (T) bot;
    }
}
