package com.kanomiya.steward.common.model.overlay;

import com.kanomiya.steward.common.model.texture.Texture;

/**
 * @author Kanomiya
 *
 */
public abstract class Overlay {

	public int x, y, width, height;
	public Texture background;

	public Overlay(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}


	public boolean hasBackground()
	{
		return (background != null);
	}

	/**
	 * @return background
	 */
	public Texture getBackground() {
		return background;
	}

	/**
	 * @param background セットする background
	 */
	public void setBackground(Texture background) {
		this.background = background;
	}

	public boolean contains(int tgtX, int tgtY)
	{
		return x <= tgtX && tgtX < x +width && y <= tgtY && tgtY < y +height;
	}


}