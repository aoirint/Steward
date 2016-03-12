package com.kanomiya.steward.common.view;

import java.awt.Color;
import java.awt.Graphics2D;

import com.kanomiya.steward.common.Game;
import com.kanomiya.steward.common.model.assets.Assets;


/**
 * @author Kanomiya
 *
 */
public class VCDebug implements IViewComponent<Game> {

	/**
	* @inheritDoc
	*/
	@Override
	public void paint(Graphics2D g, Game game, Assets assets, int frame)
	{
		g.setColor(Color.WHITE);

		String playerMode = assets.translate("playerMode") + " " + assets.translate(game.thePlayer.mode.getUnlocalizedName());
		g.drawString(playerMode, ViewConsts.viewWidth -g.getFontMetrics().stringWidth(playerMode) -10, 30);
	}

}
