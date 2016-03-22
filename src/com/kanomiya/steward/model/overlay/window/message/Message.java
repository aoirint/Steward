package com.kanomiya.steward.model.overlay.window.message;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kanomiya.steward.model.overlay.text.Choice;
import com.kanomiya.steward.model.overlay.text.ISelectableText;
import com.kanomiya.steward.model.overlay.text.Text;

/**
 * @author Kanomiya
 *
 */
public class Message {

	protected LinkedList<Text> items;
	protected List<ISelectableText> selectableTexts;
	protected Map<Character, Choice> charToChoice;
	protected boolean isClosable;
	public int selectedIndexOfSelectable;

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

	public int selectableCount()
	{
		if (selectableTexts == null) return 0;
		return selectableTexts().size();
	}


	public Message print(Text text)
	{
		items().addLast(text);

		if (text instanceof ISelectableText)
		{
			ISelectableText selectable = (ISelectableText) text;
			selectableTexts().add(selectable);

			if (text instanceof Choice)
			{
				Choice choice = (Choice) selectable;
				if (choice.ch != null) charToChoice().put(choice.ch, choice);
			}
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
	public List<ISelectableText> selectableTexts()
	{
		if (selectableTexts == null) selectableTexts = Lists.newArrayList();
		return selectableTexts;
	}

	public ISelectableText getSelectedText() {
		return selectableTexts.get(selectedIndexOfSelectable);
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
	public boolean hasSelectable() {
		return (selectableCount() != 0);
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




	public boolean isFirstSelectable()
	{
		return (selectedIndexOfSelectable == 0);
	}

	public boolean isLastSelectable()
	{
		return (selectedIndexOfSelectable == selectableCount() -1);
	}


	public boolean prevSelectable()
	{
		if (isFirstSelectable()) return false;

		selectedIndexOfSelectable --;
		return true;
	}

	public boolean nextSelectable()
	{
		if (isLastSelectable()) return false;

		selectedIndexOfSelectable ++;
		return true;
	}





















}
