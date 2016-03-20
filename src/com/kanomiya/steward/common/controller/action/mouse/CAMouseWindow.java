package com.kanomiya.steward.common.controller.action.mouse;

import com.kanomiya.steward.common.Game;
import com.kanomiya.steward.common.controller.action.IControlAction;
import com.kanomiya.steward.common.controller.unit.event.MouseUpdateEvent;
import com.kanomiya.steward.common.model.event.Player;
import com.kanomiya.steward.common.model.overlay.window.Window;
import com.kanomiya.steward.common.model.overlay.window.message.IngameLogger;
import com.kanomiya.steward.common.model.overlay.window.message.MessageBook;
import com.kanomiya.steward.common.view.component.window.VCMessageBook;

/**
 * @author Kanomiya
 *
 */
public class CAMouseWindow implements IControlAction<MouseUpdateEvent> {

	/**
	* @inheritDoc
	*/
	@Override
	public void action(MouseUpdateEvent event, Game game)
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

	protected static void doAction(MouseUpdateEvent event, MessageBook book)
	{
		int x = event.getX();
		int y = event.getY();


		if (book instanceof IngameLogger) ((IngameLogger) book).autoCloseLock = true;

		if (VCMessageBook.containsBtnLeft(book, x, y))
		{
			if (book instanceof IngameLogger) ((IngameLogger) book).autoLastLock(IngameLogger.millsWait);

			if (! book.isFirstPage())
			{
				book.prevPage();
			}
		}

		if (VCMessageBook.containsBtnRight(book, x, y))
		{
			if (book instanceof IngameLogger) ((IngameLogger) book).autoLastLock(IngameLogger.millsWait);

			if (! book.isLastPage())
			{
				book.nextPage();
			}
		}

		if (VCMessageBook.containsBtnCheck(book, x, y))
		{
			if (book.isClosable())
			{
				book.setVisible(false);
			}
		}

		/*
		Message page = book.currentPage();

		if (VCMessageBook.containsBtnUp(book, x, y))
		{

			if (! page.isFirstChoice())
			{
				page.prevChoice();
				return true;
			}
		}

		if (VCMessageBook.containsBtnDown(book, x, y))
		{

			if (! page.isLastChoice())
			{
				page.nextChoice();
				return true;
			}
		}
		*/

	}



}
