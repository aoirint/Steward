package com.kanomiya.steward.common;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Insets;

import com.kanomiya.steward.common.view.View;

/**
 * @author Kanomiya
 *
 */
public class FrameWithView<T> extends Frame {

	public View<T> view;
	public T model;

	public Insets insets;

	public FrameWithView(View<T> view, T model)
	{
		this.view = view;
		this.model = model;
	}

	public void setRealSize(int width, int height)
	{
		insets = getInsets();
		setSize(width +insets.left +insets.right, height +insets.top +insets.bottom);
	}


	@Override
	public void update(Graphics g)
	{
		paint(g);
	}

	@Override
	public void paint(Graphics g)
	{
		if (insets == null)
		{
			insets = getInsets();
		}

		g.translate(insets.left, insets.top);
		view.update(g, model);
		g.translate(-insets.left, -insets.top);
	}

}
