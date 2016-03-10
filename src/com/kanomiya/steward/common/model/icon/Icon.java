package com.kanomiya.steward.common.model.icon;


/**
 * @author Kanomiya
 *
 */
public class Icon {

	public String[] src;
	public IconMode mode;
	public int interval;

	public transient int count = 0;
	public transient int index = 0;


	public Icon()
	{
		this(null, IconMode.STATIC, 1000);
	}


	public Icon(String src)
	{
		this(new String[] { src }, IconMode.STATIC, 1000);
	}

	public Icon(String[] src, IconMode mode, int interval)
	{
		this.src = src;
		this.mode = mode;
		this.interval = Math.max(interval, 1000);
	}

}
