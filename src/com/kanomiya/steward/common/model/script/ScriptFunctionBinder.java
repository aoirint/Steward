package com.kanomiya.steward.common.model.script;

import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.event.Player;

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



}
