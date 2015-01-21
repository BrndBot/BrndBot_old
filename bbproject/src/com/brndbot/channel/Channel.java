package com.brndbot.channel;

import com.brndbot.block.ChannelEnum;
import com.brndbot.channel.ChannelLayout.ChannelBlockType;

/** A superclass for the classes that represent specific channels.
 *  It provides an interface for setting up a ChannelLayout and filtering
 *  operations.
 */
public abstract class Channel {

	ChannelLayout channelLayout;
	
	public Channel() {
		channelLayout = new ChannelLayout();
	}

	/* Need a function or servlet to deliver as JSON? Better yet,
	 * make it part of the JSP. Or both at once; make it a JSON
	 * string in the JSP? But then other functionality needs to
	 * be in JavaScript so we don't have constant server callbacks.
	 * Channel layout checking really wants to be in JS, not on
	 * server.
	 * 
	 * Do I need a channel factory to deliver the right channel
	 * based on a session attribute?
	 */
	
	/** Return the ChannelEnum value associated with this channel. */
	public abstract ChannelEnum getChannelEnum ();
		
	
	
	/** Return true if adding a block of the given type is
	 *  permitted, false if not. The position argument is
	 *  0-based.
	 *  
	 *  If not overridden, forbids all adding of blocks.
	 */
	public boolean canAddBlock (ChannelBlockType cbt, int position) {
		return false;
	}
}
