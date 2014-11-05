package com.brndbot.mindbody;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.brndbot.block.Block;
import com.brndbot.block.BlockType;
import com.brndbot.block.ChannelEnum;
import com.brndbot.system.SystemProp;
import com.brndbot.system.Utils;
import com.brndbot.user.UserLogo;
import com.mindbodyonline.clients.svStaff.ArrayOfInt;
import com.mindbodyonline.clients.svStaff.ArrayOfLong;
import com.mindbodyonline.clients.svStaff.ArrayOfString;
import com.mindbodyonline.clients.svStaff.GetStaffRequest;
import com.mindbodyonline.clients.svStaff.GetStaffResult;
import com.mindbodyonline.clients.svStaff.SourceCredentials;
import com.mindbodyonline.clients.svStaff.Staff;
import com.mindbodyonline.clients.svStaff.StaffX0020Service;
import com.mindbodyonline.clients.svStaff.StaffX0020ServiceSoap;

public class MBStaff extends MBPoly 
{
	private StaffX0020ServiceSoap _staffSoap;
	private GetStaffRequest _request;
	private SourceCredentials _sourceCredentials;

	static public MBField STAFF_ID = new MBField("Staff.ID");
	static public MBField INSTRUCTOR_LAST_NAME = new MBField("StaffMembers.Staff.LastName");
	static public MBField INSTRUCTOR_FIRST_NAME = new MBField("StaffMembers.Staff.FirstName");
	static public MBField INSTRUCTOR_BIO = new MBField("StaffMembers.Staff.Bio");

	public MBStaff(int user_id)
	{
		super(user_id);
		_mb_package = BlockType.STAFF;
	}

	public void init()
	{
		_sourceCredentials = new SourceCredentials();
		_request = new GetStaffRequest();
		StaffX0020Service staffService = new StaffX0020Service();
		_staffSoap = staffService.getStaffX0020ServiceSoap();
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
		
//		_request.setXMLDetail(XMLDetailLevel.BARE);
		ArrayOfString fields = new ArrayOfString();
		fields.getString().add(MBStaff.STAFF_ID.getRequestField());
		fields.getString().add(MBStaff.INSTRUCTOR_LAST_NAME.getRequestField());
		fields.getString().add(MBStaff.INSTRUCTOR_FIRST_NAME.getRequestField());
		fields.getString().add(MBStaff.INSTRUCTOR_BIO.getRequestField());
//		_request.setFields(fields);

		GetStaffResult staff = _staffSoap.getStaff(_request);
		List<Staff> result = staff.getStaffMembers().getStaff();

		JSONArray json_array = new JSONArray();
//		System.out.println("staff result size: " + result.size());
		for (int i = 0; i < result.size(); i++)
		{
			Staff next_staff = result.get(i);
			if (next_staff.getEmail() != null && next_staff.getEmail().length() > 3)
			{
				JSONObject json_obj = new JSONObject();
				try 
				{
					json_obj.put("ID", next_staff.getID().getValue());
					String lastName = next_staff.getLastName();
					String desc = next_staff.getBio();
					json_obj.put("Name", next_staff.getFirstName() + " " + lastName);
					json_obj.put("FullName", next_staff.getFirstName() + " " + lastName);
					json_obj.put("ShortDescription", MBPoly.chopDesc(MBPoly.cleanDescription(desc)));
					json_obj.put("FullDescription", MBPoly.chopDesc(MBPoly.cleanDescription(desc), 450));
					json_obj.put("ItemLogo", MBStaff.getItemLogo());
					json_obj.put("ImgURL", UserLogo.getBoundImageByWidth(MBStaff.getDefaultImgURL(), max_width));

					json_array.put(json_obj);
				}
				catch (JSONException e)
				{
					e.printStackTrace();
				}
			}
		}
		return json_array.toString();
	}

	static public String getDefaultImgURL()
	{
		return Utils.Slashies(SystemProp.get(SystemProp.ASSETS) + "//" + "images/headshot.jpg");
	}

	static public String getItemLogo()
	{
		return "images/Teacher_Blue.png";
	}

	public Block retrieveAsBlock(ChannelEnum channelType, int user_id, int id, int maxDescriptionSize, int maxImageWidth)
	{
		init();
		_request.setPageSize(new Integer(1));
		_request.setCurrentPageIndex(new Integer(1));
		
//		_request.setXMLDetail(XMLDetailLevel.BARE);
		ArrayOfString fields = new ArrayOfString();
		fields.getString().add(MBStaff.STAFF_ID.getRequestField());
		fields.getString().add(MBStaff.INSTRUCTOR_LAST_NAME.getRequestField());
		fields.getString().add(MBStaff.INSTRUCTOR_FIRST_NAME.getRequestField());
//		_request.setFields(fields);

		// Set the MB class ID
		ArrayOfLong idsWeWant = new ArrayOfLong();
		idsWeWant.getLong().add(new Long(id));
		_request.setStaffIDs(idsWeWant);

		GetStaffResult staff = _staffSoap.getStaff(_request);
		List<Staff> result = staff.getStaffMembers().getStaff();

		Staff next_staff =null;
		for (int i = 0; i < result.size(); i++)
		{
			next_staff = result.get(i);
			if (next_staff.getID().getValue().intValue() == id)
			{
				System.out.println("Found the teacher Joe!");
				i = result.size();
			}
		}

		String desc = null;
		if (maxDescriptionSize > 0)
		{
			desc = MBPoly.chopDesc(next_staff.getBio(), maxDescriptionSize);
		}
		else
		{
			desc = next_staff.getBio();
		}
		desc = MBPoly.cleanDescription(desc);
		System.out.println(desc);
		Block block = new Block
		(
			ChannelEnum.create(ChannelEnum.EMAIL.getValue().intValue()),
			BlockType.create(BlockType.STAFF.getValue().intValue()),
			" this Teacher",
			next_staff.getID().getValue().intValue(),
			next_staff.getFirstName() + " " + 
					next_staff.getLastName(),
			next_staff.getFirstName() + " " + 
					next_staff.getLastName(),
			"No StartDate",
			"schedref",
			desc,
			MBPoly.chopDesc(desc),
			UserLogo.getBoundImageByWidth(MBStaff.getDefaultImgURL(), maxImageWidth));

		return block;
		
	}
}
