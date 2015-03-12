/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2015
 *
*/
package com.brndbot.jsphelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.brndbot.block.Block;
import com.brndbot.block.ChannelEnum;
import com.brndbot.client.ClientInterface;
//import com.brndbot.client.ClientInterfaceFactory;
//import com.brndbot.client.Model;
import com.brndbot.client.Promotion;
import com.brndbot.db.DbConnection;
import com.brndbot.db.Palette;
import com.brndbot.promo.Client;

/** BenchHelper provides Java logic for the editor (edit.jsp).
 *  It's designed so it can (at least mostly) be accessed with
 *  the useBean tag. Therefore, it conforms closely to the Java Bean
 *  pattern. 
 */
public class BenchHelper extends Helper {

	final static Logger logger = LoggerFactory.getLogger(BenchHelper.class);
	
//	private DbConnection con;
	
	//private HttpSession session;
	private int userId;
	private int channel;
	private int databaseId;
	private ChannelEnum chEnum;
	private String organization;
	private String modelName;
	private String promoProto;		// promotion prototype name
	private ArrayList<Palette> paletteArray;
	private Promotion activePromotion;
	private Client client;

	public BenchHelper () {
	}
	
	
	/** Use c:out on this at the point where the buttons for viewing lists of
	 *  promo protos should be inserted.
	 */
	public String getRenderModelLinks () {
		logger.debug ("getRenderModelLinks");
		ModelLinkRenderer renderer = new ModelLinkRenderer(client);
		return renderer.getFragment ();
	}
	
	/** Call this to get a promotion prototype
	 *  and return its HTML. Uses previously set values for modelName
	 *  and promoProto.
	 *  
	 *  Currently it isn't cloning it to a promotion. It probably has
	 *  to, since we want to edit the promotion and not the prototype.
	 *  
	 */
	public String insertPromotion (Client client) {
		try {
			if (client == null) {
				logger.error ("Client is null!");
			}
			ClientInterface ci = client.getClientInterface();
			if (ci == null) {
				logger.error ("No client interface!");
			}
			Map<String, Promotion> prototypes = client.getPromotionPrototypes(modelName);
			if (prototypes == null) {
				logger.error ("No prototypes!");
			}
			Promotion prototype = prototypes.get (promoProto);
			if (prototype == null) {
				logger.error ("Unknown promotion prototype {} for model {}", 
						promoProto,
						modelName);
			}
			// Clone a promotion from the prototype.
			activePromotion = new Promotion (prototype);
			BlockRenderer renderer = new BlockRenderer (activePromotion);
			renderer.setPaletteArray(getPaletteArray());
			return renderer.render();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error ("Exception inserting promotion {}", promoProto);
			logger.error ("Exception type: {}, message: {}", 
					e.getClass().getName(), 
					e.getMessage());
			return "";
		}
	}

	/** Get the user palette */
	public ArrayList<Palette> getUserPalette () {
		DbConnection con = DbConnection.GetDb();
		try {
			return Palette.getUserPalette (userId, con);
		}
		finally {
			con.close();
		}
	}
	
	/** Get a List of colors from the user palette.
	 *  We have to escape the pound sign in front of the color so Kendo
	 *  won't try to evaluate it. We can't do that in the JSP because it
	 *  will escape the EL expressioN! */
	public List<String> getUserPaletteColors () {
		logger.debug ("getUserPaletteColors");
		List<Palette> palettes = getUserPalette();
		List<String> colors = new ArrayList<>();
		for (Palette pal : palettes) {
			logger.debug ("Adding color {}", pal.getColor());
			// Four backslashes needed here to turn into 2 in the JSP
			colors.add("\\\\" + pal.getColor());
			
			// Limit to 4 colors. TODO why does it get more than 4 colors??
			if (colors.size() == 4)
				break;
		}
		return colors;
	}
	
	
	/** We have to set the session with a scriptlet, not
	 *  setProperty */
	//public void setSession (HttpSession s) {
	//	session = s;
	//}

	public int getUserId () {
		return userId;
	}
	
	public void setUserId (int u) {
		userId = u;
		client = Client.getByUserId(u);
	}
	
//	public DbConnection getConnection () {
//		return con;
//	}
	
	public String getModelName () {
		return modelName;
	}
	
	public void setModelName (String nm) {
		modelName = nm;
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
		DbConnection con = null;
		try {
			if (paletteArray == null) {
				con = DbConnection.GetDb();
				paletteArray = Palette.getUserPalette(userId, con);
			}
		} finally {
			if (con != null)
				con.close();
		}
		return paletteArray;
	}
	
	public String getPaletteColor (int idx) {
		List<Palette> paletteArray = getPaletteArray ();
		if (paletteArray == null || paletteArray.size() <= idx) {
			return "#000000";		// no palette color for index, things are looking black
		}
		return paletteArray.get(idx).getColor();
	}
	

}
