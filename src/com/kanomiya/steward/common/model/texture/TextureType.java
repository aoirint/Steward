package com.kanomiya.steward.common.model.texture;

/**
 * @author Kanomiya
 *
 */
public enum TextureType {
	front(false, false), // 前方 1x1
	directable(true, false), // 向き 1x4
	// aslant, // 斜め
	walkable(true, true), // 歩行 3x4
	// aslantwalkable, // 斜め歩行

	;
	private boolean isDirectable, isWalkable;

	private TextureType(boolean isDirectable, boolean isWalkable)
	{
		this.isDirectable = isDirectable;
		this.isWalkable = isWalkable;
	}

	public boolean isDirectable()
	{
		return isDirectable;
	}

	public boolean isWalkable()
	{
		return isWalkable;
	}

}
