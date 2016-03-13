package com.kanomiya.steward.common.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
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
	public void paint(Graphics2D g, Texture texture, Assets assets, int frame) {

		if (texture.src == null) return ;

		g.setColor(Color.WHITE);

		int srcLen = texture.src[0].length;
		int dx = texture.x;
		int dy = texture.y;

		int width = texture.width;
		int height = texture.height;

		g.translate(texture.offsetX, texture.offsetY);

		if (texture.hasOwner())
		{
			if (texture.type.isWalkable()) dx = texture.getOwner().getWalkState().getIconX();
			if (texture.type.isDirectable()) dy = texture.getOwner().getDirection().getIconY();
		}

		switch (texture.mode)
		{
		case STATIC:

			for (int i=0; i<srcLen; i++)
			{
				Image img = assets.getCachedImage(texture.src[0][i]);
				Dimension dim = assets.getCachedImageDim(img);

				if (texture.autoSize)
				{
					width = dim.width;
					height = dim.height;
				}

				g.drawImage(img, 0,0, width, height, dx, dy, dx +width, dy +height, null);
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

			for (int i=0; i<srcLen; i++)
			{
				Image img = assets.getCachedImage(texture.src[texture.index][i]);
				Dimension dim = assets.getCachedImageDim(img);

				g.drawImage(img, 0,0, dim.width, dim.height, dx, dy, dx +dim.width, dy +dim.height, null);
			}


			break;

		}

		g.translate(-texture.offsetX, -texture.offsetY);

	}

}
