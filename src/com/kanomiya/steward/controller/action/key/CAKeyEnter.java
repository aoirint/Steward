package com.kanomiya.steward.controller.action.key;

import com.kanomiya.steward.Game;
import com.kanomiya.steward.config.GameKeys;
import com.kanomiya.steward.controller.action.IControlAction;
import com.kanomiya.steward.controller.unit.event.KeyboardUpdateEvent;
import com.kanomiya.steward.model.event.Player;
import com.kanomiya.steward.model.overlay.text.Choice;
import com.kanomiya.steward.model.overlay.text.ISelectableText;
import com.kanomiya.steward.model.overlay.window.message.Message;
import com.kanomiya.steward.model.overlay.window.message.MessageBook;

/**
 * @author Kanomiya
 *
 */
public class CAKeyEnter implements IControlAction<KeyboardUpdateEvent> {

	/**
	* @inheritDoc
	*/
	@Override
	public void action(KeyboardUpdateEvent event, Game game)
	{
		if (event.isCancelledOrConsumed()) return ;
		if (! event.isPressed(GameKeys.ENTER)) return ;


		Player player = game.assets.getPlayer();

		if (player.hasWindow())
		{
			if (player.getWindow() instanceof MessageBook)
			{
				MessageBook book = (MessageBook) player.getWindow();

				Message current = book.currentPage();

				if (current.hasSelectable())
				{
					ISelectableText selected = current.getSelectedText();
					if (selected instanceof Choice)
					{
						((Choice) selected).confirm();
						event.consume();
					}

				} else if (event.isShifted())
				{
					book.prevPage();
				}
				else if (! book.isLastPage())
				{
					book.nextPage();
				}
				else if (book.isClosable())
				{
					player.removeWindow();
				}
			}
		} else
		{
			if (player.getMode().enableSelecter())
			{
				player.selectedX = player.focusedX;
				player.selectedY = player.focusedY;
			}

		}


	}



}
