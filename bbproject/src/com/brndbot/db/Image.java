/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.db;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javazoom.upload.UploadFile;

import com.brndbot.system.SystemProp;
import com.brndbot.system.Utils;
import com.brndbot.util.AppEnvironment;

/** TODO Need to add MIME type to Image table.
 *  ImageType is the usage type, not the MIME type.
 */
public class Image implements TableModel 
{
	
	final static Logger logger = LoggerFactory.getLogger(Image.class);
	final static String tableName = "images";
	
	private Integer _image_id;
	private Integer _user_id;  // 0 if it's a Brndbot stock image
	private ImageType _image_type;
	private String _image_name;
	private Integer _image_size;
	private Integer _image_height;
	private Integer _image_width;
	private Blob _image;
	private String mime_type;

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
	
	public String getTableName () {
		return tableName;
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


	public Blob getImage()
	{ 
		return _image; 
	}

	public void setImage(byte[] blobData) throws SQLException { 
		Blob blob = new SerialBlob (blobData);
		_image = blob; 
	}

	public void setImage(Blob blob) throws SQLException { 
		_image = blob; 
	}

	public Image(ResultSet rs)
	{
		try
		{
			_image_id = new Integer(rs.getInt("ImageID"));
			_user_id = new Integer(rs.getInt("UserID"));
			_image_type = ImageType.getByItemNumber(rs.getInt("ImageType"));
			_image_name = rs.getString("ImageName");
			_image_size = new Integer(rs.getInt("ImageSize"));
			_image_height = new Integer(rs.getInt("ImageHeight"));
			_image_width = new Integer(rs.getInt("ImageWidth"));
			_image = rs.getBlob("Image");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
		}
	}

	/** save assumes that the image is always defined by "name" (really
	 *  path) rather than blob. This whole scheme is wonky.
	 */
	public int save(DbConnection con) throws SQLException
	{
		logger.debug("IMAGE SAVE SAVE SAVE**************");
		PreparedStatement pstmt;
		if (_image == null) {
			pstmt = con.createPreparedStatement("INSERT INTO images (" +
				"ImageType, UserID, ImageName, ImageSize, ImageHeight, ImageWidth) " +
				"VALUES (?, ?, ?, ?, ?, ?);");
		}
		else {
			pstmt = con.createPreparedStatement("INSERT INTO images (" +
					"ImageType, UserID, ImageName, ImageSize, ImageHeight, ImageWidth, Image) " +
					"VALUES (?, ?, ?, ?, ?, ?);");
			pstmt.setBlob(7, getImage());
		}
		pstmt.setInt(1, getImageType().getValue().intValue());
		pstmt.setInt(2, getUserID().intValue());
		pstmt.setString(3, getImageName());
		pstmt.setInt(4, getImageSize().intValue());
		pstmt.setInt(5, getImageHeight().intValue());
		pstmt.setInt(6, getImageWidth().intValue());
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
			logger.error ("SQLException getting image: {}", e.getMessage());
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
		logger.debug("entering getBoundImage");
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
				logger.error("Image not found, image ID = {}", image_id);
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

	/*  As it stands, this always uploads to a file that's directly
	 *  accessible by a URL. We want to replace this with files in the
	 *  database or else files outside the publicly accessible space.
	 */
	static public Image uploadFile(
			int user_id, 
			ImageType image_type, 
			@SuppressWarnings("rawtypes") Hashtable files, 
			DbConnection con) throws IOException, SQLException 
	{
		Image return_image = null;
		logger.debug ("uploadFile");

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

					// Get the image info from the image in memory, instead of writing it
					// to a file and then reading again.
					ByteArrayInputStream instrm = new ByteArrayInputStream (bytes);
					BufferedImage bimg = ImageIO.read (instrm);
					return_image.setImageWidth(bimg.getWidth());
					return_image.setImageHeight(bimg.getHeight());

					// Store in the database
					int count = Image.getImageCount(con);
					return_image.setUserID(user_id);
					return_image.setImageType(image_type);
					String url_file_name = Utils.Slashies(image_type.getFolder() + user_id + "-" + count + "-" +
							image_file.getFileName());
					logger.debug("Relative URL file name: " + url_file_name);
					return_image.setImageSize((int)image_file.getFileSize());
					// For the moment, only "stock" (user gallery) images are saved to
					// the database. Once this is working, we should migrate logos
					// there as well.
					if (image_type == ImageType.USER_UPLOAD) {
						saveToDatabase (bytes, image_file, url_file_name, return_image);
					} else {
						return_image.setImageName(url_file_name);
						saveToFile (bytes, image_file, url_file_name);
					}
				}
			}
		}
		return return_image;
	}

	/* Complete the file save to the file system */
	private static void saveToFile (byte[] bytes, UploadFile uploadFile, String fileName) 
			throws IOException {
		// Now save image to the images/uploads/.. folder
		uploadFile.setFileName(AppEnvironment.baseInAppDirectory(fileName));	// huh??
		logger.debug ("Saving image to {}", uploadFile.getFileName());
		FileOutputStream fos = new FileOutputStream(uploadFile.getFileName());
		fos.write(bytes);
		fos.close();
	}
	
	/* Save the file to the database */
	private static void saveToDatabase (byte[] bytes, UploadFile uploadFile, String imageName, Image image) 
				throws SQLException {
		// TODO stub
		logger.debug ("Saving image data to database");
		image.setImage(bytes);
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
		catch (Exception e) 
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
	
	/** Returns an OutputStream width the content of the image. Returns
	 *  null if the image isn't available, or if the database entry
	 *  references the image by "name" rather than having a blob.
	 *  
	 */
	static public MimeTypedInputStream getImageStream (int user_id, int image_id, DbConnection con) {
		PreparedStatement pstmt = con.createPreparedStatement
				("SELECT Image, MimeType FROM images WHERE UserID = ? AND ImageID = ?");
		ResultSet rs = null;
		try {
			pstmt.setInt (1, user_id);
			pstmt.setInt (2, image_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Blob imageBlob = rs.getBlob (1);
				if (imageBlob == null) {
					logger.warn ("getImageStream reading row with null blob");
					return null;
				}
				String mimeType = rs.getString(2);
				InputStream blobStream = imageBlob.getBinaryStream ();
				return new MimeTypedInputStream (blobStream, mimeType);
			}
		}
		catch (SQLException e) {
			logger.error ("Exception in getImageStream: {}", e.getClass().getName());
			return null;
		}
		finally {
			try {
				// Is it safe to close these before the InputStream has been read?
				pstmt.close ();
				if (rs != null)
					rs.close();
			} catch (Exception e) {}
		}
		return null;	
	}
}
