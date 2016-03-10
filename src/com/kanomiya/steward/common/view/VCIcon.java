package com.kanomiya.steward.common.view;

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

		switch (icon.mode)
		{
		case STATIC:
			for (String path: icon.src) g.drawImage(assets.getTexture(path), 0,0, null);
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
