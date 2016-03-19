package com.kanomiya.steward.common.controller.component;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.kanomiya.steward.common.controller.ControlListener;
import com.kanomiya.steward.common.controller.IControllerComponent;
import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.overlay.message.IngameLogger;
import com.kanomiya.steward.common.model.overlay.message.Message;
import com.kanomiya.steward.common.model.overlay.message.MessageBook;
import com.kanomiya.steward.common.model.overlay.text.ChoiceResult;
import com.kanomiya.steward.common.view.component.window.VCMessageBook;

/**
 * @author Kanomiya
 *
 */
public class CMessageBook extends IControllerComponent<MessageBook> {

	/**
	* @inheritDoc
	*/
	@Override
	public boolean input(KeyEvent keyEvent, ControlListener controlListener, MessageBook book, Assets assets)
	{
		Message current = book.currentPage();

		if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER)
		{
			if (current.hasChoice())
			{
				current.getSelectedChoice().apply();

				return true;
			}
			else if (keyEvent.isShiftDown())
			{
				book.prevPage();
				return true;
			}
			else if (! book.isLastPage())
			{
				book.nextPage();
				return true;
			}
			else if (book.isClosable())
			{
				controlListener.game.thePlayer.removeWindow();
				return true;
			}
		}

		switch (keyEvent.getKeyCode())
		{
		case KeyEvent.VK_UP:
		case KeyEvent.VK_NUMPAD8:
			if (current.hasChoice() && ! current.isFirstChoice())
			{
				current.prevChoice();
				return true;
			}

			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_NUMPAD2:
			if (current.hasChoice() && ! current.isLastChoice())
			{
				current.nextChoice();
				return true;
			}

			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_NUMPAD4:
			if (! book.isFirstPage())
			{
				book.prevPage();
				return true;
			}

			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_NUMPAD6:
			if (! book.isLastPage())
			{
				book.nextPage();
				return true;
			}

			break;
		}


		if (! current.hasChoice()) return false;

		char ch = keyEvent.getKeyChar();

		if (current.charToChoice().containsKey(ch))
		{
			ChoiceResult result = current.charToChoice().get(ch).apply();


		}


		return false;
	}



	/**
	* @inheritDoc
	*/
	@Override
	public boolean click(MouseEvent mouseEvent, int x, int y, ControlListener controlListener, MessageBook book, Assets assets)
	{
		if (book instanceof IngameLogger) ((IngameLogger) book).autoCloseLock = true;

		if (VCMessageBook.containsBtnLeft(book, x, y))
		{
			if (book instanceof IngameLogger) ((IngameLogger) book).autoLastLock(IngameLogger.millsWait);

			if (! book.isFirstPage())
			{
				book.prevPage();
				return true;
			}
		}

		if (VCMessageBook.containsBtnRight(book, x, y))
		{
			if (book instanceof IngameLogger) ((IngameLogger) book).autoLastLock(IngameLogger.millsWait);

			if (! book.isLastPage())
			{
				book.nextPage();
				return true;
			}
		}

		if (VCMessageBook.containsBtnCheck(book, x, y))
		{
			if (book.isClosable())
			{
				book.setVisible(false);
				return true;
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


		return false;
	}



}
