package com.kanomiya.steward.common.view;

import java.awt.Graphics;

import com.kanomiya.steward.common.model.area.Area;
import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.icon.Icon;

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
				g.translate(x *ViewConsts.tileSize, y * ViewConsts.tileSize);

				Icon icon = (area.tipExists(x, y)) ? area.getTip(x, y).getIcon() : ViewConsts.iconUnknown;

				ViewConsts.vcIcon.paint(g, icon, assets, frame);

				g.translate(-x *ViewConsts.tileSize, -y * ViewConsts.tileSize);
			}
		}

	}


}
