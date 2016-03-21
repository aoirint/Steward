package com.kanomiya.steward.common.view.component;

import java.awt.Color;
import java.awt.Graphics2D;

import com.kanomiya.steward.common.Game;
import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.view.ViewConsts;


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

		String playerMode = assets.translate("playerMode") + " " + assets.translate(game.thePlayer.getMode().getUnlocalizedName());
		g.drawString(playerMode, ViewConsts.viewWidth -g.getFontMetrics().stringWidth(playerMode) -10, 30);


		String labelArea = assets.translate("area");
		g.drawString(labelArea, 10, 30);

		String areaName = assets.translate("vocabulary.name") + " " + assets.translate(game.thePlayer.area.getName());
		g.drawString(areaName, 10, 50);

		String areaSize = assets.translate("vocabulary.size") + " " + game.thePlayer.area.getWidth() + ", " + game.thePlayer.area.getHeight();
		g.drawString(areaSize, 10, 70);

		String labelPlayer = assets.translate("player");
		g.drawString(labelPlayer, 10, 90);

		String playerCoord = assets.translate("vocabulary.coordinate") + " " + game.thePlayer.x + ", " + game.thePlayer.y;
		g.drawString(playerCoord, 10, 110);


	}

}
