package com.kanomiya.steward.view;

import java.awt.Graphics2D;

import com.kanomiya.steward.Game;

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
		ViewConsts.viewPlayerEye.paint(g, game.assets.getPlayer(), game.assets, frame);
		if (game.assets.getPlayer().debug) ViewConsts.vcStat.paint(g, game, game.assets, frame);

	}


}
