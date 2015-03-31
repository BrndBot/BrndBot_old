package com.brndbot.channel;

import com.brndbot.block.ChannelEnum;
import com.brndbot.channel.ChannelLayout.ChannelBlockType;

/** The Facebook channel specifies the block layout for Facebook. */
public class FacebookChannel extends Channel {

	public FacebookChannel() {
		// TODO Auto-generated constructor stub
		super ();
		channelLayout.addBlock(ChannelBlockType.PLAIN_TEXT);
		channelLayout.addBlock(ChannelBlockType.PROMOTION);
	}
	
	public ChannelEnum getChannelEnum () {
		return ChannelEnum.CH_FACEBOOK;
	}

}
