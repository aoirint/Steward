package com.kanomiya.steward.common.view;

import java.awt.Graphics;
import java.util.List;

import com.kanomiya.steward.common.model.area.Area;
import com.kanomiya.steward.common.model.area.Tip;
import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.event.Event;

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

		if (area.hasBackground()) ViewConsts.vcTexture.paint(g, area.getBackground(), assets, frame);

		for (int y=0; y<height; y++)
		{
			for (int x=0; x<width; x++)
			{
				if (! area.tipExists(x, y)) continue;

				Tip tip = area.getTip(x, y);

				g.translate(x *ViewConsts.tileSize, y * ViewConsts.tileSize);
				ViewConsts.vcTexture.paint(g, tip.getIcon(), assets, frame);
				g.translate(-x *ViewConsts.tileSize, -y * ViewConsts.tileSize);
			}
		}

		List<Event> eventList = area.eventList();
		for (Event tevent: eventList)
		{
			g.translate(tevent.x *ViewConsts.tileSize, tevent.y * ViewConsts.tileSize);
			ViewConsts.vcTexture.paint(g, tevent.getIcon(), assets, frame);
			g.translate(-tevent.x *ViewConsts.tileSize, -tevent.y * ViewConsts.tileSize);
		}

	}


}
