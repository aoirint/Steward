package com.kanomiya.steward.common.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.texture.Texture;


/**
 * @author Kanomiya
 *
 */
public class VCTexture implements IViewComponent<Texture> {

	/**
	* @inheritDoc
	*/
	@Override
	public void paint(Graphics g, Texture texture, Assets assets, int frame) {

		g.setColor(Color.WHITE);

		switch (texture.mode)
		{
		case STATIC:
			int srcLen = texture.src.length;
			int dx = 0;
			int dy = 0;

			if (texture.hasOwner())
			{
				if (texture.type.isWalkable()) dx = texture.getOwner().getWalkState().getIconX();
				if (texture.type.isDirectable()) dy = texture.getOwner().getDirection().getIconY();
			}


			for (int i=0; i<srcLen; i++)
			{
				Image img = assets.getCachedImage(texture.src[i]);
				Dimension dim = assets.getCachedImageDim(img);

				g.drawImage(img, 0,0, dim.width, dim.height, dx, dy, dx +dim.width, dy +dim.height, null);
			}

			break;

		case ANIMATION:

			texture.count ++;
			if (texture.interval <= texture.count)
			{
				texture.count = 0;
				texture.index ++;
			}
			if (texture.src.length <= texture.index) texture.index = 0;

			g.drawImage(assets.getCachedImage(texture.src[texture.index]), 0,0, null);

			break;

		}

	}

}
