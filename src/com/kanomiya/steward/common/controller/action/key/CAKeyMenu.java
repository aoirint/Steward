package com.kanomiya.steward.common.controller.action.key;

import com.kanomiya.steward.common.Game;
import com.kanomiya.steward.common.controller.action.IControlAction;
import com.kanomiya.steward.common.controller.unit.VirtualKeypad;
import com.kanomiya.steward.common.controller.unit.event.KeyboardUpdateEvent;
import com.kanomiya.steward.common.model.event.Player;
import com.kanomiya.steward.common.model.overlay.InventoryWindow;

/**
 * @author Kanomiya
 *
 */
public class CAKeyMenu implements IControlAction<KeyboardUpdateEvent> {

	/**
	* @inheritDoc
	*/
	@Override
	public void action(KeyboardUpdateEvent event, Game game)
	{
		if (event.isCancelledOrConsumed()) return ;

		Player player = game.thePlayer;


		if (event.isPressed(VirtualKeypad.LOG)) // log
		{
			if (! player.hasWindow()) player.logger.setVisible(! player.logger.isVisible());
		}


		if (event.isPressed(VirtualKeypad.INVENTORY)) // inventory
		{
			if (! player.hasWindow()) player.showWindow(new InventoryWindow(player.getInventory()));
		}




	}



}
