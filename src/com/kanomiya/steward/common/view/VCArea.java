package com.kanomiya.steward.common.view;

import java.awt.Graphics;

import com.kanomiya.steward.common.model.area.Area;
import com.kanomiya.steward.common.model.area.Tile;
import com.kanomiya.steward.common.model.assets.Assets;

/**
 * @author Kanomiya
 *
 */
public class VCArea implements IViewComponent<Area> {

	/**
	* @inheritDoc
	*/
	@Override
	public void paint(Graphics g, Area area, Assets assets, int frame) {
		if (area == null) return ;

		int width = area.getWidth();
		int height = area.getHeight();

		for (int y=0; y<height; y++)
		{
			for (int x=0; x<width; x++)
			{
				if (! area.tileExists(x, y)) continue;

				Tile tile = area.getTile(x, y);

				g.translate(x *ViewConsts.tileSize, y * ViewConsts.tileSize);
				if (tile.hasTip()) ViewConsts.vcIcon.paint(g, tile.getTip().getIcon(), assets, frame);
				if (tile.hasEvent()) ViewConsts.vcIcon.paint(g, tile.getEvent().getIcon(), assets, frame);
				g.translate(-x *ViewConsts.tileSize, -y * ViewConsts.tileSize);
			}
		}


	}


}
