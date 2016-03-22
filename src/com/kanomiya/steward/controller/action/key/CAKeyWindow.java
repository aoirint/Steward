package com.kanomiya.steward.controller.action.key;

import com.kanomiya.steward.Game;
import com.kanomiya.steward.controller.action.IControlAction;
import com.kanomiya.steward.controller.unit.event.KeyboardUpdateEvent;
import com.kanomiya.steward.controller.unit.identifier.Key;
import com.kanomiya.steward.model.event.Player;
import com.kanomiya.steward.model.overlay.text.ConfirmResult;
import com.kanomiya.steward.model.overlay.text.IEditableText;
import com.kanomiya.steward.model.overlay.window.Window;
import com.kanomiya.steward.model.overlay.window.message.Message;
import com.kanomiya.steward.model.overlay.window.message.MessageBook;

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

		if (! current.hasSelectable()) return ;

		if (current.getSelectedText() instanceof IEditableText) // c.f ControlListener#keyTyped
		{
			IEditableText editable = (IEditableText) current.getSelectedText();

			int cridx = editable.getCaretIndex();

			if (event.isPressed(Key.LEFT) && 0 < cridx)
				editable.setCaretIndex(--cridx);
			if (event.isPressed(Key.RIGHT) && cridx < editable.getTextString().length())
				editable.setCaretIndex(++cridx);

		} else if (current.hasChoice())
		{
			Key key = event.getUpdateKey();

			if (! key.hasChar()) return ;

			char ch = key.toChar();

			if (current.charToChoice().containsKey(ch))
			{
				ConfirmResult result = current.charToChoice().get(ch).confirm();


			}

		}


	}



}
