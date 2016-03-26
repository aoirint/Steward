package com.kanomiya.steward;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.common.eventbus.EventBus;
import com.kanomiya.steward.controller.ControlListener;
import com.kanomiya.steward.controller.handler.PlayerControlEventHandler;
import com.kanomiya.steward.editor.FrameTip;
import com.kanomiya.steward.model.Turn;
import com.kanomiya.steward.model.assets.Assets;
import com.kanomiya.steward.model.event.Event;
import com.kanomiya.steward.model.event.PlayerMode;
import com.kanomiya.steward.view.ViewConsts;

/**
 * @author Kanomiya
 *
 */
public class Game {

	public static int tickMills = 100;

	protected static Game instance;

	public static Logger logger = Logger.getLogger("Steward");



	public static Game getInstance(Assets assets)
	{
		if (instance == null) instance = new Game(assets);
		return instance;
	}

	public Assets assets;
	public Turn currentTurn;

	protected EventBus eventBus;
	protected ControlListener controlListener;

	public FrameTip frameTip;
	protected GameFrame gameFrame;

	protected Game(Assets assets)
	{
		this.assets = assets;

		PlayerMode.changeMode(this, assets.getPlayer(), assets.getPlayer().getMode());

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

		assets.getPlayer().turn();

		List<Event> eventList = assets.getPlayer().area.eventList();
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
