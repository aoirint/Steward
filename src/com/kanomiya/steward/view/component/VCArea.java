package com.kanomiya.steward.view.component;

import java.awt.Graphics2D;
import java.util.List;

import com.kanomiya.steward.model.area.Area;
import com.kanomiya.steward.model.area.Tip;
import com.kanomiya.steward.model.assets.Assets;
import com.kanomiya.steward.model.event.Event;
import com.kanomiya.steward.view.ViewConsts;

/**
 * @author Kanomiya
 *
 */
public class VCArea implements IViewComponent<Area> {

	/**
	* @inheritDoc
	*/
	@Override
	public void paint(Graphics2D g, Area area, Assets assets, int frame) {
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
			if (! tevent.isVisible()) continue;
			if (tevent.isDead()) continue;

			g.translate(tevent.x *ViewConsts.tileSize, tevent.y * ViewConsts.tileSize);

			ViewConsts.vcTexture.temporaryOwner = tevent;
			ViewConsts.vcTexture.paint(g, tevent.getIcon(), assets, frame);
			ViewConsts.vcTexture.temporaryOwner = null;

			g.translate(-tevent.x *ViewConsts.tileSize, -tevent.y * ViewConsts.tileSize);
		}

	}


}
