package com.kanomiya.steward.common.model.event;

/**
 * @author Kanomiya
 *
 */
public enum WalkState {
	upright(0), // 直立
	forward(32), // 前進

	;

	private int iconX;

	private WalkState(int iconX)
	{
		this.iconX = iconX;
	}

	;
	public WalkState next()
	{
		if (this == upright) return forward;
		return upright;
	}

	public int getIconX()
	{
		return iconX;
	}

}
