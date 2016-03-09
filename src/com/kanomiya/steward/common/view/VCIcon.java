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
		case "static":
			for (String path: icon.src) g.drawImage(assets.getTexture(path), 0,0, null);
			break;

		}

	}

}
