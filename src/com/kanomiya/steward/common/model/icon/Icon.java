package com.kanomiya.steward.common.model.icon;

import com.google.gson.annotations.Expose;


/**
 * @author Kanomiya
 *
 */
public class Icon {

	@Expose public String[] src;
	@Expose public IconMode mode;
	@Expose public IconType type;
	@Expose public int interval;

	public int count = 0;
	public int index = 0;

	public IIconOwner owner;

	public Icon()
	{
		this(null, IconMode.STATIC, IconType.front, null, 1000);
	}


	public Icon(String src)
	{
		this(new String[] { src }, IconMode.STATIC, IconType.front, null, 1000);
	}

	/**
	 *
	 * @param src
	 * @param mode
	 * @param type
	 * @param owner typeがfrontのときはnullでよい
	 * @param interval
	 */
	public Icon(String[] src, IconMode mode, IconType type, IIconOwner owner, int interval)
	{
		this.src = src;
		this.mode = mode;
		this.type = type;
		this.interval = Math.max(interval, 1000);
	}

	public boolean hasOwner()
	{
		return (owner != null);
	}

	public IIconOwner getOwner()
	{
		return owner;
	}

}
