package com.kanomiya.steward.common.controller.action.key;

import com.kanomiya.steward.common.Game;
import com.kanomiya.steward.common.config.GameKeys;
import com.kanomiya.steward.common.controller.action.IControlAction;
import com.kanomiya.steward.common.controller.unit.event.KeyboardUpdateEvent;
import com.kanomiya.steward.common.model.event.Player;
import com.kanomiya.steward.common.model.event.PlayerMode;

/**
 * @author Kanomiya
 *
 */
public class CAKeyMode implements IControlAction<KeyboardUpdateEvent> {

	/**
	* @inheritDoc
	*/
	@Override
	public void action(KeyboardUpdateEvent event, Game game)
	{
		if (event.isCancelledOrConsumed()) return ;

		Player player = game.thePlayer;

		if (player.hasWindow()) return ;


		if (event.isPressed(GameKeys.TARGET)) // target
			player.changeMode(player.modeIs(PlayerMode.TARGET) ? PlayerMode.NORMAL : PlayerMode.TARGET);

		if (event.isPressed(GameKeys.F3)) // debug
			player.debug = ! player.debug;

		if (event.isPressed(GameKeys.F12)) // wizard
			player.changeMode(player.modeIs(PlayerMode.WIZARD) ? PlayerMode.NORMAL : PlayerMode.WIZARD);



	}



}
