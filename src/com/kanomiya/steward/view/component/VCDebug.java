package com.kanomiya.steward.view.component;

import java.awt.Color;
import java.awt.Graphics2D;

import com.kanomiya.steward.Game;
import com.kanomiya.steward.model.assets.Assets;
import com.kanomiya.steward.model.event.Player;
import com.kanomiya.steward.model.overlay.GameFont;
import com.kanomiya.steward.view.ViewConsts;


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
		g.setFont(GameFont.textFont);

		Player player = assets.getPlayer();

		String playerMode = assets.translate("playerMode") + " " + assets.translate(player.getMode().getUnlocalizedName());
		g.drawString(playerMode, ViewConsts.viewWidth -g.getFontMetrics().stringWidth(playerMode) -10, 30);


		String labelArea = assets.translate("area");
		g.drawString(labelArea, 10, 30);

		String areaName = assets.translate("vocabulary.name") + " " + assets.translate(player.area.getUnlocalizedName());
		g.drawString(areaName, 10, 50);

		String areaSize = assets.translate("vocabulary.size") + " " + player.area.getWidth() + ", " + player.area.getHeight();
		g.drawString(areaSize, 10, 70);

		String labelPlayer = assets.translate("player");
		g.drawString(labelPlayer, 10, 90);

		String playerCoord = assets.translate("vocabulary.coordinate") + " " + player.x + ", " + player.y;
		g.drawString(playerCoord, 10, 110);


	}

}
