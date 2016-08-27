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

import org.pircbotx.snapshot.UserChannelDaoSnapshot;

import javax.annotation.Nullable;

/**
 *
 * @author Leon Blakey
 */
public interface GenericSnapshotEvent {
    /**
     * Snapshot of the UserChannelDao data before the event.
     *
     * @see org.pircbotx.Configuration.Builder#setSnapshotsEnabled(boolean)
     */
    @Nullable
    UserChannelDaoSnapshot getUserChannelDaoSnapshot();
}
