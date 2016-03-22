package com.kanomiya.steward.common.model.texture;


/**
 * @author Kanomiya
 *
 */
public class TextureFrame {
	public TextureImage[] images;

	public int drawingX, drawingY;


	public TextureFrame()
	{

	}

	public TextureImage getImageAt(int index)
	{
		return images[index];
	}


	public int getImageCount()
	{
		return images.length;
	}










}
