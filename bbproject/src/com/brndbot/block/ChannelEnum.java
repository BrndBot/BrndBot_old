package com.brndbot.block;

import java.util.ArrayList;

public class ChannelEnum
{
	final static ChannelEnum[] _ENUMS;

	public final static ChannelEnum UNDEFINED = 
			new ChannelEnum(new Integer(-1), "Undefined", 100);

	public final static ChannelEnum EMAIL = 
			new ChannelEnum(new Integer(1), "Email", 280);

	public final static ChannelEnum FACEBOOK = 
			new ChannelEnum(new Integer(2), "Facebook", 600);

	public final static ChannelEnum TWITTER = 
			new ChannelEnum(new Integer(3), "Twitter", -1);

	public final static ChannelEnum POSTER = 
			new ChannelEnum(new Integer(4), "Poster", 400);
	
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

    public Integer getValue() { return _item_number; }
    
    public Integer getDefaultImgWidth() { return _default_img_width; }

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
    	System.out.println("**************Unknown ChannelEnum: " + type);
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
    	return getValue().intValue();
    }
}
