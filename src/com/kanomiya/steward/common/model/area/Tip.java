package com.kanomiya.steward.common.model.area;

import com.google.gson.annotations.Expose;
import com.kanomiya.steward.common.model.texture.Texture;

/**
 * @author Kanomiya
 *
 */
public class Tip {
	@Expose protected String id, name;
	@Expose protected Texture icon;
	@Expose protected AccessType access; // optional

	public Tip()
	{
		access = AccessType.ALLOW;
	}

	public String getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public Texture getIcon()
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
