package com.kanomiya.steward.common.view;

import java.awt.Color;
import java.awt.Graphics;

import com.kanomiya.steward.common.model.assets.Assets;


/**
 * @author Kanomiya
 *
 */
public class VCMarker implements IViewComponent<Color> {

	/**
	* @inheritDoc
	*/
	@Override
	public void paint(Graphics g, Color color, Assets assets, int frame)
	{
		g.setColor(color);
		g.fillRect(0, 0, ViewConsts.tileSize, ViewConsts.tileSize);
	}

}
