package com.kanomiya.steward.common.view;

import java.awt.Graphics2D;

import com.kanomiya.steward.common.model.area.Area;
import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.event.Player;

/**
 * @author Kanomiya
 *
 */
public class VCPlayerEye implements IViewComponent<Player> {


	/**
	* @inheritDoc
	*/
	@Override
	public void paint(Graphics2D g, Player player, Assets assets, int frame) {
		if (player == null) return;

		Area area = player.area;

		int camX = ViewConsts.getCamX(player.x, area.getWidth()) *ViewConsts.tileSize;
		int camY = ViewConsts.getCamY(player.y, area.getHeight()) *ViewConsts.tileSize;

		g.translate(camX, camY);

		ViewConsts.viewArea.paint(g, area, assets, frame);

		g.translate(player.x *ViewConsts.tileSize, player.y *ViewConsts.tileSize);

		// TODO: others
		ViewConsts.vcTexture.paint(g, player.getIcon(), assets, frame);
		g.translate(-player.x *ViewConsts.tileSize, -player.y *ViewConsts.tileSize);

		ViewConsts.vcSelect.paint(g, player, assets, frame);

		g.translate(-camX, -camY);

		ViewConsts.vcMode.paint(g, player, assets, frame);

		if (player.logger.isVisible()) ViewConsts.vcIngameLogger.paint(g, player.logger, assets, frame);



	}



}
