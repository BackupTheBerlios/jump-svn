/** 
 * MPDCommandException.java
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
 * Exception thrown by Mpd4j when an error occurs talking to MPD.
 *  
 * @author <a href="mailto:sithlord@berlios.de">Michael Osterlie</a>
 */
public class MPDCommandException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MPDCommandException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MPDCommandException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public MPDCommandException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public MPDCommandException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
}
