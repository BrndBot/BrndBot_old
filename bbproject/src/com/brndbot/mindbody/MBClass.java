/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.mindbody;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.brndbot.block.Block;
import com.brndbot.block.BlockType;
import com.brndbot.block.ChannelEnum;
import com.brndbot.system.SystemProp;
import com.brndbot.system.Utils;
import com.brndbot.user.UserLogo;
import com.mindbodyonline.clients.svClass.ArrayOfInt;
import com.mindbodyonline.clients.svClass.ArrayOfString;
import com.mindbodyonline.clients.svClass.Class;
import com.mindbodyonline.clients.svClass.ClassX0020Service;
import com.mindbodyonline.clients.svClass.ClassX0020ServiceSoap;
import com.mindbodyonline.clients.svClass.GetClassesRequest;
import com.mindbodyonline.clients.svClass.GetClassesResult;
import com.mindbodyonline.clients.svClass.SourceCredentials;
import com.mindbodyonline.clients.svClass.XMLDetailLevel;

public class MBClass extends MBPoly 
{
	private ClassX0020ServiceSoap _classSoap;
	private GetClassesRequest _request;
	private SourceCredentials _sourceCredentials;

	static public MBField CLASS_ID = new MBField("Classes.ID");
	static public MBField CLASS_NAME = new MBField("Classes.ClassDescription.Name");
	static public MBField CLASS_DESCRIPTION = new MBField("Classes.ClassDescription.Description");
	static public MBField START_DATE = new MBField("Classes.StartDateTime");
	static public MBField INSTRUCTOR_LAST_NAME = new MBField("Classes.Staff.LastName");
	static public MBField INSTRUCTOR_FIRST_NAME = new MBField("Classes.Staff.FirstName");

	public MBClass(int user_id)
	{
		super(user_id);
		_mb_package = BlockType.CLASS;
	}

	public void init()
	{
		_sourceCredentials = new SourceCredentials();
		_request = new GetClassesRequest();
		ClassX0020Service classService = new ClassX0020Service();
		_classSoap = classService.getClassX0020ServiceSoap();
		setCredentials(getUserID());
	}

	protected void setCredentialDetails(
			String mb_name, String mb_password,	int mb_id) 
	{
		ArrayOfInt arrayOfInt = new ArrayOfInt();
		arrayOfInt.getInt().add(new Integer(mb_id));
		_sourceCredentials.setSourceName(mb_name);
		_sourceCredentials.setPassword(mb_password);
//		System.out.println("id: " + mb_id + ", name: " + mb_name + ", pw: " + mb_password);

		_sourceCredentials.setSiteIDs(arrayOfInt);
		_request.setSourceCredentials(_sourceCredentials);
	}

	// Make the actual call to MindBody, return JSON-ready String
	public String getDataAsJSON(int count, int page, int max_width)
	{
		if (count > 0)
		{
			_request.setPageSize(new Integer(count));
			_request.setCurrentPageIndex(new Integer(page > 0 ? page : 1));
		}

		_request.setXMLDetail(XMLDetailLevel.BARE);
		ArrayOfString fields = new ArrayOfString();
		fields.getString().add(MBClass.CLASS_ID.getRequestField());
		fields.getString().add(MBClass.CLASS_NAME.getRequestField());
		fields.getString().add(MBClass.INSTRUCTOR_LAST_NAME.getRequestField());
		fields.getString().add(MBClass.INSTRUCTOR_FIRST_NAME.getRequestField());
		fields.getString().add(MBClass.START_DATE.getRequestField());
		fields.getString().add(MBClass.CLASS_DESCRIPTION.getRequestField());
		_request.setFields(fields);
		
		// For now, up to 30 days in the future
		GregorianCalendar cal = new GregorianCalendar();
		cal.add(Calendar.DATE, 30);
		XMLGregorianCalendar xmlCal;
		try 
		{
			xmlCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		    _request.setEndDateTime(xmlCal);
		} 
		catch (DatatypeConfigurationException e1) 
		{
			e1.printStackTrace();
		}  

		// Skip cancelled
		_request.setHideCanceledClasses(new Boolean(true));

		GetClassesResult classes = _classSoap.getClasses(_request);
		List<Class> result = classes.getClasses().getClazz();
		JSONArray json_array = new JSONArray();
		for (int i = 0; i < result.size(); i++)
		{
			Class next_class = result.get(i);
			JSONObject json_obj = new JSONObject();
			try 
			{
//				next_class.getClassDescription().getImageURL();
				json_obj.put("ID", next_class.getID().getValue().intValue());
//				System.out.println(next_class.getID().getValue().intValue() + " : " +
//						next_class.getClassDescription().getName());
				json_obj.put("Name", next_class.getClassDescription().getName());
				json_obj.put("LastName", next_class.getStaff().getLastName() + ", " + 
						next_class.getStaff().getFirstName());
				json_obj.put("FullName", next_class.getStaff().getFirstName() + " " + 
						next_class.getStaff().getLastName());
				json_obj.put("StartDate", 
					MBUtils.formatXMLDate(
						next_class.getStartDateTime().getValue()));
				String desc = next_class.getClassDescription().getDescription();
//				System.out.println("\nSHORT DESC:\n" +
//						MBPoly.chopDesc(MBPoly.cleanDescription(desc)));
				json_obj.put("ShortDescription", MBPoly.chopDesc(MBPoly.cleanDescription(desc)));
				json_obj.put("FullDescription", MBPoly.chopDesc(desc, 450));

				String itemlogo = next_class.getClassDescription().getImageURL();
				if (itemlogo == null || itemlogo.length() == 0) 
					itemlogo = MBClass.getDefaultImgURL();
				json_obj.put("ImgURL", UserLogo.getBoundImageByWidth(itemlogo, max_width));

				json_obj.put("ItemLogo", MBClass.getItemLogo());
				json_array.put(json_obj);
			} 
			catch (JSONException e) 
			{
				e.printStackTrace();
			}
		}

		return json_array.toString();
	}
	
	static public String getDefaultImgURL()
	{
		return Utils.Slashies(SystemProp.get(SystemProp.ASSETS) + "//" + "images/barre-1.jpg");
	}

	static public String getItemLogo()
	{
		return "images/Class_Green.png";
	}
	public Block retrieveAsBlock(
		ChannelEnum channelType,
		int user_id, 
		int database_id, 
		int maxDescriptionSize, 
		int maxImageWidth)
	{
		System.out.println(channelType.getValue());
		System.out.println(user_id);
		System.out.println(database_id);
	
		init();
		
		_request.setPageSize(new Integer(1));
		_request.setCurrentPageIndex(new Integer(1));

		_request.setXMLDetail(XMLDetailLevel.BARE);
		ArrayOfString fields = new ArrayOfString();
		fields.getString().add(MBClass.CLASS_ID.getRequestField());
		fields.getString().add(MBClass.CLASS_NAME.getRequestField());
		fields.getString().add(MBClass.INSTRUCTOR_LAST_NAME.getRequestField());
		fields.getString().add(MBClass.INSTRUCTOR_FIRST_NAME.getRequestField());
		fields.getString().add(MBClass.START_DATE.getRequestField());
		fields.getString().add(MBClass.CLASS_DESCRIPTION.getRequestField());
		_request.setFields(fields);

		// For now, up to 30 days in the future
		GregorianCalendar cal = new GregorianCalendar();
		cal.add(Calendar.DATE, 30);
		XMLGregorianCalendar xmlCal;
		try 
		{
			xmlCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		    _request.setEndDateTime(xmlCal);
		} 
		catch (DatatypeConfigurationException e1) 
		{
			e1.printStackTrace();
		}  

		// Skip cancelled
		_request.setHideCanceledClasses(new Boolean(true));

		// Set the MB class ID
		ArrayOfInt idsWeWant = new ArrayOfInt();
		idsWeWant.getInt().add(new Integer(database_id));
		_request.setClassIDs(idsWeWant);

		GetClassesResult classes = _classSoap.getClasses(_request);
		List<Class> result = classes.getClasses().getClazz();
//		Assert.that(result.size() == 1, "Result is greater than expected! " + result.size());
		Class next_class = null;

		for (int i = 0; i < result.size(); i++)
		{
			next_class = result.get(i);
			System.out.println(next_class.getID().getValue().intValue() + " : " +
					next_class.getClassDescription().getName());

			// for some reason, searching using the IDs does not work
			if (next_class.getID().getValue().intValue() == database_id)
			{
				i = result.size();
			}
		}
		String desc = null;
		if (maxDescriptionSize > 0)
		{
//			System.out.println("\n1 next: " + next_class);
//			System.out.println("2 cl desc: " + next_class.getClassDescription());
//			System.out.println("3 cl desc: " + next_class.getClassDescription().getDescription());
			desc = MBPoly.chopDesc(
				MBPoly.cleanDescription(next_class.getClassDescription().getDescription()), maxDescriptionSize);
		}
		else
		{
			desc = MBPoly.cleanDescription(next_class.getClassDescription().getDescription());
//			desc = next_class.getClassDescription().getDescription();
		}

		
		String itemlogo = next_class.getClassDescription().getImageURL();
		if (itemlogo == null || itemlogo.length() == 0) 
			itemlogo = MBClass.getDefaultImgURL();

		desc = MBPoly.cleanDescription(desc);
		Block block = new Block
		(
			ChannelEnum.create(ChannelEnum.EMAIL.getValue().intValue()),
			BlockType.create(BlockType.CLASS.getValue().intValue()),
			" this Class",
			next_class.getID().getValue().intValue(),
			next_class.getClassDescription().getName(),
			next_class.getStaff().getFirstName() + " " + 
					next_class.getStaff().getLastName(),
			MBUtils.formatXMLDate(
					next_class.getStartDateTime().getValue()),
			"schedref",
			desc,
			MBPoly.chopDesc(desc),
			UserLogo.getBoundImageByWidth(itemlogo, maxImageWidth));
		return block;
	}
}
