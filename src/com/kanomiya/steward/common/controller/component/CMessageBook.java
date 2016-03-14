package com.kanomiya.steward.common.controller.component;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.kanomiya.steward.common.controller.ControlListener;
import com.kanomiya.steward.common.controller.IControllerComponent;
import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.overlay.message.ChoiceResult;
import com.kanomiya.steward.common.model.overlay.message.IngameLogger;
import com.kanomiya.steward.common.model.overlay.message.Message;
import com.kanomiya.steward.common.model.overlay.message.MessageBook;
import com.kanomiya.steward.common.view.component.VCMessageBook;

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

		if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE && book.isClosable()) book.setVisible(false);

		Message current = book.currentPage();

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
			if (book instanceof IngameLogger) ((IngameLogger) book).autoScrollLock(IngameLogger.millsWait);

			if (! book.isFirstPage())
			{
				book.prevPage();
				return true;
			}
		}

		if (VCMessageBook.containsBtnRight(book, x, y))
		{
			if (book instanceof IngameLogger) ((IngameLogger) book).autoScrollLock(IngameLogger.millsWait);

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


		if (VCMessageBook.containsBtnUp(book, x, y))
		{
			if (book instanceof IngameLogger) ((IngameLogger) book).autoScrollLock(IngameLogger.millsWait);

			if (! book.isFirstLine())
			{
				book.prevLine();
				return true;
			}
		}

		if (VCMessageBook.containsBtnDown(book, x, y))
		{
			if (book instanceof IngameLogger) ((IngameLogger) book).autoScrollLock(IngameLogger.millsWait);

			if (! book.isLastLine())
			{
				book.nextLine();
				return true;
			}
		}


		return false;
	}



}
