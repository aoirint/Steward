package com.kanomiya.steward.common.view;

import java.awt.Graphics;

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
	public void paint(Graphics g, Player player, Assets assets, int frame) {
		if (player == null) return;

		Area area = assets.getArea(player.areaId);

		int camX = getCamX(player.x, area.getWidth());
		int camY = getCamY(player.y, area.getHeight());

		g.translate(camX, camY);

		ViewConsts.viewArea.paint(g, area, assets, frame);

		g.translate(player.x *ViewConsts.tileSize, player.y *ViewConsts.tileSize);

		// TODO: others
		ViewConsts.vcIcon.paint(g, player.getIcon(), assets, frame);
		g.translate(-player.x *ViewConsts.tileSize, -player.y *ViewConsts.tileSize);

		g.translate(-camX, -camY);

	}

	protected int getCamX(int x, int width)
	{
		int camX = x *ViewConsts.tileSize -ViewConsts.viewWidth /2;
		if (camX < 0) return 0;

		int r = (width +1) *ViewConsts.tileSize -ViewConsts.viewWidth;
		if (r < camX) return -r;

		return -camX;
	}

	protected int getCamY(int y, int height)
	{
		int camY = y *ViewConsts.tileSize -ViewConsts.viewHeight /2;
		if (camY < 0) return 0;

		int b = (height +1) *ViewConsts.tileSize -ViewConsts.viewHeight;
		if (b < camY) return -b;

		return -camY;

	}


}
