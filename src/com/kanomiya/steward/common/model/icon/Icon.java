package com.kanomiya.steward.common.model.icon;


/**
 * @author Kanomiya
 *
 */
public class Icon {

	public String[] src;
	public String mode;
	public int interval;

	public Icon()
	{
		this(null, "static", 0);
	}

	public Icon(String src)
	{
		this(new String[] { src }, "static", 0);
	}

	public Icon(String[] src, String mode, int interval)
	{
		this.src = src;
		this.mode = mode;
		this.interval = interval;
	}

}
