package com.brndbot.mindbody;

import com.brndbot.block.BlockType;


public class MBPolyFactory 
{
	public MBPolyFactory() { }

	public MBPoly createMBPoly(BlockType data_type, int user_id) 
			throws MBPolyException
	{
		if (data_type.equals(BlockType.CLASS))
		{
			System.out.println("CLASS");
			return new MBClass(user_id);
		}
		else if (data_type.equals(BlockType.WORKSHOP))
		{
			System.out.println("WORKSHOP");
			return new MBWorkshop(user_id);
		}
		else if (data_type.equals(BlockType.STAFF))
		{
			System.out.println("STAFF");
			return new MBStaff(user_id);
		}

		throw new MBPolyException("Unsupported MBPackageEnum: " +
			data_type.getItemText());
	}
}
