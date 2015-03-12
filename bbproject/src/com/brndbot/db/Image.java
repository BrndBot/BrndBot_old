/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.db;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
//import java.net.MalformedURLException;
//import java.net.URL;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

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
	
	final private static Random randomizer = new Random (new Date().getTime());
	
	private Integer imageId;
	private Integer userId;  // 0 if it's a Brndbot stock image
	private ImageType imageType;
	private String imageUrl;
	private String imageName;
	private Integer imageSize;
	private Integer imageHeight;
	private Integer imageWidth;
	private Blob image;
	private String mimeType;

	// Max file size for a image
	public static long MAX_IMAGE_SIZE = 500000L;
	
	public Image()
	{
		imageId = new Integer(0);
		userId = new Integer(0);
		imageType = null;
		imageUrl = "";
		imageName = "";
		imageSize = new Integer(0);
		imageHeight = new Integer(0);
		imageWidth = new Integer(0);
		image = null;
	}
	
	/** Consructor that loads up the image from a ResultSet that includes ImageID, UserID,
	 *  ImageType, ImageURL, ImageSize, ImageHeight, and ImageWidth. 
	 *  The field Image is not included here since it's so big it should
	 *  be loaded only when actually needed. */
//	public Image(ResultSet rs)
//	{
//		try
//		{
//			imageId = new Integer(rs.getInt("ImageID"));
//			userId = new Integer(rs.getInt("UserID"));
//			imageType = ImageType.getByItemNumber(rs.getInt("ImageType"));
//			imageUrl = rs.getString("ImageURL");
//			imageSize = new Integer(rs.getInt("ImageSize"));
//			imageHeight = new Integer(rs.getInt("ImageHeight"));
//			imageWidth = new Integer(rs.getInt("ImageWidth"));
//			//image = rs.getBlob("Image");
//		}
//		catch (SQLException e) 
//		{
//			e.printStackTrace();
//		}
//	}
	public Image(ResultSet rs)
	{
		try
		{
			ResultSetMetaData md = rs.getMetaData();
			for (int i = 1; i <= md.getColumnCount(); i++) {
				String name = md.getColumnName(i);
				switch (name) {
				case "ImageID":
					imageId = new Integer(rs.getInt(name));
					break;
				case "UserID":
					userId = new Integer(rs.getInt(name));
					break;
				case "ImageType":
					imageType = ImageType.getByItemNumber(rs.getInt(name));
					break;
				case "ImageURL":
					imageUrl = rs.getString(name);
					break;
				case "ImageSize":
					imageSize = rs.getInt(name);
					break;
				case "ImageHeight":
					imageHeight = new Integer(rs.getInt(name));
					break;
				case "ImageWidth":
					imageWidth = new Integer(rs.getInt(name));
					break;
					
				}
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	/** Copy constructor */
	public Image(Image m)
	{
		imageId = m.imageId;
		userId = m.userId;
		imageUrl = m.imageUrl;
		imageName = m.imageName;
		imageType = m.imageType;
		imageSize = new Integer(m.imageSize.intValue());
		imageHeight = new Integer(m.imageHeight.intValue());
		imageWidth = new Integer(m.imageWidth.intValue());
		image = m.image;
	}

	public String getTableName () {
		return tableName;
	}
	

//	static final String CS = ", ";

	public Integer getImageID() { return imageId; }
	public void setImageID(int arg) { imageId = arg; } 

	public Integer getUserID() { return userId; }
	public void setUserID(int arg) { userId = arg; } 

	public String getImageUrl() { return imageUrl; }
	public void setImageUrl(String arg) { imageUrl = arg; }

	public String getImageName() { return imageName; }
	public void setImageName(String arg) { imageName = arg; }

	public ImageType getImageType() { return imageType; }
	public void setImageType(ImageType arg) { imageType = arg; }

	public Integer getImageHeight() { return imageHeight; }
	public void setImageHeight(int arg) { imageHeight = arg; } 

	public Integer getImageWidth() { return imageWidth; }
	public void setImageWidth(int arg) { imageWidth = arg; } 

	public String getMimeType() { return mimeType; }
	public void setMimeType(String arg) { mimeType = arg; } 

	public Integer getImageSize() 
	{
		return imageSize; 
	}

	public void setImageSize(int arg) { imageSize = new Integer(arg); } 

	/** Return the binary image data. May be null if imageUrl is used instead. */
	public Blob getImage()
	{ 
		return image; 
	}

	public void setImage(byte[] blobData) throws SQLException { 
		Blob blob = new SerialBlob (blobData);
		image = blob; 
	}

	public void setImage(Blob blob) throws SQLException { 
		image = blob; 
	}
	
	/** Load the image blob in an already populated Image. */
	public void loadImage (DbConnection con) throws Exception {
		PreparedStatement pstmt = con.createPreparedStatement
				("SELECT Image FROM images WHERE UserID = ?");
		pstmt.setInt (1, imageId);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			image = rs.getBlob (1);
		}
	}

	/** Create a new row in the table "images" with the specified data.
	 *  if image (the big blob) is non-null, it's included, otherwise not.
	 */
	public int save(DbConnection con) throws SQLException
	{
		logger.debug("IMAGE SAVE SAVE SAVE**************");
		PreparedStatement pstmt;
		if (image == null) {
			pstmt = con.createPreparedStatement("INSERT INTO images (" +
				"ImageType, UserID, ImageURL, ImageSize, ImageHeight, ImageWidth, ImageName) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?);");
		}
		else {
			pstmt = con.createPreparedStatement("INSERT INTO images (" +
					"ImageType, UserID, ImageURL, ImageSize, ImageHeight, ImageWidth, ImageName, Image) " +
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
			pstmt.setBlob(8, getImage());
		}
		pstmt.setInt(1, getImageType().getValue());
		pstmt.setInt(2, getUserID().intValue());
		pstmt.setString(3, getImageUrl());
		pstmt.setInt(4, getImageSize());
		pstmt.setInt(5, getImageHeight());
		pstmt.setInt(6, getImageWidth());
		pstmt.setString(7, getImageName());
		// Timestamp is defined by default as current system time 
		pstmt.executeUpdate();
		pstmt.close();
		logger.debug ("returning from save");
		return DbUtils.getLastInsertID(con);
	}

	/** Returns the total number of images in the database
	 *  (why was this ever written?) */
//	static public int getImageCount(DbConnection con)
//	{
//		String sql = "SELECT COUNT(*) FROM images;";
//		Statement stmt = con.createStatement();
//		ResultSet rs = null;
//		int count = 0;
//		try
//		{
//			rs = stmt.executeQuery(sql);
//			if (rs.next())
//			{
//				count = rs.getInt(1);
//			}
//		} 
//		catch (SQLException e) 
//		{
//			e.printStackTrace();
//			throw new RuntimeException(e.getMessage());
//		}
//		finally
//		{
//			DbUtils.close(stmt, rs);
//		}
//		return count;
//	}

	/** Deletes an image that matches the specified ImagdID and UserID.
	 *  For some unclear reason, fetches the image first. The only reason I
	 *  can see for this is to prevent deletion of defective images.
	 *  But why would we not want to delete defective images? */
	static public void deleteImage(int image_id, int user_id, DbConnection con) 
			throws SQLException
	{
		Image Image = getImageByID(image_id, user_id, con);
		if (Image != null)
		{
			String sql = "DELETE FROM images WHERE ImageID = " + image_id + " AND UserID = " + user_id + ";";
			con.ExecuteDB(sql, false);
		}
	}

	static public String getBoundImage(int image_id, int user_id,
			int max_image_height, int max_image_width)
	{
		logger.debug("entering getBoundImage");
		DbConnection con = DbConnection.GetDb();
		String s = "<div style=\"padding:0.625rem;\">Invalid image</div>";
		try {
			Image image = getImageByID(image_id, user_id, con);
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
							image.getImageUrl(), h, w);
				}
			}
		}
		finally {
			con.close();
		}
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

	/** This is a peculiar thing. It does one thing if json_obj is non-null,
	 *  something quite different if it's null. For a non-null json_obj,
	 *  it adds the values height, width, and imgTag, where imgTag is the
	 *  file name, and it returns an empty string.
	 *  
	 *  If json_obj is null, it returns an img element with empty alt text,
	 *  width and height proportionally scaled to fit in the specified max values, 
	 *  and the file name.
	 */
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
					json_obj.put("url", " ");		// for template compatibility
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

	/** Creates an Image object from the ImageID and UserID. 
	 *  Returns null if there's no match.
	 *  What's the difference between this and makeThisImage?
	 */	
	static public Image getImageByID(int image_id, int user_id, DbConnection con)
	{
		PreparedStatement pstmt = con.createPreparedStatement
				("SELECT ImageID, UserID, ImageType, ImageURL, ImageSize, ImageHeight, " +
				"ImageWidth, Image FROM images WHERE ImageID = ? AND UserID = ?");
		ResultSet rs = null;
		Image image = null;
		try 
		{
			pstmt.setInt (1, image_id);
			pstmt.setInt (2, user_id);
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				image = new Image(rs);
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
			if (rs != null)
				DbUtils.close(pstmt, rs);
		}
		return image;
	}

	/** Returns all images of type USER_UPLOAD for the user. Without the 
	 *  image blobs loaded, of course.
	 */
	static public List<Image> getAllUserImages (int user_id, DbConnection con) {
		PreparedStatement pstmt = con.createPreparedStatement
				("SELECT ImageID, UserID, ImageType, ImageURL, ImageSize, ImageHeight, " +
				"ImageWidth, Image FROM images WHERE UserID = ? ORDER BY UserID");
		ResultSet rs = null;
		List<Image> retList = new ArrayList<>();
		try {
			pstmt.setInt (1, user_id);
			rs = pstmt.executeQuery();
			while (rs.next ()) {
				Image image = new Image (rs);
				retList.add (image);
			}
		}
		catch (SQLException e) 
		{
			logger.error("Exception in getAllUserImages(): " + 
						e.getClass().getName() + ", message = " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			if (rs != null)
				DbUtils.close(pstmt, rs);
		}
		return retList;
	}
	 
	/** Creates an Image object from the ImageID and UserID. 
	 *  Returns null if there's no match.
	 *  Doesn't load the image blob.
	 *  What's the difference between this and getImageByID?
	 *  Only that this doesn't work with database images.
	 */
//	static public Image makeThisImage(int image_id, int user_id, DbConnection con)
//	{
//		Statement stmt = con.createStatement();
//		String sql = "SELECT ImageID, UserID, ImageType, ImageURL, ImageSize, ImageHeight, ImageWidth FROM images WHERE ImageID = " + image_id + " AND UserID = " + user_id + ";";
//		ResultSet rs = con.QueryDB(sql, stmt);
//		Image image = null;
//		try 
//		{
//			if (rs.next())
//			{
//				image = new Image(rs);
//				if (image.getImageID().intValue() != image_id)
//				{
//					image = null;
//				}
//			}
//		}
//		catch (SQLException e)
//		{
//			logger.error ("SQLException getting image: {}", e.getMessage());
//			e.printStackTrace();
//		}
//		finally 
//		{
//			DbUtils.close(stmt, rs);
//		}
//		return image;
//	}


	static public boolean isAnImage(String extension)
	{
		boolean ret = (extension.equals("png") ||
				extension.equals("bmp") ||
				extension.equals("jpg") ||
				extension.equals("gif"));
		return ret;
	}

	/**
	 * Handle file upload. 
	 * 
	 * User upload files are saved to the database. Logos and alternate
	 * logos are saved to files. This is just a legacy which needs to be
	 * fixed; all files should be in the database, or at least not on
	 * public URLs.
	 *
	 * @return  an Image object which has _not_ been saved to the database.
	 * 
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
			return_image.setUserID(user_id);
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
						logger.info("Image file is too big");
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
					
					return_image.setImageType(image_type);
					
					// use File as a convenient way to extract the name from the path
					File theFile = new File(image_file.getFileName());
					return_image.setImageName(theFile.getName());
					return_image.setImageSize((int)image_file.getFileSize());
					// For the moment, only "stock" (user gallery) images are saved to
					// the database. Once this is working, we should migrate logos
					// there as well.
					if (image_type == ImageType.USER_UPLOAD) {
						return_image.setImage(bytes);
					} else {
						// Argh, argh, argh!!! This code was RECYCLING DELETED IMAGE IDs to set the
						// ID, in order to generate a "unique" file name!!!
						// Generate a moderately big random number, that's safer.
						int count = randomizer.nextInt() % 9999999;
						String url_file_name = Utils.Slashies(image_type.getFolder() + user_id + "-" + count + "-" +
								image_file.getFileName());
						return_image.setImageUrl(url_file_name);
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

	
	/** Returns the default image for a user. For the moment, this is the
	 *  oldest image; it may change.
	 *  
	 *  @return  An Image with just the imageId and userId set.
	 */
	static public Image getDefaultImage (int userId, DbConnection con) {
		PreparedStatement pstmt = con.createPreparedStatement
				("SELECT ImageID, CreateDateTime FROM images WHERE UserID = ? " + 
								"ORDER BY CreateDateTime ASC");
		ResultSet rs = null;
		try {
			pstmt.setInt (1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int imageId = rs.getInt(1);
				Image img = new Image();
				img.setImageID(imageId);
				img.setUserID(userId);
				return img;
			}
		} catch (Exception e) {
			logger.error ("Exception in getDefaultImage: {}   {}", 
					e.getClass().getName(),
					e.getMessage());
			return null;
		}
		return null;		// no images for user ID
	}

	
	static public JSONArray getImagesForDisplay(int user_id, ImageType image_type, DbConnection con)
	{
		Statement stmt = con.createStatement();
		ResultSet rs = null;
		String sql = "SELECT ImageID, ImageURL, ImageHeight, ImageWidth FROM images WHERE UserID = " + user_id + 
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
	
	/** The call of last resort. If we can't get a user image, display the default
	 *  image. */
	static public MimeTypedInputStream getGlobalDefaultStream (DbConnection con) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT Image, MimeType FROM images WHERE UserID = 0 AND ImageName = '__Default.jpg'");
			if (rs.next()) {
				Blob imageBlob = rs.getBlob (1);
				if (imageBlob == null) {
					logger.error ("getGlobalDefaultStream reading row with null blob");
					return null;
				}
				String mimeType = rs.getString(2);
				InputStream blobStream = imageBlob.getBinaryStream ();
				return new MimeTypedInputStream (blobStream, mimeType);
			}
		}
		catch (SQLException e) {
			logger.error ("Exception in getGlobalDefaultStream: {}", e.getClass().getName());
			return null;
		}
		finally {
			try {
				// Is it safe to close these before the InputStream has been read?
				if (stmt != null)
					stmt.close ();
				if (rs != null)
					rs.close();
			} catch (Exception e) {}
		}
		return null;	

	}
	
	/** Returns an OutputStream width the content of the user's default image. Returns
	 *  null if the image isn't available, or if the database entry
	 *  references the image by URL rather than having a blob.
	 *  
	 */
	static public MimeTypedInputStream getDefaultImageStream (int user_id, DbConnection con) {
		Image defImage = getDefaultImage(user_id, con);
		// This has only the image and user IDs
		return getImageStream (user_id, defImage.getImageID(), con);
	}
	
	/** Returns an OutputStream with the content of the user's default logo.
	 *  For legacy reasons, we get this from a relative path. 
	 */
	static public MimeTypedInputStream getLogoImageStream (int user_id, DbConnection con) {
		UserLogo uLogo = UserLogo.getLogoByUserID(user_id, con);
		Image logoImage = uLogo.getImage();
		String mimeType = logoImage.getMimeType();
		String tomcatBase = SystemProp.get(SystemProp.TOMCAT_BASE);
		String imagePath = Utils.Slashies(tomcatBase + "\\" + logoImage.getImageUrl());
		try {
			FileInputStream fileStream = new FileInputStream (imagePath);
			return new MimeTypedInputStream(fileStream, mimeType);
		} catch (IOException e) {
			logger.error ("Exception getting file: {} on {}", e.getClass().getName(), imagePath);
		}
		return null;		// TODO stub
	}
}
