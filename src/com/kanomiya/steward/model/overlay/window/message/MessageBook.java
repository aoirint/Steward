package com.kanomiya.steward.model.overlay.window.message;

import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Lists;
import com.kanomiya.steward.model.overlay.LocationType;
import com.kanomiya.steward.model.overlay.text.Text;
import com.kanomiya.steward.model.overlay.window.Window;

/**
 * @author Kanomiya
 *
 */
public class MessageBook extends Window {

	protected LinkedList<Message> pages;

	protected int currentPageIndex;
	public boolean autoLastLock;

	public static MessageBook create()
	{
		return new MessageBook();
	}

	protected MessageBook() {
		pages = Lists.newLinkedList();
		// setBackground(new Texture("background/overlay/messageBox.png"));
		width = 756;
		height = 252;

		autoLocation(LocationType.CENTER);
		isClosable = true;
	}



	public MessageBook append(Message message)
	{
		pages.add(message);
		if (! autoLastLock) goLast();
		return this;
	}

	public Message get(int pageIndex)
	{
		return pages.get(pageIndex);
	}





	public int pageCount()
	{
		return pages.size();
	}

	/**
	 * @return
	 */
	public LinkedList<Message> pages() {
		return pages;
	}

	public int itemCount(int pageIndex)
	{
		return pages.get(pageIndex).itemCount();
	}

	/**
	 * @return
	 */
	public LinkedList<Text> items(int pageIndex) {
		return get(pageIndex).items();
	}




	public int getFirstPageIndex()
	{
		return 0;
	}

	public int getLastPageIndex()
	{
		return Math.max(0, pageCount() -1);
	}

	public boolean isFirstPage()
	{
		return (currentPageIndex == getFirstPageIndex());
	}

	public boolean isLastPage()
	{
		return (currentPageIndex == getLastPageIndex());
	}



	public boolean prevPage()
	{
		if (isFirstPage()) return false;

		currentPageIndex --;
		currentPage().selectedIndexOfSelectable = Math.min(currentPage().selectedIndexOfSelectable, currentPage().itemCount() -1);
		return true;
	}

	public boolean nextPage()
	{
		if (isLastPage()) return false;

		currentPageIndex ++;
		currentPage().selectedIndexOfSelectable = Math.min(currentPage().selectedIndexOfSelectable, currentPage().itemCount() -1);
		return true;
	}


	public MessageBook goFirst()
	{
		currentPageIndex = getFirstPageIndex();
		return this;
	}

	public MessageBook goLast()
	{
		currentPageIndex = getLastPageIndex();
		return this;
	}



	public int getCurrentPageIndex()
	{
		return currentPageIndex;
	}

	/**
	 * @return
	 */
	public Message currentPage() {
		return get(currentPageIndex);
	}


	public List<Message> list() {
		return pages;
	}





	public MessageBook print(int indexX, Text text, boolean pageBreak)
	{
		int pageCount = pageCount();
		Message page = (indexX < pageCount) ? get(indexX) : null;

		if (page == null)
		{
			append(Message.create(text));
			return this;
		}

		if (pageBreak)
		{
			page.print(text);
			append(Message.create());
			return this;
		}

		page.print(text);
		return this;
	}

	public MessageBook print(int indexX, Text text)
	{
		return print(indexX, text, false);
	}

	public MessageBook print(int indexX, String text, boolean pageBreak)
	{
		return print(indexX, Text.create(text), pageBreak);
	}

	public MessageBook print(int indexX, String text)
	{
		return print(indexX, text, false);
	}

	public MessageBook println(int indexX, Text text, boolean pageBreak)
	{
		return print(indexX, text.lineBreak(), pageBreak);
	}

	public MessageBook println(int indexX, Text text)
	{
		return println(indexX, text, false);
	}

	public MessageBook println(int indexX, String text, boolean pageBreak)
	{
		return println(indexX, Text.create(text), pageBreak);
	}

	public MessageBook println(int indexX, String text)
	{
		return println(indexX, text, false);
	}



	public Message replace(int indexX, Message message)
	{
		return pages.set(indexX, message);
	}

	public Message replace(Message message)
	{
		return replace(currentPageIndex, message);
	}



	/**
	 * @return
	 */
	public boolean hasPage() {
		return ! pages.isEmpty();
	}

	/**
	 * @return
	 */
	public boolean hasItem(int pageIndex) {
		return get(pageIndex).hasItem();
	}

	public int getMaxLine()
	{
		return height /Text.defaultHeight;
	}


	@Override
	public boolean isClosable()
	{
		return super.isClosable() && (! hasPage() || currentPage().isClosable());
	}


	public MessageBook lockClose()
	{
		setClosable(false);
		return this;
	}





	public MessageBook print(Text text, boolean pageBreak) {
		return print(currentPageIndex, text, pageBreak);
	}

	public MessageBook println(Text text, boolean pageBreak)
	{
		return println(currentPageIndex, text, pageBreak);
	}

	public MessageBook print(String text, boolean pageBreak)
	{
		return print(currentPageIndex, text, pageBreak);
	}

	public MessageBook println(String text, boolean pageBreak)
	{
		return println(currentPageIndex, text, pageBreak);
	}

	public MessageBook print(Text text) {
		return print(text, false);
	}

	public MessageBook println(Text text)
	{
		return println(text, false);
	}

	public MessageBook print(String text)
	{
		return print(text, false);
	}

	public MessageBook println(String text)
	{
		return println(text, false);
	}


	public int innerWidth()
	{
		return width -27*3;
	}

	public int innerHeight()
	{
		return height;
	}



}
