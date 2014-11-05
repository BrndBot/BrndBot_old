package com.brndbot.user;

import java.util.ArrayList;

public class ImageType
{
	final static ImageType[] _ENUMS;

	public final static ImageType ALTERNATE_LOGO = 
			new ImageType(new Integer(1), "Logo", "images\\uploads\\logos\\",
					300000L);

	public final static ImageType USER_UPLOAD = 
			new ImageType(new Integer(2), "Uploaded by user", "images\\uploads\\user\\",
					300000L);

	public final static ImageType STOCK = 
			new ImageType(new Integer(3), "Stock image", "images\\uploads\\brndbot\\",
					300000L);

	public final static ImageType TEACHER_PHOTO = 
			new ImageType(new Integer(4), "Teacher photo", "images\\uploads\\teacher\\",
					300000L);

	public final static ImageType DEFAULT_LOGO = 
			new ImageType(new Integer(5), "Default Logo", "images\\uploads\\logos\\",
					300000L);

	// When we take HTML and convert to a single image, this is the type for those
	public final static ImageType FUSED_IMAGE = 
			new ImageType(new Integer(6), "Fused Image", "images\\htmlimages\\",
					500000L);

	static
    {
        ArrayList<ImageType> enums = new ArrayList<ImageType>( );

        enums.add(ALTERNATE_LOGO);
        enums.add(USER_UPLOAD);
        enums.add(STOCK);
        enums.add(TEACHER_PHOTO);
        enums.add(DEFAULT_LOGO);
        enums.add(FUSED_IMAGE);
    	_ENUMS = (ImageType[])enums.
            toArray(new ImageType[enums.size()]);
    }

    // Data
    final Integer _item_number;
    final String _item_text;
    final String _folder;
    final Long _max_file_size; // bytes
    
    public ImageType(Integer value, String sent_txt, String folder,
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

    static public ImageType create(int type)
    {
    	ImageType stype = new ImageType(new Integer(type), "", "", 0L);
    	if (stype.equals(ALTERNATE_LOGO))
    	{
    		return ALTERNATE_LOGO;
    	}
    	else if (stype.equals(USER_UPLOAD))
    	{
    		return USER_UPLOAD;
    	}
    	else if (stype.equals(STOCK))
    	{
    		return STOCK;
    	}
    	else if (stype.equals(TEACHER_PHOTO))
    	{
    		return TEACHER_PHOTO;
    	}
    	else if (stype.equals(DEFAULT_LOGO))
    	{
    		return DEFAULT_LOGO;
    	}
    	else if (stype.equals(FUSED_IMAGE))
    	{
    		return FUSED_IMAGE;
    	}

    	stype = null;
    	throw new RuntimeException("Unknown BlockType: " + type);
    }

    public boolean equals(Object obj)
    {
    	boolean equals = false;
    	if (obj != null)
    	{
    		equals = (hashCode() == ((ImageType)obj).hashCode());
    	}
    	return equals;
   	}

    public int hashCode()
    {
    	return getValue().intValue();
    }
}