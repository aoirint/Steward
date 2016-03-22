package com.kanomiya.steward.view.component;

import java.awt.Color;
import java.awt.Graphics2D;

import com.kanomiya.steward.model.assets.Assets;
import com.kanomiya.steward.view.ViewConsts;


/**
 * @author Kanomiya
 *
 */
public class VCMarker implements IViewComponent<Color> {

	/**
	* @inheritDoc
	*/
	@Override
	public void paint(Graphics2D g, Color color, Assets assets, int frame)
	{
		g.setColor(color);
		g.fillRect(0, 0, ViewConsts.tileSize, ViewConsts.tileSize);
	}

}
