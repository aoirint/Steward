package com.kanomiya.steward.common.model.event;

/**
 * @author Kanomiya
 *
 */
public enum PlayerMode {
	NORMAL,

	SELECT,
	WIZARD,


	;

	public boolean enableMove()
	{
		switch (this)
		{
		case NORMAL: return true;
		case SELECT: return true;
		case WIZARD: return true;
		}

		return false;
	}

	public boolean enableTurn()
	{
		switch (this)
		{
		case NORMAL: return true;
		case SELECT: return true;
		case WIZARD: return false;
		}

		return false;
	}

	public boolean enableSelecter()
	{
		switch (this)
		{
		case NORMAL: return false;
		case SELECT: return true;
		case WIZARD: return true;
		}

		return false;
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
