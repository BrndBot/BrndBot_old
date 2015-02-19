/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.servlets;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.xhtmlrenderer.simple.ImageRenderer;

import com.brndbot.db.DbConnection;
import com.brndbot.db.Image;
import com.brndbot.db.ImageType;
import com.brndbot.system.Assert;
import com.brndbot.system.SessionUtils;
import com.brndbot.system.SystemProp;
import com.brndbot.system.Utils;

public class SaveHTMLAsImageServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	static private String FUSED_FOLDER = "htmlimages\\";

	public SaveHTMLAsImageServlet() {};

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("--------Entering new SaveHTMLAsImageServlet----------");

		HttpSession session = request.getSession();
		int user_id = SessionUtils.getIntSession(session, SessionUtils.USER_ID);
		if (user_id == 0)
		{
			System.out.println("USER NOT LOGGED IN, SENDING TO LOGIN PAGE");
			response.sendRedirect("index.jsp");
			return;
		}
		System.out.println("User ID: " + user_id);

		// Make sure the image type is passed
		int type = SessionUtils.getIntSession(session, SessionUtils.IMAGE_ID_KEY);
		if (type == 0)
		{
			System.out.println("No IMAGE TYPE passed (type=" + type + "). Programming error.");
			return;
		}
		System.out.println("Image ID Key: " + type);
		ImageType image_type = ImageType.getByItemNumber(type);
		if (image_type == null)
		{
			throw new RuntimeException("Unexpected image type: " + type);
		}

		String html = Utils.getStringParameter(request, "hiddenHtml");
//		String html = "<div id=\"graphicBlock-2\" style=\"\" class=\"blockSelectable\"><div style=\"width:94%;padding:0rem 1.25rem;background:#ffffff;overflow:hidden;\"><div style=\"width:100%;\"><div style=\"text-align:center;padding-top:.5rem;padding-bottom:.5rem\"><img src=\"http://localhost:8080/images/barre-1.jpg\" alt=\"\" height=\"355\" width=\"500\" /></div></div></div></div>";
		Assert.that(html.length() > 0, "Empty HTML, barf it up for now!");
		Document doc = Jsoup.parse(html);
		doc.select("div[style*=display:none]").remove();
		Element link = doc.select("body").first();
		html = link.html();
		System.out.println("WHAT DOES HTML LOOK LIKE: " + html);
//		System.out.println("LENGTH AFTER: " + html.length() + "\n");
//		System.out.println("TT: " + doc.toString() + "\n");
		long millis = System.currentTimeMillis();
//		System.out.println("Millis: " + millis);

		String tomcat_base = SystemProp.get(SystemProp.TOMCAT_BASE);
		String file_name = user_id + "_" + millis;
		String address = Utils.Slashies(tomcat_base + "\\" + FUSED_FOLDER + file_name + ".txt");
		
		PrintWriter fileWriter = new PrintWriter(new FileOutputStream(address), true);
		fileWriter.println(doc.toString());
		fileWriter.close();
		String assets = SystemProp.get(SystemProp.ASSETS);

		address = Utils.Slashies(assets + "\\" + FUSED_FOLDER + file_name + ".txt");
//		System.out.println("TXT: " + address);

		String rel_path = FUSED_FOLDER + file_name + ".png";
		String outputAddress = Utils.Slashies(tomcat_base  + "\\" + rel_path);
//		System.out.println("Output Address: " + outputAddress);
		// render
		BufferedImage buff = null;
		buff = ImageRenderer.renderToImage(address, outputAddress, 700);
//		System.out.println("Buff size: " + buff.getWidth() + " x " + buff.getHeight());

		// Write to image table
		DbConnection con = DbConnection.GetDb();
		Image image = new Image();
		image.setUserID(user_id);
		image.setImageType(image_type);
		image.setImageHeight(buff.getHeight());
		image.setImageWidth(buff.getWidth());
		image.setImageUrl(rel_path);
		int saved_img_id;
		try
		{
			saved_img_id = image.save(con);
			Assert.that(saved_img_id > 0, "Image ID returned as zero, check error logs!");
			session.setAttribute(SessionUtils.FUSED_IMAGE_ID_KEY, "" + saved_img_id);
		}
		catch (SQLException e1) 
		{
			e1.printStackTrace();
			throw new RuntimeException("Error in JSON prep.\n" + e1.getMessage());
		}
		System.out.println("Saved Image ID = " + saved_img_id);

		JSONObject json_obj = new JSONObject();
		try
		{
			json_obj.put(SessionUtils.IMAGE_ID_KEY, "" + saved_img_id);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(json_obj);
			out.flush();
			response.setStatus(HttpServletResponse.SC_OK);
		}
		catch (JSONException e) 
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return;
	}
}
