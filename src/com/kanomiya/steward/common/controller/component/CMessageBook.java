package com.kanomiya.steward.common.controller.component;

import com.kanomiya.steward.common.controller.ControlListener;
import com.kanomiya.steward.common.controller.IControllerComponent;
import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.overlay.message.IngameLogger;
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
	public boolean click(int button, int x, int y, ControlListener controlListener, MessageBook book, Assets assets)
	{
		if (book instanceof IngameLogger) ((IngameLogger) book).autoCloseLock = true;

		if (20 <= y && y < 20 +18)
		{
			if (book.width -60 <= x && x < book.width -60 +18)
			{
				if (0 < book.getCurrentPageIndex())
				{
					book.currentPageIndex --;
					book.beginIndex = 0;
				}
				if (book instanceof IngameLogger) ((IngameLogger) book).autoScrollLock(IngameLogger.millsWait);
				return true;
			}

			if (book.width -60 +27 <= x && x < book.width -60 +27 +18)
			{
				if (book.getCurrentPageIndex() < book.pageCount() -1)
				{
					book.currentPageIndex ++;
					book.beginIndex = 0;
				}
				else if (book.getCurrentPageIndex() == book.pageCount() -1) book.setVisible(false);

				if (book instanceof IngameLogger) ((IngameLogger) book).autoScrollLock(IngameLogger.millsWait);

				return true;
			}

		}

		if (book.height -30 <= y && y < book.height -30 +18)
		{

			if (book.width -60 +2 <= x && x < book.width -60 +2 +18)
			{
				if (0 < book.beginIndex) book.beginIndex --;
				if (book instanceof IngameLogger) ((IngameLogger) book).autoScrollLock(IngameLogger.millsWait);
				return true;
			}

			if (book.width -60 +2 +24 <= x && x < book.width -60 +2 +24 +18)
			{
				if (book.beginIndex < book.currentPage().itemCount() -1) book.beginIndex ++;
				if (book instanceof IngameLogger) ((IngameLogger) book).autoScrollLock(IngameLogger.millsWait);
				return true;
			}

		}



		return false;
	}



}
