package com.kanomiya.steward.model.texture;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.kanomiya.steward.model.assets.resource.IResource;

/**
 * @author Kanomiya
 *
 */
public class TransformerTextureImage implements IResource {

	public String id;
	public TextureImage base;

	public boolean autoSize;
	public int width, height;

	public int imageX, imageY;
	public double rotation; // 0-360

	public TransformerTextureImage(String id, TextureImage base)
	{
		this.id = id;
		this.base = base;
		autoSize = true;
	}

	public TextureImage toTextureImage()
	{

		if (autoSize)
		{
			width = base.getWidth();
			height = base.getHeight();
		}

		TextureImage img = new TextureImage(id, width, height, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g = img.createGraphics();

		double theta = Math.toRadians(rotation);

		g.rotate(theta, width /2, height /2);
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 12, 12);
		g.drawImage(base.getSubimage(imageX, imageY, width, height), 0, 0, null);

		g.dispose();

		return img;
	}


	/**
	* @inheritDoc
	*/
	@Override
	public String getId() {
		return id;
	}

}
