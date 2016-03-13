package com.kanomiya.steward.common.model.texture;

import com.kanomiya.steward.common.view.ViewConsts;


/**
 * @author Kanomiya
 *
 */
public class Texture {

	public String[][] src;
	public TextureMode mode;
	public TextureType type;
	public int interval;
	public int x, y;
	public int offsetX, offsetY;
	public int width, height;
	public boolean autoSize;

	public int count = 0;
	public int index = 0;

	public ITextureOwner owner;

	public Texture()
	{
		this((String[][]) null, 0,0, ViewConsts.tileSize, ViewConsts.tileSize, false, 0,0, TextureMode.STATIC, TextureType.FRONT, null, 1000);
	}


	public Texture(String src)
	{
		this(src, ViewConsts.tileSize, ViewConsts.tileSize);
	}

	public Texture(String src, int width, int height)
	{
		this(new String[][] { new String[] { src } }, 0,0, width, height, false, 0,0, TextureMode.STATIC, TextureType.FRONT, null, 1000);
	}

	/**
	 *
	 * @param src
	 * @param mode
	 * @param type
	 * @param owner typeがfrontのときはnullでよい
	 * @param interval
	 */
	public Texture(String[][] src, int x, int y, int width, int height, boolean autoSize, int offsetX, int offsetY, TextureMode mode, TextureType type, ITextureOwner owner, int interval)
	{
		this.src = src;

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.autoSize = autoSize;

		this.offsetX = offsetX;
		this.offsetY = offsetY;

		this.mode = mode;
		this.type = type;
		this.owner = owner;
		this.interval = Math.max(interval, 1000);
	}

	public boolean hasOwner()
	{
		return (owner != null);
	}

	public ITextureOwner getOwner()
	{
		return owner;
	}

}
