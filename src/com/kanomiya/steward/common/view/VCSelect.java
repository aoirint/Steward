package com.kanomiya.steward.common.view;

import java.awt.Graphics;

import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.event.Player;


/**
 * @author Kanomiya
 *
 */
public class VCSelect implements IViewComponent<Player> {

	/**
	* @inheritDoc
	*/
	@Override
	public void paint(Graphics g, Player player, Assets assets, int frame)
	{
		if (player.mode.enableSelecter())
		{
			g.translate(player.focusedX *ViewConsts.tileSize, player.focusedY *ViewConsts.tileSize);
			ViewConsts.vcMarker.paint(g, ViewConsts.colorFocused, assets, frame);
			g.translate(-player.focusedX *ViewConsts.tileSize, -player.focusedY *ViewConsts.tileSize);

			g.translate(player.selectedX *ViewConsts.tileSize, player.selectedY *ViewConsts.tileSize);
			ViewConsts.vcBorder.paint(g, ViewConsts.colorSelected, assets, frame);
			g.translate(-player.selectedX *ViewConsts.tileSize, -player.selectedY *ViewConsts.tileSize);
		}

	}

}