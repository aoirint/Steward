package com.kanomiya.steward.common.model;

/**
 * @author Kanomiya
 *
 */
public class Turn {

	protected boolean consumed;

	public void consume()
	{
		consumed = true;
	}

	public boolean isConsumed()
	{
		return consumed;
	}

}
