package com.kanomiya.steward.common;
import java.util.List;

import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.event.Event;
import com.kanomiya.steward.common.model.event.Player;

/**
 * @author Kanomiya
 *
 */
public class Game {

	public static int tickMills = 100;

	public Assets assets;
	public Player thePlayer;

	public Game(Assets assets, Player thePlayer)
	{
		this.assets = assets;
		this.thePlayer = thePlayer;
	}


	public void turn()
	{
		// TODO: others

		thePlayer.turn();

		List<Event> eventList = thePlayer.area.eventList();
		int elLen = eventList.size();

		for (int i=0; i<elLen; i++)
		{
			eventList.get(i).turn();
		}

	}


}
