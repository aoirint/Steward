package com.kanomiya.steward.model.overlay.window;

import java.awt.Color;

import com.kanomiya.steward.model.overlay.Overlay;

/**
 * @author Kanomiya
 *
 */
public class Component extends Overlay {

	public Color backgroundColor;
	public Color foregroundColor;

	public Component() {
		super();
	}

	public Component(int x, int y) {
		super(x, y);
	}

	public Component(int x, int y, int width, int height) {
		super(x, y, width, height);
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


}
