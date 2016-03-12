package com.kanomiya.steward.common.model.area;

import com.google.gson.annotations.Expose;
import com.kanomiya.steward.common.model.icon.Icon;

/**
 * @author Kanomiya
 *
 */
public class Tip {
	@Expose protected String id, name;
	@Expose protected Icon icon;
	@Expose protected AccessType access; // optional

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
