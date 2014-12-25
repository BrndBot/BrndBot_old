/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.block;

import java.util.ArrayList;


public class ChannelEnum
{
	public final static int CH_NONE = -1;
	public final static int CH_EMAIL = 1;
	public final static int CH_FACEBOOK = 2;
	public final static int CH_TWITTER = 3;
	public final static int CH_POSTER = 4;
	
	final static ChannelEnum[] _ENUMS;

	/* This is the most contorted way to define a constant I've seen.
	 * I've added actual integer constants above.
	 */
	public final static ChannelEnum UNDEFINED = 
			new ChannelEnum(new Integer(CH_NONE), "Undefined", 100);

	public final static ChannelEnum EMAIL = 
			new ChannelEnum(new Integer(CH_EMAIL), "Email", 280);

	public final static ChannelEnum FACEBOOK = 
			new ChannelEnum(new Integer(CH_FACEBOOK), "Facebook", 600);

	public final static ChannelEnum TWITTER = 
			new ChannelEnum(new Integer(CH_TWITTER), "Twitter", -1);

	public final static ChannelEnum POSTER = 
			new ChannelEnum(new Integer(CH_POSTER), "Poster", 400);
	
	static
    {
        ArrayList<ChannelEnum> enums = new ArrayList<ChannelEnum>( );

        enums.add(UNDEFINED);
        enums.add(EMAIL);
        enums.add(FACEBOOK);
        enums.add(TWITTER);
        enums.add(POSTER);

    	_ENUMS = (ChannelEnum[])enums.
            toArray(new ChannelEnum[enums.size()]);
    }

    // Data
    final Integer _item_number;
    final String _item_text;
    final Integer _default_img_width;
    
    public ChannelEnum(Integer value, String sent_txt, int default_img_width)
    {
    	_item_number = value;
    	_item_text = sent_txt;
    	_default_img_width = default_img_width;
    }
    
    public String getItemText() { return _item_text; }
    
    public String getItemTextLowerCase() { return _item_text.toLowerCase(); }    

    public int getValue() { return _item_number; }
    
    public int getDefaultImgWidth() { return _default_img_width; }

    static public ChannelEnum create(int type)
    {
    	ChannelEnum stype = new ChannelEnum(new Integer(type), "", 0);
    	if (stype.equals(EMAIL))
    	{
    		return EMAIL;
    	}
    	else if (stype.equals(FACEBOOK))
    	{
    		return FACEBOOK;
    	}
    	else if (stype.equals(TWITTER))
    	{
    		return TWITTER;
    	}
    	else if (stype.equals(POSTER))
    	{
    		return POSTER;
    	}
    	return UNDEFINED;
    }

    public boolean equals(Object obj)
    {
    	boolean equals = false;
    	if (obj != null)
    	{
    		equals = (hashCode() == ((ChannelEnum)obj).hashCode());
    	}
    	return equals;
   	}

    public int hashCode()
    {
    	return getValue();
    }
}
