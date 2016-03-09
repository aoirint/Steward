package com.kanomiya.steward.common;

import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.event.Player;
import com.kanomiya.steward.common.model.icon.Icon;

/**
 * @author Kanomiya
 *
 */
public class Game{

	public static int tickMills = 100;

	public Assets assets;
	public Player thePlayer;

	public Game(Assets assets)
	{
		this.assets = assets;
		thePlayer = new Player("test", 3, 3, new Icon("player.png")); // TODO: ソース直書き
	}


	public void turn()
	{
		// TODO: others

		thePlayer.turn(assets);
	}

}
