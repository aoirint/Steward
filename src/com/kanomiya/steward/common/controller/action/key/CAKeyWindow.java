package com.kanomiya.steward.common.controller.action.key;

import com.kanomiya.steward.common.Game;
import com.kanomiya.steward.common.controller.action.IControlAction;
import com.kanomiya.steward.common.controller.unit.event.KeyboardUpdateEvent;
import com.kanomiya.steward.common.controller.unit.identifier.Key;
import com.kanomiya.steward.common.model.event.Player;
import com.kanomiya.steward.common.model.overlay.text.ChoiceResult;
import com.kanomiya.steward.common.model.overlay.window.Window;
import com.kanomiya.steward.common.model.overlay.window.message.Message;
import com.kanomiya.steward.common.model.overlay.window.message.MessageBook;

/**
 * @author Kanomiya
 *
 */
public class CAKeyWindow implements IControlAction<KeyboardUpdateEvent> {

	/**
	* @inheritDoc
	*/
	@Override
	public void action(KeyboardUpdateEvent event, Game game)
	{
		if (event.isCancelledOrConsumed()) return ;

		Player player = game.thePlayer;

		if (player.hasWindow())
		{
			Window window = player.getWindow();
			if (window.isVisible())
			{
				if (window instanceof MessageBook) doAction(event, (MessageBook) window);
			}

		} else if (player.logger.isVisible())
		{
			doAction(event, player.logger);
		}


	}

	protected static void doAction(KeyboardUpdateEvent event, MessageBook book)
	{
		Message current = book.currentPage();

		if (! current.hasChoice()) return ;

		Key key = event.getUpdateKey();

		if (! key.hasChar()) return ;

		char ch = key.toChar();

		if (current.charToChoice().containsKey(ch))
		{
			ChoiceResult result = current.charToChoice().get(ch).apply();


		}

	}



}
