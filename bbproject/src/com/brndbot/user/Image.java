package com.brndbot.user;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javazoom.upload.UploadFile;

import com.brndbot.db.DbConnection;
import com.brndbot.db.DbUtils;
import com.brndbot.system.SystemProp;
import com.brndbot.system.Utils;
import com.brndbot.util.AppEnvironment;

public class Image 
{
	
	final static Logger logger = LoggerFactory.getLogger(Image.class);
	
	private Integer _image_id;
	private Integer _user_id;  // 0 if it's a Brndbot stock image
	private ImageType _image_type;
	private String _image_name;
	private Integer _image_size;
	private Integer _image_height;
	private Integer _image_width;
	private Blob _image;
	private byte[] _image_bytes; // to store to db

	// Max file size for a image
	public static long MAX_IMAGE_SIZE = 500000L;
	
	public Image()
	{
		_image_id = new Integer(0);
		_user_id = new Integer(0);
		_image_type = null;
		_image_name = "";
		_image_size = new Integer(0);
		_image_height = new Integer(0);
		_image_width = new Integer(0);
		_image = null;
	}

	public Image(Image m)
	{
		_image_id = m._image_id;
		_user_id = m._user_id;
		_image_name = m._image_name;
		_image_type = m._image_type;
		_image_size = new Integer(m._image_size.intValue());
		_image_height = new Integer(m._image_height.intValue());
		_image_width = new Integer(m._image_width.intValue());
		_image = m._image;
	}

	static final String CS = ", ";

	public Integer getImageID() { return _image_id; }
	public void setImageID(int arg) { _image_id = new Integer(arg); } 

	public Integer getUserID() { return _user_id; }
	public void setUserID(int arg) { _user_id = new Integer(arg); } 

	public String getImageName() { return _image_name; }
	public void setImageName(String arg) { _image_name = arg; }

	public ImageType getImageType() { return _image_type; }
	public void setImageType(ImageType arg) { _image_type = arg; }

	public Integer getImageHeight() { return _image_height; }
	public void setImageHeight(int arg) { _image_height = new Integer(arg); } 

	public Integer getImageWidth() { return _image_width; }
	public void setImageWidth(int arg) { _image_width = new Integer(arg); } 

	public Integer getImageSize() 
	{
		return _image_size; 
	}

	public void setImageSize(int arg) { _image_size = new Integer(arg); } 

	public byte[] getImageBytes() 
	{ 
		if (_image_bytes != null)
			return _image_bytes;
		return new byte[0];
	}

	public void setImageBytes(byte[] bytes) 
	{
		_image_bytes = null;
		_image_bytes = new byte[bytes.length];
		for (int i=0; i < bytes.length; i++)
		{
			_image_bytes[i] = bytes[i];
		}
	} 

	public Blob getImage()
	{ 
		return _image; 
	}

	public void setImage(Blob arg) { _image = arg; }

	public Image(ResultSet rs)
	{
		try
		{
			_image_id = new Integer(rs.getInt("ImageID"));
			_user_id = new Integer(rs.getInt("UserID"));
			_image_type = ImageType.create(rs.getInt("ImageType"));
			_image_name = rs.getString("ImageName");
			_image_size = new Integer(rs.getInt("ImageSize"));
			_image_height = new Integer(rs.getInt("ImageHeight"));
			_image_width = new Integer(rs.getInt("ImageWidth"));
// never used			_image = rs.getBlob("Image");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
//			System.out.println("att name: " + _image_name);
		}
	}

	public int save(DbConnection con) throws SQLException
	{
		logger.debug("IMAGE SAVE SAVE SAVE**************");
		PreparedStatement pstmt = con.createPreparedStatement("INSERT INTO images (" +
				"ImageType, UserID, ImageName, ImageSize, ImageHeight, ImageWidth) " +
				"VALUES (?, ?, ?, ?, ?, ?);");
		pstmt.setInt(1, getImageType().getValue().intValue());
		pstmt.setInt(2, getUserID().intValue());
		pstmt.setString(3, getImageName());
		pstmt.setInt(4, getImageSize().intValue());
		pstmt.setInt(5, getImageHeight().intValue());
		pstmt.setInt(6, getImageWidth().intValue());
//		pstmt.setBytes(7, getImageBytes());  Not sure that we ever use
		// Timestamp is defined by default as current system time 
		pstmt.executeUpdate();
		pstmt.close();
		logger.debug ("returning from save");
		return DbUtils.getLastInsertID(con);
	}

	static public int getImageCount(DbConnection con)
	{
		String sql = "SELECT COUNT(*) FROM images;";
		Statement stmt = con.createStatement();
		ResultSet rs = null;
		int count = 0;
		try
		{
			rs = stmt.executeQuery(sql);
			if (rs.next())
			{
				count = rs.getInt(1);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		finally
		{
			DbUtils.close(stmt, rs);
		}
		return count;
	}

	static public void deleteImage(int image_id, int user_id, DbConnection con) 
			throws SQLException
	{
		Image Image = makeThisImage(image_id, user_id, con);
		if (Image != null)
		{
			String sql = "DELETE FROM images WHERE ImageID = " + image_id + " AND UserID = " + user_id + ";";
			con.ExecuteDB(sql, false);
		}
	}

	static public Image makeThisImage(int image_id, int user_id, DbConnection con)
	{
		Statement stmt = con.createStatement();
/*
			_image_id = new Integer(rs.getInt("ImageID"));
			_user_id = new Integer(rs.getInt("UserID"));
			_image_type = ImageType.create(rs.getInt("ImageType"));
			_image_name = rs.getString("ImageName");
			_image_size = new Integer(rs.getInt("ImageSize"));
			_image_height = new Integer(rs.getInt("ImageHeight"));
			_image_width = new Integer(rs.getInt("ImageWidth"));
 */
		String sql = "SELECT ImageID, UserID, ImageType, ImageName, ImageSize, ImageHeight, ImageWidth FROM images WHERE ImageID = " + image_id + " AND UserID = " + user_id + ";";
//		System.out.println("SQL: " + sql);
		ResultSet rs = con.QueryDB(sql, stmt);
		Image image = null;
		try 
		{
			if (rs.next())
			{
				image = new Image(rs);
				if (image.getImageID().intValue() != image_id)
				{
					image = null;
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
		return image;
	}

	static public String getBoundImage(int image_id, int user_id,
			int max_image_height, int max_image_width)
	{
		logger.debug("----entering getBoundImage----");
		DbConnection con = DbConnection.GetDb();
		Image image = getImageByID(image_id, user_id, con);
		String s = "<div style=\"padding:0.625rem;\">Invalid image</div>";
		if (image != null)
		{
			int height = image.getImageHeight().intValue();
			int width = image.getImageWidth().intValue();
			if (height > 0 && width > 0)
			{
				double scalex = (double) max_image_width / width;
				double scaley = (double) max_image_height / height;
				double scale = Math.min(scalex, scaley);
				int h = ((int)(height * scale));
				int w = ((int)(width * scale));
				s = String.format("<img src=\"%s\" alt=\"\" height=\"%d\" width=\"%d\"></img>",
						image.getImageName(), h, w);
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
			String local_image_file_name, int max_img_height, int max_img_width)
	{
		// local_image_file_name should be something like 'images/file
		String tomcat_base = SystemProp.get(SystemProp.TOMCAT_BASE);
		String image_file = Utils.Slashies(tomcat_base + "\\" + local_image_file_name);
		logger.debug("getBoundImage, Image file path: {}", image_file);
		BufferedImage bimg = null;
		try {
			bimg = ImageIO.read(new File(image_file));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		String s = "<div style=\"padding:10px;\">Invalid image</div>";

		if (bimg != null)
		{
			int width = bimg.getWidth();
			int height = bimg.getHeight();

			s = genImageTag(null, local_image_file_name, max_img_height, max_img_width, height, width);
		}
		return s;
	}

	static public String genImageTag(JSONObject json_obj, String local_image_file_name, 
			 int max_img_height, int max_img_width, int height, int width)
	{
		String s = "";
		if (height > 0 && width > 0)
		{
			double scalex = (double) max_img_width / width;
			double scaley = (double) max_img_height / height;
			double scale = Math.min(scalex, scaley);
			int h = ((int)(height * scale));
			int w = ((int)(width * scale));
			if (json_obj != null)
			{
				try {
					json_obj.put("height", h);
					json_obj.put("width", w);
					json_obj.put("imgTag", local_image_file_name);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			else
				s = String.format("<img src=\"%s\" alt=\"\" height=\"%d\" width=\"%d\"></img>",
					local_image_file_name, h, w);
		}
		return s;
	}

	static public Image getImageByID(int image_id, int user_id, DbConnection con)
	{
		Statement stmt = con.createStatement();
//		String sql = "SELECT * FROM images WHERE ImageID = " + image_id + " AND UserID = " + user_id + ";";
		String sql = "SELECT ImageID, UserID, ImageType, ImageName, ImageSize, ImageHeight, ImageWidth FROM images WHERE ImageID = " + image_id + " AND UserID = " + user_id + ";";
		logger.debug("For images:\n" + sql);
		ResultSet rs = con.QueryDB(sql, stmt);
		Image Image = null;
		try 
		{
			if (rs.next())
			{
				Image = new Image(rs);
				logger.debug("FOUNDLOGO IMAGE");
			}
			else
				logger.error("Image not found!");
		}
		catch (SQLException e) 
		{
			logger.error("Exception in getImageByID(): " + 
						e.getClass().getName() + ", message = " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			DbUtils.close(stmt, rs);
		}
		return Image;
	}

	static public boolean isAnImage(String extension)
	{
		boolean ret = (extension.equals("png") ||
				extension.equals("bmp") ||
				extension.equals("jpg") ||
				extension.equals("gif"));
		return ret;
	}

	static public Image uploadFile(
			int user_id, 
			ImageType image_type, 
			@SuppressWarnings("rawtypes") Hashtable files, 
			DbConnection con) throws IOException
	{
		Image return_image = null;
		FileOutputStream fos = null;

		if (files.size() > 0)
		{
			// We got one
			Enumeration<?> e = files.keys();
			return_image = new Image();
			UploadFile image_file = (UploadFile)files.get(e.nextElement());
			InputStream uploadInStream = image_file.getInpuStream();
			if (uploadInStream != null)
			{
				String original_name = image_file.getFileName().toLowerCase();
				int pos = original_name.lastIndexOf(".");

				// Make sure this is an image
				String extension = original_name.substring(pos+1).toLowerCase();
				if (!Image.isAnImage(extension))
				{
					con.close();
					logger.debug("Invalid file type, must be an image, extension: " + extension);
					//TODO these log messages really don't help the user!!!
					return null;
				}
				else
				{
					if (image_file.getFileSize() > image_type.getMaxFileSize())
					{
						logger.info("Logo file is too big");
						//TODO these log messages really don't help the user!!!
						return null;
					}

					byte[] bytes = new byte[(int)image_file.getFileSize()+1];
					uploadInStream.read(bytes);

					// Store in the database
					int count = Image.getImageCount(con);
					return_image.setUserID(user_id);
					return_image.setImageType(image_type);
					return_image.setImageBytes(bytes);
					String url_file_name = Utils.Slashies(image_type.getFolder() + user_id + "-" + count + "-" +
							image_file.getFileName());
					System.out.println("Relative URL file name: " + url_file_name);
					return_image.setImageName(url_file_name);
					return_image.setImageSize((int)image_file.getFileSize());

					// Now save image to the images/uploads/.. folder
					//String tomcat_base = SystemProp.get(SystemProp.TOMCAT_BASE);
					//image_file.setFileName(Utils.Slashies(tomcat_base + "\\" + url_file_name));
					image_file.setFileName(AppEnvironment.baseInAppDirectory(url_file_name));
					fos = new FileOutputStream(image_file.getFileName());
					fos.write(bytes);
					fos.close();

					BufferedImage bimg = ImageIO.read(new File(image_file.getFileName()));
					return_image.setImageWidth(bimg.getWidth());
					return_image.setImageHeight(bimg.getHeight());
				}
			}
		}
		return return_image;
	}

	static public JSONArray getImagesForDisplay(int user_id, ImageType image_type, DbConnection con)
	{
		Statement stmt = con.createStatement();
		ResultSet rs = null;
		String sql = "SELECT ImageID, ImageName, ImageHeight, ImageWidth FROM images WHERE UserID = " + user_id + 
				" AND ImageType = " + image_type.getValue().intValue() + " ORDER BY CreateDateTime;";
		logger.debug("getImagesForDisplay: " + sql);
		JSONArray json_array = new JSONArray();
		try 
		{
			rs = stmt.executeQuery(sql);
			while (rs.next())
			{
				JSONObject json_obj = new JSONObject();
				json_obj.put("ID", rs.getInt(1));
				genImageTag(json_obj, rs.getString(2), 200, 200,
						rs.getInt(3), rs.getInt(4));
				json_array.put(json_obj);
			}
		}
		catch (SQLException | JSONException e) 
		{
			e.printStackTrace();
			throw new RuntimeException("Error processing image library.\n" + e.getMessage());
		}
		finally
		{
			DbUtils.close(stmt, rs);
		}
		return json_array;
	}
}
