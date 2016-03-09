package com.kanomiya.steward.common;

import com.kanomiya.steward.common.model.ITurnObject;
import com.kanomiya.steward.common.model.assets.Assets;

/**
 * @author Kanomiya
 *
 */
public class Game implements ITurnObject {

	public static int tickMills = 100;

	public Assets assets;
	public Player thePlayer;

	public Game(Assets assets)
	{
		this.assets = assets;
		thePlayer = new Player("test", 3, 3);
	}

	public void turn()
	{

	}

}
