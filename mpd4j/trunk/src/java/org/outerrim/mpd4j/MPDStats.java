/**
 * MPDStats.java
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
 * @author <a href="mailto:sithlord@berlios.de">Michael Osterlie</a>
 *
 */
public class MPDStats {
    private int artists;
    private int albums;
    private int songs;
    private int uptime;
    private int playtime;
    private int dbPlaytime;
    private int dbUpdate;
    
    /*package*/ MPDStats() {}
    
    /*package*/ void setSongs( int s ) { songs = s; }
    /*package*/ void setUptime( int u ) { uptime = u; }
    /*package*/ void setPlaytime( int p ) { playtime = p; }
    /*package*/ void setDBPlaytime( int p ) { dbPlaytime = p; }
    /*package*/ void setDBUpdate( int u ) { dbUpdate = u; }
    /*package*/ void setArtists( int a ) { artists = a; }
    /*package*/ void setAlbums( int a ) { albums = a; }
    
    public int getSongs() { return songs; }
    public int getUptime() { return uptime; }
    public int getPlaytime() { return playtime; }
    public int getDBPlaytime() { return dbPlaytime; }
    public int getDBUpdate() { return dbUpdate; }
    public int getArtists() { return artists; }
    public int getAlbums() { return albums; }
    
    public String toString() {
        return 
            "artists [" + artists + "], " +
            "albums [" + albums + "], " +
            "songs [" + songs + "], " +
            "uptime [" + uptime + "], " +
            "playtime [" + playtime + "], " +
            "db_playtime [" + dbPlaytime + "], " +
            "dbUptime [" + dbUpdate + "]";
    }
}
