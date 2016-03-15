package com.kanomiya.steward.common.model.overlay.message;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kanomiya.steward.common.model.overlay.text.Choice;
import com.kanomiya.steward.common.model.overlay.text.Text;

/**
 * @author Kanomiya
 *
 */
public class Message {

	protected LinkedList<Text> items;
	protected List<Choice> choices;
	protected Map<Character, Choice> charToChoice;
	protected boolean isClosable;
	public int selectedChoiceIndex;

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
		isClosable = true;
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
			choices().add(choice);
			if (choice.ch != null) charToChoice().put(choice.ch, choice);
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
	 *
	 * @return
	 */
	public List<Choice> choices()
	{
		if (choices == null) choices = Lists.newArrayList();
		return choices;
	}

	/**
	 *
	 */
	public Choice getSelectedChoice() {
		return choices.get(selectedChoiceIndex);
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


	/**
	 *
	 * 任意のタイミングで閉じることができるかどうか
	 *
	 * @return 閉じられる場合true
	 */
	public boolean isClosable()
	{
		return isClosable;
	}

	public void setClosable(boolean bool)
	{
		isClosable = bool;
	}

	public Message lockClose()
	{
		isClosable = false;
		return this;
	}




	public boolean isFirstChoice()
	{
		return (selectedChoiceIndex == 0);
	}

	public boolean isLastChoice()
	{
		return (selectedChoiceIndex == choiceCount() -1);
	}


	public boolean prevChoice()
	{
		if (isFirstChoice()) return false;

		selectedChoiceIndex --;
		return true;
	}

	public boolean nextChoice()
	{
		if (isLastChoice()) return false;

		selectedChoiceIndex ++;
		return true;
	}





















}
