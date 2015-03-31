/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.block;

import java.util.ArrayList;


public enum ChannelEnum
{
	CH_NONE (-1, "Undefined", 100),
	CH_EMAIL (1, "Email", 280),
	CH_FACEBOOK (2, "Facebook", 600),
	CH_TWITTER (3, "Twitter", -1);
	
	
    // Data
    final Integer itemNumber;
    final String itemText;
    final Integer defaultImgWidth;
    
    private ChannelEnum(Integer value, String sent_txt, int default_img_width)
    {
    	itemNumber = value;
    	itemText = sent_txt;
    	defaultImgWidth = default_img_width;
    }
    
    public String getItemText() { return itemText; }
    
    public String getItemTextLowerCase() { return itemText.toLowerCase(); }    

    public int getValue() { return itemNumber; }
    
    public int getDefaultImgWidth() { return defaultImgWidth; }

    
    /** Return the ChannelEnum with the specified numeric value */
    public static ChannelEnum getByValue (int value) {
    	if (value == CH_EMAIL.itemNumber)
    		return CH_EMAIL;
    	else if (value == CH_FACEBOOK.itemNumber)
    		return CH_FACEBOOK;
    	else if (value == CH_TWITTER.itemNumber)
    		return CH_TWITTER;
    	else
    		return CH_NONE;
    	
    }
    
    /** Return the ChannelEnum with the specified item text */
    public static ChannelEnum getByText (String text) {
    	if (text.equals (CH_EMAIL.itemText))
    		return CH_EMAIL;
    	else if (text.equals (CH_FACEBOOK.itemText))
    		return CH_FACEBOOK;
    	else if (text.equals (CH_TWITTER.itemText))
    		return CH_TWITTER;
    	else
    		return CH_NONE;
    }

}
