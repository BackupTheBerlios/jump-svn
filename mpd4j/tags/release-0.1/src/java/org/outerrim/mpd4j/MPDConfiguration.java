/** 
 * MPDConfiguration.java
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
 * Configuration values for Mpd4j.
 * 
 * @author <a href="mailto:sithlord@berlios.de">Michael Osterlie</a>
 */
public class MPDConfiguration {
	private String host = "";
	private int port = -1;
	
	public MPDConfiguration() {}
	
    /**
     * The host MPD is running on.
     * @return the host
     */
	public String getHost() { return host; }
    
    /**
     * The port MPD is listening on
     * @return the port
     */
	public int getPort() { return port; }
	
    /**
     * Set the host to connect to.
     * @param h the host
     */
	public void setHost( String h ) { host = h; }
    
    /**
     * Set the port MPD is running on.
     * @param p the port
     */
	public void setPort( int p ) { port = p; }
}
