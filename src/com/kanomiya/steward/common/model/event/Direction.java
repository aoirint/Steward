package com.kanomiya.steward.common.model.event;

import com.kanomiya.steward.common.model.IEnumWithId;

/**
 * @author Kanomiya
 *
 */
public enum Direction implements IEnumWithId {
	SOUTH("south", 0), // front
	WEST("west", 32),
	EAST("east", 64),
	NORTH("north", 96),

	;

	private String id;
	private int iconY;

	private Direction(String id, int iconY)
	{
		this.id = id;
		this.iconY = iconY;
	}

	public int getIconY()
	{
		return iconY;
	}

	@Override
	public String getId() {
		return id;
	}


	public Direction opposite()
	{
		switch (this)
		{
		case SOUTH: return NORTH;
		case NORTH: return SOUTH;
		case WEST: return EAST;
		case EAST: return WEST;
		}

		throw new IllegalArgumentException();
	}

	public Direction right()
	{
		switch (this)
		{
		case SOUTH: return WEST;
		case NORTH: return EAST;
		case WEST: return NORTH;
		case EAST: return SOUTH;
		}

		throw new IllegalArgumentException();
	}

	public Direction left()
	{
		switch (this)
		{
		case SOUTH: return EAST;
		case NORTH: return WEST;
		case WEST: return SOUTH;
		case EAST: return NORTH;
		}

		throw new IllegalArgumentException();
	}


	public static Direction getDirection(int x1, int y1, int x2, int y2, Direction defaulz)
	{
		if (x1 == x2 && y1 == y2) return defaulz;

		if (y2 < y1) return NORTH; // /\
		else if (y1 < y2) return SOUTH; // \/

		if (x2 < x1) return WEST; // <-
		else if (x1 < x2) return EAST; // ->

		return defaulz;
	}

	/**
	 * @param id
	 * @return
	 */
	public static Direction getFromId(String id)
	{
		Direction[] values = values();

		for (Direction type: values)
		{
			if (id.equals(type.id)) return type;
		}

		throw new IllegalArgumentException("No such object in Direction: name=" + id);
	}



}
