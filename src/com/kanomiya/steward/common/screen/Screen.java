package com.kanomiya.steward.common.screen;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * @author Kanomiya
 *
 */
public class Screen {

	public int frame;
	protected BufferedImage image;
	protected Graphics2D graph;

	public Screen(int width, int height)
	{
		frame = 0;
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		graph = image.createGraphics();
	}

	public Graphics2D getGraphics()
	{
		return graph;
	}



}
