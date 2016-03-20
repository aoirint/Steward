package com.kanomiya.steward.common.controller.action.key;

import com.kanomiya.steward.common.Game;
import com.kanomiya.steward.common.controller.action.IControlAction;
import com.kanomiya.steward.common.controller.unit.VirtualKeypad;
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

		if (event.isPressed(VirtualKeypad.TARGET)) // target
			player.changeMode((player.mode == PlayerMode.TARGET) ? PlayerMode.NORMAL : PlayerMode.TARGET);

		if (event.isPressed(VirtualKeypad.F3)) // debug
			player.debug = ! player.debug;

		if (event.isPressed(VirtualKeypad.F12)) // wizard
			player.changeMode((player.mode == PlayerMode.WIZARD) ? PlayerMode.NORMAL : PlayerMode.WIZARD);



	}



}
