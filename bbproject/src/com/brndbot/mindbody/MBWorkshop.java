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
import com.mindbodyonline.clients.svClass.ClassSchedule;
import com.mindbodyonline.clients.svClass.ClassX0020Service;
import com.mindbodyonline.clients.svClass.ClassX0020ServiceSoap;
import com.mindbodyonline.clients.svClass.GetEnrollmentsRequest;
import com.mindbodyonline.clients.svClass.GetEnrollmentsResult;
import com.mindbodyonline.clients.svClass.SourceCredentials;

public class MBWorkshop extends MBPoly 
{
	private ClassX0020ServiceSoap _classSoap;
	private GetEnrollmentsRequest _request;
	private SourceCredentials _sourceCredentials;

	static public MBField CLASS_ID = new MBField("Classes.ID");
	static public MBField CLASS_NAME = new MBField("Classes.ClassDescription.Name");
	static public MBField CLASS_DESCRIPTION = new MBField("Classes.ClassDescription.Description");
	static public MBField START_DATE = new MBField("Classes.StartDateTime");
	static public MBField INSTRUCTOR_LAST_NAME = new MBField("Classes.Appointments.Staff.LastName");
	static public MBField INSTRUCTOR_FIRST_NAME = new MBField("Classes.Appointments.Staff.FirstName");

	public MBWorkshop(int user_id)
	{
		super(user_id);
		_mb_package = BlockType.CLASS;
	}

	public void init()
	{
		_sourceCredentials = new SourceCredentials();
		_request = new GetEnrollmentsRequest();
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
		
//		_request.setXMLDetail(XMLDetailLevel.BARE);
		ArrayOfString fields = new ArrayOfString();
		fields.getString().add(MBWorkshop.CLASS_ID.getRequestField());
		fields.getString().add(MBWorkshop.CLASS_NAME.getRequestField());
		fields.getString().add(MBWorkshop.INSTRUCTOR_LAST_NAME.getRequestField());
		fields.getString().add(MBWorkshop.INSTRUCTOR_FIRST_NAME.getRequestField());
		fields.getString().add(MBWorkshop.START_DATE.getRequestField());
		fields.getString().add(MBWorkshop.CLASS_DESCRIPTION.getRequestField());
//		_request.setFields(fields);
		
		// For now, up to 30 days in the future
		GregorianCalendar cal = new GregorianCalendar();
		cal.add(Calendar.DATE, 120);
		XMLGregorianCalendar xmlCal;
		try
		{
			xmlCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		    _request.setEndDate(xmlCal);
		} 
		catch (DatatypeConfigurationException e1) 
		{
			e1.printStackTrace();
		}  

		GetEnrollmentsResult classes = _classSoap.getEnrollments(_request);
		List<ClassSchedule> result = classes.getEnrollments().getClassSchedule();

		JSONArray json_array = new JSONArray();
		for (int i = 0; i < result.size(); i++)
		{
			ClassSchedule next_class = result.get(i);
			JSONObject json_obj = new JSONObject();
			try 
			{
				json_obj.put("ID", next_class.getID().getValue());
				json_obj.put("Name", next_class.getClassDescription().getName());
				json_obj.put("FullName", next_class.getStaff().getFirstName() + " " + 
						next_class.getStaff().getLastName());
				json_obj.put("StartDate", 
					MBUtils.formatXMLDate(
						next_class.getStartDate().getValue()));
				String desc = next_class.getClassDescription().getDescription();
//				System.out.println("\n\nSD: " + MBPoly.cleanDescription(MBPoly.chopDesc(desc)));
				json_obj.put("ShortDescription", MBPoly.cleanDescription(MBPoly.chopDesc(desc)));
				String tmp = MBPoly.cleanDescription(MBPoly.chopDesc(desc, 450));
//				System.out.println("FD: " + tmp);
				json_obj.put("FullDescription", tmp);

				String itemlogo = next_class.getClassDescription().getImageURL();
				if (itemlogo == null || itemlogo.length() == 0) 
					itemlogo = MBWorkshop.getDefaultImgURL();
				json_obj.put("ImgURL", UserLogo.getBoundImageByWidth(itemlogo, max_width));

				json_obj.put("ItemLogo", MBWorkshop.getItemLogo());
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
		return "images/Workshop_DarkBlue.png";
	}


	public Block retrieveAsBlock(ChannelEnum channelType, int user_id, int database_id, int maxDescriptionSize, int maxImageWidth)
	{
		init();
		_request.setPageSize(new Integer(1));
		_request.setCurrentPageIndex(new Integer(1));
		
//		_request.setXMLDetail(XMLDetailLevel.BARE);
		ArrayOfString fields = new ArrayOfString();
		fields.getString().add(MBWorkshop.CLASS_ID.getRequestField());
		fields.getString().add(MBWorkshop.CLASS_NAME.getRequestField());
		fields.getString().add(MBWorkshop.INSTRUCTOR_LAST_NAME.getRequestField());
		fields.getString().add(MBWorkshop.INSTRUCTOR_FIRST_NAME.getRequestField());
		fields.getString().add(MBWorkshop.START_DATE.getRequestField());
		fields.getString().add(MBWorkshop.CLASS_DESCRIPTION.getRequestField());
//		_request.setFields(fields);
		
		ArrayOfInt idsWeWant = new ArrayOfInt();
		idsWeWant.getInt().add(new Integer(database_id));
		_request.setClassScheduleIDs(idsWeWant);

		// For now, up to 30 days in the future
		GregorianCalendar cal = new GregorianCalendar();
		cal.add(Calendar.DATE, 120);
		XMLGregorianCalendar xmlCal;
		try
		{
			xmlCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		    _request.setEndDate(xmlCal);
		} 
		catch (DatatypeConfigurationException e1) 
		{
			e1.printStackTrace();
		}

		GetEnrollmentsResult classes = _classSoap.getEnrollments(_request);
		List<ClassSchedule> result = classes.getEnrollments().getClassSchedule();

		ClassSchedule next_class = null;
		for (int i = 0; i < result.size(); i++)
		{
			next_class = result.get(i);
		}

		String desc = null;
		if (maxDescriptionSize > 0)
		{
			desc = MBPoly.cleanDescription(
				MBPoly.chopDesc(next_class.getClassDescription().getDescription(), maxDescriptionSize));
		}
		else
		{
			desc = next_class.getClassDescription().getDescription();
		}

		String itemlogo = next_class.getClassDescription().getImageURL();
		if (itemlogo == null || itemlogo.length() == 0) 
			itemlogo = MBWorkshop.getDefaultImgURL();
		desc = MBPoly.cleanDescription(desc);
		Block block = new Block
		(
			ChannelEnum.create(ChannelEnum.EMAIL.getValue().intValue()),
			BlockType.create(BlockType.WORKSHOP.getValue().intValue()),
			" this Workshop",
			next_class.getID().getValue().intValue(),
			next_class.getClassDescription().getName(),
			next_class.getStaff().getFirstName() + " " + 
					next_class.getStaff().getLastName(),
			MBUtils.formatXMLDate(
					next_class.getStartDate().getValue()),
			"schedref",
			desc,
			MBPoly.chopDesc(desc),
			UserLogo.getBoundImageByWidth(itemlogo, maxImageWidth));
		return block;
	}

}
