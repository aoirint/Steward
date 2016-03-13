package com.kanomiya.steward.common.model.event;

import com.kanomiya.steward.common.model.IEnumWithId;

/**
 * @author Kanomiya
 *
 */
public enum PlayerMode implements IEnumWithId {
	NORMAL("normal", true, true, false, true),

	SELECT("select", false, false, true, false),
	WIZARD("wizard", true, false, true, true),


	;

	private String id;
	private boolean enableMove, enableTurn, enableSelecter, enableSave;


	private PlayerMode(String id, boolean enableMove, boolean enableTurn, boolean enableSelecter, boolean enableSave) {
		this.id = id;
		this.enableMove = enableMove;
		this.enableTurn = enableTurn;
		this.enableSelecter = enableSelecter;
		this.enableSave = enableSave;
	}

	public boolean enableMove()
	{
		return enableMove;
	}

	public boolean enableTurn()
	{
		return enableTurn;
	}

	public boolean enableSelecter()
	{
		return enableSelecter;
	}

	public boolean enableSave()
	{
		return enableSave;
	}

	@Override
	public String getId()
	{
		return id;
	}

	public String getUnlocalizedName()
	{
		return "playerMode." + id;
	}


	@Override
	public String toString()
	{
		return name();
	}



	/**
	 * @param id
	 * @return
	 */
	public static PlayerMode getFromId(String id)
	{
		PlayerMode[] values = values();

		for (PlayerMode type: values)
		{
			if (id.equals(type.id)) return type;
		}

		throw new IllegalArgumentException("No such object in PlayerMode: name=" + id);
	}

}
