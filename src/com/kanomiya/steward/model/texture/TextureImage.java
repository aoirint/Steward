package com.kanomiya.steward.model.texture;

import java.awt.image.BufferedImage;

import com.kanomiya.steward.model.assets.resource.IResource;

/**
 * @author Kanomiya
 *
 */
public class TextureImage extends BufferedImage implements IResource {

	protected String id;

	public TextureImage(String id, BufferedImage image) {
		super(image.getColorModel(), image.getRaster(), false, null);

		this.id = id;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public String getId()
	{
		return id;
	}




}
