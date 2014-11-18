/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.block;

import java.util.ArrayList;

public class BlockStack 
{
	private ArrayList<Block> _stack;
	
	// _stack_index points to the block in the stack that is active in the display
	private Integer _stack_index;
	
	public BlockStack(ChannelEnum channel_type)
	{
		_stack = new ArrayList<Block>();
		_stack_index = new Integer(0);
	}
	
	public int increment()
	{
		int val = _stack_index.intValue() + 1; 
		if (val == _stack.size())
		{
			val = 0;
		}
		return setIndex(val);
	}

	public int setIndex(int index)
	{
		int val = index;
		if (val == _stack.size())
		{
			val = 0;
		}
		_stack_index = new Integer(val);
		return val;
	}

	public int addBlock(Block block)
	{
		int val = -1;  // -1 means block already exists
		if (!_stack.contains(block))
		{
			_stack.add(new Block(block));
			val = _stack.size();
		}
		return val;
	}
}
