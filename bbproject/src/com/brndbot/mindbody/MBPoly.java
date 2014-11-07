package com.brndbot.mindbody;

import org.jsoup.Jsoup;

import com.brndbot.block.BlockBase;
import com.brndbot.block.BlockType;
import com.brndbot.block.ChannelEnum;
import com.brndbot.system.Assert;
import com.brndbot.system.SystemProp;

/* This class gives a single interface for the various MB packages.
 * Call the factoryCreateMBPoly method to create for client code use. */
abstract public class MBPoly
{
	protected BlockType _mb_package;
	private int _user_id;

	static public int MAX_DESCRIPTION_LENGTH = 110;

	public BlockType getMBPackageEnum() { return _mb_package; }
	public int getUserID() { return _user_id; }

	protected MBPoly(int user_id)
	{
		_user_id = user_id;
	}

	protected void setCredentials(int user_id)
	{
		Assert.that(user_id != 0, "User ID is 0 in MBPoly initialization!");
		String _mb_name = SystemProp.get(SystemProp.MINDBODY_NAME);
		String _mb_password = SystemProp.get(SystemProp.MINDBODY_KEY);
		int _mb_id = Integer.parseInt(SystemProp.get(SystemProp.MINDBODY_STUDIOID));

		setCredentialDetails(_mb_name, _mb_password, _mb_id);
	}

	abstract protected void setCredentialDetails(
			String mb_name, String mb_password, int mb_id);

	static public String cleanDescription(String desc)
	{
//	    System.out.println("Before: " + desc);
		if (desc == null)
		{
			return "";
		}
		String ret = desc.replaceAll("\"", "&quot;");
		ret = ret.replaceAll("'", "&apos;");
//		ret = ret.replaceAll("�", "&apos;");
		ret = ret.replaceAll("`", "&apos;");
		String text = Jsoup.parse(ret.replaceAll("(?i)<br[^>]*>", "br2nl").replaceAll("\n", "br2nl")).text();
	    return text.replaceAll("br2nl ", "\n").replaceAll("br2nl", "\n").trim();
	}
	
	static public String escapeQuotes(String desc_arg)
	{
		String desc = desc_arg.replaceAll("\"", "&quot;");
		return desc.replaceAll("'", "&apos;");
	}
	static public String chopDesc(String desc_arg)
	{
		return chopDesc(desc_arg, MAX_DESCRIPTION_LENGTH);
	}
	
	static public String chopDesc(String desc_arg, int maxDesc)
	{
		if (desc_arg == null)
		{
			return "";
		}

		String desc = desc_arg.trim();
		if (desc.length() == 0)
		{
			desc = "No description available.";
		}
		if (desc.length() > maxDesc)
		{
//			System.out.println("Chopping to the length of: " + maxDesc);
			return desc.substring(0, maxDesc) + "...";
		}
		return desc;
	}

/*	public String getDataAsJSON()
	{
		return getDataAsJSON(0, 0); // get all
	}
*/
	public String getDataAsJSON(int count, int max_width)
	{
		return getDataAsJSON(count, 1, max_width); // get first #
	}

	abstract protected void init();

	abstract public String getDataAsJSON(int count, int page, int max_width);

//	abstract public BlockBase retrieveAsBlock(int user_id, int id);

//	abstract public BlockBase retrieveAsBlock(int user_id, int id, int maxDescSize);

	abstract public BlockBase retrieveAsBlock(ChannelEnum channelType, int user_id, int id, int maxDescSize, int width);
}
