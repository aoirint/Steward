package com.kanomiya.steward.common.controller.action.key;

import com.kanomiya.steward.common.Game;
import com.kanomiya.steward.common.controller.action.IControlAction;
import com.kanomiya.steward.common.controller.unit.VirtualKeypad;
import com.kanomiya.steward.common.controller.unit.event.KeyboardUpdateEvent;
import com.kanomiya.steward.common.model.event.Player;
import com.kanomiya.steward.common.model.overlay.text.Choice;
import com.kanomiya.steward.common.model.overlay.text.ISelectableText;
import com.kanomiya.steward.common.model.overlay.window.message.Message;
import com.kanomiya.steward.common.model.overlay.window.message.MessageBook;

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
		if (! event.isPressed(VirtualKeypad.ENTER)) return ;


		Player player = game.thePlayer;

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
