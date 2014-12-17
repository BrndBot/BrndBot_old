/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.block;

import java.util.ArrayList;

// The enumerated valu and prefix are used to define Javascript equivalent variables
//  in bench.jsp, so if you add something to this list, change the jsp too.
public class BlockType
{
	final static BlockType[] _ENUMS;

	public final static BlockType CLASS = 
			new BlockType(new Integer(1), "Class");

	public final static BlockType WORKSHOP = 
			new BlockType(new Integer(2), "Workshop");

	public final static BlockType STAFF = 
			new BlockType(new Integer(3), "Staff");

	public final static BlockType CLIENT = 
			new BlockType(new Integer(4), "Client");

	public final static BlockType FINDER = 
			new BlockType(new Integer(5), "Finder");

	public final static BlockType SALE = 
			new BlockType(new Integer(6), "Sale");

	public final static BlockType SITE = 
			new BlockType(new Integer(7), "Site");
	
	public final static BlockType APPOINTMENT = 
			new BlockType(new Integer(8), "Appointment");

	public final static BlockType SCHEDULE = 
			new BlockType(new Integer(9), "Schedule");

	public final static BlockType TEXT = 
			new BlockType(new Integer(10), "Text");

	public final static BlockType FOOTER = 
			new BlockType(new Integer(11), "Footer");

	public final static BlockType SOCIAL = 
			new BlockType(new Integer(12), "Social");

	public final static BlockType GRAPHIC = 
			new BlockType(new Integer(13), "Graphic");

	public final static BlockType NONCLASS = 
			new BlockType(new Integer(14), "NonClass");

	public final static BlockType NONWORKSHOP = 
			new BlockType(new Integer(15), "NonWorkshop");

	public final static BlockType WEB_LINK = 
			new BlockType(new Integer(16), "WebLink");

	// VIDEO SHOULD BE THE LAST, OR HIGHEST ENUMERATED VALUE.  There is a kludge reference in bench.jsp 
	//  that is based on this assumption.  You could use the size of the arraylist enums to avoid that
	//  assumption if you put a getter on it.
	public final static BlockType VIDEO = 
			new BlockType(new Integer(17), "Video");

	static
    {
        ArrayList<BlockType> enums = new ArrayList<BlockType>( );

        enums.add(WORKSHOP);
        enums.add(CLASS);
        enums.add(STAFF);
        enums.add(APPOINTMENT);
        enums.add(CLIENT);
        enums.add(FINDER);
        enums.add(SALE);
        enums.add(APPOINTMENT);
        enums.add(SCHEDULE);
        enums.add(TEXT);
        enums.add(FOOTER);
        enums.add(SOCIAL);
        enums.add(GRAPHIC);
        enums.add(NONCLASS);
        enums.add(NONWORKSHOP);
        enums.add(WEB_LINK);
        enums.add(GRAPHIC);

    	_ENUMS = (BlockType[])enums.
            toArray(new BlockType[enums.size()]);
    }

    // Data
    final Integer _item_number;
    final String _item_text;
    
    public BlockType(Integer value, String sent_txt)
    {
    	_item_number = value;
    	_item_text = sent_txt;
    }
    
    public String getItemText() { return _item_text; }
    
    public String getItemTextLowerCase() { return _item_text.toLowerCase(); }    

    public Integer getValue() { return _item_number; }
    
    static public BlockType create(int type)
    {
    	BlockType stype = new BlockType(new Integer(type), "");
    	if (stype.equals(APPOINTMENT))
    	{
    		return APPOINTMENT;
    	}
    	else if (stype.equals(CLASS))
    	{
    		return CLASS;
    	}
    	else if (stype.equals(CLIENT))
    	{
    		return CLIENT;
    	}
    	else if (stype.equals(FINDER))
    	{
    		return FINDER;
    	}
    	else if (stype.equals(SALE))
    	{
    		return SALE;
    	}
    	else if (stype.equals(SITE))
    	{
    		return SITE;
    	}
    	else if (stype.equals(STAFF))
    	{
    		return STAFF;
    	}
    	else if (stype.equals(WORKSHOP))
    	{
    		return WORKSHOP;
    	}
    	else if (stype.equals(APPOINTMENT))
    	{
    		return APPOINTMENT;
    	}
    	else if (stype.equals(SCHEDULE))
    	{
    		return SCHEDULE;
    	}
    	else if (stype.equals(TEXT))
    	{
    		return TEXT;
    	}
    	else if (stype.equals(FOOTER))
    	{
    		return FOOTER;
    	}
    	else if (stype.equals(SOCIAL))
    	{
    		return SOCIAL;
    	}
    	else if (stype.equals(GRAPHIC))
    	{
    		return GRAPHIC;
    	}
    	else if (stype.equals(NONCLASS))
    	{
    		return NONCLASS;
    	}
    	else if (stype.equals(NONWORKSHOP))
    	{
    		return NONWORKSHOP;
    	}
    	else if (stype.equals(WEB_LINK))
    	{
    		return WEB_LINK;
    	}
    	else if (stype.equals(VIDEO))
    	{
    		return VIDEO;
    	}

    	stype = null;
    	throw new RuntimeException("Unknown BlockType: " + type);
    }

    public boolean equals(Object obj)
    {
    	boolean equals = false;
    	if (obj != null)
    	{
    		equals = (hashCode() == ((BlockType)obj).hashCode());
    	}
    	return equals;
   	}
    
    public int hashCode()
    {
    	return getValue().intValue();
    }
}