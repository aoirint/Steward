package com.kanomiya.steward.common.model.overlay.message;

import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Lists;
import com.kanomiya.steward.common.model.overlay.Overlay;
import com.kanomiya.steward.common.model.overlay.PrettyText;
import com.kanomiya.steward.common.model.texture.Texture;

/**
 * @author Kanomiya
 *
 */
public class MessageBook extends Overlay {

	public LinkedList<Message> pages;

	public int currentPageIndex, beginIndex;

	public static MessageBook create()
	{
		return new MessageBook();
	}

	protected MessageBook() {
		pages = Lists.newLinkedList();
		setBackground(new Texture("background/overlay/messageBox.png"));
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
	public LinkedList<PrettyText> items(int pageIndex) {
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




	public MessageBook print(int indexX, PrettyText text) {
		get(indexX).print(text);
		return this;
	}

	public MessageBook println(int indexX, PrettyText text)
	{
		return print(indexX, text.lineBreak());
	}

	public MessageBook print(int indexX, String text)
	{
		return print(indexX, PrettyText.create(text));
	}

	public MessageBook println(int indexX, String text)
	{
		return print(indexX, PrettyText.create(text).lineBreak());
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

















}
