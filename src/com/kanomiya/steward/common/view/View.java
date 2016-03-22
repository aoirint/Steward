package com.kanomiya.steward.common.view;

import java.awt.Graphics;

/**
 * @author Kanomiya
 *
 */
public abstract class View<T> {

	protected int internalFPS;
	private int frame;

	public View(int internalFPS)
	{
		this.internalFPS = internalFPS;
		frame = 0;
	}

	public void update(Graphics g, T model)
	{
		frame ++;
		if (internalFPS < frame) frame = 0;

		paint(g, model, frame);
	}

	public abstract void paint(Graphics g, T model, int frame);

	public int getInternalFPS() { return internalFPS; }

}
