package com.kanomiya.steward.common.view;

import java.awt.Graphics2D;

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
		super(ViewConsts.FPS);
	}

	/**
	* @inheritDoc
	*/
	@Override
	public void paintBuffer(Graphics2D g, Game game, int frame)
	{
		ViewConsts.viewPlayerEye.paint(g, game.thePlayer, game.assets, frame);
		if (game.thePlayer.debug) ViewConsts.vcStat.paint(g, game, game.assets, frame);

	}


}
