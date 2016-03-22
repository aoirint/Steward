package com.kanomiya.steward.model.texture;

import java.awt.Graphics2D;

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
		TextureImage img = new TextureImage(id, base);
		Graphics2D g = img.createGraphics();

		if (autoSize)
		{
			width = img.getWidth();
			height = img.getHeight();
		}

		img.getSubimage(imageX, imageY, width, height);

		double theta = Math.toRadians(rotation);
		ImageEffectUtils.rotate(g, width, height, theta);

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
