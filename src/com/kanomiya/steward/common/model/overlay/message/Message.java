package com.kanomiya.steward.common.model.overlay.message;

import java.util.LinkedList;

import com.google.common.collect.Lists;
import com.kanomiya.steward.common.model.overlay.PrettyText;

/**
 * @author Kanomiya
 *
 */
public class Message {

	public LinkedList<PrettyText> items;

	public static Message create()
	{
		return new Message();
	}

	protected Message() {
		items = Lists.newLinkedList();
	}


	public int itemCount()
	{
		return items.size();
	}


	public Message print(PrettyText text)
	{
		items.addLast(text);
		return this;
	}

	public Message println(PrettyText text)
	{
		return print(text.lineBreak());
	}

	public Message print(String text)
	{
		return print(PrettyText.create(text));
	}

	public Message println(String text)
	{
		return print(PrettyText.create(text).lineBreak());
	}

	/**
	 * @return
	 */
	public LinkedList<PrettyText> items() {
		return items;
	}

	/**
	 * @return
	 */
	public boolean hasItem() {
		return ! items.isEmpty();
	}


	public MessageBook asBook()
	{
		return MessageBook.create().append(this);
	}




}
