package com.kanomiya.steward.common.model.area;

import com.kanomiya.steward.common.model.icon.Icon;

/**
 * @author Kanomiya
 *
 */
public class Tip {
	protected String id, name;
	protected Icon icon;
	protected AccessType access; // optional

	public Tip()
	{
		access = AccessType.allow;
	}

	public String getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public Icon getIcon()
	{
		return icon;
	}

	public AccessType getAccessType()
	{
		return access;
	}

	@Override
	public String toString()
	{
		return getName();
	}

}
