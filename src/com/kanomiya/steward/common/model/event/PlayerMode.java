package com.kanomiya.steward.common.model.event;

/**
 * @author Kanomiya
 *
 */
public enum PlayerMode {
	NORMAL("normal", true, true, false, true),

	SELECT("select", true, true, true, false),
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

}
