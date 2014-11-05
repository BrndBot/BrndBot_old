package com.brndbot.history;

import java.util.ArrayList;

public class HistoryType
{
	final static HistoryType[] _ENUMS;

	public final static HistoryType EMAIL = 
			new HistoryType(new Integer(1), "Email");

	public final static HistoryType FACEBOOK = 
			new HistoryType(new Integer(2), "Facebook");

	public final static HistoryType TWITTER = 
			new HistoryType(new Integer(3), "Twitter");

	static
    {
        ArrayList<HistoryType> enums = new ArrayList<HistoryType>( );

        enums.add(EMAIL);
        enums.add(FACEBOOK);
        enums.add(TWITTER);

    	_ENUMS = (HistoryType[])enums.
            toArray(new HistoryType[enums.size()]);
    }

    // Data
    final Integer _item_number;
    final String _item_text;
    
    public HistoryType(Integer value, String sent_txt)
    {
    	_item_number = value;
    	_item_text = sent_txt;
    }
    
    public String getItemText() { return _item_text; }
    
    public String getItemTextLowerCase() { return _item_text.toLowerCase(); }    

    public Integer getValue() { return _item_number; }
    
    static public HistoryType create(int type)
    {
    	HistoryType stype = new HistoryType(new Integer(type), "");
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
    	stype = null;
    	throw new RuntimeException("Unknown HistoryType: " + type);
    }

    public boolean equals(Object obj)
    {
    	boolean equals = false;
    	if (obj != null)
    	{
    		equals = (hashCode() == ((HistoryType)obj).hashCode());
    	}
    	return equals;
   	}
    
    public int hashCode()
    {
    	return getValue().intValue();
    }
}
