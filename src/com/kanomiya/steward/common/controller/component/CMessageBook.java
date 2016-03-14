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

/**
 * @author Kanomiya
 *
 */
public class CMessageBook extends IControllerComponent<MessageBook> {

	/**
	* @inheritDoc
	*/
	@Override
	public boolean input(KeyEvent keyEvent, ControlListener controlListener, MessageBook book, Assets assets) {

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

		if (20 <= y && y < 20 +18)
		{
			if (book.width -60 <= x && x < book.width -60 +18)
			{
				book.prevPage();

				if (book instanceof IngameLogger) ((IngameLogger) book).autoScrollLock(IngameLogger.millsWait);
				return true;
			}

			if (book.width -60 +27 <= x && x < book.width -60 +27 +18)
			{
				if (! book.nextPage()) book.setVisible(false);

				if (book instanceof IngameLogger) ((IngameLogger) book).autoScrollLock(IngameLogger.millsWait);
				return true;
			}

		}

		if (book.height -30 <= y && y < book.height -30 +18)
		{

			if (book.width -60 +2 <= x && x < book.width -60 +2 +18)
			{
				book.prevLine();

				if (book instanceof IngameLogger) ((IngameLogger) book).autoScrollLock(IngameLogger.millsWait);
				return true;
			}

			if (book.width -60 +2 +24 <= x && x < book.width -60 +2 +24 +18)
			{
				book.nextLine();

				if (book instanceof IngameLogger) ((IngameLogger) book).autoScrollLock(IngameLogger.millsWait);
				return true;
			}

		}



		return false;
	}



}
