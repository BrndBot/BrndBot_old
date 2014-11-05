package com.brndbot.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.brndbot.db.DbConnection;
import com.brndbot.system.SessionUtils;
import com.brndbot.system.Utils;
import com.brndbot.user.User;

public class SavePaletteServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public SavePaletteServlet ()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("--------Entering SavePaletteServlet----------");

		// Get rid of previous values
		DbConnection con = DbConnection.GetDb();

		HttpSession session = request.getSession();
		int user_id = Utils.getIntSession(session, SessionUtils.USER_ID);
		if (user_id == 0)
		{
			System.out.println("USER NOT LOGGED IN, SENDING TO LOGIN PAGE");
			response.sendRedirect("index.jsp");
			return;
		}

		// For now, just delete any previous palette information with id #1
		try 
		{
			User.DeletePalettes(user_id, con);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new RuntimeException("Exception: " + e);
		}

		// Gather data
		String yourPalette = Utils.getStringParameter(request, "hiddenYourPalette");
		String suggestedPalette = Utils.getStringParameter(request, "hiddenSuggestedPalette");
		String[] suggested = suggestedPalette.split(",");
		String[] yours = yourPalette.split(",");
		try
		{
			writePalette(user_id, true, suggested, con);
			writePalette(user_id, false, yours, con);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		finally
		{
			con.close();
		}
//		System.out.println("YP: " + yourPalette);
//		System.out.println("SP: " + suggestedPalette);
		return;
	}

	private void writePalette(int user_id, boolean isSuggested, String[] palette, DbConnection con) throws SQLException
	{
		con.ExecuteDB("delete from palettes where UserId = " + user_id + " and IsSuggested = " +
				(isSuggested ? "1" : "0"), true);
		for (int i = 0; i < palette.length; i++)
		{
			con.ExecuteDB("insert into palettes (UserID, Sequence, IsSuggested, Color) values ("
					+ user_id + ", " + i + ", " + (isSuggested ? "1" : "0") + ", \"" + palette[i] + "\");", false);
		}
	}
}
