package com.kanomiya.steward.model.texture;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


/**
 * @author Kanomiya
 *
 */
public class ImageEffectUtils {

	public static void rotate(BufferedImage image, double theta)
	{
		rotate(image.createGraphics(), image.getWidth(), image.getHeight(), theta);
	}


	public static void rotate(Graphics2D g, int width, int height, double theta) // VELIF
	{
		g.rotate(theta, width /2, height /2);
		g.translate(width /2, height /2);
	}

}
