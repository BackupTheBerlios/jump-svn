/** 
 * Mpd4j.java
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * API for talking to a Music Player Daemon. 
 * 
 * After creating an instance, either by using a MPDConfiguration object or passing
 * in the host and port that MPD is running on, the methods here can be used to 
 * interact with the daemon.
 * 
 * @author <a href="mailto:sithlord@berlios.de">Michael Osterlie</a>
 */
public class Mpd4j {
	private MPDConfiguration config;
	
	static private final Log log = LogFactory.getLog( Mpd4j.class ); 
	
	/**
	 * Creates a new connection to a MPD.
	 * 
	 * @param host The host on which MPD is running
	 * @param port The port to connect to on the host
	 */
	public Mpd4j( String host, int port ) {
        config = new MPDConfiguration();
		config.setHost( host );
		config.setPort( port );
	}
    
    /**
     * Creates a new connection to a MPD, using the supplied configuration.
     * 
     * @param conf MPDConfiguration containing the configuration information for the server.
     */
    public Mpd4j( MPDConfiguration conf ) {
        config = conf;
    }
	
    /**
     * Retrieves the current playlist.
     * 
     * It has the following known information:
     * <ul>
     *  <li> file
     *  <li> Time
     *  <li> Title
     *  <li> Artist
     *  <li> Track
     *  <li> Album
     *  <li> Genre
     *  <li> Pos
     *  <li> Id
     * </ul>
     * 
     * @return A List containing the information from the playlist
     */
    public List<Track> getPlaylist() {
        List<Track> results = new ArrayList<Track>();
        String response = sendCommand( MPDConstants.MPD_CMD_PL_LIST );
        StringTokenizer st = new StringTokenizer( response, "\n" );
        
        Track track = null;
        while(  st.hasMoreTokens() ) {
            String[] element = st.nextToken().split( ": " );
            
            if( element[0].equals( "file" ) ) {
                track = new Track();
                results.add( track );
                track.setFilename( element[1] );
            } else if( element[0].equals( "Time" ) ) {
                track.setTime( element[1] );
            } else if( element[0].equals( "Title" ) ) {
                track.setName( element[1] );
            } else if( element[0].equals( "Artist" ) ) {
                track.setArtist( element[1] );
            } else if( element[0].equals( "Track" ) ) {
                track.setTrackNumber( Integer.parseInt( element[1] ) );
            } else if( element[0].equals( "Album" ) ) {
                track.setAlbum( element[1] );
            } else if( element[0].equals( "Genre" ) ) {
                track.setGenre( element[1] );
            }
        }
        
        return results;
    }
    
	/**
	 * Starts playing the current playlist.
	 */
	public void play() {
		sendCommand( MPDConstants.MPD_CMD_PLAY );
	}

	/**
	 * Stops the daemon.
	 */
	public void stop() {
		sendCommand( MPDConstants.MPD_CMD_STOP );
	}

	/**
	 * Pauses playback.
	 */
	public void pause() {
        String[] args = new String[] { "1" };
		sendCommand( MPDConstants.MPD_CMD_PAUSE, args );
	}

	/**
	 * Moves to the next song in the playlist.
	 */
	public void next() {
		sendCommand( MPDConstants.MPD_CMD_NEXT );
	}
	
	/**
	 * Moves to the previous song in the playlist.
	 */
	public void previous() {
		sendCommand( MPDConstants.MPD_CMD_PREV );
	}
	
	/**
	 * Refreshes the daemon's database
	 */
	public void refreshDB() {
		sendCommand( MPDConstants.MPD_CMD_UPDATE );
	}
	
	/**
	 * Sets whether to repeat or not.
	 * @param repeat Whether to repeat
	 */
	public void setRepeat( boolean repeat ) {
		String[] args = new String[] { repeat ? "1" : "0" };
		sendCommand( MPDConstants.MPD_CMD_REPEAT, args );
	}
    
    /**
     * Sets the crossfade length.
     * @param seconds number of seconds
     */
    public void setCrossfade( int seconds ) {
        String[] args = new String[] { seconds + "" };
        sendCommand( MPDConstants.MPD_CMD_CROSSFADE, args );        
    }
	
	/**
	 * Shuffles the current playlist.
	 */
	public void shuffle() {
		sendCommand( MPDConstants.MPD_CMD_PL_SHUFFLE );
	}
	
    /**
     * Moves a song to a different spot in the playlist.
     * 
     * @param songid The songid of the song to move.
     * @param toLocation The new location in the playlist.
     */
    public void move( int songid, int toLocation ) {
        String[] args = new String[] { 
                songid + "",
                toLocation + "" };
        sendCommand( MPDConstants.MPD_CMD_MOVE, args );        
        
    }
    
    /**
     * Clears the playlist.
     */
    public void clear() {
        sendCommand( MPDConstants.MPD_CMD_PL_CLEAR );
    }
    
    /**
     * Loads a playlist into MPD
     * @param file filename of the playlist (_filename_.m3u will be loaded).
     */
    public void loadPlaylist( String file ) {
        String[] args = new String[] { file };
        sendCommand( MPDConstants.MPD_CMD_PL_LOAD, args );        
    }
    
    /**
     * Saves the current playlist into a m3u file.
     * @param file filename of the playlist (_filename_.m3u will be saved).
     */
    public void savePlaylist( String file ) {
        String[] args = new String[] { file };
        sendCommand( MPDConstants.MPD_CMD_PL_SAVE, args );        
    }
    
    /**
     * Removes the given playlist from the playlist directory.
     * @param file filename of the playlist (_filename_.m3u will be removed).
     */
    public void removePlaylist( String file ) {
        String[] args = new String[] { file };
        sendCommand( MPDConstants.MPD_CMD_PL_REMOVE, args );        
    }
    
	/**
	 * Adds the given file to the end of the playlist.
	 * @param file The file to add (relative to the daemon's music directory)
	 */
	public void addToPlaylist( String file ) {
		String[] args = new String[] { file };
		sendCommand( MPDConstants.MPD_CMD_ADD, args );
	}
	
    /**
     * Removes the given song from the playlist.
     * @param id The [songid] is the song to remove.
     */
    public void removeFromPlaylist( int id ) {
        String[] args = new String[] { id + "" };
        sendCommand( MPDConstants.MPD_CMD_REMOVE, args );
    }
    
    /**
     * Retrieves the stats for the MPD.
     * 
     * The Map will contain the following known values:
     * <ul>
     *  <li> songs
     *  <li> uptime
     *  <li> playtime
     *  <li> db_playtime
     *  <li> db_update
     * </ul>
     * 
     * @return Map containg the statistics.
     */
    public MPDStats getMPDStats() {
        MPDStats result = new MPDStats();
        
        String response = sendCommand( MPDConstants.MPD_CMD_STATISTICS );
        log.info( response );
        
        StringTokenizer st = new StringTokenizer( response, "\n" );
        while( st.hasMoreTokens() ) {
            String[] stat = st.nextToken().split( ": " );
            if( stat[0].equals( MPDConstants.MPD_STATS_SONGS ) ) {
                result.setSongs( Integer.parseInt( stat[1] ) );
            } else if( stat[0].equals( MPDConstants.MPD_STATS_UPTIME ) ) {
                result.setUptime( Integer.parseInt( stat[1] ) );
            } else if( stat[0].equals( MPDConstants.MPD_STATS_PLAYTIME ) ) {
                result.setPlaytime( Integer.parseInt( stat[1] ) );                
            } else if( stat[0].equals( MPDConstants.MPD_STATS_DB_PLAYTIME ) ) {
                result.setDBPlaytime( Integer.parseInt( stat[1] ) );
            } else if( stat[0].equals( MPDConstants.MPD_STATS_DB_UPDATE ) ) {
                result.setDBUpdate( Integer.parseInt( stat[1] ) );
            } else if( stat[0].equals( MPDConstants.MPD_STATS_ARTISTS ) ) {
                result.setArtists( Integer.parseInt( stat[1] ) );
            } else if( stat[0].equals( MPDConstants.MPD_STATS_ALBUMS ) ) {
                result.setAlbums( Integer.parseInt( stat[1] ) );
            } else {
                log.error( "Stat type not recognized : " + stat[0] );
            }
        }
        
        return result;
    }
    
	/**
	 * Retrieves the status from the daemon.
	 * 
	 * Current known values retrieved:
	 * <ul>
	 *  <li> state
	 *  <li> song
	 *  <li> time
	 *  <li> repeat
	 *  <li> volume
	 *  <li> uptime
	 *  <li> playtime
	 *  <li> songs_played
	 *  <li> num_artists
	 *  <li> num_songs
	 *  <li> num_albums
	 * </ul>
	 * 
	 * @return Map containing each of the status values
	 */
	public MPDStatus getStatus() {
        MPDStatus result = new MPDStatus();
		
		String response = sendCommand( MPDConstants.MPD_CMD_STATUS );
        log.info( response );
		StringTokenizer st = new StringTokenizer( response, "\n" );
		while( st.hasMoreTokens() ) {
			String[] stat = st.nextToken().split( ": " );
            if( stat[0].equals( MPDConstants.MPD_STATUS_STATE ) ) {
                result.setState( stat[1] );
            } else if( stat[0].equals( MPDConstants.MPD_STATUS_SONG ) ) {
                result.setSong( Integer.parseInt( stat[1] ) );
            } else if( stat[0].equals( MPDConstants.MPD_STATUS_TIME ) ) {
                String[] time = stat[1].split( ":" );
                result.setTotalTime( Long.parseLong( time[0] ) );                
                result.setElapsedTime( Long.parseLong( time[1] ) );                
            } else if( stat[0].equals( MPDConstants.MPD_STATUS_REPEAT ) ) {
                result.setRepeat( stat[1].equals( "1" ) ? true : false );
            } else if( stat[0].equals( MPDConstants.MPD_STATUS_RANDOM ) ) {
                result.setRandom( stat[1].equals( "1" ) ? true : false );
            } else if( stat[0].equals( MPDConstants.MPD_STATUS_VOLUME ) ) {
                result.setVolume( Integer.parseInt( stat[1] ) );
            } else if( stat[0].equals( MPDConstants.MPD_STATUS_UPTIME ) ) {
                result.setUptime( Integer.parseInt( stat[1] ) );
            } else if( stat[0].equals( MPDConstants.MPD_STATUS_PLAYTIME ) ) {
                result.setPlaytime( Integer.parseInt( stat[1] ) );
            } else if( stat[0].equals( MPDConstants.MPD_STATUS_SONGS_PLAYED ) ) {
                result.setSongsPlayed( Integer.parseInt( stat[1] ) );
            } else if( stat[0].equals( MPDConstants.MPD_STATUS_NUMBER_ARTISTS ) ) {
                result.setNumberOfArtists( Integer.parseInt( stat[1] ) );
            } else if( stat[0].equals( MPDConstants.MPD_STATUS_NUMBER_SONGS ) ) {
                result.setNumberOfSongs( Integer.parseInt( stat[1] ) );
            } else if( stat[0].equals( MPDConstants.MPD_STATUS_NUMBER_ALBUMS ) ) {
                result.setNumberOfAlbums( Integer.parseInt( stat[1] ) );
            } else if( stat[0].equals( MPDConstants.MPD_STATUS_PLAYLIST ) ) {
                result.setPlaylist( Integer.parseInt( stat[1] ) );
            } else if( stat[0].equals( MPDConstants.MPD_STATUS_PLAYLIST_LENGTH ) ) {
                result.setPlaylistLength( Integer.parseInt( stat[1] ) );
            } else if( stat[0].equals( MPDConstants.MPD_STATUS_CROSSFADE ) ) {
                result.setCrossfade( Integer.parseInt( stat[1] ) );
            } else if( stat[0].equals( MPDConstants.MPD_STATUS_SONGID ) ) {
                result.setSongId( Integer.parseInt( stat[1] ) );
            } else if( stat[0].equals( MPDConstants.MPD_STATUS_BITRATE ) ) {
                result.setBitrate( Integer.parseInt( stat[1] ) );
            } else if( stat[0].equals( MPDConstants.MPD_STATUS_AUDIO ) ) {
                result.setAudio( stat[1] );
            } else {
                log.error( "Status type not recognized : " + stat[0] );
            }
        }
		
		return result;
	}
	
    /**
     * Sets the volume on the MPD server.
     * @param percent percent volume (0-100)
     */
    public void setVolume( int percent ) {
        if( percent < 0 ) { percent = 0; }
        if( percent > 100 ) { percent = 100; }
        
        String[] args = new String[] { percent + "" };
        sendCommand( MPDConstants.MPD_CMD_VOLUME, args );
    }
    
    /**
     * Kills the MPD server.
     */
    public void kill() {
        sendCommand( MPDConstants.MPD_CMD_KILL );
    }
    
	private String sendCommand( String command ) {
		return sendCommand( command, null );
	}
	
	private String sendCommand( String command, String[] args ) {
		StringBuffer returnVal = new StringBuffer();
		
		Socket socket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		
		try {
			socket = new Socket( config.getHost(), config.getPort() );
			
			out = new PrintWriter( socket.getOutputStream(), true );
			in = new BufferedReader( 
					new InputStreamReader( socket.getInputStream() ) );
			
			String response;
			log.debug( "Sending " + command + " command" );
			if( args != null ) {
				log.debug( "\twith arguments : " );
				for( String arg : args ) {
					log.debug(  "\t" + arg );
					command += " \"" + arg + "\"";
				}
			}
			
			out.println( command );

			while( (response = in.readLine()) != null ) {
				if( response.startsWith( MPDConstants.MPD_RESPONSE_OK ) ) {
                    // An OK on its own means the end of the transmission, 
                    // ignore and break out of the loop
					if( response.equals( MPDConstants.MPD_RESPONSE_OK ) ) {
						break;
					} else {
                        // Otherwise, ignore this line and continue
						continue;
					}
				}
				
				if( response.startsWith( MPDConstants.MPD_RESPONSE_ERR ) ) {
					throw new MPDCommandException( response );
				}

				returnVal.append( response + "\n" );
			}
			log.debug( "Done processing" );
		} catch( IOException E ) {
			throw new MPDCommandException( E );
		} finally {
			try {
				in.close();
				out.close();
				socket.close();
			} catch( IOException E ) {
				throw new MPDCommandException( E );
			}
		}
		
		return returnVal.toString();
	}
    
    static public void main( String[] args ) {
        Mpd4j mpd = new Mpd4j( "kashyyyk", 6600 );
        MPDStatus status = mpd.getStatus();
        log.info( "status : " + status.toString() );
    }
}
