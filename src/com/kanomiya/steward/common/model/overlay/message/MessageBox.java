package com.kanomiya.steward.common.model.overlay.message;

import java.util.LinkedList;

import com.google.common.collect.Lists;
import com.kanomiya.steward.common.model.overlay.Overlay;
import com.kanomiya.steward.common.model.overlay.PrettyText;

/**
 * @author Kanomiya
 *
 */
public class MessageBox extends Overlay {

	public LinkedList<PrettyText> items;
	public int beginIndex;

	public MessageBox(int x, int y, int width, int height) {
		super(x, y, width, height);

		items = Lists.newLinkedList();
		// setBackground(new Texture("background/overlay/ingameLogger.png", true));

	}


	public void print(PrettyText text)
	{
		items.addLast(text);
	}

	public void println(PrettyText text)
	{
		print(text.lineBreak());
	}

	public void print(String text)
	{
		print(PrettyText.create(text));
	}

	public void println(String text)
	{
		print(PrettyText.create(text).lineBreak());
	}


	public int getTopIndexToShow()
	{
		return beginIndex;
	}

	public int getLastIndexToShow(int oneHeight)
	{
		return Math.min(beginIndex +oneHeight, items.size());
	}


}
