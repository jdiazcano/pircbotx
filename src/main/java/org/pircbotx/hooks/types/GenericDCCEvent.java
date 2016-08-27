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
package org.pircbotx.hooks.types;

import java.net.InetAddress;

/**
 * Any event dealing with DCC. This includes chat and file transfers. This is
 * more of a marker event then anything else as DCC events don't have much in
 * common
 *
 * @author Leon Blakey
 */
public interface GenericDCCEvent extends GenericUserEvent {
    boolean isPassive();

    InetAddress getAddress();

    int getPort();

    String getToken();
}
