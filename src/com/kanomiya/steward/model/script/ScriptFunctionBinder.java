package com.kanomiya.steward.model.script;

import com.kanomiya.steward.model.assets.Assets;
import com.kanomiya.steward.model.event.Player;

/**
 * @author Kanomiya
 *
 */
public class ScriptFunctionBinder {

	protected Assets assets;
	protected Player player;

	public ScriptFunctionBinder(Assets assets, Player player)
	{
		this.assets = assets;
		this.player = player;
	}


	public void exit()
	{
		System.exit(0); // VELIF
	}


	public char nextChar(char ch)
	{
		return nextChar(ch, 1);
	}

	public char nextChar(char ch, int step)
	{
		return (char) (ch +step);
	}


}
