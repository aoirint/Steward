package com.kanomiya.steward.common;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.google.common.collect.Maps;
import com.google.common.eventbus.EventBus;
import com.kanomiya.steward.common.controller.ControlListener;
import com.kanomiya.steward.common.controller.handler.PlayerControlEventHandler;
import com.kanomiya.steward.common.model.Turn;
import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.event.Event;
import com.kanomiya.steward.common.model.event.Player;
import com.kanomiya.steward.common.model.event.PlayerMode;
import com.kanomiya.steward.common.view.ViewConsts;
import com.kanomiya.steward.editor.FrameTip;

/**
 * @author Kanomiya
 *
 */
public class Game {

	public static int tickMills = 100;

	protected static Map<Assets, Game> instanceMap;

	public static Logger logger = Logger.getLogger("Steward");



	public static Game newInstance(Assets assets)
	{
		if (instanceMap == null) instanceMap = Maps.newHashMap();

		Game instance = new Game(assets);
		instanceMap.put(assets, instance);

		return instance;
	}

	public static Game getInstance(Assets assets)
	{
		return instanceMap.get(assets);
	}

	public Assets assets;
	public Player thePlayer;
	public Turn currentTurn;

	protected EventBus eventBus;
	protected ControlListener controlListener;

	public FrameTip frameTip;
	protected GameFrame gameFrame;

	protected Game(Assets assets)
	{
		this.assets = assets;
		thePlayer = assets.getPlayer();

		PlayerMode.changeMode(this, thePlayer, thePlayer.getMode());

		ViewConsts.viewGame.setSize(ViewConsts.viewWidth, ViewConsts.viewHeight);

		eventBus = new EventBus();
		controlListener = new ControlListener(this);

		frameTip = new FrameTip(this);

		gameFrame = new GameFrame(ViewConsts.viewGame, this);
		gameFrame.setVisible(true);

		eventBus.register(new PlayerControlEventHandler(this));

		currentTurn = new Turn();
	}


	public void turnWithCheck()
	{
		if (currentTurn != null && currentTurn.isConsumed()) turn();
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

		currentTurn = new Turn();
	}


	public Turn currentTurn()
	{
		return currentTurn;
	}


	public EventBus eventBus()
	{
		return eventBus;
	}

	public ControlListener controlListener()
	{
		return controlListener;
	}


	public GameFrame getFrame()
	{
		return gameFrame;
	}


}
