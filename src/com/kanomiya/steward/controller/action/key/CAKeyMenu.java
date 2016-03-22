package com.kanomiya.steward.controller.action.key;

import com.kanomiya.steward.Game;
import com.kanomiya.steward.config.GameKeys;
import com.kanomiya.steward.controller.action.IControlAction;
import com.kanomiya.steward.controller.unit.event.KeyboardUpdateEvent;
import com.kanomiya.steward.model.event.Player;
import com.kanomiya.steward.model.overlay.InventoryWindow;

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


		if (event.isPressed(GameKeys.LOG)) // log
		{
			if (! player.hasWindow()) player.logger.setVisible(! player.logger.isVisible());
		}


		if (event.isPressed(GameKeys.INVENTORY)) // inventory
		{
			if (! player.hasWindow()) player.showWindow(new InventoryWindow(player.getInventory()));
		}




	}



}
