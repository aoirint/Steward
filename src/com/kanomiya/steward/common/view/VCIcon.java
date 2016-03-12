package com.kanomiya.steward.common.view;

import java.awt.Color;
import java.awt.Graphics;

import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.icon.Icon;


/**
 * @author Kanomiya
 *
 */
public class VCIcon implements IViewComponent<Icon> {

	/**
	* @inheritDoc
	*/
	@Override
	public void paint(Graphics g, Icon icon, Assets assets, int frame) {

		g.setColor(Color.WHITE);

		switch (icon.mode)
		{
		case STATIC:
			int srcLen = icon.src.length;
			int dx = 0;
			int dy = 0;

			if (icon.hasOwner())
			{
				if (icon.type.isWalkable()) dx = icon.getOwner().getWalkState().getIconX();
				if (icon.type.isDirectable()) dy = icon.getOwner().getDirection().getIconY();
			}


			for (int i=0; i<srcLen; i++)
			{
				g.drawImage(assets.getTexture(icon.src[i]), 0,0, ViewConsts.tileSize, ViewConsts.tileSize, dx, dy, ViewConsts.tileSize, ViewConsts.tileSize, null);
			}

			break;

		case ANIMATION:

			icon.count ++;
			if (icon.interval <= icon.count)
			{
				icon.count = 0;
				icon.index ++;
			}
			if (icon.src.length <= icon.index) icon.index = 0;

			g.drawImage(assets.getTexture(icon.src[icon.index]), 0,0, null);

			break;

		}

	}

}
