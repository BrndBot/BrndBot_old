/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.user;

public class Palette 
{
	private String _color;
	
	public Palette()
	{
		_color = "ffffff";
	}
	
	public Palette(String color)
	{
		_color = color;
	}

	public String getColor()
	{
		return _color;
	}
	
	public void setColor(String color) { _color = color; }
}
