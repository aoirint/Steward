package com.kanomiya.steward.common.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.kanomiya.steward.common.Game;

/**
 * @author Kanomiya
 *
 */
public class ViewGame extends View<Game> {

	public int width, height;
	public BufferedImage buffer;
	public Graphics bufferGraph;

	/**
	 * @param maxFrame
	 */
	public ViewGame() {
		super(ViewConsts.maxFrame);
	}

	/**
	* @inheritDoc
	*/
	@Override
	public void paint(Graphics g, Game game, int frame)
	{
		if (buffer == null || bufferGraph == null || width != ViewConsts.viewWidth || height != ViewConsts.viewHeight)
		{
			width = ViewConsts.viewWidth;
			height = ViewConsts.viewHeight;
			buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			bufferGraph = buffer.getGraphics();
		}

		bufferGraph.setColor(Color.BLACK);
		bufferGraph.fillRect(0, 0, ViewConsts.viewWidth, ViewConsts.viewHeight);

		ViewConsts.viewPlayerEye.paint(bufferGraph, game.thePlayer, game.assets, frame);

		g.drawImage(buffer, 0, 0, null);
	}

}
