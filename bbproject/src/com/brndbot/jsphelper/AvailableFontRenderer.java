package com.brndbot.jsphelper;

import java.util.List;

import com.brndbot.db.DbConnection;
import com.brndbot.db.Font;
import com.brndbot.db.User;
import com.brndbot.system.BrndbotException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AvailableFontRenderer extends Renderer {

	final static Logger logger = LoggerFactory.getLogger(AvailableFontRenderer.class);
	
	private int userId;
	
	public AvailableFontRenderer(int userId) {
		this.userId = userId;
	}
	
	/* This will return a string of option elements with the value attribute being
	 * the font name, and the element content a prettified version of the name */
	public String getFragment () throws BrndbotException {
		logger.debug ("getFragment");
		User u = new User (userId);
		DbConnection con = null;
		try {
			con = DbConnection.getDb();
			logger.debug ("Calling loadClientInfo");
			u.loadClientInfo(con);
			int persId = u.getPersonalityID();
			logger.debug ("Got personality ID {}", persId);
			List<String> fonts = Font.getFontsForPersonality (persId, con);
			StringBuilder sb = new StringBuilder();
			for (String font : fonts) {
				logger.debug ("Adding font {}", font);
				sb.append ("<option style='font-family:");
				sb.append (font);		// TODO do I need to change spaces to +s?
				sb.append ("' value='");
				sb.append (font);
				sb.append ("'>");
				sb.append (prettify(font));
				sb.append ("</option>\n");
			}
			logger.debug ("Returning {}", sb.toString());
			return sb.toString();
		}
		finally {
			if (con != null)
				con.close();
		}
	}
	
	private String prettify (String f) {
		return f;		// TODO make this do something interesting
	}

}
