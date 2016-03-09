package com.kanomiya.steward.common.view;

import java.awt.Graphics;

/**
 * @author Kanomiya
 *
 */
public abstract class View<T> {

	protected int maxFrame;
	private int frame;

	public View(int maxFrame)
	{
		this.maxFrame = maxFrame;
		frame = 0;
	}

	public void update(Graphics g, T model)
	{
		frame ++;
		if (maxFrame < frame) frame = 0;

		paint(g, model, frame);
	}

	public abstract void paint(Graphics g, T model, int frame);

	public int getMaxFrame() { return maxFrame; }

}
