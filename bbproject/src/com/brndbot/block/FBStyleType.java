/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.block;

import java.util.ArrayList;

public class FBStyleType
{
	final static FBStyleType[] _ENUMS;

	public final static FBStyleType FB_IMAGE_AND_BANNER = 
			new FBStyleType(new Integer(1), "Image and Banner");

	public final static FBStyleType FB_TEXT_OVER_IMAGE = 
			new FBStyleType(new Integer(2), "Text over Image");

	public final static FBStyleType FB_LOGO_ONLY = 
			new FBStyleType(new Integer(3), "Logo");

	public final static FBStyleType FB_INFORMATION = 
			new FBStyleType(new Integer(4), "Information");

	static
    {
        ArrayList<FBStyleType> enums = new ArrayList<FBStyleType>( );

        enums.add(FB_IMAGE_AND_BANNER);
        enums.add(FB_TEXT_OVER_IMAGE);
        enums.add(FB_LOGO_ONLY);
        enums.add(FB_INFORMATION);

    	_ENUMS = (FBStyleType[])enums.
            toArray(new FBStyleType[enums.size()]);
    }

    // Data
    final Integer _item_number;
    final String _item_text;
    
    public FBStyleType(Integer value, String sent_txt)
    {
    	_item_number = value;
    	_item_text = sent_txt;
    }
    
    public String getItemText() { return _item_text; }
    
    public String getItemTextLowerCase() { return _item_text.toLowerCase(); }    

    public Integer getValue() { return _item_number; }
    
    static public FBStyleType create(int type)
    {
    	FBStyleType stype = new FBStyleType(new Integer(type), "");
    	if (stype.equals(FB_IMAGE_AND_BANNER))
    	{
    		return FB_IMAGE_AND_BANNER;
    	}
    	else if (stype.equals(FB_TEXT_OVER_IMAGE))
    	{
    		return FB_TEXT_OVER_IMAGE;
    	}
    	else if (stype.equals(FB_LOGO_ONLY))
    	{
    		return FB_LOGO_ONLY;
    	}
    	else if (stype.equals(FB_INFORMATION))
    	{
    		return FB_INFORMATION;
    	}

    	stype = null;
    	throw new RuntimeException("Unknown FBStyleType: " + type);
    }

    public boolean equals(Object obj)
    {
    	boolean equals = false;
    	if (obj != null)
    	{
    		equals = (hashCode() == ((FBStyleType)obj).hashCode());
    	}
    	return equals;
   	}
    
    public int hashCode()
    {
    	return getValue().intValue();
    }
}