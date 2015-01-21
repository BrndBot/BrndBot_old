package com.brndbot.channel;

import java.util.ArrayList;
import java.util.List;

/**
 *  A ChannelLayout describes the default set of blocks in
 *  the editor and the limits on adding blocks.
 */
public class ChannelLayout {

	public enum ChannelBlockType {
		PLAIN_TEXT,
		PROMOTION
	}
	
	private List<ChannelBlockType> initialBlocks;
	
	public ChannelLayout() {
		initialBlocks = new ArrayList<>();
	}

	/** Add a block. Use this in constructing a ChannelLayout. */
	public void addBlock (ChannelBlockType cb) {
		initialBlocks.add(cb);
	}
}
