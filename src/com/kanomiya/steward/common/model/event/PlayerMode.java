package com.kanomiya.steward.common.model.event;

/**
 * @author Kanomiya
 *
 */
public enum PlayerMode {
	NORMAL(true, true, false, true),

	SELECT(true, true, true, false),
	WIZARD(true, false, true, true),


	;

	private boolean enableMove, enableTurn, enableSelecter, enableSave;


	private PlayerMode(boolean enableMove, boolean enableTurn, boolean enableSelecter, boolean enableSave) {
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
	public String toString()
	{
		switch (this)
		{
		case NORMAL: return "Normal";
		case SELECT: return "Select";
		case WIZARD: return "Wizard";
		}

		return name();
	}

}
