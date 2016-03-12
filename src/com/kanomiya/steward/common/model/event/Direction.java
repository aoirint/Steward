package com.kanomiya.steward.common.model.event;

/**
 * @author Kanomiya
 *
 */
public enum Direction {
	south(0), // front
	west(32),
	east(64),
	north(96),

	;
	private int iconY;

	private Direction(int iconY)
	{
		this.iconY = iconY;
	}

	public int getIconY()
	{
		return iconY;
	}


	public static Direction getDirection(int x1, int y1, int x2, int y2, Direction defaulz)
	{
		if (x1 == x2 && y1 == y2) return defaulz;

		if (y2 < y1) return north; // /\
		else if (y1 < y2) return south; // \/

		if (x2 < x1) return west; // <-
		else if (x1 < x2) return east; // ->

		return defaulz;
	}

}
