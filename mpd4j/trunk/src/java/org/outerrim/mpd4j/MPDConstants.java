/** 
 * MPDConstants.java
 * Copyright (C) 2005 Michael Osterlie
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package org.outerrim.mpd4j;

/**
 * Constants used for Mpd4j.
 * 
 * @author <a href="mailto:sithlord@berlios.de">Michael Osterlie</a>
 */
public class MPDConstants {
    // Client version
	static public final String MPD4J_VERSION = "0.1";
	
    // Responses
	static public final String MPD_RESPONSE_OK = "OK";
	static public final String MPD_RESPONSE_ERR = "ACK";
	
    // Commands
	static public final String MPD_CMD_STATUS = "status";
	static public final String MPD_CMD_STATISTICS = "stats";
	static public final String MPD_CMD_VOLUME = "setvol";
	static public final String MPD_CMD_PLAY = "play";
	static public final String MPD_CMD_STOP = "stop";
	static public final String MPD_CMD_PAUSE = "pause";
	static public final String MPD_CMD_NEXT = "next";
	static public final String MPD_CMD_PREV = "previous";
	static public final String MPD_CMD_PL_LIST = "playlistinfo";
	static public final String MPD_CMD_ADD = "add";
	static public final String MPD_CMD_REMOVE = "delete";
	static public final String MPD_CMD_PL_CLEAR = "clear";
	static public final String MPD_CMD_PL_SHUFFLE = "shuffle";
	static public final String MPD_CMD_PL_LOAD = "load";
	static public final String MPD_CMD_PL_SAVE = "save";
    static public final String MPD_CMD_PL_REMOVE = "rm";
	static public final String MPD_CMD_KILL = "kill";
	static public final String MPD_CMD_UPDATE = "update";
	static public final String MPD_CMD_REPEAT = "repeat";
    static public final String MPD_CMD_CROSSFADE = "crossfade";
    static public final String MPD_CMD_MOVE = "move";
	static public final String MPD_CMD_START_BULK = "command_list_begin"; // Not yet implemented
	static public final String MPD_CMD_END_BULK = "command_list_end"; // Not yet implemented
    
    // Status items (for MPDStats)
    static public final String MPD_STATS_ARTISTS = "artists";
    static public final String MPD_STATS_ALBUMS = "albums";
    static public final String MPD_STATS_SONGS = "songs";
    static public final String MPD_STATS_UPTIME = "uptime";
    static public final String MPD_STATS_PLAYTIME = "playtime";
    static public final String MPD_STATS_DB_PLAYTIME = "db_playtime";
    static public final String MPD_STATS_DB_UPDATE = "db_update";
}
