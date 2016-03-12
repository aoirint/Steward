package com.kanomiya.steward.common.model.texture;

import com.google.gson.annotations.Expose;


/**
 * @author Kanomiya
 *
 */
public class Texture {

	@Expose public String[] src;
	@Expose public TextureMode mode;
	@Expose public TextureType type;
	@Expose public int interval;

	public int count = 0;
	public int index = 0;

	public ITextureOwner owner;

	public Texture()
	{
		this(null, TextureMode.STATIC, TextureType.front, null, 1000);
	}


	public Texture(String src)
	{
		this(new String[] { src }, TextureMode.STATIC, TextureType.front, null, 1000);
	}

	/**
	 *
	 * @param src
	 * @param mode
	 * @param type
	 * @param owner typeがfrontのときはnullでよい
	 * @param interval
	 */
	public Texture(String[] src, TextureMode mode, TextureType type, ITextureOwner owner, int interval)
	{
		this.src = src;
		this.mode = mode;
		this.type = type;
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
