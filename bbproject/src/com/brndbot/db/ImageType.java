/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.db;

import java.util.HashMap;
import java.util.Map;


/** 
 * Replacing Joe's bizarre pseudo-enum with a real enum.
 */
public enum ImageType
{
	ALTERNATE_LOGO (1, "Logo", "images\\uploads\\logos\\",
			4194304L),
	USER_UPLOAD (2, "Uploaded by user", "images\\uploads\\user\\",
			4194304L),
	STOCK (3, "Stock image", "images\\uploads\\brndbot\\",
			4194304L),
	DEFAULT_LOGO (5, "Default Logo", "images\\uploads\\logos\\",
			4194304L),
	// When we take HTML and convert to a single image, this is the type for those
	FUSED_IMAGE (6, "Fused Image", "images\\htmlimages\\",
			8388608L);

    // Data
    final Integer _item_number;
    final String _item_text;
    final String _folder;
    final Long _max_file_size; // bytes
    
    /* Map from item number to ImageType */
    private static Map<Integer, ImageType> itemNumMap;
    
    static {
    	itemNumMap = new HashMap<>();
    	for (ImageType imageType : ImageType.values()) {
    		itemNumMap.put (imageType._item_number, imageType);
    	}
    }
    
    private ImageType(Integer value, String sent_txt, String folder,
    		long max_file_size)
    {
    	_item_number = value;
    	_item_text = sent_txt;
    	_folder = folder;
    	_max_file_size = max_file_size;
    }
    
    public String getItemText() { return _item_text; }
    
    public String getItemTextLowerCase() { return _item_text.toLowerCase(); }    

    public String getFolder() { return _folder; }

    public Integer getValue() { return _item_number; }
    
    public Long getMaxFileSize() { return _max_file_size; }
    
    public static ImageType getByItemNumber (int num) {
    	return itemNumMap.get (num);
    }



//    public boolean equals(Object obj)
//    {
//    	boolean equals = false;
//    	if (obj != null)
//    	{
//    		equals = (hashCode() == ((ImageType)obj).hashCode());
//    	}
//    	return equals;
//   	}

//    public int hashCode()
//    {
//    	return getValue().intValue();
//    }
}