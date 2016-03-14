package com.kanomiya.steward.common.model.overlay.message;

import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Lists;
import com.kanomiya.steward.common.model.overlay.Overlay;
import com.kanomiya.steward.common.model.overlay.Text;
import com.kanomiya.steward.common.model.texture.Texture;

/**
 * @author Kanomiya
 *
 */
public class MessageBook extends Overlay {

	protected LinkedList<Message> pages;

	protected int currentPageIndex, beginIndex;
	protected boolean isClosable;

	public static MessageBook create()
	{
		return new MessageBook();
	}

	protected MessageBook() {
		pages = Lists.newLinkedList();
		setBackground(new Texture("background/overlay/messageBox.png"));

		isClosable = true;
	}



	public MessageBook append(Message message)
	{
		pages.add(message);
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





	public boolean isFirstPage()
	{
		return (currentPageIndex == 0);
	}

	public boolean isLastPage()
	{
		return (currentPageIndex == pageCount() -1);
	}

	public boolean isFirstLine()
	{
		return (beginIndex == 0);
	}

	public boolean isLastLine()
	{
		return (beginIndex == currentPage().itemCount() -1);
	}


	public boolean prevPage()
	{
		if (isFirstPage()) return false;

		currentPageIndex --;
		beginIndex = 0;
		return true;
	}

	public boolean nextPage()
	{
		if (isLastPage()) return false;

		currentPageIndex ++;
		beginIndex = 0;
		return true;
	}

	public boolean prevLine()
	{
		if (isFirstLine()) return false;

		beginIndex --;
		return true;
	}

	public boolean nextLine()
	{
		if (isLastLine()) return false;

		beginIndex ++;
		return true;
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


	public int getTopIndexToShow()
	{
		return beginIndex;
	}

	public int getLastIndexToShow(int length)
	{
		return Math.min(beginIndex +length, itemCount(currentPageIndex));
	}

	public List<Message> list() {
		return pages;
	}




	public MessageBook print(int indexX, Text text) {
		get(indexX).print(text);
		return this;
	}

	public MessageBook println(int indexX, Text text)
	{
		return print(indexX, text.lineBreak());
	}

	public MessageBook print(int indexX, String text)
	{
		return print(indexX, Text.create(text));
	}

	public MessageBook println(int indexX, String text)
	{
		return print(indexX, Text.create(text).lineBreak());
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


	/**
	 *
	 * 任意のタイミングで閉じることができるかどうか
	 *
	 * @return 閉じられる場合true
	 */
	public boolean isClosable()
	{
		return isClosable && (! hasPage() || currentPage().isClosable());
	}

	public void setClosable(boolean bool)
	{
		isClosable = bool;
	}

	public MessageBook lockClose()
	{
		isClosable = false;
		return this;
	}













}
