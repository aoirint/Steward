package com.kanomiya.steward.common.model.texture;

import com.kanomiya.steward.common.model.IEnumWithId;

/**
 * @author Kanomiya
 *
 */
public enum TextureType implements IEnumWithId {
	FRONT("front", false, false), // 前方 1x1
	DIRECTABLE("directable", true, false), // 向き 1x4
	// aslant, // 斜め
	WALKABLE("walkable", true, true), // 歩行 3x4
	// aslantwalkable, // 斜め歩行

	;
	private String id;
	private boolean isDirectable, isWalkable;

	private TextureType(String id, boolean isDirectable, boolean isWalkable)
	{
		this.id = id;
		this.isDirectable = isDirectable;
		this.isWalkable = isWalkable;
	}

	@Override
	public String getId()
	{
		return id;
	}

	public String getUnlocalizedName()
	{
		return "textureType." + id;
	}


	public boolean isDirectable()
	{
		return isDirectable;
	}

	public boolean isWalkable()
	{
		return isWalkable;
	}


	/**
	 * @param id
	 * @return
	 */
	public static TextureType getFromId(String id)
	{
		TextureType[] values = values();

		for (TextureType type: values)
		{
			if (id.equals(type.id)) return type;
		}

		throw new IllegalArgumentException("No such object in TextureType: name=" + id);
	}

}
