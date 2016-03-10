package com.kanomiya.steward.common.view;

import java.awt.Graphics;

import com.kanomiya.steward.common.Game;

/**
 * @author Kanomiya
 *
 */
public class ViewGame extends ViewBuffered<Game> {

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
	public void paintBuffer(Graphics g, Game game, int frame)
	{
		ViewConsts.viewPlayerEye.paint(g, game.thePlayer, game.assets, frame);

	}

}
