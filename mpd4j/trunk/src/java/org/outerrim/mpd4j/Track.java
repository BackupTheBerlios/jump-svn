/**
 * 
 */
package org.outerrim.mpd4j;

/**
 * @author <a href="mailto:sithlord@berlios.de">Michael Osterlie</a>
 *
 */
public class Track {
    private String name;
    private String time;
    private String artist;
    private String album;
    private String genre;
    private int trackNum;
    private String filename;
    
    public Track() {}
    
    public String getName() { return name; }
    public String getTime() { return time; }
    public String getArtist() { return artist; }
    public String getAlbum() { return album; }
    public String getGenre() { return genre; }
    public Integer getTrackNumber() { return trackNum; }
    public String getFilename() { return filename; }
    
    public void setName( String n ) { name = n; }
    public void setTime( String t ) { time = t; }
    public void setArtist( String a ) { artist = a; }
    public void setAlbum( String a ) { album = a; }
    public void setGenre( String g ) { genre = g; }
    public void setTrackNumber( int n ) { trackNum = n; }
    public void setFilename( String f ) { filename =  f; }
    
    public String toString() { return artist + " - " + name; }
}
