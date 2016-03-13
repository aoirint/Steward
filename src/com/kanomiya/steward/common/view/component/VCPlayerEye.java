package com.kanomiya.steward.common.view.component;

import java.awt.Graphics2D;

import com.kanomiya.steward.common.model.area.Area;
import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.event.Player;
import com.kanomiya.steward.common.view.ViewConsts;
import com.kanomiya.steward.common.view.ViewUtils;

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

		int camX = ViewUtils.getCamX(player) *ViewConsts.tileSize;
		int camY = ViewUtils.getCamY(player) *ViewConsts.tileSize;

		g.translate(camX, camY);

		ViewConsts.viewArea.paint(g, area, assets, frame);

		g.translate(player.x *ViewConsts.tileSize, player.y *ViewConsts.tileSize);

		// TODO: others
		ViewConsts.vcTexture.paint(g, player.getIcon(), assets, frame);
		g.translate(-player.x *ViewConsts.tileSize, -player.y *ViewConsts.tileSize);

		ViewConsts.vcSelect.paint(g, player, assets, frame);

		g.translate(-camX, -camY);

		if (player.logger != null && player.logger.isVisible()) ViewConsts.vcMessageBox.paint(g, player.logger, assets, frame);
		if (player.hasMessage() && player.getMessage().isVisible()) ViewConsts.vcMessageBox.paint(g, player.getMessage(), assets, frame);



	}



}
