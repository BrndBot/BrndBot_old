/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.db;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.system.Assert;
import com.brndbot.system.SystemProp;
import com.brndbot.system.Utils;

public class UserLogo implements TableModel
{
	final static Logger logger = LoggerFactory.getLogger(UserLogo.class);
	final static String tableName = "userlogo";

	private Integer _user_id;
	private Image _image;

	// Max file size for a logo
	public static long MAX_LOGO_SIZE = 300000L;

	// Max bounding display dimensions to scale the logo
	public static int MAX_BOUNDING_HEIGHT = 180;
	public static int MAX_BOUNDING_WIDTH = 400;

	public UserLogo(int user_id)
	{
		Assert.that(user_id > 0, "User_ID is zero in the UserLogo constructor!");
		_user_id = new Integer(user_id);
		_image = new Image();
	}

	public UserLogo(int user_id, Image image)
	{
		Assert.that(user_id > 0, "User_ID is zero in the UserLogo constructor!");
		_user_id = new Integer(user_id);
		_image = new Image(image);
	}

	public UserLogo(UserLogo m)
	{
		_user_id = m._user_id;
		_image = new Image(m.getImage());
	}

	//static final String CS = ", ";

	public String getTableName () {
		return tableName;
	}

	public Integer getUserID() { 
		return _user_id; 
	}
	
	public void setUserID(int arg) { 
		_user_id = new Integer(arg); 
	} 

	public Image getImage() { 
		return _image; 
	}
	
	public void setImage(Image img) { 
		_image = img; 
	}

	public UserLogo(ResultSet rs)
	{
		try
		{
			_user_id = new Integer(rs.getInt("UserID"));
			_image = new Image();
			_image.setImageID(rs.getInt("ImageID"));
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
//			System.out.println("att name: " + _logo_name);
		}
	}
	
	public int save(DbConnection con) throws ImageException
	{
		logger.debug("saving logo");
		PreparedStatement pstmt = null;
		int image_id = 0;
		try 
		{
			// start a database transaction
			con.startTransaction();
			getImage().setUserID(this.getUserID().intValue());
			image_id = getImage().save(con);
			logger.debug("Image_ID from save: {}", image_id);
			
			pstmt = con.createPreparedStatement("INSERT INTO userlogo (UserID, ImageID" +
					") VALUES (?, ?);"); 
			pstmt.setInt(1, getUserID().intValue());
			pstmt.setInt(2, image_id);
			pstmt.executeUpdate();
			con.commit();
		} 
		catch (SQLException e)
		{
			logger.error ("Error saving logo: {}    {}", 
					e.getClass().getName(),
					e.getMessage());
			con.rollback();
			throw new ImageException("Unexpected error:\n" + e.getMessage());
		}
		finally
		{
			DbUtils.close(pstmt);
		}
		return image_id;
	}

	static public void deleteLogo(int user_id, DbConnection con)
	{
		logger.debug("Deleting logo for user ID {}", user_id);
		try 
		{
			con.startTransaction();
			UserLogo user_logo = UserLogo.getLogoByUserID(user_id, con);
			if (user_logo != null)
			{
				Image.deleteImage(user_logo.getImage().getImageID().intValue(), user_id, con);
			}
			String sql = "DELETE FROM userlogo WHERE UserID = " + 
					user_id + ";";
			con.ExecuteDB(sql, false);
			con.commit();
		} 
		catch (SQLException e1) 
		{
			logger.error ("Error deleting logo: {}    {}", 
					e1.getClass().getName(),
					e1.getMessage());
			con.rollback();
		}
	}
/*
	static public UserLogo makeThisLogo(int user_id, int logo_id, DbConnection con)
	{
		Statement stmt = con.createStatement();
		String sql = "SELECT a.* FROM userlogo as a, User WHERE LogoID = " + logo_id + 
			" AND a.UserID = User.UserID;";
//		System.out.println("SQL: " + sql);
		ResultSet rs = con.QueryDB(sql, stmt);
		UserLogo Logo = null;
		try 
		{
			if (rs.next())
			{
				Logo = new UserLogo(rs);
				if (Logo.getUserID().intValue() != user_id)
				{
					Logo = null;
				}
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			DbUtils.close(stmt, rs);
		}
		return Logo;
	}
*/
	static public String getBoundLogo(
			int user_id, int max_logo_height, int max_logo_width)
	{
		System.out.println("---entering getBoundLogo---");
		DbConnection con = DbConnection.GetDb();
		UserLogo logo = getLogoByUserID(user_id, con);
		String s = "<div style=\"padding:0.625rem;\">Invalid logo</div>";
		if (logo != null)
		{
			int height = logo.getImage().getImageHeight().intValue();
			int width = logo.getImage().getImageWidth().intValue();
			if (height > 0 && width > 0)
			{
				double scalex = (double) max_logo_width / width;
				double scaley = (double) max_logo_height / height;
				double scale = Math.min(scalex, scaley);
				int h = ((int)(height * scale));
				int w = ((int)(width * scale));
				s = String.format("<img src=\"%s\" alt=\"\" height=\"%d\" width=\"%d\"></img>",
						logo.getImage().getImageName(), h, w);
//				System.out.println("Logo: " + s);
			}
		}
		con.close();
		return s;
	}

	static public String getBoundImageByWidth(
			String image_name, int width)
	{
		// local_image_file_name should be something like 'images/file
		return String.format("<img src=\"%s\" alt=\"\" width=\"%d\"></img>",
						image_name, width);
	}

	static public String getBoundImage(
			String local_image_file_name, 
			int max_img_height, 
			int max_img_width,
			boolean use_FQDN)
	{
		String name = local_image_file_name;
		if (use_FQDN)
		{
			// Make sure we have a FQDN
			String tmp = local_image_file_name.toLowerCase();
			if (local_image_file_name.indexOf("http:") == -1)
			{
				name = SystemProp.get(SystemProp.ASSETS) + "//" + local_image_file_name;
			}
		}
		logger.debug("FQDN: " + name);
		String s = String.format("<img src=\"%s\" alt=\"\"></img>",
				name);

		// local_image_file_name should be something like 'images/file
		if (max_img_height > 0 || max_img_width > 0)
		{
			String tomcat_base = SystemProp.get(SystemProp.TOMCAT_BASE);
			String image_file = Utils.Slashies(tomcat_base + "\\" + local_image_file_name);
			BufferedImage bimg = null;
			try {
				bimg = ImageIO.read(new File(image_file));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			if (bimg != null)
			{
				int width = bimg.getWidth();
				int height = bimg.getHeight();
				
				double scalex = (double) max_img_width / width;
				double scaley = (double) max_img_height / height;
				double scale = Math.min(scalex, scaley);
				int h = ((int)(height * scale));
				int w = ((int)(width * scale));
				s = String.format("<img src=\"%s\" alt=\"\" height=\"%d\" width=\"%d\"></img>",
						name, h, w);
			}
		}
		return s;
	}
	
	static public UserLogo getLogoByUserID(int user_id, DbConnection con)
	{
		Statement stmt = con.createStatement();
		String sql = "SELECT * FROM userlogo WHERE UserID = " + user_id + ";";
		logger.debug(sql);
		ResultSet rs = con.QueryDB(sql, stmt);
		UserLogo logo = null;
		try 
		{
			if (rs.next())
			{
				logo = new UserLogo(rs);
				Image image = Image.getImageByID(logo.getImage().getImageID().intValue(), user_id, con);
				logo.setImage(null);
				logo.setImage(image);
			}
			else
				System.out.println("Not found in getLogoByUserID");
		}
		catch (SQLException e) 
		{
			logger.error("Exception in getLogoByUserID(): " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			DbUtils.close(stmt, rs);
		}
		return logo;
	}

	static public int getLogoIDForUser(int user_id, DbConnection con)
	{
		Statement stmt = con.createStatement();
		String sql = "SELECT LogoID FROM userlogo as a, User WHERE a.UserID = " + user_id + 
			" AND a.UserID = User.UserID;";
		ResultSet rs = con.QueryDB(sql, stmt);
		int logo_id = 0;
		try 
		{
			if (rs.next())
			{
				logo_id = rs.getInt(1);
			}
		}
		catch (SQLException e) 
		{
			logger.error("Exception in getLogoByUserID(): {}    {}", 
					e.getClass().getName(),
					e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			DbUtils.close(stmt, rs);
		}
		return logo_id;
	}

	static public boolean isAnImage(String extension)
	{
		return Image.isAnImage(extension);
	}
}
