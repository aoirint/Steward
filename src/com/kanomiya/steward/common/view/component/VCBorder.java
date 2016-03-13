package com.kanomiya.steward.common.view.component;

import java.awt.Color;
import java.awt.Graphics2D;

import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.view.ViewConsts;


/**
 * @author Kanomiya
 *
 */
public class VCBorder implements IViewComponent<Color> {

	public int width = 1;

	/**
	* @inheritDoc
	*/
	@Override
	public void paint(Graphics2D g, Color color, Assets assets, int frame)
	{
		g.setColor(color);

		for (int i=0; i<width; i++)
		{
			g.drawRect(i, i, ViewConsts.tileSize -i*2, ViewConsts.tileSize -i*2);
		}

	}

}
