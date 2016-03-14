package com.kanomiya.steward.common.model.overlay.message;

import java.util.LinkedList;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kanomiya.steward.common.model.overlay.Text;

/**
 * @author Kanomiya
 *
 */
public class Message {

	protected LinkedList<Text> items;
	protected Map<Character, Choice> charToChoice;

	public static Message create()
	{
		return new Message();
	}

	public static Message create(Text firstItem)
	{
		return new Message(firstItem);
	}

	public static Message create(String firstItem)
	{
		return new Message(firstItem);
	}

	protected Message()
	{

	}

	protected Message(Text firstItem)
	{
		this();
		print(firstItem);
	}

	protected Message(String firstItem)
	{
		this();
		print(firstItem);
	}


	public int itemCount()
	{
		if (items == null) return 0;
		return items().size();
	}

	public int choiceCount()
	{
		if (charToChoice == null) return 0;
		return charToChoice().size();
	}


	public Message print(Text text)
	{
		items().addLast(text);

		if (text instanceof Choice)
		{
			Choice choice = (Choice) text;
			charToChoice().put(choice.ch, choice);
		}

		return this;
	}

	public Message println(Text text)
	{
		return print(text.lineBreak());
	}

	public Message print(String text)
	{
		return print(Text.create(text));
	}

	public Message println(String text)
	{
		return print(Text.create(text).lineBreak());
	}



	/**
	 * @return
	 */
	public LinkedList<Text> items() {
		if (items == null) items = Lists.newLinkedList();
		return items;
	}

	/**
	 * @return
	 */
	public Map<Character, Choice> charToChoice() {
		if (charToChoice == null) charToChoice = Maps.newHashMap();
		return charToChoice;
	}

	/**
	 * @return
	 */
	public boolean hasItem() {
		return (itemCount() != 0);
	}

	/**
	 * @return
	 */
	public boolean hasChoice() {
		return (choiceCount() != 0);
	}


	public MessageBook asBook()
	{
		return MessageBook.create().append(this);
	}




}
