package com.kanomiya.steward.common.model.event;

import com.kanomiya.steward.common.model.util.IEnumWithId;

/**
 * @author Kanomiya
 *
 */
public enum WalkState implements IEnumWithId {
	UPRIGHT("upright", 0), // 直立
	FORWARD("forward", 32), // 前進

	;

	private String id;
	private int iconX;

	private WalkState(String id, int iconX)
	{
		this.id = id;
		this.iconX = iconX;
	}

	;
	public WalkState next()
	{
		if (this == UPRIGHT) return FORWARD;
		return UPRIGHT;
	}

	public int getIconX()
	{
		return iconX;
	}


	@Override
	public String getId() {
		return id;
	}


	public static WalkState getFromId(String id)
	{
		WalkState[] values = values();

		for (WalkState mode: values)
		{
			if (id.equals(mode.id)) return mode;
		}

		throw new IllegalArgumentException("No such object in WalkState: name=" + id);
	}

}
