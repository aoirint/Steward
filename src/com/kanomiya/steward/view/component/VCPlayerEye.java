package com.kanomiya.steward.view.component;

import java.awt.Graphics2D;

import com.kanomiya.steward.model.area.Area;
import com.kanomiya.steward.model.assets.Assets;
import com.kanomiya.steward.model.event.Player;
import com.kanomiya.steward.view.ViewConsts;
import com.kanomiya.steward.view.ViewUtils;

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

		ViewConsts.vcSelect.paint(g, player, assets, frame);

		g.translate(-camX, -camY);

		if (player.logger.isVisible()) ViewConsts.vcWindow.paint(g, player.logger, assets, frame);
		// if (player.hasMessage() && player.getMessage().isVisible()) ViewConsts.vcMessageBox.paint(g, player.getMessage(), assets, frame);

		if (player.hasWindow() && player.getWindow().isVisible())
		{
			ViewConsts.vcWindow.paint(g, player.getWindow(), assets, frame);
		}



	}



}
