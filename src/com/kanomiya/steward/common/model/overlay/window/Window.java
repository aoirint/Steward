package com.kanomiya.steward.common.model.overlay.window;

import java.awt.Color;

import com.kanomiya.steward.common.model.overlay.Overlay;

/**
 * @author Kanomiya
 *
 */
public class Window extends Overlay {

	public Color backgroundColor;
	public Color foregroundColor;
	public boolean isClosable;

	public Window()
	{
		this(0,0, 0,0);
	}

	public Window(int x, int y)
	{
		this(x,y, 0,0);
	}

	public Window(int x, int y, int width, int height)
	{
		super(x, y, width, height);

		isClosable = true;
	}

	public boolean hasBackgroundColor()
	{
		return (backgroundColor != null);
	}

	public Color getBackgroundColor()
	{
		return backgroundColor;
	}

	public boolean hasForegroundColor()
	{
		return (foregroundColor != null);
	}

	public Color getForegroundColor()
	{
		return foregroundColor;
	}


	/**
	 *
	 * 任意のタイミングで閉じることができるかどうかを判定します
	 *
	 * @return 閉じられるならtrue
	 */
	public boolean isClosable() {
		return isClosable;
	}

	public void setClosable(boolean isClosable) {
		this.isClosable = isClosable;
	}


}
