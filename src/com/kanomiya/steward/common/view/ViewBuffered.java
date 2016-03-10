package com.kanomiya.steward.common.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * @author Kanomiya
 *
 */
public class ViewBuffered<T> extends View<T> {

	protected int width, height;
	public BufferedImage buffer;
	public Graphics bufferGraph;

	/**
	 * @param maxFrame
	 */
	public ViewBuffered(int maxFrame) {
		super(maxFrame);
	}

	public void setSize(int width, int height)
	{
		this.width = width;
		this.height = height;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public final void paint(Graphics g, T model, int frame)
	{

		if (buffer == null || bufferGraph == null || width != buffer.getWidth() || height != buffer.getHeight())
		{
			buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			bufferGraph = buffer.getGraphics();
		}

		bufferGraph.setColor(Color.BLACK);
		bufferGraph.fillRect(0, 0, width, height);

		paintBuffer(bufferGraph, model, frame);

		g.drawImage(buffer, 0, 0, null);
	}

	public void paintBuffer(Graphics g, T model, int frame)
	{

	}

}
