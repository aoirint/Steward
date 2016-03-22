package com.kanomiya.steward.controller.handler;

import com.google.common.eventbus.Subscribe;
import com.kanomiya.steward.Game;
import com.kanomiya.steward.controller.action.key.CAKeyArrow;
import com.kanomiya.steward.controller.action.key.CAKeyEnter;
import com.kanomiya.steward.controller.action.key.CAKeyEscape;
import com.kanomiya.steward.controller.action.key.CAKeyFunction;
import com.kanomiya.steward.controller.action.key.CAKeyMenu;
import com.kanomiya.steward.controller.action.key.CAKeyMode;
import com.kanomiya.steward.controller.action.key.CAKeySave;
import com.kanomiya.steward.controller.action.key.CAKeyWindow;
import com.kanomiya.steward.controller.action.mouse.CAMouseWindow;
import com.kanomiya.steward.controller.unit.event.KeyboardUpdateEvent;
import com.kanomiya.steward.controller.unit.event.MouseUpdateEvent;

/**
 * @author Kanomiya
 *
 */
public class PlayerControlEventHandler {

	protected static CAKeyArrow caKeyArrow = new CAKeyArrow();
	protected static CAKeyEnter caKeyEnter = new CAKeyEnter();
	protected static CAKeyEscape caKeyEscape = new CAKeyEscape();
	protected static CAKeyFunction caKeyFunction = new CAKeyFunction();
	protected static CAKeyMenu caKeyMenu = new CAKeyMenu();

	protected static CAKeyMode caKeyMode = new CAKeyMode();
	protected static CAKeySave caKeySave = new CAKeySave();
	protected static CAKeyWindow caKeyWindow = new CAKeyWindow();

	protected static CAMouseWindow caMouseWindow = new CAMouseWindow();

	protected Game game;

	public PlayerControlEventHandler(Game game)
	{
		this.game = game;

	}



	@Subscribe
	public void onKeyboardUpdateEvent(KeyboardUpdateEvent event)
	{
		// if (event.isCancelledOrConsumed()) return ;

		caKeyArrow.action(event, game);
		caKeyEnter.action(event, game);
		caKeyEscape.action(event, game);
		caKeyFunction.action(event, game);
		caKeyMenu.action(event, game);

		caKeyMode.action(event, game);
		caKeySave.action(event, game);
		caKeyWindow.action(event, game);


	}


	@Subscribe
	public void onMouseUpdateEvent(MouseUpdateEvent event)
	{
		// if (event.isCancelledOrConsumed()) return ;

		caMouseWindow.action(event, game);


	}






}
