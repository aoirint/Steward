package com.kanomiya.steward.model.texture;

import com.kanomiya.steward.model.assets.resource.IResource;


/**
 * @author Kanomiya
 *
 */
public class Texture implements IResource {

	public String id;
	protected TextureFrame[] frames;
	public TextureType type;

	protected int width, height;
	protected int tileWidth, tileHeight;

	// public int interval;
	// public double rotation; // 0-360

	public int count = 0;
	public int index = 0;


	protected Texture(String id, TextureFrame[] frames)
	{
		this.id = id;
		this.frames = frames;

		type = TextureType.FRONT;

		calcSize();

		tileWidth = width;
		tileHeight = height;
	}


	public TextureFrame getFrameAt(int index)
	{
		return frames[index];
	}

	public int getFrameCount()
	{
		return frames.length;
	}


	public void calcSize()
	{
		width = height = 0;

		if (frames == null) return;

		for (int i=0; i<frames.length; i++)
		{
			if (frames[i] == null) continue;

			for (int j=0; j<frames[i].images.length; j++)
			{
				if (frames[i].images[j] == null) continue;

				width = Math.max(width, frames[i].images[j].getWidth());
				height = Math.max(height, frames[i].images[j].getHeight());
			}
		}

	}

	/**
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return
	 */
	public int getHeight() {
		return height;
	}


	/**
	 * @return tileWidth
	 */
	public int getTileWidth() {
		return tileWidth;
	}



	/**
	 * @return tileHeight
	 */
	public int getTileHeight() {
		return tileHeight;
	}



	/**
	 * @param tileWidth セットする tileWidth
	 */
	public void setTileWidth(int tileWidth) {
		this.tileWidth = tileWidth;
	}



	/**
	 * @param tileHeight セットする tileHeight
	 */
	public void setTileHeight(int tileHeight) {
		this.tileHeight = tileHeight;
	}



	/**
	* @inheritDoc
	*/
	@Override
	public String getId() {
		return id;
	}



	/**
	 * @return
	 */
	public boolean hasFrame() {
		return frames != null && 0 < frames.length;
	}












}
