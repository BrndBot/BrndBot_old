/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 *
*/
package com.brndbot.jsphelper;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.block.Block;
import com.brndbot.block.ChannelEnum;
import com.brndbot.db.DbConnection;
import com.brndbot.db.Palette;
import com.brndbot.db.User;

/** BenchHelper provides Java logic for the editor (bench.jsp).
 *  It's designed so it can (at least mostly) be accessed with
 *  the useBean tag. Therefore, it conforms closely to the Java Bean
 *  pattern. 
 */
public class BenchHelper {

	final static Logger logger = LoggerFactory.getLogger(BenchHelper.class);
	
	private DbConnection con;
	
	private HttpSession session;
	private int userId;
	private int channel;
	private int databaseId;
	private ChannelEnum chEnum;
	private String organization;
	private String promoProto;		// promotion prototype name
	private ArrayList<Palette> paletteArray;
	
	public BenchHelper () {
		con = DbConnection.GetDb();	
	}
	
	/** Must call dismiss to prevent leakage. */
	public void dismiss () {
		if (con != null) {
			con.close();
		}
	}
	
	/** We have to set the session with a scriptlet, not
	 *  setProperty */
	public void setSession (HttpSession s) {
		session = s;
	}

	public int getUserId () {
		return userId;
	}
	
	public void setUserId (int u) {
		userId = u;
	}
	
	public DbConnection getConnection () {
		return con;
	}
	
	public String getOrganization () {
		return organization;
	}
	
	public void setOrganization (String org) {
		organization = org;
	}
	
	/** What exactly is the database ID? You can't set up a DBConnection
	 *  from one number, at least not without a lot of senseless encoding. */
	public int getDatabaseId () {
		return databaseId;
	}
	
	public void setDatabaseId (int d) {
		databaseId = d;
	}
	
	public String getPromoProto () {
		return promoProto;
	}
	
	public void setPromoProto (String name) {
		promoProto = name;
	}
	
	public int getChannel () {
		return channel;
	}
	
	public void setChannel (int c) {
		channel = c;
		chEnum = ChannelEnum.create(c);
	}
	
	/** Returns the maximum logo width, which is determined by the channel. */
	// These are the bounding max width and height for sizing images.  This whole mechanism needs better requirements
	//  and a more substantial commitment in terms of a class or set of classes that house all the image resizing
	//  logic.  Requirements are needed before that class can be implemented.
	public int getHeaderMaxLogoWidth () {
		return chEnum.getDefaultImgWidth();
	}
	
	/** Returns the maximum logo height, which is the same as the width. */
	public int getHeaderMaxLogoHeight () {
		return chEnum.getDefaultImgWidth();
	}
	
	public List<Palette> getPaletteArray () {
		if (paletteArray == null) {
			paletteArray = User.getUserPalette(userId, con);
		}
		return paletteArray;
	}
	
	/** Create a starting Block for the promotion. */
//	public Block getStartingBlock () {
//		// TODO largely fake
//		return new Block (chEnum,
//				"DummyBlock",
//				databaseId,
//				"dummyName",
//				"dummyFullName",
//				"dummyStartingDate",
//				"dummyRef",
//				"dummyDesc",
//				"dummyShortDesc"
//				);
//	}
}
