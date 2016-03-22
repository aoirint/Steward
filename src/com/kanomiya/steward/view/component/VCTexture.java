package com.kanomiya.steward.view.component;

import java.awt.Graphics2D;

import com.kanomiya.steward.Game;
import com.kanomiya.steward.model.assets.Assets;
import com.kanomiya.steward.model.event.Player;
import com.kanomiya.steward.model.texture.ITextureOwner;
import com.kanomiya.steward.model.texture.Texture;
import com.kanomiya.steward.model.texture.TextureFrame;
import com.kanomiya.steward.model.texture.TextureImage;


/**
 * @author Kanomiya
 *
 */
public class VCTexture implements IViewComponent<Texture> {

	protected ITextureOwner temporaryOwner;

	/**
	* @inheritDoc
	*/
	@Override
	public void paint(Graphics2D g, Texture texture, Assets assets, int frame)
	{
		if (! texture.hasFrame()) return ;

		TextureFrame texFrame = texture.getFrameAt(Math.max(0, Math.min(texture.getFrameCount() -1, frame /10))); // 0-9, 10-19, 20-29, 30-39, 40-49, 50-59, 60

		int imgCount = texFrame.getImageCount();

		g.translate(texFrame.drawingX, texFrame.drawingY);

		int dx = 0;
		int dy = 0;

		if (temporaryOwner != null)
		{
			if (texture.type.isWalkable()) dx = temporaryOwner.getWalkState().getIconX();
			if (texture.type.isDirectable()) dy = temporaryOwner.getDirection().getIconY();

			if (temporaryOwner instanceof Player)
			{
				Game.logger.info(dx + ", " + texture.getTileWidth());
				Game.logger.info(dy + ", " + texture.getTileHeight());

			}

		}

		for (int i=0; i<imgCount; i++)
		{
			TextureImage img = texFrame.getImageAt(i);

			g.drawImage(img.getSubimage(dx, dy, texture.getTileWidth(), texture.getTileHeight()), 0, 0, null); // VELIF CPU load

			// g.drawImage(img, 0, 0, texture.getTileWidth(), texture.getTileHeight(), dx, dy, dx +texture.getTileWidth(), dy +texture.getTileHeight(), null);


		}

		g.translate(-texFrame.drawingX, -texFrame.drawingY);


	}

}
