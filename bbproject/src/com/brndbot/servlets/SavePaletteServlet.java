/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.db.DbConnection;
import com.brndbot.db.Palette;
import com.brndbot.system.SessionUtils;
import com.brndbot.system.Utils;

public class SavePaletteServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	final static Logger logger = LoggerFactory.getLogger(SavePaletteServlet.class);

	public SavePaletteServlet ()
    {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		logger.debug("--------Entering SavePaletteServlet----------");
		DbConnection con = null;
		try {
			
			// Get rid of previous values
			con = DbConnection.getDb();
	
			HttpSession session = request.getSession();
			int user_id = SessionUtils.getIntSession(session, SessionUtils.USER_ID);
	
			try 
			{
				Palette.deletePalettes(user_id, con);
			} 
			catch (SQLException e) 
			{
				logger.error("Error deleting palette: {}  {}", 
						e.getClass().getName(),
						e.getMessage());
				e.printStackTrace();
				throw new RuntimeException("Exception: " + e);
			}
	
			// Gather data
			String yourPalette = Utils.getStringParameter(request, "hiddenYourPalette");
			//String suggestedPalette = Utils.getStringParameter(request, "hiddenSuggestedPalette");
			//String[] suggested = suggestedPalette.split(",");
			String[] yours = yourPalette.split(",");
			logger.debug("Writing palettes");
			writePalette(user_id, false, yours, con);
		}
		catch (Exception e) 
		{
			logger.error("Error writing palette: {}  {}", 
					e.getClass().getName(),
					e.getMessage());
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		finally
		{
			if (con != null)
				con.close();
		}
		return;
	}

	private void writePalette(int user_id, boolean isSuggested, String[] paletteColors, DbConnection con) throws SQLException
	{
		con.startTransaction ();
		try {
			Palette.deletePalettes(user_id, isSuggested, con);
			// Write no more than 4 colors
			int nColors = paletteColors.length;
			if (nColors > 4)
				nColors = 4;
			for (int i = 0; i < nColors; i++)
			{
				Palette palette = new Palette();
				palette.setUserId(user_id);
				palette.setIsSuggested(isSuggested);
				palette.setColor(paletteColors[i]);
				palette.setSequence(i);
				palette.save(con);
			}
		}
		catch (Exception e) {
			con.rollback();
			throw e;
		}
		con.commit();
	}
}
