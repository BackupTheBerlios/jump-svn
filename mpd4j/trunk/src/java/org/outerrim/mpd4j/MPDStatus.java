/**
 * MPDStatus.java
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
public class MPDStatus {
    private String state;
    private int song;
    private long totalTime;
    private long elapsedTime;
    private boolean repeat;
    private int volume;
    private int uptime;
    private int playtime;
    private int songsPlayed;
    private int numArtists;
    private int numSongs;
    private int numAlbums;
    private boolean random;
    private int playlist;
    private int playlistLength;
    private int crossfade;
    private int songid;
    private int bitrate;
    private String audio;
    
    /*package*/ MPDStatus() {}
    
    /*package*/ void setState( String s ) { state = s; }
    /*package*/ void setSong( int s ) { song = s; }
    /*package*/ void setTotalTime( long t ) { totalTime = t; }
    /*package*/ void setElapsedTime( long t ) { elapsedTime = t; }
    /*package*/ void setRepeat( boolean r ) { repeat = r; }
    /*package*/ void setRandom( boolean r ) { random = r; }
    /*package*/ void setVolume( int v ) { volume = v; }
    /*package*/ void setUptime( int u ) { uptime = u; }
    /*package*/ void setPlaytime( int p ) { playtime = p; }
    /*package*/ void setSongsPlayed( int s ) { songsPlayed = s; }
    /*package*/ void setNumberOfArtists( int a ) { numArtists = a; }
    /*package*/ void setNumberOfSongs( int s ) { numSongs = s; }
    /*package*/ void setNumberOfAlbums( int a ) { numAlbums = a; }
    /*package*/ void setPlaylist( int p ) { playlist = p; }
    /*package*/ void setPlaylistLength( int l ) { playlistLength = l; }
    /*package*/ void setCrossfade( int c ) { crossfade = c; }
    /*package*/ void setSongId( int i ) { songid = i; }
    /*package*/ void setBitrate( int b ) { bitrate = b; }
    /*package*/ void setAudio( String a ) { audio = a; }
    
    public String getState() { return state; }
    public int getSong() { return song; }
    public long getTotalTime() { return totalTime; }
    public long getElapsedTime() { return elapsedTime; }
    public boolean isRepeat() { return repeat; }
    public boolean isRandom() { return random; }
    public int getVolume() { return volume; }
    public int getUptime() { return uptime; }
    public int getPlaytime() { return playtime; }
    public int getSongsPlayed() { return songsPlayed; }
    public int getNumberOfArtists() { return numArtists; }
    public int getNumberOfSongs() { return numSongs; }
    public int getNumberOfAlbums() { return numAlbums; }
    public int getPlaylist() { return playlist; }
    public int getPlaylistLength() { return playlistLength; }
    public int getCrossfade() { return crossfade; }
    public int getSongId() { return songid; }
    public int getBitrate() { return bitrate; }
    public String getAudio() { return audio; }
    
    public String toString() {
        return 
            "state [" + state + "], " +
            "song [" + song + "], " +
            "time [" + elapsedTime + ":" + totalTime + "], " +
            "repeat [" + repeat + "], " +
            "random [" + random + "], " +
            "volume [" + volume + "], " +
            "uptime [" + uptime + "], " +
            "playtime [" + playtime + "], " +
            "songs_played [" + songsPlayed + "], " +
            "num_artists [" + numArtists + "], " +
            "num_songs [" + numSongs + "], " +
            "num_albums [" + numAlbums + "], " +
            "playlist [" + playlist + "], " +
            "playlistlength [" + playlistLength + "], " +
            "songid [" + songid + "], " +
            "bitrate [" + bitrate + "], " +
            "audio [" + audio + "], " +
            "xfade [" + crossfade + "]";
    }
}
